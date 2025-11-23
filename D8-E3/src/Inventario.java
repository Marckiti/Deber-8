import java.util.ArrayList;
import java.util.Random;

public class Inventario {
    private ArrayList<Producto> productos;
    private String nombreTienda;

    public Inventario(String nombreTienda) {

        if (nombreTienda == null || nombreTienda.isEmpty())
            throw new IllegalArgumentException("El nombre de la tienda no puede ser vacío.");

        this.nombreTienda = nombreTienda;
        this.productos = new ArrayList<>();
    }

    public static String generarCodigo(String categoria) {
        int num = new Random().nextInt(9000) + 1000;
        return categoria.toUpperCase() + "-" + num;
    }

    public void agregarProducto(Producto p) {
        if (p == null)
            throw new NullPointerException("No se puede agregar un producto null.");
        productos.add(p);
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo))
                return p;
        }
        throw new ProductoNoEncontradoException("Producto con código " + codigo + " no existe.");
    }

    public double venderProducto(String codigo, int cantidad) throws StockInsuficienteException {

        if (cantidad <= 0)
            throw new IllegalArgumentException("Cantidad inválida.");

        Producto p = buscarPorCodigo(codigo);

        if (p.getStock() < cantidad)
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + codigo);

        p.reducirStock(cantidad);
        return p.calcularPrecioFinal() * cantidad;
    }

    public double calcularValorInventario() {
        if (productos.isEmpty())
            throw new IllegalStateException("Inventario vacío.");

        double total = 0;
        for (Producto p : productos) {
            total += p.calcularPrecioFinal() * p.getStock();
        }
        return total;
    }

    public ArrayList<Producto> listarProductosBajoStock(int minimo) {
        ArrayList<Producto> lista = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() < minimo) {
                lista.add(p);
            }
        }
        return lista;
    }

    public void ordenarPorPrecio() {

        for (int i = 0; i < productos.size() - 1; i++) {
            for (int j = 0; j < productos.size() - i - 1; j++) {

                double precio1 = productos.get(j).calcularPrecioFinal();
                double precio2 = productos.get(j + 1).calcularPrecioFinal();

                if (precio1 > precio2) {
                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Inventario de " + nombreTienda + ":\n" + productos;
    }
}
