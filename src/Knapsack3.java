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
            if(item.getWeight() <= PackMaxWeight) {
                return new KnapResult(item.getValue(), item.getWeight(), item);
            }else{
                return new KnapResult(0,0,null);
            }
        }
        //recursion
            //last option was better
            KnapResult noItem = recursiveKnap(startIndex, endIndex-1, weight);
            //use current item as well as best of whats remaining
            Item curItem = availableItems.getItem(endIndex);
            int adjustedWeight = weight - curItem.getWeight();
            KnapResult minusItem = recursiveKnap(startIndex, endIndex-1, adjustedWeight);
            int valWithItem = minusItem.currentValue + curItem.getValue();
            //compare the two recursive calls for results
            if(valWithItem > noItem.currentValue && (minusItem.currentWeight += curItem.getWeight()) <= PackMaxWeight ){
                minusItem.currentValue += curItem.getValue();
                minusItem.CurrentKnapsack.add(curItem);
                return minusItem;
            }else{
                return noItem;
            }

    }



    public void printBestResults(){
        System.out.println("Total weight: " + finalResult.currentWeight);
        System.out.println("Total value: " + finalResult.currentValue);
    }

    private class KnapResult{
        ArrayList<Item> CurrentKnapsack;
        int currentValue;
        int currentWeight;

        KnapResult(int currentValue, int currentWeight, Item single){
            this.currentValue = currentValue;
            this.currentWeight = currentWeight;
            CurrentKnapsack = new ArrayList<Item>();
            if(single != null){
                CurrentKnapsack.add(single);
            }
        }
    }
}
