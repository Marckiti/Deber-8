public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Probando creación con titular vacío...");
            CuentaBancaria CB = new CuentaBancaria(Banco.generarNumeroCuenta(), "", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        try {
            System.out.println("\nProbando creación con saldo negativo...");
            CuentaBancaria CB = new CuentaBancaria(Banco.generarNumeroCuenta(), "Pedro", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        Banco banco = new Banco("Banco Ejemplo");

        CuentaAhorros ah1 = new CuentaAhorros(Banco.generarNumeroCuenta(), "Ana", 1000.0, 0.02);
        CuentaAhorros ah2 = new CuentaAhorros(Banco.generarNumeroCuenta(), "Luis", 0.0, 0.03); // saldo 0 para probar inactiva

        CuentaCorriente cc1 = new CuentaCorriente(Banco.generarNumeroCuenta(), "Marcos", 200.0, 300.0);
        CuentaCorriente cc2 = new CuentaCorriente(Banco.generarNumeroCuenta(), "Olga", 1000.0, 500.0);

        CuentaInversion ci1 = new CuentaInversion(Banco.generarNumeroCuenta(), "Carla", 5000.0, 100.0, 0.12);
        CuentaInversion ci2 = new CuentaInversion(Banco.generarNumeroCuenta(), "Diego", 200.0, 50.0, 0.08);

        banco.abrirCuenta(ah1);
        banco.abrirCuenta(ah2);
        banco.abrirCuenta(cc1);
        banco.abrirCuenta(cc2);
        banco.abrirCuenta(ci1);
        banco.abrirCuenta(ci2);

        System.out.println("\nCuentas abiertas:");
        for (CuentaBancaria c : banco.getCuentas()) {
            System.out.println(c);
        }

        System.out.println("\nRealizando operaciones válidas...");
        cc1.depositar(150.0);
        System.out.println("Después de depositar en cc1: " + cc1);

        try {
            ci1.depositar(200.0);
            System.out.println("Después de depositar en ci1: " + ci1);
        } catch (MontoInvalidoException e) {
            System.out.println("Error depósito inversión: " + e.getMessage());
        }

        try {
            cc1.retirar(300.0);
            System.out.println("Después de retirar en cc1: " + cc1);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Error retiro cc1: " + e.getMessage());
        }

        System.out.println("\nProbando retiro con saldo insuficiente en cuenta de ahorros (debe lanzar SaldoInsuficienteException)...");
        try {
            ah1.retirar(980.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Capturado SaldoInsuficienteException: " + e.getMessage());
        } catch (MontoInvalidoException e) {
            System.out.println("Capturado MontoInvalidoException inesperado: " + e.getMessage());
        }

        System.out.println("\nProbando transferencia exitosa (de cc2 a ah1)...");
        try {
            System.out.println("Saldo antes cc2: " + cc2.getSaldo() + " - ah1: " + ah1.getSaldo());
            banco.transferir(cc2.getNumeroCuenta(), ah1.getNumeroCuenta(), 200.0);
            System.out.println("Transferencia OK.");
            System.out.println("Saldo después cc2: " + cc2.getSaldo() + " - ah1: " + ah1.getSaldo());
        } catch (Exception e) {
            System.out.println("Transferencia falló: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        System.out.println("\nProbando transferencia con monto negativo (debe lanzar MontoInvalidoException)...");
        try {
            banco.transferir(cc2.getNumeroCuenta(), ah1.getNumeroCuenta(), -50.0);
        } catch (MontoInvalidoException e) {
            System.out.println("Capturado MontoInvalidoException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otra excepción en transferencia negativa: " + e.getMessage());
        }

        System.out.println("\nProbando transferencia fallida por saldo insuficiente (debe lanzar SaldoInsuficienteException)...");
        try {
            banco.transferir(cc1.getNumeroCuenta(), ah1.getNumeroCuenta(), 10000.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Capturado SaldoInsuficienteException en transferencia: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Otra excepción en transferencia insuficiente: " + e.getMessage());
        }

        System.out.println("\nCalculando saldo total del banco...");
        try {
            double total = banco.obtenerSaldoTotal();
            System.out.println("Saldo total banco: $" + String.format("%.2f", total));
        } catch (IllegalStateException e) {
            System.out.println("No se pudo calcular saldo total: " + e.getMessage());
        }

        System.out.println("\nAplicando intereses a cuentas de ahorro (primera llamada debe lanzar CuentaInactivaException)...");
        try {
            banco.aplicarInteresesAhorros();
            System.out.println("Intereses aplicados correctamente.");
        } catch (CuentaInactivaException e) {
            System.out.println("Capturada CuentaInactivaException: " + e.getMessage());
        }

        System.out.println("\nDepositando en ah2 para activarla y reintentando aplicar intereses...");
        try {
            ah2.depositar(100.0);
            banco.aplicarInteresesAhorros();
            System.out.println("Intereses aplicados. Saldos actuales:");
            for (CuentaBancaria c : banco.getCuentas()) {
                if (c instanceof CuentaAhorros) {
                    System.out.println(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al aplicar intereses tras activar cuenta: " + e.getMessage());
        }

        System.out.println("\nOrdenando cuentas por saldo (ascendente) y mostrando...");
        banco.ordenarPorSaldo();
        for (CuentaBancaria c : banco.getCuentas()) {
            System.out.println(c);
        }

        System.out.println("\nProbando rendimiento en cuenta de inversión ci1:");
        System.out.println("Rendimiento mensual (ci1): $" + String.format("%.2f", ci1.calcularRendimiento()));
        System.out.println("Rendimiento en 6 meses (ci1): $" + String.format("%.2f", ci1.calcularRendimiento(6)));

        System.out.println("\nProbando depósito en cuenta inversión por debajo del mínimo (debe lanzar MontoInvalidoException)...");
        try {
            ci2.depositar(10.0);
        } catch (MontoInvalidoException e) {
            System.out.println("Capturado MontoInvalidoException: " + e.getMessage());
        }

        System.out.println("\n--- FIN PRUEBAS ---");
    }
}
