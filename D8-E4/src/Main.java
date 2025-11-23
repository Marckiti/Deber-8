public class Main {
    public static void main(String[] args) {
        Biblioteca b = new Biblioteca("Municipal");

        try {
            Libro l = new Libro("LIB-001", "", "Autor", 2000, 100, "Editorial", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Libro l = new Libro("LIB-002", "Titulo", "Autor", 500, 100, "Edit", false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            b.agregarMaterial(new Libro("XXX-999", "Libro", "Autor", 2000, 100, "Edit", false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            b.agregarMaterial(new Libro("LIB-101", "Libro A", "Autor1", 2010, 200, "Planeta", false));
            b.agregarMaterial(new Libro("LIB-102", "Libro B", "Autor2", 2015, 150, "Norma", true));

            b.agregarMaterial(new Revista("REV-201", "Revista A", "Editor1", 2020, 10, "Enero", false));
            b.agregarMaterial(new Revista("REV-202", "Revista B", "Editor2", 2018, 5, "Marzo", true));

            b.agregarMaterial(new DVD("DVD-301", "Película A", "Director1", 2012, 120, "Acción", true));
            b.agregarMaterial(new DVD("DVD-302", "Película B", "Director2", 2019, 90, "Drama", false));

            b.agregarMaterial(new Libro("LIB-103", "Libro C", "Autor3", 2005, 300, "Taurus", false));
            b.agregarMaterial(new Revista("REV-203", "Revista C", "Editor3", 2022, 12, "Junio", false));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        b.prestarMaterial("LIB-101");
        b.prestarMaterial("REV-201");
        b.prestarMaterial("DVD-301");
        b.prestarMaterial("LIB-102");

        try {
            b.prestarMaterial("LIB-101");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            b.buscarMaterial("LIB-999");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Multa 1: $" + b.devolverMaterial("DVD-301", 3));
        System.out.println("Multa 2: $" + b.devolverMaterial("LIB-102", 10));

        System.out.println("\nMateriales disponibles:");
        for (var m : b.listarMaterialesDisponibles()) {
            System.out.println(m);
        }

        b.ordenarPorAnio();
        System.out.println("\nCatalogo ordenado por año:");
        System.out.println(b);
    }
}
