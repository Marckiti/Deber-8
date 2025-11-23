public class ProductoRopa extends Producto {
    private String talla;
    private String material;

    public ProductoRopa(String codigo, String nombre, double precioBase, int stock,
                        String talla, String material) {

        super(codigo, nombre, precioBase, stock);

        if (talla == null || talla.isEmpty())
            throw new IllegalArgumentException("La talla no puede ser vacía.");

        if (material == null || material.isEmpty())
            throw new IllegalArgumentException("El material no puede ser vacío.");

        this.talla = talla;
        this.material = material;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 1.12;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Ropa: talla=" + talla +
                ", material=" + material;
    }
}
