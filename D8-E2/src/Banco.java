import java.util.ArrayList;
import java.util.Random;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas;
    private String nombre;

    public Banco(String nombre) {
        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("El nombre del banco no puede ser vacío.");
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public static String generarNumeroCuenta() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int dig = rnd.nextInt(10);
            sb.append(dig);
        }
        return sb.toString();
    }

    public void abrirCuenta(CuentaBancaria cuenta) {
        if (cuenta == null)
            throw new NullPointerException("La cuenta no puede ser null.");
        cuentas.add(cuenta);
    }

    private CuentaBancaria buscarPorNumero(String numero) {
        for (CuentaBancaria c : cuentas) {
            if (c.getNumeroCuenta().equals(numero))
                return c;
        }
        return null;
    }

    public void transferir(String origen, String destino, double monto) throws SaldoInsuficienteException {
        if (monto <= 0)
            throw new MontoInvalidoException("El monto de la transferencia debe ser mayor que 0.");

        CuentaBancaria Origen = buscarPorNumero(origen);
        CuentaBancaria Destino = buscarPorNumero(destino);

        if (Origen == null)
            throw new IllegalArgumentException("Cuenta origen no encontrada: " + origen);
        if (Destino == null)
            throw new IllegalArgumentException("Cuenta destino no encontrada: " + destino);

        Origen.retirar(monto);
        Destino.depositar(monto);
    }

    public void aplicarInteresesAhorros() {
        for (CuentaBancaria c : cuentas) {
            if (c instanceof CuentaAhorros) {
                if (c.getSaldo() == 0.0) {
                    throw new CuentaInactivaException("Cuenta de ahorros inactiva (saldo 0): " + c.getNumeroCuenta());
                }
                // casteo seguro
                CuentaAhorros cuenta = (CuentaAhorros) c;
                cuenta.aplicarInteres();
            }
        }
    }

    public double obtenerSaldoTotal() {
        if (cuentas.isEmpty())
            throw new IllegalStateException("No hay cuentas en el banco.");
        double total = 0;
        for (CuentaBancaria c : cuentas)
            total += c.getSaldo();
        return total;
    }

    public void ordenarPorSaldo() {
        int n = cuentas.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (cuentas.get(j).getSaldo() < cuentas.get(minIndex).getSaldo()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                CuentaBancaria temp = cuentas.get(i);
                cuentas.set(i, cuentas.get(minIndex));
                cuentas.set(minIndex, temp);
            }
        }
    }

    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
