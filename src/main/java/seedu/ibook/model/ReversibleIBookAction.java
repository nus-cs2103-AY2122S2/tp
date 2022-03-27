package seedu.ibook.model;

/**
 * Represents a reversible action of IBook.
 */
public abstract class ReversibleIBookAction {

    /**
     * Performs the forward action.
     *
     * @param iBook the {@code iBook} which the action should operate on.
     */
    public abstract void performForwardAction(IBook iBook);

    /**
     * Performs the backward action.
     *
     * @param iBook the {@code iBook} which the action should operate on.
     */
    public abstract void performBackwardAction(IBook iBook);
}
