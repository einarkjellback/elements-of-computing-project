package gates;

public class ConstantGate implements Gate {
    public ConstantGate(boolean signal) {
    }

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
        return null;
    }
}
