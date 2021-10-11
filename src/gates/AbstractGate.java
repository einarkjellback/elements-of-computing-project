package gates;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractGate {
    public static Gate fromFunction(Function<List<Boolean>, List<Boolean>> f) {
        return new Gate() {

            @Override
            public List<Boolean> input(List<Boolean> input) {
                return f.apply(input);
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
