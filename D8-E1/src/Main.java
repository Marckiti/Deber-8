public class Main {
    public static void main(String[] args) {

        // PRUEBAS DE EXCEPCIONES
        try {
            Vehiculo v1 = new Auto("Toyota", "Corolla", 1800, 15000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear vehículo (año inválido): " + e.getMessage());
        }

        try {
            Vehiculo v2 = new Moto("Honda", "CBR", 2020, -5000, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear vehículo (precio negativo): " + e.getMessage());
        }

        Concesionaria c = new Concesionaria();

        // PROBAR calcularTotalImpuestos() con lista vacía
        try {
            c.calcularTotalImpuestos();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // AGREGAR 6 VEHÍCULOS
        c.agregarVehiculo(new Auto("Toyota", "Corolla", 2020, 15000));
        c.agregarVehiculo(new Auto("Kia", "Rio", 2021, 13000, 5));

        c.agregarVehiculo(new Moto("Honda", "CB190", 2022, 5000, 190));
        c.agregarVehiculo(new Moto("Yamaha", "R3", 2020, 9000, 321));

        c.agregarVehiculo(new Camion("Volvo", "FH", 2019, 80000, 18));
        c.agregarVehiculo(new Camion("Scania", "R500", 2021, 95000, 20));

        // CALCULAR TOTAL IMPUESTOS
        System.out.println("\nTotal impuestos: $" + c.calcularTotalImpuestos());

        // VEHÍCULO MÁS CARO
        System.out.println("\nVehículo más caro:");
        System.out.println(c.obtenerVehiculoMasCaro());

        // ORDENAR POR PRECIO
        c.ordenarPorPrecio();

        System.out.println("\nInventario ordenado por precio:");
        for (Vehiculo v : c.getInventario()) {
            System.out.println(v);
        }
    }
}