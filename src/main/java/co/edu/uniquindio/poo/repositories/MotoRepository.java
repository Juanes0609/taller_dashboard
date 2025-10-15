package co.edu.uniquindio.poo.repositories;

import java.util.ArrayList;
import co.edu.uniquindio.poo.model.Moto;
/**
 * Repositorio centralizado para gestionar los productos
 * Singleton para garantizar una única instancia en toda la aplicación
 */
public class MotoRepository {
    private static MotoRepository instance;
    private ArrayList<Moto> products;

    private MotoRepository() {
        products = new ArrayList<>();
        loadDataExample();
    }

    /**
     * Obtiene la instancia única del repositorio
     */
    public static MotoRepository getInstance() {
        if (instance == null) {
            instance = new MotoRepository();
        }
        return instance;
    }

    /**
     * Carga algunos Motos de ejemplo
     */
    private void loadDataExample() {
        products.add(new Moto("P001", "Laptop Dell", "Laptop Dell Inspiron 15", 1200.00, 10));
        products.add(new Moto("P002", "Mouse Logitech", "Mouse inalámbrico Logitech MX Master", 89.99, 25));
        products.add(new Moto("P003", "Teclado Mecánico", "Teclado mecánico RGB", 150.00, 15));
    }

    /**
     * Obtiene todos los Motos
     */
    public ArrayList<Moto> getProducts() {
        return products;
    }

    /**
     * Agrega un nuevo Moto
     */
    public void addProduct(Moto product) {
        products.add(product);
    }

    /**
     * Elimina un Moto
     */
    public boolean deleteProduct(Moto product) {
        return products.remove(product);
    }

    /**
     * Busca un Moto por código
     */
    public Moto searchByCode(String code) {
        return products.stream()
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene la cantidad de Motos
     */
    public int getCantidadMotos() {
        return products.size();
    }
}

