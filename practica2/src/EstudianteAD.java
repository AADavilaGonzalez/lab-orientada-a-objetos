
class EstudianteAD {

    private static int sig_matricula = 1;

    public String nombre;
    public String carrera;
    public int edad;
    public int semestreActual;
    public final int matricula;

    private EstudianteAD(String nombre, String carrera, int edad,
        int semestreActual, int matricula
    ) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.edad = edad;
        this.semestreActual = semestreActual;
        this.matricula = matricula;
    }

    public EstudianteAD(String nombre, String carrera, int edad,
        int semestreActual
    ) {
        this(nombre, carrera, edad, semestreActual, sig_matricula); 
        ++sig_matricula;
    }

    public EstudianteAD(String nombre, String carrera, int edad) {
         this(nombre, carrera, edad, 1, sig_matricula);
         ++sig_matricula;
    }

    @Override
    public String toString() {
        return String.format("Estudiante(%s, %s, %d, %d, %d)",
            nombre, carrera, edad, semestreActual, matricula
        );
    }

    public String fmt() {
        return String.format(
            "<%s>\nMatricula: %d\nCarrera: %s\nSemestre: %d\nEdad: %d\n",
            nombre, matricula, carrera, semestreActual, edad
        ); 
    }

    public void print() {System.out.print(this.fmt());}

    public void cambiarNombre(String nombre) {this.nombre = nombre;}

    public void cambiarCarrera(String carrera) {this.carrera = carrera;}

    public void cursarSemestre(float calificacion_final) {
        if(calificacion_final >= 70) {++semestreActual;}
    }

}
