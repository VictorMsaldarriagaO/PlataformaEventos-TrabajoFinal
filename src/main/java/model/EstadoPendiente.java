package model;

public class EstadoPendiente implements CompraState {

    @Override
    public void pagar(Compra compra) {
        System.out.println("Procesando pago exitosamente...");

        // Cambiamos el estado de los asientos a VENDIDO
        compra.getEntradas().forEach(entrada -> {
            if (entrada.getAsiento() != null) {
                entrada.getAsiento().setEstadoAsiento(EstadoAsiento.VENDIDO);
            }
        });

        compra.setEstadoActual(new EstadoPagada());
        System.out.println("La compra ahora está PAGADA.");
    }

    @Override
    public void cancelar(Compra compra) {
        System.out.println("Cancelando la reserva de la compra...");

        // Liberamos los asientos
        compra.getEntradas().forEach(entrada -> {
            if (entrada.getAsiento() != null) {
                entrada.getAsiento().setEstadoAsiento(EstadoAsiento.DISPONIBLE);
            }
        });

        compra.setEstadoActual(new EstadoCancelada());
        System.out.println("La compra ahora está CANCELADA.");
    }
}