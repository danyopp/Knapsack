import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemList {

    private List<Item> itemList = new ArrayList();

    //Add item to the Itemlist arraylist
    public void addItem(Item newItem){
        itemList.add(newItem);
    }

    //Remove item for the Itemlist arraylist by index in list
    public void deleteItem(int index){
        if(itemList.size()-1 >= index ){
            itemList.remove(index);
        }
    }

    //Get item from itemList arraylist by index number
    public Item getItem(int index){
        if(itemList.size()-1 >= index ){
            return itemList.get(index);
        }
        return null;
    }

    //Print the list of items
    public void printList(){
        System.out.println("AVAILABLE ITEMS:");
        itemList.forEach((n)-> System.out.println("Name: " + String.format("%1$"+15+"s", n.getName()) + "    Weight: " + n.getWeight() + "   Value: " +n.getValue() + "   Ratio: " + n.getRatio()));
    }

    //Get the number of items in the arraylist
    public int size(){
        return itemList.size();
    }

    public void sortByRatio(){
        Collections.sort(itemList, (o1, o2) -> -Double.compare(o1.getRatio(), o2.getRatio()));
    }



}
