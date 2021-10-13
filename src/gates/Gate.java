package gates;

import java.util.List;

/**
 * This represents a physical device that behaves like some boolean function. It can take n variables as input and
 * output m variables.
 */
public interface Gate {
    List<Boolean> input(List<Boolean> b);

    Pin getOut(int i);

    Pin getIn(int i);

    List<Pin> allOutPins();

    List<Pin> allInPins();
}
