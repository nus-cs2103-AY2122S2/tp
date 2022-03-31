package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.position.Position.MESSAGE_CONSTRAINTS;
import static seedu.address.model.position.PositionOpenings.isValidNumber;

import seedu.address.model.ImmutableCounter;

/**
 * Represents the number of offers for a Position in HireLah.
 * Guarantees: immutable; is always initialized as 0 {@link #PositionOffers()}
 */
public class PositionOffers implements ImmutableCounter {

    private Integer numOfOffers;

    /**
     * Constructs a PositionOffers with a count of 0
     */
    public PositionOffers() {
        numOfOffers = 0;
    }

    /**
     * Constructs a {@code PositionOffers}
     * Constructor is used internally to increment or decrement the counter
     */
    private PositionOffers(Integer offers) {
        requireNonNull(offers);
        numOfOffers = offers;
    }

    /**
     * Constructs a {@code PositionOpenings}
     * @param offers A valid non-negative integer that is between 1 and 3 digits
     */
    public PositionOffers(String offers) {
        requireNonNull(offers);
        checkArgument(isValidNumber(offers), MESSAGE_CONSTRAINTS);
        numOfOffers = Integer.parseInt(offers);
    }

    @Override
    public PositionOffers increment() {
        return new PositionOffers(numOfOffers + 1);
    }

    @Override
    public PositionOffers decrement() {
        assert numOfOffers > 0;
        return new PositionOffers(numOfOffers - 1);
    }

    @Override
    public Integer getCount() {
        return numOfOffers;
    }

    @Override
    public String toString() {
        return numOfOffers.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionOffers // instanceof handles nulls
                && numOfOffers.equals(((PositionOffers) other).numOfOffers)); // state check
    }

    @Override
    public int hashCode() {
        return numOfOffers.hashCode();
    }
}
