package figura;

public abstract class Figura28 {

    public abstract double getPerimetro();
    public abstract double getArea();
    protected abstract String getStrTipo();
    protected abstract String getReprAttr();
    
    @Override
    public String toString() {
        return String.format("%s:{%s}",
            getStrTipo(), getReprAttr()
        );
    }
} 
