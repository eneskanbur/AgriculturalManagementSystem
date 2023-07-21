
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edit

import java.util.ArrayList;

/**
 *
 * @author sena
 */
public class Supplier implements CropKeeper {
    
    private String name;
    private long id;
    private double budget;
    ArrayList<Crop> cropList = new ArrayList<>();
    
    Supplier(){
        
    }
    Supplier(String name, long id, double budget){
        this.name = name;
        this.budget = budget;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    public void buyCrop(Crop c, Store s, double kg) throws FruitNotAvailableException, SupplierHasNotEnougMoneyException {
     
        if (s.getFruitList().contains(c) && this.budget >= (((Fruit)c).getPrice())*kg){
          this.budget = this.budget - (((Fruit)c).getPrice()*kg);
          if(this.cropList.contains(c)){
              c.setWeight(c.getWeight()+kg);
          }else{
              this.cropList.add(c);
          }
          } 
        else if (s.getFruitList().contains(c) == false){
              throw new FruitNotAvailableException("Fruit is not available..");
          }
        else if (this.budget < (((Fruit)c).getPrice())*kg){
              throw new SupplierHasNotEnougMoneyException("Supplier has not enough money..");
        }
    }
   
    
    public void sellCrop(Crop c, Store s, double kg) throws FruitNotFoundException, CapacityNotEnoughException {
        
        if(this.cropList.contains(c)){
            c.setWeight(c.getWeight()-kg);
            System.out.println(s.importCrop((Fruit)c));
            this.cropList.remove(c);
            this.budget = this.budget + (((Fruit)c).getPrice()*kg);
        } 
        else {
            throw new FruitNotFoundException("Fruit not found..");
            
        }
    }

        @Override
        public String howToStore(Object o) {
        if(o instanceof Fruit){
            return "Fruits are stored in big refrigerators.";
            }
            return "Vegetables in the field booths.";
            }

    @Override
    public String toString() {
        return "Name:" + name + " Id:" + id + " Budget:" + budget;
    }

    public ArrayList<Crop> getCropList() {
        return cropList;
    }
    
    
}
