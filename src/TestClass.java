
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TestClass {
    public static void main(String[] args) throws FileNotFoundException, IOException, FruitNotAvailableException, SupplierHasNotEnougMoneyException, FruitNotFoundException, CapacityNotEnoughException, CanNotBeStoredException {
        ArrayList<Supplier> suppliersList = new ArrayList<>();
        ArrayList<Store> storeList = new ArrayList<>();
        ArrayList<Crop> cropList = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
   
        FileInputStream stores = new FileInputStream("Stores.txt");
        Scanner fscn = new Scanner(stores);
        Store str = null;
        
        while(fscn.hasNext()){
            String name = fscn.next();
            int id = fscn.nextInt();
            double maxCap = fscn.nextDouble();
            double usedCap = fscn.nextDouble();
            str = new Store(name, id, maxCap, usedCap);
            storeList.add(str);
        }
        fscn.close();
        
        
        File suppliers = new File("Suppliers.txt");
        Scanner sscn = new Scanner(suppliers);
        Supplier supp = null;
        
        while(sscn.hasNext()){
            String name = sscn.next();
            int id = sscn.nextInt();
            double bugdet = sscn.nextDouble();
            supp = new Supplier(name, id, bugdet);
            suppliersList.add(supp);
        }
        sscn.close();
        
        File crops = new File("Crops.txt");
        Scanner cscn = new Scanner(crops);
        Crop crp = null;
        
        while(cscn.hasNext()){
            String name = cscn.next();
            String type = cscn.next();

            if("vegetable".equals(type)){
                double kg = cscn.nextDouble();
                String cityName = cscn.next();
                crp = new Vegetable(name, type, kg, name);
                cropList.add(crp);
            }
            else {
                double kg = cscn.nextDouble();
                String season = cscn.next();
                String taste = cscn.next();
                double price = cscn.nextInt();
                crp = new Fruit(name, type, price, season, taste, price);
                cropList.add(crp);
            }

        }
        cscn.close();
        
        boolean loop;
        while (loop = true) {            
            System.out.println("  \n"+"Please make a choice(0 to 9):\n"
                   + "*********************** \n"
                   + "1.Display all suppliers \n"
                   + "2.Display all stores \n"
                   + "3.Buy a fruit crop \n"
                   + "4.Sell a fruit crop \n"
                   + "5.Remove a fruit from a store \n"
                   + "6.Remove a crop from a supplier \n"
                   + "7.Add crop \n"
                   + "8.Show remaining budget \n"
                   + "9.Show remaining capacity \n"
                   + "0.Quit \n"
                   + "***********************\n");
        int action = scn.nextInt();
        switch(action){
            case 1:
                displayAllSuppliers(suppliersList);
                break;
            case 2:
                displayAllStores(storeList);
                break;
            case 3:
                System.out.println("Enter crop, store name, kg, supplier name");
                
                String name = scn.next();
                String storeName = scn.next();
                double kg = scn.nextInt();
                String supplierName0 = scn.next();
                
                //store bulduk
                int index = 0;
                for (int i = 0; i < storeList.size(); i++) {
                    if(storeList.get(i).getName().equals(storeName)){
                        index = i;
                        break;
                    }else{
                        System.out.println("There is no store " + storeName);
                        break;
                    }
                }
                
                //storedaki listeden crop
                int cropİndex =0;
                for (int i = 0; i < storeList.get(index).getFruitList().size(); i++) {
                    if(storeList.get(index).getFruitList().get(i).getName().equals(name)){
                        cropİndex = i;
                        break;
                    }else{
                        System.out.println("There is no crop " + name);
                        break;
                    }
                }
                
                //supplier bulduk
                int supplierİndex2 =0;
                for (int i = 0; i < suppliersList.size(); i++) {
                    if(suppliersList.get(i).getName().equals(supplierName0)){
                        supplierİndex2 = i;
                        break;
                    }else{
                        System.out.println("There is no supplier " + supplierName0);
                        break;
                    }
                }
                
                suppliersList.get(supplierİndex2).buyCrop(storeList.get(index).getFruitList().get(cropİndex), storeList.get(index), kg);
                break;
            case 4:
                System.out.println("Enter crop, store name, kg and supplier name");
                
                String name2 = scn.next();
                String storeName2 = scn.next();
                double kg2 = scn.nextInt();
                String supplierName = scn.next();
                
                //store bulduk
                int index2 = 0;
                for (int i = 0; i < storeList.size(); i++) {
                    if(storeList.get(i).getName().equals(storeName2)){
                        index2 = i;
                        break;
                    }else{
                        System.out.println("There is no store " + storeName2);
                        break;
                    }
                }
                
                int cropİndex2 =0;
                for (int i = 0; i < storeList.get(index2).getFruitList().size(); i++) {
                    if(storeList.get(index2).getFruitList().get(i).getName().equals(name2)){
                        cropİndex2 = i;
                        break;
                    }else{
                        System.out.println("There is no crop " + storeName2);
                        break;
                    }
                }
                
                int supplierİndex =0;
                for (int i = 0; i < suppliersList.size(); i++) {
                    if(suppliersList.get(i).getName().equals(supplierName)){
                        supplierİndex = i;
                        break;
                    }else{
                        System.out.println("There is no supplier " + supplierName);
                        break;
                    }
                }
                
                storeList.get(index2).importCrop((Fruit)(suppliersList.get(supplierİndex).getCropList().get(cropİndex2)));
                suppliersList.get(supplierİndex).sellCrop(storeList.get(index2).getFruitList().get(cropİndex2), storeList.get(index2), kg2);
                break;
            
            case 5:
                System.out.println("Enter a crop:");
                String removeCrop = scn.next();
                System.out.println("Enter a store name:");
                String removeStoreName = scn.next();
                
                int index3 = 0;
                for (int i = 0; i < storeList.size(); i++) {
                    if(storeList.get(i).getName().equals(removeStoreName)){
                        index3 = i;
                        break;
                    }else{
                        System.out.println("There is no store " + removeStoreName);
                        break;
                    }
                }
                
                int exportİndex =0;
                for (int i = 0; i < storeList.get(index3).getFruitList().size(); i++) {
                    if(storeList.get(index3).getFruitList().get(i).getName().equals(removeCrop)){
                        exportİndex = i;
                        break;
                    }else{
                        System.out.println("There is no crop " + removeCrop);
                        break;
                    }
                }
                storeList.get(index3).exportCrop(storeList.get(index3).getFruitList().get(exportİndex));
                break;
            
            case 6:
                System.out.println("Enter a crop:");
                String removeCropSupplier = scn.next();
                System.out.println("Enter a supplier name:");
                String removeStoreNameSupplier = scn.next();
                
                int supplierİndex3 =0;
                for (int i = 0; i < suppliersList.size(); i++) {
                    if(suppliersList.get(i).getName().equals(removeStoreNameSupplier)){
                        supplierİndex3 = i;
                        break;
                    }else{
                        System.out.println("There is no supplier " + removeStoreNameSupplier);
                        break;
                    }
                }
                
                int removeCropİndex=0;
                for (int i = 0; i < suppliersList.get(supplierİndex3).getCropList().size(); i++) {
                    if(suppliersList.get(supplierİndex3).getCropList().get(i).getName().equals(removeCropSupplier)){
                        removeCropİndex =i;
                        break;
                    }else{
                        System.out.println("There is no crop " + removeCropSupplier);
                        break;
                    }
                }
                
                suppliersList.get(supplierİndex3).getCropList().remove(suppliersList.get(supplierİndex3).getCropList().get(removeCropİndex));
                break;
            case 7:
                System.out.println("Enter a crop and a supplier or store: ");
                String addCrop = scn.next();
                String supOrStore = scn.next();
                
                int cropİndex3=0;
                for (int i = 0; i < cropList.size(); i++) {
                    if(cropList.get(i).getName().equals(addCrop)){
                        cropİndex3 =i;
                        break;
                    }else{
                        System.out.println("There is no crop: " + addCrop);
                        break;
                    }
                }
                
                int es =0;
                for (int i = 0; i < storeList.size(); i++) {
                    if(storeList.get(i).getName().equals(supOrStore)){
                        es=1;
                        cropList.get(cropİndex3).storeIt(storeList.get(i), null,es);
                        break;
                    }else if(suppliersList.get(i).getName().equals(supOrStore)){
                        es=2;//es 2 is supplier
                        cropList.get(cropİndex3).storeIt(null, suppliersList.get(i), es);
                        break;
                    }else{
                        System.out.println("Not found store or supplier");
                        break;
                    }
                }
                
                break;
            case 8:
                System.out.println("Enter a supplier:");
                String budgetSupp = scn.next();
                
                for (int i = 0; i < suppliersList.size(); i++) {
                    if(suppliersList.get(i).getName().equals(budgetSupp)){
                        System.out.println(suppliersList.get(i).getBudget());
                    }
                }
                
                break;
            case 9:
                System.out.println("Enter a store:");
                String capacityStore = scn.next();
                
                for (int i = 0; i < storeList.size(); i++) {
                    if(storeList.get(i).getName().equals(capacityStore)){
                        System.out.println(storeList.get(i).availableCapacity());
                    }
                }
                break;
            case 0:
                loop = false;
                break;
            }
        
            }
        }

    public static void displayAllSuppliers(ArrayList suppliersList){
        for (int i = 0; i < suppliersList.size(); i++) {
                    System.out.println(suppliersList.get(i).toString());
                }
    }

    public static void displayAllStores(ArrayList storeList){
        for (int i = 0; i < storeList.size(); i++) {
                    System.out.println(storeList.get(i).toString());
                }
    }
    
}

