package Model;
public abstract class Toy {
    private String name;
    private int quantity;
    private double weight;
    //метод игрушки, включающей название, количество и частоту выпадения
    public Toy(String name, int quantity, double weight) {
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight2) {
        this.weight = weight2;
    }

    @Override
    public String toString() {
        return "Toy [name=" + name + ", quantity=" + quantity + ", weight=" + weight + "]";
    }
    
}

