public class CuentaCorriente extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo, double limiteCredito) {
        super(numeroCuenta, titular, saldo);
        if (limiteCredito < 0)
            throw new IllegalArgumentException("El límite de crédito debe ser mayor o igual a 0.");
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto a retirar debe ser mayor que 0.");

        double disponible = getSaldo() + limiteCredito;
        if (monto > disponible) {
            throw new SaldoInsuficienteException("Retiro excede saldo + límite de crédito. Disponible: " + disponible);
        }

         setSaldo(getSaldo() - monto);
    }

    @Override
    public String toString() {
        return "---CuentaCorriente---" + super.toString() +
                "\nLímite crédito: $" + String.format("%.2f", limiteCredito);
    }
}
