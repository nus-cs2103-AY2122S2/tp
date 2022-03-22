package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.LogName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Edits a log of a person in the address book.
 */
public class EditLogCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "editlog";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing log "
            + "of an existing friend in Amigos. "
            + "Parameters: "
            + "INDEX ? " + PREFIX_NAME + "NAME "
            + PREFIX_LOG_INDEX + "LOG_INDEX ["
            + PREFIX_TITLE + "NEW_TITLE] ["
            + PREFIX_DESCRIPTION + "NEW DESCRIPTION]\n"
            + "Note that at least one of title and description must be provided.\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_LOG_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Likes apples";

    public static final String MESSAGE_EDIT_LOG_SUCCESS = "Log successfully edited!";
    public static final String MESSAGE_LOG_NOT_FOUND = "The specified log does not exist!";
    public static final String MESSAGE_DUPLICATE_LOG = "This log already exists for this friend.";

    // data fields
    private final Index index;
    private final Index logIndex;
    private final FriendName nameToEditLog;
    private final EditLogDescriptor editLogDescriptor;
    private final boolean byName;

    /**
     * Creates an EditLogCommand to add the specified {@code Log} to the
     * specified {@code Person}.
     */
    public EditLogCommand(Index index, Index logIndex, EditLogDescriptor editLogDescriptor) {
        requireAllNonNull(index, logIndex, editLogDescriptor);
        this.index = index;
        this.nameToEditLog = null;
        this.logIndex = logIndex;
        this.editLogDescriptor = editLogDescriptor;
        this.byName = false;
    }

    /**
     * Creates an EditLogCommand to add the specified {@code Log} to the specified
     * {@code Person}.
     */
    public EditLogCommand(FriendName name, Index logIndex, EditLogDescriptor editLogDescriptor) {
        requireAllNonNull(name, logIndex, editLogDescriptor);
        this.nameToEditLog = name;
        this.index = null;
        this.logIndex = logIndex;
        this.editLogDescriptor = editLogDescriptor;
        this.byName = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit;

        if (this.byName) {
            personToEdit = EditLogCommand.getPersonByName(model, this.nameToEditLog);
        } else {
            personToEdit = EditLogCommand.getPersonByFilteredIndex(model, this.index);
        }

        // create person with edited logs
        Person addedLogPerson = createEditedLogPerson(personToEdit, this.logIndex, this.editLogDescriptor);

        // add to address book
        model.setPerson(personToEdit, addedLogPerson);
        return new CommandResult(MESSAGE_EDIT_LOG_SUCCESS);
    }

    private static Person createEditedLogPerson(
            Person personToEdit, Index logIndex, EditLogDescriptor editLogDescriptor)
            throws CommandException {
        requireAllNonNull(personToEdit, logIndex, editLogDescriptor);
        FriendName name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Description description = personToEdit.getDescription();
        Set<Tag> tags = personToEdit.getTags();
        List<Log> updatedLogs = editLogDescriptor.getLogsAfterEdit(personToEdit, logIndex); // main logic encompassed here
        return new Person(name, phone, email, address, description, tags, updatedLogs);
    }


    @Override
    public boolean equals (Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLogCommand)) {
            return false;
        }

        // cast
        EditLogCommand e = (EditLogCommand) other;

        // compare descriptors and log index
        if (!this.editLogDescriptor.equals(e.editLogDescriptor)
            || !(this.logIndex.equals(e.logIndex))) {
            return false;
        }

        // compare name
        if ((this.byName) && (e.byName)) {
            assert ((this.index == null) && (e.index == null));
            return this.nameToEditLog.equals(e.nameToEditLog);

        } else if ((!this.byName) && (!e.byName)) {
            assert ((this.nameToEditLog == null) && (e.nameToEditLog == null));
            return this.index.equals(e.index);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Index: " + this.index.toString() + ", Log Index: " + this.logIndex.toString()
                + "\nContent:\n" + this.editLogDescriptor.toString();
    }

    /**
     * Stores the details of the edited log to edit a person's logs with, as well as the person's
     * original details.
     */
    public static class EditLogDescriptor {

        private LogName newTitle;
        private Description newDescription;

        /**
         * Constructs a new {@code AddLogDescriptor} object.
         */
        public EditLogDescriptor() {
            this.newTitle = null;
            this.newDescription = null;
        }

        public void setNewTitle(String newTitle) {
            this.newTitle = new LogName(newTitle);
        }

        public void setNewTitle(LogName newTitle) {
            this.newTitle = newTitle;
        }

        public void setNewDescription(String newDescription) {
            this.newDescription = new Description(newDescription);
        }

        public void setNewDescription(Description newDescription) {
            this.newDescription = newDescription;
        }

        /**
         * Returns true if title or description has been edited.
         */
        public boolean isEdited() {
            return this.newTitle != null || this.newDescription != null;
        }

        /**
         * Returns a list of {@code Log} objects that include the {@code Person}'s original logs
         * as well as the new logs.
         */
        public List<Log> getLogsAfterEdit(Person personToEdit, Index logIndex) throws CommandException {

            // sanity checks
            assert (this.isEdited());
            List<Log> logs = personToEdit.getLogs();
            if (logIndex.getZeroBased() >= logs.size()) {
                throw new CommandException(MESSAGE_LOG_NOT_FOUND);
            }

            // get log to edit
            Log initialLog = logs.get(logIndex.getZeroBased());
            Log editedLog = new Log(
                    this.newTitle != null ? this.newTitle : initialLog.getTitle(),
                    this.newDescription != null ? this.newDescription : initialLog.getDescription()); // create log to be added

            // another sanity check
            if (personToEdit.containsLogExactly(editedLog)) {
                throw new CommandException(MESSAGE_DUPLICATE_LOG); // ensure not a duplicate log being inserted
            }

            // set log
            List<Log> newLogs = new ArrayList<>(personToEdit.getLogs());
            newLogs.set(logIndex.getZeroBased(), editedLog);

            return newLogs;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof
            if (!(other instanceof EditLogCommand.EditLogDescriptor)) {
                return false;
            }

            // cast and check by wrapping into log object
            EditLogCommand.EditLogDescriptor e = (EditLogCommand.EditLogDescriptor) other;
            Log thisLog = new Log(this.newTitle, this.newDescription);
            Log otherLog = new Log(e.newTitle, e.newDescription);

            return thisLog.equals(otherLog);
        }

        @Override
        public String toString() {
            return "Title: " + this.newTitle + "\nDescription: \n" + this.newDescription;
        }

    }
}
