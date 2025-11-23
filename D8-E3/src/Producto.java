public abstract class Producto {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int stock;

    public Producto(String codigo, String nombre, double precioBase, int stock) {

        if (codigo == null || codigo.isEmpty())
            throw new IllegalArgumentException("El código no puede ser vacío.");

        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El nombre no puede ser vacío.");

        if (precioBase <= 0)
            throw new PrecioInvalidoException("El precio base debe ser mayor a 0.");

        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo.");

        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precioBase; }
    public int getStock() { return stock; }

    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    public abstract double calcularPrecioFinal();

    @Override
    public String toString() {
        return "Producto {" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precioBase=" + precioBase +
                ", stock=" + stock +
                '}';
    }
}

