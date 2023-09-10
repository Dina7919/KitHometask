package Model;
//метод игрушек, наследованной от игрушки и включающей в себя id
public class Toys extends Toy{

    private int id;

    public Toys(String name, int quantity, double weight, int id) {
        super(name, quantity, weight);
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Toys [quantity=" + super.getQuantity() + 
        ", name=" + super.getName() + ", weight=" + super.getWeight() + ", id=" + id + "]";
    }
  
    }
