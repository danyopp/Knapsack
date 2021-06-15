import java.util.ArrayList;

public class Knapsack3 implements Knapsack{

    //Completely Recursive version of 0-1 Knapsack
    int PackMaxWeight;
    ItemList availableItems;
    KnapResult finalResult;


    Knapsack3(int maxPackWeight, ItemList availableItems){
        this.PackMaxWeight = maxPackWeight;
        this.availableItems = availableItems;
        findBestFit();
    }

    public void findBestFit(){
        this.finalResult = recursiveKnap(0, availableItems.size()-1, PackMaxWeight);
    }

    private KnapResult recursiveKnap(int startIndex, int endIndex, int weight ){
        //basecase
        if(endIndex - startIndex == 0){
            Item item = availableItems.getItem(startIndex);
            if(item.getWeight() <= weight) {
                return new KnapResult(item.getValue(), item.getWeight(), item);
            }else{
                return new KnapResult(0,0,null);
            }
        }
        //recursive call to get the previous best
        KnapResult noItem = recursiveKnap(startIndex, endIndex-1, weight);
        Item curItem = availableItems.getItem(endIndex);
        int adjustedWeight = weight - curItem.getWeight();
        //recursive call with current item index and weight removed from parameters
        KnapResult minusItem = recursiveKnap(startIndex, endIndex-1, adjustedWeight);
        int valWithItem = minusItem.currentValue + curItem.getValue();
        int totalWeightWithNewItem = minusItem.currentWeight + curItem.getWeight();
        //compare if adding the item to an adjusted weight knapsack or taking a previous best knapsack is a better value
        if(valWithItem >= noItem.currentValue && totalWeightWithNewItem <= weight ){
            //Add item to best list with adjusted weight
            minusItem.addAnotherItem(curItem);
            return minusItem;
        }else{
            //Don't add the item, just take previous best
            return noItem;
        }
    }

    public void printBestResults(){
        System.out.println("Total weight: " + finalResult.currentWeight);
        System.out.println("Total value: " + finalResult.currentValue);
        finalResult.CurrentKnapsack.forEach((n)->System.out.println(n.getName() + " " + n.getValue() + " " + n.getWeight()));
    }

    private class KnapResult{
        ArrayList<Item> CurrentKnapsack;
        int currentValue ;
        int currentWeight ;

        KnapResult(int currentValue, int currentWeight, Item single){
            this.currentValue = currentValue;
            this.currentWeight = currentWeight;
            CurrentKnapsack = new ArrayList<Item>();
            if(single != null){
                CurrentKnapsack.add(single);
            }
        }

        void addAnotherItem(Item i){
            if((i.getWeight() + currentWeight) > PackMaxWeight){
                throw new IllegalStateException();
            }
            this.currentWeight += i.getWeight();
            this.currentValue += i.getValue();
            CurrentKnapsack.add(i);
        }

        void print(){
            this.CurrentKnapsack.forEach((n)->System.out.println(n.getName() + " " + n.getValue() + " " + n.getWeight()));
            System.out.println("Total weight: " + currentWeight);
            System.out.println("Total value: " + currentValue);
        }
    }
}
