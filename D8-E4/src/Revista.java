public class Revista extends MaterialBiblioteca {
    private int numeroEdicion;
    private String mesPublicacion;
    private boolean esEspecializada;

    public Revista(String codigo, String titulo, String autor, int anioPublicacion,
                   int numeroEdicion, String mesPublicacion, boolean esEspecializada) {

        super(codigo, titulo, autor, anioPublicacion);

        if (numeroEdicion <= 0)
            throw new IllegalArgumentException("Número de edición inválido.");

        if (mesPublicacion == null || mesPublicacion.isEmpty())
            throw new IllegalArgumentException("Mes de publicación no puede ser vacío.");

        this.numeroEdicion = numeroEdicion;
        this.mesPublicacion = mesPublicacion;
        this.esEspecializada = esEspecializada;
    }

    @Override
    public double calcularMultaPorRetraso(int diasRetraso) {
        if (diasRetraso < 0)
            throw new IllegalArgumentException("Días de retraso inválidos.");

        return esEspecializada ? diasRetraso * 2.00 : diasRetraso * 0.75;
    }

    @Override
    public int calcularTiempoMaximoPrestamo() {
        return esEspecializada ? 5 : 7;
    }

    @Override
    public String toString() {
        return "Revista → " + super.toString() +
                " | Edición: " + numeroEdicion +
                " | Mes: " + mesPublicacion +
                " | Especializada: " + esEspecializada;
    }
}
