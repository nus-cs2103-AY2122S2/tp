package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringBuilderUtil;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;

/**
 * Wrapper class for contents that are undoable in the Model
 */
public class Content {
    private final ContactList contactList;
    private final PriorityTaskList taskList;
    private final UniqueModuleList moduleList;

    /**
     * Constructor for the Content class.
     * @param contactList contact list version of the content
     * @param moduleList
     */
    public Content(ReadOnlyContactList contactList, ReadOnlyTaskList taskList, UniqueModuleList moduleList) {
        requireNonNull(contactList);
        requireNonNull(taskList);
        requireNonNull(moduleList);
        this.contactList = new ContactList(contactList);
        this.taskList = new PriorityTaskList(taskList);
        this.moduleList = new UniqueModuleList(moduleList);
    }

    /**
     * Constructor for Content class that takes in another content.
     * Used to make copies the content objects
     * @param otherContent other content to create a copy of
     */
    public static Content getContentCopy(Content otherContent) {
        return new Content(otherContent.getContactList(), otherContent.getTaskList(), otherContent.getModuleList());
    }

    /**
     * Returns the Contact List.
     */
    public ReadOnlyContactList getContactList() {
        return new ContactList(this.contactList);
    }

    /**
     * Returns the Task List.
     */
    public ReadOnlyTaskList getTaskList() {
        return new PriorityTaskList(this.taskList);
    }

    /**
     * Returns the Module List.
     */
    public UniqueModuleList getModuleList() {
        return this.moduleList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else if (other instanceof Content) { // instance of handles nulls
            Content otherContent = (Content) other;

            // checks contactList, taskLists and moduleLists are the same
            return contactList.equals(otherContent.getContactList())
                    && taskList.equals(otherContent.getTaskList())
                    && moduleList.equals(otherContent.getModuleList());
        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();
        stringBuilderUtil.appendAll("Contact List: ", contactList.toString(),
                "\nTask List: ", taskList.toString());

        return stringBuilderUtil.getFormattedOutput();
    }
}
