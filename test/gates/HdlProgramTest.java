package gates;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class HdlProgramTest {
    @Test public void
    notTest() {
        final Pin in = new Pin(null);
        final Pin out = new Pin(null);
        Gate constGate = new ConstantGate(false);
        Pin constPin = constGate.getOut(0);
        final Gate nand = new NandGate();

        Gate not = HdlProgram.getInstance()
                .setInput(in)
                .setOutput(out)
                .addPart(constGate)
                .addPart(nand)
                .connect(in, 0)
                .connect(constPin, 1)
                .connectToOut()
                .connect(nand.getOut(0))
                .build();

        assertThat(not.input(List.of(false)), is(true));
        assertThat(not.input(List.of(true)), is(false));
    }
}
