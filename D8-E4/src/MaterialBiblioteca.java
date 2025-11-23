public abstract class MaterialBiblioteca {
    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean estaPrestado;

    public MaterialBiblioteca(String codigo, String titulo, String autor, int anioPublicacion) {

        if (codigo == null || codigo.isEmpty())
            throw new IllegalArgumentException("Código no puede estar vacío.");

        if (titulo == null || titulo.isEmpty())
            throw new IllegalArgumentException("Título no puede estar vacío.");

        if (autor == null || autor.isEmpty())
            throw new IllegalArgumentException("Autor no puede estar vacío.");

        if (anioPublicacion < 1000 || anioPublicacion > 2025)
            throw new IllegalArgumentException("Año fuera de rango permitido.");

        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.estaPrestado = false;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public boolean isPrestado() { return estaPrestado; }

    public void prestar() {
        if (estaPrestado)
            throw new MaterialNoDisponibleException("El material ya está prestado.");
        estaPrestado = true;
    }

    public void devolver() {
        estaPrestado = false;
    }

    public abstract double calcularMultaPorRetraso(int diasRetraso);

    public abstract int calcularTiempoMaximoPrestamo();

    @Override
    public String toString() {
        return "[" + codigo + "] " + titulo + " - " + autor +
                " (" + anioPublicacion + ") Prestado: " + estaPrestado;
    }
}
