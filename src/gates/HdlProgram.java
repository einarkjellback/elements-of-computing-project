package gates;

import jdk.internal.jimage.ImageStream;

import java.util.*;

/**
 * A class that follows the builder pattern for building chips. It is supposed to emulate Hardware Description
 * Language syntax used in the specification of chips.
 */
public final class HdlProgram implements SetInput, SetOutput, AddPart, ConnectToOut {

    private final List<Pin> input = new ArrayList<>();
    private final Collection<Pin> output = new ArrayList<>();
    private final Deque<Gate> parts = new ArrayDeque<>();
    private Map<Pin, Pin> connections = new HashMap<>();

    private HdlProgram() {}

    public static SetInput getInstance() {
        return new HdlProgram();
    }

    @Override
    public SetOutput setInput(Pin... inputPins) {
        Collections.addAll(input, inputPins);
        return this;
    }

    @Override
    public AddPart setOutput(Pin... outputPins) {
        Collections.addAll(this.output, outputPins);
        return this;
    }

    @Override
    public AddPart addPart(Gate gate) {
        this.parts.addFirst(gate);
        return this;
    }

    @Override
    public AddPart connect(Pin from, int pinIndex) {
        Pin currentPin = parts.peekFirst().getIn(pinIndex);
        this.connections.put(from, currentPin);
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
