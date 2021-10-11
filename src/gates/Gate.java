package gates;

/**
 * This represents a physical device that behaves like some boolean function. It can take n variables as input and
 * output m variables.
 */
public interface Gate {
    boolean input(boolean b);
}
