package seedu.address.model;

/**
 * API for an object that keeps track of an internal counter.
 */
public interface ImmutableCounter {
    /**
     * Increases an internal counter.
     */
    ImmutableCounter increment();

    /**
     * Decreases an internal country.
     */
    ImmutableCounter decrement();

    /**
     * Returns an integer that represents the value of the current state of the counter.
     */
    Integer getCount();
}
