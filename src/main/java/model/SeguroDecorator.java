package model;
// SeguroDecorator.java
public class SeguroDecorator extends EntradaDecorator {
    private double costoSeguro;

    public SeguroDecorator(EntradaComponent entrada, double costoSeguro) {
        super(entrada);
        this.costoSeguro = costoSeguro;
    }

    @Override
    public double getPrecioFinal() { return super.getPrecioFinal() + costoSeguro; }

    @Override
    public String getDetalles() { return super.getDetalles() + " + Seguro de Cancelación"; }
}