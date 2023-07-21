
import java.util.logging.Level;
import java.util.logging.Logger;

class Fruit extends Crop implements Comparable<Fruit>{
        
        private String taste;
        private double price;
        
            Fruit(String newName,String type, double newWeight, String newSeason,String newTaste, double newPrice) {
              
                super(newName, type, newWeight, newSeason);
                taste = newTaste;
                price = newPrice;
    }

        @Override
        public String toString() {       
            return "Name: " + this.getName() + " Weight: " + this.getWeight() + " Cultivated Season: " + this.getCultivatedSeason() + " Taste: " + taste + " Price: " + price;
        }
        

        @Override
        public void consumeIt() {
            System.out.println("Fruits are consumed raw.");
            
        }

        @Override
        public void storeIt(Store s, Supplier sp, int es) throws CanNotBeStoredException{
            if(es ==1){
                try {
                    s.importCrop(this);
                } catch (CapacityNotEnoughException ex) {
                    Logger.getLogger(Fruit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(es==2){
                sp.cropList.add(this);
            }
        }

    public String getTaste() {
        return taste;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Fruit t) {
        if(this.getName().equals(t.getName())){
            return 1;
        }else{
            return (int)(this.getWeight() - t.getWeight());
        }
    }
    
    
        
}