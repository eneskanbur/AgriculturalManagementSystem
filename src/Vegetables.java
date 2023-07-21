class Vegetable extends Crop implements Comparable<Vegetable>{
        
        private final String cultivatedRegion;
        
        Vegetable(String newName, String type,double newWeight, String newRegion){
            
            super(newName,type, newWeight, null);
            cultivatedRegion = newRegion;
            
        }

        @Override
        public String toString() {
            return "Name: " + this.getName() + " Weight: " + this.getWeight() + " Cultivated Region: " + cultivatedRegion;
        }

        

        @Override
        public void consumeIt() {
            System.out.println("Vegetables are consumed cooked.");
        }

        @Override
        public void storeIt(Store s, Supplier sp,int es) throws CanNotBeStoredException{
            throw new CanNotBeStoredException("Vegetables cannot be stored.");
        }

    @Override
    public int compareTo(Vegetable t) {
        if(this.getName().equals(t.getName())){
            return 1;
        }else{
            return (int)(this.getWeight() - t.getWeight());
        }
    }
        
        
        
    }