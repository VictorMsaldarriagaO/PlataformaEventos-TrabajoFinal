package model;

// EstadoCancelada.java
public class EstadoCancelada implements CompraState {

    @Override
    public void pagar(Compra compra) {
        System.out.println("Error: No se puede pagar una compra que ya está cancelada.");
    }

    @Override
    public void cancelar(Compra compra) {
        System.out.println("Error: La compra ya se encuentra cancelada.");
    }
}