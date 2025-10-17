package co.edu.uniquindio.poo.repositories;

import java.util.ArrayList;
import co.edu.uniquindio.poo.model.Moto;

public class MotoRepository {
    private static MotoRepository instance;
    private final ArrayList<Moto> motos;

    private MotoRepository() {
        motos = new ArrayList<>();
        loadDataExample();
    }

    public static MotoRepository getInstance() {
        if (instance == null) {
            instance = new MotoRepository();
        }
        return instance;
    }

    private void loadDataExample() {
        motos.add(new Moto.Builder()
                          .plate("P001")
                          .brand("Yamaha")
                          .yearModel("2022")
                          .build()
        );

        motos.add(new Moto.Builder()
                          .plate("P002")
                          .brand("Suzuki")
                          .yearModel("2017")
                          .build()
        );

        motos.add(new Moto.Builder()
                          .plate("P003")
                          .brand("BMW")
                          .yearModel("2026")
                          .build()
        );
    }

    public ArrayList<Moto> getMotos() { return motos; }

    public void addMoto(Moto moto) { 
        motos.add(moto); 
    }

    public boolean deleteMoto(Moto moto) { 
        return motos.remove(moto); 
    }

    public Moto searchByPlate(String plate) {
        return motos.stream()
                .filter(p -> p.getPlate().equals(plate))
                .findFirst()
                .orElse(null);
    }
}
