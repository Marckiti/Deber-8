public class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (numeroCuenta == null || numeroCuenta.isEmpty()){
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío.");
        }else{
            this.numeroCuenta = numeroCuenta;}

        if (titular == null || titular.isEmpty()){
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        }else{
            this.titular = titular;}

        if (saldo < 0){
            throw new IllegalArgumentException("El saldo inicial debe ser mayor o igual a 0.");
        }else{
            this.saldo = saldo;}
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto a depositar debe ser mayor que 0.");
        this.saldo += monto;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto a retirar debe ser mayor que 0.");

        if (monto > saldo)
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: " + saldo);

        this.saldo -= monto;
    }

    @Override
    public String toString() {
        return "\nCuenta: " + numeroCuenta +
                "\nTitular: " + titular +
                "\nSaldo: $" + String.format("%.2f", saldo);
    }
}