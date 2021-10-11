package gates;

/**
 * A class that follows the builder pattern for building chips. It is supposed to emulate HDL-like syntax for the
 * specification of chips.
 */
public final class HdlProgram implements SetInput, SetOutput, AddPart, ConnectToOut {

    private HdlProgram() {}

    public static SetInput getInstance() {
        return new HdlProgram();
    }

    @Override
    public SetOutput setInput(Pin... inputPins) {
        return null;
    }

    @Override
    public AddPart setOutput(Pin... outputPins) {
        return null;
    }

    @Override
    public AddPart addPart(Gate gate) {
        return null;
    }

    @Override
    public AddPart connect(Pin from, int pinIndex) {
        return null;
    }

    @Override
    public AddPart connect(boolean constantSignal, int pinIndex) {
        return null;
    }

    @Override
    public ConnectToOut connectToOut() {
        return null;
    }

    @Override
    public ConnectToOut connect(Pin from, Pin out) {
        return null;
    }

    @Override
    public Gate build() {
        return null;
    }
}
