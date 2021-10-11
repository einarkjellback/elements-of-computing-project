package gates;

public class ConstantGate implements Gate {
    public ConstantGate(boolean signal) {
    }

    @Override
    public boolean input(boolean b) {
        return false;
    }
}
