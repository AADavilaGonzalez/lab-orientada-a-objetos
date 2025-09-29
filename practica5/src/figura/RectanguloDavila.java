package figura;

public final class RectanguloDavila extends Figura28 {
   
    public double l;
    public double h;

    public RectanguloDavila(double l, double h) {
        this.l = l;
        this.h = h;
    }

    @Override
    public double getPerimetro() {return 2*l+2*h;}
    @Override
    public double getArea() {return l*h;}

    @Override
    protected String getStrTipo() {return "Rectangulo";}
    @Override
    protected String getReprAttr() {
        return String.format("l: %.2f, h: %.2f", l,h);
    }

}
