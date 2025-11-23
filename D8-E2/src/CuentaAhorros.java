public class CuentaAhorros extends CuentaBancaria {
    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String titular, double saldo, double tasaInteres) {
        super(numeroCuenta, titular, saldo);
        if (tasaInteres < 0 || tasaInteres > 1)
            throw new IllegalArgumentException("La tasa de interés debe estar entre 0 y 1.");
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto a retirar debe ser mayor que 0.");

        double nuevoSaldo = getSaldo() - monto;
        if (nuevoSaldo < 50) {
            throw new SaldoInsuficienteException("No se puede retirar: el saldo después del retiro sería menor a $50.");
        }

        setSaldo(nuevoSaldo);
    }

    public void aplicarInteres() {
        double nuevoSaldo = getSaldo() + (getSaldo() * tasaInteres);
        setSaldo(nuevoSaldo);
    }

    @Override
    public String toString() {
        return "---CuentaAhorros---" + super.toString() + "\nTasa: " + tasaInteres;
    }
}
