package seedu.address.logic;

import static java.util.Objects.requireNonNull;

public class SortArgument {

    public final String argument;

    /**
     * Constructs a {@code SortArgument}.
     *
     * @param argument A valid argument.
     */
    public SortArgument(String argument) {
        requireNonNull(argument);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return argument;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortArgument // instanceof handles nulls
                && argument.equals(((SortArgument) other).argument)); // state check
    }

    @Override
    public int hashCode() {
        return argument.hashCode();
    }
}
