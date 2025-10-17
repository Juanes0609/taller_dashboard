/**
 * Clase simulada que devuelve objetos con atributos diferentes
 * Se usa adapter para convertirlos a la clase "Moto"
 */

package co.edu.uniquindio.poo.model;

// Clase externa simulada
class ExternalBike {
    public String licensePlate;
    public String make;
    public String modelYear;
}

public class ExternalBikeAdapter {
    public static Moto adapt(ExternalBike externalBike) {
        return new Moto.Builder()
                       .plate(externalBike.licensePlate)
                       .brand(externalBike.make)
                       .yearModel(externalBike.modelYear)
                       .build();
    }
}