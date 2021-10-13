package gates;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class GateTest {
    @Test
    public void
    test_fromFunctionConstructor() {
        List<List<Boolean>> inputs = List.of(
                        List.of(true, false, false),
                        List.of(false, false, false),
                        List.of(true, true, true)
        );
        List<TestCase> cases = List.of(
                new TestCase(
                        "Constant false signal",
                        booleans -> List.of(false),
                        List.of(
                                List.of(false),
                                List.of(false),
                                List.of(false)
                        )
                ),
                new TestCase(
                        "Logical Not",
                        booleans -> booleans.stream().map(b -> !b).collect(Collectors.toList()),
                        List.of(
                                List.of(false, true, true),
                                List.of(true, true, true),
                                List.of(false, false, false)
                        )
                ),
                new TestCase(
                        "Accumulative And",
                        booleans -> List.of(booleans.stream().reduce(true, Boolean::logicalAnd)),
                        List.of(
                                List.of(false),
                                List.of(false),
                                List.of(true)
                        )
                ),
                new TestCase(
                        "Set first to true",
                        booleans -> {
                            List<Boolean> bCopy = new ArrayList<>(booleans);
                            bCopy.set(0, true);
                            return bCopy;
                        },
                        List.of(
                                List.of(true, false, false),
                                List.of(true, false, false),
                                List.of(true, true, true)
                        )
                )
        );

        for (TestCase c : cases) {
            Gate gate = AbstractGate.fromFunction(3, c.expected.get(0).size(), c.f);
            for (int i = 0; i < inputs.size(); i++) {
                List<Boolean> actual = gate.input(inputs.get(i));
                assertThat("Failed case: " + c.name, actual, is(c.expected.get(i)));
            }
        }
    }

    @Test public void
    given_nInputPins_when_inputArgumentNotNDimensions_then_throwException() {
        Gate gate = AbstractGate.fromFunction(3, 3, b -> b);
        assertThrows(IllegalArgumentException.class, () -> gate.input(List.of(true, true)));
    }

    @Test public void
    given_nOutputPins_when_functionReturnsListNotOfSizeN_then_throwsException() {
        List<Integer> outputDim = List.of(1, 2, 3);
        for (int dim : outputDim) {
            Gate gate = AbstractGate.fromFunction(4, dim, b -> b);
            assertThrows(IllegalArgumentException.class, () -> gate.input(List.of(true, true, false, true)));
        }
    }

    @Test public void
    test_nandGate() {
        List<List<Boolean>> inputs = List.of(
                List.of(true, true),
                List.of(true, false),
                List.of(false, true),
                List.of(false, false)
        );
        List<Boolean> expected = List.of(
                false, true, true, true
        );
        Gate nand = AbstractGate.nand();
        for (int i = 0; i < inputs.size(); i++) {
            assertThat(nand.input(inputs.get(i)), is(List.of(expected.get(i))));
        }
    }

    @Test public void
    test_notGate() {
        List<Boolean> inputs = List.of(true, false);
        List<Boolean> expected = List.of(false, true);
        Gate not = AbstractGate.not();

        for (int i = 0; i < inputs.size(); i++) {
            List<Boolean> actual = not.input(List.of(inputs.get(i)));

            assertThat(actual, is(List.of(expected.get(i))));
        }
    }

    @Test public void
    test_constantGate() {
        List<Boolean> constants = List.of(true, false);
        for (boolean constant : constants) {
            Gate gate = AbstractGate.constantGate(constant);
            List<Boolean> actual = gate.input(List.of());

            assertThat(actual, is(List.of(constant)));
        }
    }

    @Test public void
    given_invalidIndex_when_getOutInvoked_then_exceptionThrown() {
        final int outputDim = 3;
        Gate gate = AbstractGate.fromFunction(2, outputDim, booleans -> List.of());

        assertThrows(IndexOutOfBoundsException.class, () -> gate.getOut(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> gate.getOut(outputDim));
    }

    @Test public void
    given_invalidIndex_when_getInInvoked_then_exceptionThrown() {
        final int outputDim = 3;
        Gate gate = AbstractGate.fromFunction(2, outputDim, booleans -> List.of());

        assertThrows(IndexOutOfBoundsException.class, () -> gate.getIn(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> gate.getIn(outputDim));
    }

    @Test public void
    given_mapOfConnectedGates_when_inputInvoked_then_returnCorrectBoolean() {
        Map<Pin, List<Pin>> connections = new HashMap<>();

        Gate constGate = AbstractGate.constantGate(false);
        Gate nand = AbstractGate.nand();

        Pin a = nand.getIn(1);
        Pin falseSignal = constGate.getOut(0);

        connections.put(falseSignal, List.of(a));

        Gate not = AbstractGate.fromMap(connections);

        List<Boolean> inputs = List.of(true, false);
        List<Boolean> expected = List.of(false, true);

        for (int i = 0; i < inputs.size(); i++) {
            List<Boolean> actual = not.input(List.of(inputs.get(i)));

            assertThat(actual, is(List.of(expected.get(i))));
        }
    }

    private static class TestCase {
        private final String name;
        private final Function<List<Boolean>, List<Boolean>> f;
        private final List<List<Boolean>> expected;

        TestCase(String name, Function<List<Boolean>, List<Boolean>> f, List<List<Boolean>> expected) {
            this.name = name;
            this.f = f;
            this.expected = expected;
        }
    }
}
