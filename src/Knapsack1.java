import java.util.ArrayList;
import java.util.List;

//Rules for Knapsack1
//  No partial Items can be used (0-1)
//  No multiple Items can be used unless it appears in the availableItems list more than once
//


public class Knapsack1 {
    int PackMaxWeight;
    ItemList availableItems;
    List<Item> bestItems = new ArrayList<>();

    DPdata[][] arrayDP ;
    /*          Sample Table
                            Capacity
                        0   1   2   3   4   5
    Item    v=2 w=1  0  0   2   2   2   2   2
    Index   v=3 w=3  1  0   2   2   3   5   5
                     2  0
                     3  0
                     4  0
                     5  0
     */


    Knapsack1(int maxPackWeight, ItemList availableItems){
        this.PackMaxWeight = maxPackWeight;
        this.availableItems = availableItems;
        arrayDP = new DPdata[availableItems.size()][PackMaxWeight+1];
        findBestFit();
    }

    private void setUpTableEdge(){
        //Set first row with single item
        for(int i = 0; i < PackMaxWeight+1; i++){
            Item item = availableItems.getItem(0);
            if(i < item.getWeight()) {
                arrayDP[0][i] = new DPdata(0, 0, new ArrayList<Item>());
            }
            else{
                ArrayList newList = new ArrayList<Item>();
                newList.add(item);
                arrayDP[0][i] = new DPdata(item.getValue(), item.getWeight(), newList);
            }
        }

        //Set first Column with all zeros for zero capacity
        for(int i = 1; i < availableItems.size(); i++) {
            arrayDP[i][0] = new DPdata(0,0, new ArrayList<Item>());
        }
    }

    private void findBestFit(){
        setUpTableEdge();
        //Add items to dynamic programing array
        for(int curItemCounter = 1; curItemCounter < availableItems.size(); curItemCounter++){
            Item item = availableItems.getItem(curItemCounter);
            int itemWeight = item.getWeight();
            int itemValue = item.getValue();
            for(int curWeightCounter = 1; curWeightCounter <= PackMaxWeight; curWeightCounter++){
                //Weight too high to add
                int subWeight = 0;
                int subValue = 0;
                int previousValue = arrayDP[curItemCounter-1][curWeightCounter].totalValue;

                if(itemWeight <= curWeightCounter){
                   subWeight = curWeightCounter-itemWeight;
                   subValue =  arrayDP[curItemCounter-1][subWeight].totalValue;

                }

                //Can fit but not best option
                if(previousValue > (itemValue+subValue) || itemWeight+subWeight > curWeightCounter){
                    arrayDP[curItemCounter][curWeightCounter] = arrayDP[curItemCounter-1][curWeightCounter];
                }

                //Can fit and improves knapsack itemValue
                else{
                    int newWeight = arrayDP[curItemCounter-1][subWeight].totalWeight + itemWeight;
                    int newValue = subValue + itemValue;
                    ArrayList<Item> newList =  arrayDP[curItemCounter-1][subWeight].cloneList();
                    newList.add(item);
                    arrayDP[curItemCounter][curWeightCounter] = new DPdata(newValue, newWeight, newList);
                }
                //printDP();
            }
        }
    }

    public void printDP(){
        System.out.println("\nDYNAMIC PROGRAMMING CHART:");
        System.out.print("                ");
        for (int i = 0; i <= PackMaxWeight; i++){
            System.out.print("  " + String.format("%02d",i) + "  ");
        }
        System.out.println("");
        for(int curItem = 0; curItem < availableItems.size(); curItem++){
            System.out.print(String.format("%1$"+15+"s",availableItems.getItem(curItem).getName() + ":  "));
            for(int curWeight = 0; curWeight <= PackMaxWeight; curWeight++){
                if(arrayDP[curItem][curWeight] == null) {
                    System.out.print(" null ");
                }
                else{
                    System.out.print( " " + String.format("%04d", arrayDP[curItem][curWeight].totalValue) + " ");
                }
            }
            System.out.println("");
        }
    }

    public List getBestResult(){
        return arrayDP[availableItems.size()-1][PackMaxWeight].itemsInPack;
    }

    public void printBestResults(){

        availableItems.printList();
        printDP();
        System.out.println("\nBEST COMBO OF ITEMS:");
        DPdata best = arrayDP[availableItems.size()-1][PackMaxWeight];
        best.itemsInPack.forEach((n)-> System.out.println("Name: " + n.getName() + "   Value: " + n.getValue() + "   Weight: " + n.getWeight()));
        System.out.println("Total Weight: " + best.totalWeight +"    Total Value: " + best.totalValue );
        System.out.println("");
    }

    //class used to hold more information in the Dynamic Programming array
    class DPdata{
        int totalValue = 0;
        int totalWeight = 0;
        ArrayList<Item> itemsInPack = new ArrayList<Item>();

        DPdata(int totalValue, int totalWeight, ArrayList<Item> itemsInPack ){
            this.totalValue = totalValue;
            this.totalWeight = totalWeight;
            this.itemsInPack = itemsInPack;
        }

        ArrayList<Item> cloneList(){
            return (ArrayList<Item>)itemsInPack.clone();
        }

        void addListItem(Item i){
            itemsInPack.add(i);
        }
    }


}
