public class Item {
   private int weight;
   private int value;
   private String name;
   private double ratio;

   //Constructors
    public Item (int weight, int value){
        this.value = value;
        this.weight = weight;
        this.ratio = (double)this.value/this.weight;
    }

    public Item (int weight, int value, String name){
        this(weight, value);
        this.name = name;
    }

    //Getters
    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public double getRatio() {
        return ratio;
    }
}
