package gates;

public class NandGate implements Gate {
    @Override
    public boolean input(boolean b) {
        return false;
    }

    @Override
    public Pin getOut(int i) {
        return null;
    }

    @Override
    public Pin getIn(int i) {
        return new Pin();
    }
}
