public class Contracts {
private final String number;
private final Delivers deliver;
private int length;

    public Contracts(String number, Delivers deliver, int length) {
        this.number = number;
        this.deliver = deliver;
        this.length = length;
    }

    public String getNumber() {
        return number;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
