package seedu.ibook.model;

import seedu.ibook.model.exceptions.AtLatestStateException;
import seedu.ibook.model.exceptions.AtOldestStateException;

/**
 * An IBook that can be reversed to previous state as well as restored to previously undone state.
 */
public class ReversibleIBook extends IBook {

    /**
     * Creates an ReversibleIBook using the Products in the {@code toBeCopied}
     */
    public ReversibleIBook(ReadOnlyIBook toBeCopied) {
        super(toBeCopied);
    }

    //// iBook state tracking operations

    /**
     * Reverts the state of IBook to previous state.
     * @throws AtOldestStateException when current state is already oldest.
     */
    public void toPreviousState() throws AtOldestStateException {

    }

    /**
     * Restores the state of IBook to next state.
     * @throws AtLatestStateException when current state is already latest.
     */
    public void toNextState() throws AtLatestStateException {

    }
}
