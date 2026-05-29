package model;

/**
 * Estado final de éxito. Permite gestionar reembolsos y liberar aforos físicos.
 */
public class EstadoPagada implements CompraState {
    @Override
    public void pagar(Compra compra) {
        System.out.println("Error: Esta compra ya ha sido pagada previamente.");
    }

    @Override
    public void cancelar(Compra compra) {
        System.out.println("Solicitando reembolso... Compra cancelada.");
        compra.getEntradas().forEach(entrada -> {
            if (entrada.getAsiento() != null) {
                entrada.getAsiento().setEstadoAsiento(EstadoAsiento.DISPONIBLE);
            }
        });
        compra.setEstadoActual(new EstadoCancelada());
    }
}