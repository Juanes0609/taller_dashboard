package co.edu.uniquindio.poo.model;

public class Moto {
    private String plate;
    private String brand;
    private String yearModel;
   

    public Moto() {
    }

    public Moto(String plate, String brand, String yearModel) {
        this.plate = plate;
        this.brand = brand;
        this.yearModel = yearModel;
       
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYearModel() {
        return yearModel;
    }

    public void setYearModel(String yearModel) {
        this.yearModel = yearModel;
    }

    @Override
    public String toString() {
        return plate + "\t" + brand + "\t" + yearModel + "\t";
    }
}

