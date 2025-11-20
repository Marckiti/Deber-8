public class Camion extends Vehiculo{
    private double capacidadCarga;

    public Camion(String marca, String modelo, int anio, double precio, double capacidadCarga) {
        super(marca, modelo, anio, precio);
        if(capacidadCarga <= 0){
            throw new IllegalArgumentException("La capacidad de carga debe ser mayor a 0.");
        }else{
        this.capacidadCarga = capacidadCarga;}
    }

    @Override
    public double calcularImpuestoCirculacion() {
        return (getPrecio() * 0.08)+(capacidadCarga * 50);
    }

    @Override
    public String toString() {
        return "---Camion---"+super.toString();
    }
}
