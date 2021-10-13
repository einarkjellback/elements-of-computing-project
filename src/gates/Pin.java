package gates;

public class Pin {

    private final Gate owner;

    public Pin(Gate owner) {
        this.owner = owner;
    }

    public Gate getOwner() {
        return owner;
    }
}
