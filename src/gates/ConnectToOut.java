package gates;

public interface ConnectToOut {
    ConnectToOut connect(Pin from);

    Gate build();
}
