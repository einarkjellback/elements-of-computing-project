package gates;

import java.util.*;
import java.util.function.Function;

public abstract class AbstractGate implements Gate {
    private final int outputDim;
    private final List<Pin> in;

    private AbstractGate(int inputDim, int outputDim) {
        this.outputDim = outputDim;
        in = new ArrayList<>(inputDim);
        for (int i = 0; i < inputDim; i++) {
            in.add(new Pin());
        }
    }

    @Override
    public Pin getIn(int i) {
        return this.in.get(i);
    }

    @Override
    public Pin getOut(int i) {
        Objects.checkIndex(i, outputDim);
        return null;
    }

    public static Gate fromFunction(int inputDim, int outputDim, Function<List<Boolean>, List<Boolean>> f) {
        return new AbstractGate(inputDim, outputDim) {
            @Override
            public List<Boolean> input(List<Boolean> input) {
                if (input.size() != inputDim) {
                    throw new IllegalArgumentException("Expected input size "
                            + inputDim + ", but was " + input.size());
                }

                List<Boolean> output = f.apply(input);

                if (output.size() != outputDim) {
                    throw new IllegalArgumentException("Expected list of size "
                            + outputDim + " from function " + f + ", but was " + output.size());
                }
                return output;
            }
        };
    }

    public static Gate fromMap(Map<Pin, List<Pin>> connections) {
        return null;
    }

    public static Gate nand() {
        return fromFunction(2, 1, booleans -> List.of(!(booleans.get(0) && booleans.get(1))));
    }

    public static Gate not() {
        return fromFunction(1, 1, booleans -> List.of(!booleans.get(0)));
    }

    public static Gate constantGate(boolean constant) {
        return fromFunction(0, 1, booleans -> List.of(constant));
    }
}
