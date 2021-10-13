package gates;

import java.util.List;

public class NandGate implements Gate {
    @Override
    public List<Boolean> input(List<Boolean> b) {
        return List.of(false);
    }

    @Override
    public Pin getOut(int i) {
        return null;
    }

    @Override
    public Pin getIn(int i) {
        return new Pin(null);
    }
}
