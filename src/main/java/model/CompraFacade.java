package model;

public class CompraFacade {
    private PaymentAdapter adaptadorPago;
    private NotificationService servicioNotificaciones;

    public CompraFacade() {
        // Inicializamos los componentes internos del subsistema de compra
        this.adaptadorPago = new PaymentAdapterImpl(new SimulatedPaymentGateway());
        this.servicioNotificaciones = new NotificationService();
    }

    /**
     * Fachada: Orquesta de forma simplificada todo el flujo complejo de una compra
     */
    public boolean efectuarCompra(Usuario usuario, Evento evento, Zona zona, Asiento asiento,
                                  String numeroTarjeta, boolean aplicarVip, boolean aplicarSeguro) {
        try {
            // 1. Vincular al usuario como observador para este proceso (Observer)
            UsuarioNotificationObserver observador = new UsuarioNotificationObserver(usuario);
            servicioNotificaciones.attach(observador);

            // 2. Construir la estructura base mediante el Builder
            CompraBuilder builder = new CompraBuilder()
                    .setUsuario(usuario)
                    .setEvento(evento)
                    .addEntrada(zona, asiento);

            Compra compra = builder.build();

            // 3. Obtener la entrada base para aplicar envolturas dinámicas (Decorator)
            EntradaComponent entradaDecorada = compra.getEntradas().get(0);

            if (aplicarVip) {
                entradaDecorada = new VIPDecorator(entradaDecorada, 50000.0);
            }
            if (aplicarSeguro) {
                entradaDecorada = new SeguroDecorator(entradaDecorada, 15000.0);
            }

            // Reemplazamos la entrada en la compra por la versión decorada profesionalmente
            compra.getEntradas().clear();
            compra.agregarEntrada(entradaDecorada);

            // 4. Delegar la transacción al sistema bancario externo (Adapter)
            MetodoPagoSimulado tarjeta = new MetodoPagoSimulado(numeroTarjeta);
            double totalAPagar = compra.calcularTotalCompra();

            boolean pagoAutorizado = adaptadorPago.procesarPayment(tarjeta, totalAPagar);

            if (pagoAutorizado) {
                // 5. Mutar el ciclo de vida de la compra (State: Pendiente -> Pagada)
                compra.procesarPago();

                // Guardar en el almacenamiento global en memoria (Singleton)
                PlataformaService.getInstancia().registrarCompra(compra);

                // 6. Emitir alerta masiva/individual (Observer)
                servicioNotificaciones.notifyObservers("Tu compra " + compra.getIdCompra() +
                        " para el evento '" + evento.getNombre() + "' ha sido procesada con éxito por un total de $" + totalAPagar);

                servicioNotificaciones.detach(observador);
                return true;
            } else {
                compra.cancelarCompra(); // State: Pendiente -> Cancelada
                servicioNotificaciones.notifyObservers("El pago de tu compra fue rechazado por la entidad bancaria.");
                servicioNotificaciones.detach(observador);
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error crítico en la Fachada de Compras: " + e.getMessage());
            return false;
        }
    }
}