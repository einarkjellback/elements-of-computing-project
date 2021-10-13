package gates;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PinTest {

    @Test public void
    given_pinHasOwner_when_getOwnerInvoked_then_ownerReturned() {
        Gate gate = new Gate() {
            @Override
            public List<Boolean> input(List<Boolean> b) {
                return null;
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
        Pin pin = new Pin(gate);

        Gate actual = pin.getOwner();
        assertSame(actual, gate);
    }

}