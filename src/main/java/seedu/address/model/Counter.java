package seedu.address.model;

/**
 * API for an object that keeps track of an internal counter.
 */
public interface Counter {
    /**
     * Increases an internal counter.
     */
    void increment();

    /**
     * Decreases an internal country.
     */
    void decrement();

    /**
     * Returns an integer that represents the value of the current state of the counter.
     */
    Integer getCount();
}
