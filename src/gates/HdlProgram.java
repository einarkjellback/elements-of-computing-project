package gates;

/**
 * A class that follows the builder pattern for building chips. It is supposed to emulate Hardware Description
 * Language syntax used in the specification of chips.
 */
public final class HdlProgram implements SetInput, SetOutput, AddPart, ConnectToOut {

    private HdlProgram() {}

    public static SetInput getInstance() {
        return new HdlProgram();
    }

    @Override
    public SetOutput setInput(Pin... inputPins) {
        return this;
    }

    @Override
    public AddPart setOutput(Pin... outputPins) {
        return this;
    }

    @Override
    public AddPart addPart(Gate gate) {
        return this;
    }

    @Override
    public AddPart connect(Pin from, int pinIndex) {
        return this;
    }

    @Override
    public ConnectToOut connectToOut() {
        return this;
    }

    @Override
    public ConnectToOut connect(Pin from, Pin out) {
        return this;
    }

    @Override
    public Gate build() {
        return new Gate() {
            @Override
            public boolean input(boolean b) {
                return false;
            }

            @Override
            public Pin getOut(int i) {
                return null;
            }
        };
    }
}
