import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private List<Item> itemList = new ArrayList();

    public void addItem(Item newItem){
        itemList.add(newItem);
    }

    public void deleteItem(int index){
        if(itemList.size()-1 >= index ){
            itemList.remove(index);
        }
    }

    public Item getItem(int index){
        if(itemList.size()-1 >= index ){
            return itemList.get(index);
        }
        return null;
    }

    public void printList(){
        System.out.println("AVAILABLE ITEMS:");
        itemList.forEach((n)-> System.out.println("Name: " + String.format("%1$"+15+"s", n.getName()) + "    Weight: " + n.getWeight() + "   Value: " +n.getValue()));
    }

    public int size(){
        return itemList.size();
    }
}
