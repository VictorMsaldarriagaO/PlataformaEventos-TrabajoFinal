package model;

public abstract class EntradaDecorator implements EntradaComponent {
    protected EntradaComponent entradaEnvuelta;

    public EntradaDecorator(EntradaComponent entrada) {
        this.entradaEnvuelta = entrada;
    }

    @Override
    public double getPrecioFinal() { return entradaEnvuelta.getPrecioFinal(); }

    @Override
    public String getDetalles() { return entradaEnvuelta.getDetalles(); }

    @Override
    public Asiento getAsiento() {
        return entradaEnvuelta.getAsiento(); // Delega en cadena hasta llegar a BaseEntrada
    }
}