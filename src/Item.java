public class Item {
   private int weight;
   private int value;
   private String name;

    public Item (int weight, int value){
        this.value = value;
        this.weight = weight;
    }

    public Item (int weight, int value, String name){
        this(weight, value);
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
