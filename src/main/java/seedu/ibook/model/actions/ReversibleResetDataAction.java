package seedu.ibook.model.actions;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import seedu.ibook.model.IBook;
import seedu.ibook.model.ReadOnlyIBook;

public class ReversibleResetDataAction extends ReversibleIBookAction {

    private final ReadOnlyIBook newData;
    private final ReadOnlyIBook oldData;

    /**
     * Class constructor.
     * @param newData the data a new iBook is populated with.
     */
    public ReversibleResetDataAction(ReadOnlyIBook oldData, ReadOnlyIBook newData) {
        requireAllNonNull(oldData, newData);

        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.resetData(newData);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.resetData(oldData);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleResetDataAction
                    && oldData.equals(((ReversibleResetDataAction) other).oldData)
                    && newData.equals(((ReversibleResetDataAction) other).newData));
    }
}
