package figura;

//Se asumen triangulos isoceles en calculos de perimetro
//para simplificar el codigo
public final class TrianguloDavila extends Figura28 {

    public double b;
    public double h;
   
    public TrianguloDavila(double b, double h) {
        this.b = b;
        this.h = h;
    }

    @Override
    public double getPerimetro() {return b+Math.sqrt(4*h*h+b*b);}
    @Override
    public double getArea() {return b*h;}

    @Override
    protected String getStrTipo() {return "Triangulo";}
    @Override
    protected String getReprAttr() {
        return String.format("b: %.2f, h: %.2f", b, h);
    } 

}
