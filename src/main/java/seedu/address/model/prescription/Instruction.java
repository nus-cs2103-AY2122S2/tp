package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Instruction {

    public static final String MESSAGE_CONSTRAINTS = "Instruction can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    public Instruction(String instruction) {
        requireNonNull(instruction);
        checkArgument(isValidInstruction(instruction), MESSAGE_CONSTRAINTS);
        value = instruction;
    }

    public static boolean isValidInstruction(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Instruction // instanceof handles nulls
                && value.equals(((Instruction) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
