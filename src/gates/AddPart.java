package gates;

public interface AddPart {
    AddPart addPart(Gate gate);

    AddPart connect(Pin from, int pinIndex);

    ConnectToOut connectToOut();
}
