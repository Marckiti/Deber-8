public class ProductoElectronico extends Producto {
    private String marca;
    private int garantiaMeses;

    public ProductoElectronico(String codigo, String nombre, double precioBase, int stock,
                               String marca, int garantiaMeses) {

        super(codigo, nombre, precioBase, stock);

        if (marca == null || marca.isEmpty())
            throw new IllegalArgumentException("La marca no puede ser vacía.");

        if (garantiaMeses <= 0)
            throw new IllegalArgumentException("La garantía debe ser mayor a 0.");

        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.12;
    }

    public boolean aplicarGarantia() {
        return garantiaMeses == 12;
    }

    public double aplicarGarantia(int mesesExtras) {
        if (mesesExtras <= 0)
            throw new IllegalArgumentException("Los meses extras deben ser positivos.");

        return mesesExtras * 5.0;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Electrónico: marca=" + marca +
                ", garantía=" + garantiaMeses;
    }
}
