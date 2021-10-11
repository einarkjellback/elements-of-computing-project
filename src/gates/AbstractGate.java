package gates;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractGate {
    public static Gate fromFunction(int inputDimension, int outputDimension, Function<List<Boolean>, List<Boolean>> f) {
        return new Gate() {
            @Override
            public List<Boolean> input(List<Boolean> input) {
                if (input.size() != inputDimension) {
                    throw new IllegalArgumentException("Expected input size "
                            + inputDimension + ", but was " + input.size());
                }
                List<Boolean> output = f.apply(input);
                if (output.size() != outputDimension) {
                    throw new IllegalArgumentException("Expected list of size "
                            + outputDimension + " from function " + f + ", but was " + output.size());
                }
                return output;
            }

            @Override
            public Pin getOut(int i) {
                return null;
            }

            @Override
            public Pin getIn(int i) {
                return null;
            }
        };
    }
}
