public class Main {

    public static void main(String[] args) {

        ItemList itemsList = new ItemList();

        itemsList.addItem(new Item(5, 49, "Hammer"));
        itemsList.addItem(new Item(3, 27, "Water Bottle"));
        itemsList.addItem(new Item(4, 36, "Compass"));
        itemsList.addItem(new Item(7, 75, "Map"));
        itemsList.addItem(new Item(6, 57, "Trail mix"));
        itemsList.addItem(new Item(2, 18, "Sleeping Bag"));

//        itemsList.printList();

        Knapsack1 sack = new Knapsack1(14, itemsList);
        sack.printBestResults();
    }
}
