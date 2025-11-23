import java.time.LocalDate;

public class ProductoAlimenticio extends Producto {
    private LocalDate fechaVencimiento;
    private boolean requiereRefrigeracion;

    public ProductoAlimenticio(String codigo, String nombre, double precioBase, int stock,
                               LocalDate fechaVencimiento, boolean requiereRefrigeracion) {

        super(codigo, nombre, precioBase, stock);

        if (fechaVencimiento == null)
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula.");

        this.fechaVencimiento = fechaVencimiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Alimenticio: vence=" + fechaVencimiento +
                ", refrigeración=" + requiereRefrigeracion;
    }
}
