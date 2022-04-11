package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalContactList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import seedu.address.model.Content;

/**
 * A utility class containing typical {@code Content} objects to be used in tests.
 */
public class TypicalContents {
    private TypicalContents() {} // no instantiation of this class should be done

    /**
     * Returns a typical Content object
     */
    public static Content getTypicalContent() {
        return new ContentBuilder(getTypicalContactList(), getTypicalTaskList()).build();
    }
}
