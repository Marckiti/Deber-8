public class Auto extends Vehiculo{
    private int numeroPuertas;

    public Auto(String marca, String modelo, int anio, double precio, int numeroPuertas) {
        super(marca, modelo, anio, precio);
        if (numeroPuertas <= 0) {
            throw new IllegalArgumentException("El numero de puertas debe ser mayor a 0.");
        }else{
        this.numeroPuertas = numeroPuertas;}
    }
    public Auto(String marca, String modelo, int anio, double precio) {
        super(marca, modelo, anio, precio);
        this.numeroPuertas = 4;
    }

    @Override
    public double calcularImpuestoCirculacion() {
        return getPrecio()*0.05;
    }

    @Override
    public String toString() {
        return "---Auto---"+super.toString();
    }
}
