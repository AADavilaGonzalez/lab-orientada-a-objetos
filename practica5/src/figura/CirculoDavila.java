package figura;

public final class CirculoDavila extends Figura28{

    private double r;
   
    public CirculoDavila(double r) {this.r = r;}

    @Override
    public double getPerimetro() {return 2*Math.PI*r;}
    @Override
    public double getArea() {return Math.PI*r*r;}
   
    @Override
    protected String getStrTipo() {return "Circulo";}
    @Override
    protected String getReprAttr() {
        return String.format("r: %.2f", r);
    }

}
