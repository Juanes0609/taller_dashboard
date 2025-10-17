package co.edu.uniquindio.poo.model;

public class MotoFactory {
    public static Moto createMoto(String plate, String brand, String yearModel) {
        return new Moto.Builder()
                       .plate(plate)
                       .brand(brand)
                       .yearModel(yearModel)
                       .build();
    }
}
    
}
