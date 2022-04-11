package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalContactList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import seedu.address.model.ContactList;
import seedu.address.model.Content;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;

/**
 * A utility class to help with building Content objects
 */
public class ContentBuilder {
    public static final ContactList DEFAULT_CONTACT_LIST = getTypicalContactList();
    public static final PriorityTaskList DEFAULT_PRIORITY_TASK_LIST = getTypicalTaskList();

    private Content content;

    /**
     * Creates a Content object with empty contact list and task list.
     */
    public ContentBuilder() {
        this.content = new Content(new ContactList(), new PriorityTaskList(), new UniqueModuleList());
    }

    public ContentBuilder(ContactList contactList, PriorityTaskList taskList) {
        this.content = new Content(contactList, taskList, new UniqueModuleList());
    }

    public Content build() {
        return content;
    }
}
