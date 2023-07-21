abstract class Crop {
        
        private final String name;
        private double weight;
        private final String cultivatedSeason;
        private String type;
        
        Crop(String newName, String type, double newWeight, String newSeason){
            name = newName;
            weight = newWeight;
            cultivatedSeason = newSeason;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

    public String getType() {
        return type;
    }
        
        

        public double getWeight() {
            return weight;
        }

        public String getCultivatedSeason() {
            return cultivatedSeason;
        }
        
        @Override
        public abstract String toString();
        
        public abstract void consumeIt();
        
        public abstract void storeIt(Store s, Supplier sp, int es)throws CanNotBeStoredException;
        
        
}