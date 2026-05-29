package model;

/**
 * Decorador abstracto estructural encargado de mantener la cadena de referencias.
 */
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
    public Asiento getAsiento() { return entradaEnvuelta.getAsiento(); }
}