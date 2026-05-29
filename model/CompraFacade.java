package model;

/**
 * Patrón unificado Facade (Fachada). Expone un método simple de alto nivel para
 * coordinar la ejecución del subsistema complejo de compras.
 */
public class CompraFacade {
    private PaymentAdapter adaptadorPago;
    private NotificationService servicioNotificaciones;

    public CompraFacade() {
        this.adaptadorPago = new PaymentAdapterImpl(new SimulatedPaymentGateway());
        this.servicioNotificaciones = new NotificationService();
    }

    public boolean efectuarCompra(Usuario usuario, Evento evento, Zona zona, Asiento asiento,
                                  String numeroTarjeta, boolean aplicarVip, boolean aplicarSeguro) {
        try {
            UsuarioNotificationObserver observador = new UsuarioNotificationObserver(usuario);
            servicioNotificaciones.attach(observador);

            CompraBuilder builder = new CompraBuilder()
                    .setUsuario(usuario)
                    .setEvento(evento)
                    .addEntrada(zona, asiento);

            Compra compra = builder.build();
            EntradaComponent entradaDecorada = compra.getEntradas().get(0);

            if (aplicarVip) {
                entradaDecorada = new VIPDecorator(entradaDecorada, 50000.0);
            }
            if (aplicarSeguro) {
                entradaDecorada = new SeguroDecorator(entradaDecorada, 15000.0);
            }

            compra.getEntradas().set(0, entradaDecorada);

            MetodoPagoSimulado tarjeta = new MetodoPagoSimulado(numeroTarjeta);
            double totalAPagar = compra.calcularTotalCompra();

            boolean pagoAutorizado = adaptadorPago.procesarPayment(tarjeta, totalAPagar);

            if (pagoAutorizado) {
                compra.processPayment();
                PlataformaService.getInstancia().registrarCompra(compra);

                servicioNotificaciones.notifyObservers("Tu compra " + compra.getIdCompra() +
                        " para el evento '" + evento.getNombre() + "' ha sido procesada con éxito por un total de $" + totalAPagar);

                servicioNotificaciones.detach(observador);
                return true;
            } else {
                compra.cancelPurchase();
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