package gates;

public interface ConnectToOut {
    ConnectToOut connect(Pin from, Pin out);

    Gate build();
}
