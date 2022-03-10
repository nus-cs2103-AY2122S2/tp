package seedu.address.commons.core;

/**
 * Represents a pair of two objects of type {@code T}.
 */
public class Pair<T> {

    private final T first;
    private final T second;

    /**
     * Creates a Pair object.
     */
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
