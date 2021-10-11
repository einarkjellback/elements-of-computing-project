package gates;

public interface AddPart {
    AddPart addPart(Gate gate);

    AddPart connect(Pin from, int pinIndex);

    AddPart connect(boolean constantSignal, int pinIndex);

    ConnectToOut connectToOut();
}
