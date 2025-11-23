import java.util.ArrayList;
import java.util.Random;

public class Biblioteca {

    private ArrayList<MaterialBiblioteca> catalogo;
    private String nombre;

    public Biblioteca(String nombre) {

        if (nombre == null || nombre.isEmpty())
            throw new IllegalArgumentException("Nombre vacío.");

        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
    }

    public static void validarCodigo(String codigo) throws CodigoInvalidoException {

        if (!(codigo.matches("LIB-\\d{3}") ||
                codigo.matches("REV-\\d{3}") ||
                codigo.matches("DVD-\\d{3}"))) {

            throw new CodigoInvalidoException("Código inválido: " + codigo);
        }
    }

    public static String generarCodigoAleatorio(String tipo) {
        int num = new Random().nextInt(900) + 100;
        return tipo.toUpperCase() + "-" + num;
    }

    public void agregarMaterial(MaterialBiblioteca m) throws CodigoInvalidoException {

        if (m == null)
            throw new NullPointerException("Material nulo.");

        validarCodigo(m.getCodigo());

        catalogo.add(m);
    }

    public MaterialBiblioteca buscarMaterial(String codigo) {

        for (MaterialBiblioteca m : catalogo) {
            if (m.getCodigo().equalsIgnoreCase(codigo))
                return m;
        }

        throw new MaterialNoEncontradoException("No existe material: " + codigo);
    }

    public void prestarMaterial(String codigo) {

        MaterialBiblioteca m = buscarMaterial(codigo);

        if (m.isPrestado())
            throw new MaterialNoDisponibleException("Material ya prestado.");

        m.prestar();
    }

    public double devolverMaterial(String codigo, int diasRetraso) {

        if (diasRetraso < 0)
            throw new IllegalArgumentException("Días retraso inválidos.");

        MaterialBiblioteca m = buscarMaterial(codigo);

        m.devolver();
        return m.calcularMultaPorRetraso(diasRetraso);
    }

    public ArrayList<MaterialBiblioteca> listarMaterialesDisponibles() {
        ArrayList<MaterialBiblioteca> disponible = new ArrayList<>();
        for (MaterialBiblioteca m : catalogo) {
            if (!m.isPrestado())
                disponible.add(m);
        }
        return disponible;
    }

    public void ordenarPorAnio() {
        for (int i = 0; i < catalogo.size() - 1; i++) {
            for (int j = 0; j < catalogo.size() - i - 1; j++) {

                if (catalogo.get(j).getAnioPublicacion() >
                        catalogo.get(j + 1).getAnioPublicacion()) {

                    MaterialBiblioteca temp = catalogo.get(j);
                    catalogo.set(j, catalogo.get(j + 1));
                    catalogo.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        return catalogo.toString();
    }
}
