package seedu.ibook.commons.core;

public interface Distinguishable<T> {
    boolean isSame(T other);
}
