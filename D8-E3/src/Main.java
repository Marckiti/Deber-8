import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Inventario inv = new Inventario("SuperTienda");

        try {
            Producto p = new ProductoElectronico(
                    "ELEC-0001", "Laptop", -10, 5, "Dell", 12);
        } catch (PrecioInvalidoException e) {
            System.out.println(e.getMessage());
        }

        try {
            Producto p = new ProductoRopa(
                    "", "Camisa", 20, 10, "M", "Algodón");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        inv.agregarProducto(new ProductoElectronico(
                Inventario.generarCodigo("elec"), "TV", 500, 10, "Sony", 12));

        inv.agregarProducto(new ProductoElectronico(
                Inventario.generarCodigo("elec"), "Parlante", 80, 4, "JBL", 12));

        inv.agregarProducto(new ProductoAlimenticio(
                Inventario.generarCodigo("food"), "Leche", 1.2, 20,
                LocalDate.now().plusDays(10), true));

        inv.agregarProducto(new ProductoAlimenticio(
                Inventario.generarCodigo("food"), "Pan", 0.50, 3,
                LocalDate.now().plusDays(2), false));

        inv.agregarProducto(new ProductoRopa(
                Inventario.generarCodigo("ropa"), "Pantalón", 25, 6, "L", "Jean"));

        inv.agregarProducto(new ProductoRopa(
                Inventario.generarCodigo("ropa"), "Gorra", 10, 2, "Única", "Tela"));

        inv.agregarProducto(new ProductoElectronico(
                Inventario.generarCodigo("elec"), "Monitor", 150, 5, "LG", 12));

        inv.agregarProducto(new ProductoRopa(
                Inventario.generarCodigo("ropa"), "Zapatillas", 40, 8, "42", "Cuero"));

        inv.agregarProducto(new ProductoAlimenticio(
                Inventario.generarCodigo("food"), "Queso", 3.5, 4,
                LocalDate.now().plusDays(15), true));

        inv.agregarProducto(new ProductoRopa(
                Inventario.generarCodigo("ropa"), "Chaqueta", 60, 1, "XL", "Sintético"));


        try {
            String codigo = inv.buscarPorCodigo(inv.listarProductosBajoStock(100).get(0).getCodigo()).getCodigo();
            double total = inv.venderProducto(codigo, 1);
            System.out.println("Venta realizada: $" + total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Producto p = inv.listarProductosBajoStock(2).get(0);
            inv.venderProducto(p.getCodigo(), 999);
        } catch (StockInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        try {
            inv.buscarPorCodigo("NO EXISTE");
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Valor total inventario: $" + inv.calcularValorInventario());

        System.out.println("Productos bajo stock < 5: " + inv.listarProductosBajoStock(5));

        inv.ordenarPorPrecio();
        System.out.println("Inventario ordenado:");
        System.out.println(inv);
    }
}
