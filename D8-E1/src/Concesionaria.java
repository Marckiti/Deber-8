import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Concesionaria {

    private ArrayList<Vehiculo> inventario = new ArrayList<>();

    public void agregarVehiculo(Vehiculo v) {
        if (v == null) {
            throw new NullPointerException("El vehículo no puede ser null.");
        }else{inventario.add(v);}
    }

    public double calcularTotalImpuestos() {
        if (inventario.isEmpty())
            throw new IllegalStateException("No hay vehículos en el inventario.");

        double total = 0;
        for (Vehiculo v : inventario) {
            total += v.calcularImpuestoCirculacion();
        }
        return total;
    }

    public Vehiculo obtenerVehiculoMasCaro() {
        if (inventario.isEmpty()) {
            throw new IllegalStateException("No hay vehículos en el inventario.");
        }
        Vehiculo max = inventario.get(0);

        for (Vehiculo v : inventario) {
            if (v.getPrecio() > max.getPrecio())
                max = v;
        }

        return max;
    }

    public void ordenarPorPrecio() {
        Collections.sort(inventario, Comparator.comparingDouble(Vehiculo::getPrecio));
    }

    public ArrayList<Vehiculo> getInventario() {
        return inventario;
    }
}