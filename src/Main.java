public class Main {

    public static void main(String[] args) {

        ItemList itemsList = new ItemList();

        //Create a list of items that can be placed in the knapsack
        itemsList.addItem(new Item(5, 49, "Hammer"));
        itemsList.addItem(new Item(3, 27, "Water Bottle"));
        itemsList.addItem(new Item(4, 36, "Compass"));
        itemsList.addItem(new Item(7, 75, "Map"));
        itemsList.addItem(new Item(6, 57, "Trail mix"));
        itemsList.addItem(new Item(2, 18, "Sleeping Bag"));
        itemsList.addItem(new Item(2,18,  "Sleeping Pad"));

        itemsList.printList();
        itemsList.sortByRatio();
        itemsList.printList();

        //Use the knapsack class to determine best results
        Knapsack1 sack = new Knapsack1(14, itemsList);
        sack.printBestResults();
    }
}
