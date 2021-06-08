//Rules for knapsack2:
//      Knapsack version 2 will allow partial items in the knapsack
//      Knapsack version 2 does not allow duplicate items

public class Knapsack2 implements Knapsack{

    double packMaxWeight;
    double packCurrentWeight;
    double value;
    ItemList availableItems;

    Knapsack2(int packMaxWeight, ItemList availableItems){
        this.packMaxWeight = packMaxWeight;
        this.availableItems = availableItems;
        packCurrentWeight = 0;
        findBestFit();
    }

    public void findBestFit(){
        availableItems.printList();
        System.out.println("\nBEST COMBO OF ITEMS:");

        ItemList workList = availableItems.clone();
        workList.sortByRatio();

        while( packMaxWeight > packCurrentWeight)
        {
            if (workList.size() == 0){break;} //not enough Items to fill knapsack
            Item poppedItem = workList.pop();
            double remainingSpace = packMaxWeight - packCurrentWeight;
            if(remainingSpace >= (double)poppedItem.getWeight()){
                //add full popped Item
                printItem(poppedItem, poppedItem.getWeight());
                packCurrentWeight += poppedItem.getWeight();
                value += poppedItem.getValue();
            }
            else{
                //add partial popped Item+
                printItem(poppedItem, remainingSpace);
                packCurrentWeight += remainingSpace;
                value += poppedItem.getValue()* (remainingSpace/ poppedItem.getWeight());
                break;
            }

        }

    }

   private void printItem(Item n, double weightAdded){
       double percentageUsed =  weightAdded/n.getWeight();
       double valueGotten = n.getValue()*percentageUsed;
       System.out.println("Name: " + n.getName() + "   Value Received: " + valueGotten + "   Weight Added: " + weightAdded );

   }


    public void printBestResults() {
        System.out.println("Total Weight: " + packCurrentWeight +"    Total Value: " + value );
    }
}
