package gates;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class HdlProgramTest {
    @Test public void
    notTest() {
        final Pin in = new Pin();
        final Pin out = new Pin();
        Pin constPin = new ConstantPin(false);
        final Gate nand = new NandGate();

//        HdlProgram hdl = nand.getIn(0).receive(out);

        Gate not = HdlProgram.getInstance()
                .setInput(in)
                .setOutput(out)
                .addPart(nand)
                .connect(in, 0)
                .connect(constPin, 1)
                .connectToOut()
                .connect(constPin, out)
                .build();

        assertThat(not.input(false), is(true));
    }
}
