
import java.util.ArrayList;


public class Store implements CropKeeper{
    
    private int id;
    private String name;
    private double maxCapacityArea;
    private double usedCapacityArea;
    private double KGperSquareMeter;
    private ArrayList<Fruit> fruitList = new ArrayList<>();

    Store() {
        this.KGperSquareMeter = 10;
    }
    

    Store(String name, int id, double maxCapacityArea,double KGperSquareMeter) {
        this.name = name;
        this.id = id;
        this.maxCapacityArea = maxCapacityArea;
        this.KGperSquareMeter = KGperSquareMeter;
        usedCapacityArea =0;
    }


    public int getId() {
        return id;
    }

    public double getKGperSquareMeter() {
        return KGperSquareMeter;
    }

    public double getMaxCapacityArea() {
        return maxCapacityArea;
    }

    public String getName() {
        return name;
    }

    public double getUsedCapacityArea() {
        return usedCapacityArea;
    }

    public ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    public void setKGperSquareMeter(double KGperSquareMeter) {
        this.KGperSquareMeter = KGperSquareMeter;
    }

    public void setMaxCapacityArea(double maxCapacityArea) {
        this.maxCapacityArea = maxCapacityArea;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsedCapacityArea(double usedCapacityArea) {
        this.usedCapacityArea = usedCapacityArea;
    }
    
    public double availableCapacity(){
        double available =0;
        available = maxCapacityArea - usedCapacityArea;
        return available;
    }
    
    public boolean canBeStored(Fruit f){
        boolean b = false;
        if(availableCapacity() >= f.getWeight()*KGperSquareMeter){
            b = true;
        }
        return b;
    }
    
    public String importCrop(Fruit f) throws CapacityNotEnoughException {
        int equal =0;
        /*for (int i = 0; i <= fruitList.size(); i++) {
            if(fruitList.get(i).getName().equals(f.getName())){
                equal =1;
            }
        }*/
                
        if(canBeStored(f) == true && fruitList.contains(f) == false){
            usedCapacityArea = usedCapacityArea + (f.getWeight()*KGperSquareMeter);
            fruitList.add(f);
            return "Crop are stored.";
        }else if(canBeStored(f) == true && fruitList.contains(f)){
            for (int i = 0; i < fruitList.size(); i++) {
                if(fruitList.get(i).getName().equals(f.getName())){
                    fruitList.get(i).setWeight((fruitList.get(i).getWeight()) + f.getWeight());
                    usedCapacityArea = usedCapacityArea + (f.getWeight()*KGperSquareMeter);
                    return "Crop's weight is updated.";
                }
            }
        }
            throw new CapacityNotEnoughException("There is not enough capacity.");
    }
    
    public String exportCrop(Fruit f) throws FruitNotFoundException{
        for (int i = 0; i < fruitList.size(); i++) {
            if(fruitList.get(i).getName().equals(f.getName())){
                usedCapacityArea = usedCapacityArea - (f.getWeight()*KGperSquareMeter);
                System.out.println("Available Capacity after export " + f.getName()+ ": " + availableCapacity());
                fruitList.remove(f);
                return f.getName() + " is exported. ";
            }
        }
        throw new FruitNotFoundException("There is not this fruit in the list.");
    }

    @Override
    public String howToStore(Object o) {
        if(o instanceof Fruit){
            return "Fruits are stored in large refrigerated cooler rooms.";
        }
        return "Vegetables are kept in sheds, not listed.";
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", maxCapacityArea=" + maxCapacityArea + ", usedCapacityArea=" + usedCapacityArea + ", KGperSquareMeter=" + KGperSquareMeter + '}';
    }

    
        
    
}
