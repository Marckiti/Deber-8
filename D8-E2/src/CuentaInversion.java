public class CuentaInversion extends CuentaBancaria {
    private double montoMinimo; // > 0
    private double rendimientoAnual; // >= 0 (ej: 0.12 = 12%)

    public CuentaInversion(String numeroCuenta, String titular, double saldo, double montoMinimo, double rendimientoAnual) {
        super(numeroCuenta, titular, saldo);
        if (montoMinimo <= 0)
            throw new IllegalArgumentException("El monto mínimo debe ser mayor a 0.");
        if (rendimientoAnual < 0)
            throw new IllegalArgumentException("El rendimiento anual debe ser mayor o igual a 0.");
        this.montoMinimo = montoMinimo;
        this.rendimientoAnual = rendimientoAnual;
    }

    // Sobrescribimos depositar: si monto < montoMinimo -> MontoInvalidoException
    @Override
    public void depositar(double monto) {
        if (monto < montoMinimo)
            throw new MontoInvalidoException("El depósito debe ser al menos el monto mínimo: " + montoMinimo);
        super.depositar(monto);
    }

    // calcularRendimiento mensual simple (rendimientoAnual / 12)
    public double calcularRendimiento() {
        return getSaldo() * (rendimientoAnual / 12.0);
    }

    public double calcularRendimiento(int meses) {
        if (meses < 0)
            throw new IllegalArgumentException("Los meses no pueden ser negativos.");
        return getSaldo() * (rendimientoAnual / 12.0) * meses;
    }

    @Override
    public String toString() {
        return "----CuentaInversion----" + super.toString() +
                "\nMínimo: $" + montoMinimo +
                "\nRendimiento Anual: " + rendimientoAnual;
    }
}
