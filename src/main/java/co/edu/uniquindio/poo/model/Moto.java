package co.edu.uniquindio.poo.model;

public class Moto {
    private String plate;
    private String brand;
    private String yearModel;

    private Moto(Builder builder) {
        this.plate = builder.plate;
        this.brand = builder.brand;
        this.yearModel = builder.yearModel;
    }

    public static class Builder {
        private String plate;
        private String brand;
        private String yearModel;

        public Builder plate(String plate) {
            this.plate = plate;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder yearModel(String yearModel) {
            this.yearModel = yearModel;
            return this;
        }

        public Moto build() {
            return new Moto(this);
        }
    }

    public String getPlate() {
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getYearModel() {
        return yearModel;
    }

    
}
