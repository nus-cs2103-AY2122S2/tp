package seedu.address.model.position;

import seedu.address.model.Counter;

/**
 * Represents the number of offers for a Position in HireLah.
 * Guarantees: immutable; is always initialized as 0 {@link #PositionOffers()}
 */
public class PositionOffers implements Counter {

    private Integer numOfOffers;

    /**
     * Constructs a PositionOffers with a count of 0
     */
    public PositionOffers() {
        numOfOffers = 0;
    }

    @Override
    public void increment() {
        numOfOffers++;
    }

    @Override
    public void decrement() {
        assert numOfOffers > 0;
        numOfOffers--;
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
