import java.util.ArrayList;
import java.util.List;

//Rules for Knapsack1
//  No partial Items can be used (0-1)
//  No multiple Items can be used unless it appears in the availableItems list more than once
//

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
public class Knapsack1 implements Knapsack {

    int PackMaxWeight;
    ItemList availableItems;
    DPdata[][] arrayDP ;

    //Constructor that will fill in packweight and the list of available items and then call the class functions to build
    //the Dynamic Programming table
    Knapsack1(int maxPackWeight, ItemList availableItems){
        this.PackMaxWeight = maxPackWeight;
        this.availableItems = availableItems;
        arrayDP = new DPdata[availableItems.size()][PackMaxWeight+1];
        findBestFit();
    }

    //Helper function that sets up the first row in the table with a value of zero if the first item cant fit and the value
    //of the first item if it can fit. It then fills in the 0 column with all zeros since no item can fit in a knapsack with capacity zero
    //This function helps to simplify the logic of the function "findBestFit" that actually fills the entire DP table
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

    //Function that calls helper to set up Dynamic programming table and then completes a logic analysis for each spot in table
    //There are three options:
        //1. The item of the row will not fit in a knapsack of the given capacity
        //2. The item of the row will fit in the knapsack of the given capacity
            //2a. The value of the item and the value of the remaining space is greater than or equal to the previous best for that capacity
            //2b. The value of the item and the value of the remaining space is less than or equal to the previous best for that capacity
        //In the event the capacities are equal, this code uses the new item in the solution
    public void findBestFit(){
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

    //Function for printing out the Dynamic Programming table
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

    //returns the list of items form the Final square of the DP table
    public List getBestResult(){
        return arrayDP[availableItems.size()-1][PackMaxWeight].itemsInPack;
    }

    //Prints list items, DP table, and best combo of items
    public void printBestResults(){
        availableItems.printList();
        printDP();
        System.out.println("\nBEST COMBO OF ITEMS:");
        DPdata best = arrayDP[availableItems.size()-1][PackMaxWeight];
        best.itemsInPack.forEach((n)-> System.out.println("Name: " + n.getName() + "   Value: " + n.getValue() + "   Weight: " + n.getWeight()));
        System.out.println("Total Weight: " + best.totalWeight +"    Total Value: " + best.totalValue );
        System.out.println("");
    }

    //class used to hold more information about best solution in the Dynamic Programming array
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
