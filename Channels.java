public class Channels {
private String name;
private float price;
private final Categories category;
private final Contracts contractNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Categories getCategory() {
        return category;
    }


    public Channels(String name, float price, Categories category, Contracts contractNumber) {
        this.name = name;
        this.price = price;
        this.category=category;
        this.contractNumber = contractNumber;
    }
}
