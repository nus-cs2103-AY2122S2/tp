package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.LogName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Adds a log to a person in the address book.
 */
public class AddLogCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "addlog";
    public static final String COMMAND_ALIAS = "al";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " / " + COMMAND_ALIAS
            + ": Adds a log to an existing friend in Amigos. "
            + "Parameters: "
            + "INDEX ? " + PREFIX_NAME + "NAME "
            + PREFIX_TITLE + "TITLE"
            + " [" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_TITLE + "Likes apples";

    public static final String MESSAGE_ADD_LOG_SUCCESS = "New log added!";
    public static final String MESSAGE_DUPLICATE_LOG = "This log already exists for this friend.";

    private final Index index;
    private final Name nameToAddLog;
    private final AddLogDescriptor addLogDescriptor;
    private final boolean byName;

    /**
     * Creates an AddLogCommand to add the specified {@code Log} to the
     * specified {@code Person}.
     */
    public AddLogCommand(Index index, AddLogDescriptor addLogDescriptor) {
        requireAllNonNull(index, addLogDescriptor);
        this.index = index;
        this.nameToAddLog = null;
        this.addLogDescriptor = addLogDescriptor;
        this.byName = false;
    }

    /**
     * Creates an AddLogCommand to add the specified {@code Log} to the specified
     * {@code Person}.
     */
    public AddLogCommand(FriendName name, AddLogDescriptor addLogDescriptor) {
        requireAllNonNull(name, addLogDescriptor);
        this.nameToAddLog = name;
        this.index = null;
        this.addLogDescriptor = addLogDescriptor;
        this.byName = true;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit;

        if (this.byName) {
            personToEdit = this.getPersonByName(model, this.nameToAddLog);
        } else {
            personToEdit = this.getPersonByFilteredIndex(model, this.index);
        }

        // create person with added logs
        Person addedLogPerson = createAddedLogPerson(personToEdit, this.addLogDescriptor);

        // add to address book
        model.setPerson(personToEdit, addedLogPerson);
        return new CommandResult(MESSAGE_ADD_LOG_SUCCESS);
    }

    /**
     * Creates a {@code Person} with the details of {@code personToEdit}, with logs modified by
     * {@code addLogDescriptor}.
     *
     * @throws CommandException if {@code addLogDescriptor} results in an invalid {@code Log}
     *                          being created.
     */
    private static Person createAddedLogPerson(Person personToEdit, AddLogDescriptor addLogDescriptor)
            throws CommandException {
        requireAllNonNull(personToEdit, addLogDescriptor);
        FriendName name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Description description = personToEdit.getDescription();
        Set<Tag> tags = personToEdit.getTags();
        List<Log> updatedLogs = addLogDescriptor.getLogsAfterAdd(personToEdit); // main logic encompassed here
        return new Person(name, phone, email, address, description, tags, updatedLogs);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLogCommand)) {
            return false;
        }

        // cast
        AddLogCommand a = (AddLogCommand) other;

        // compare descriptors
        if (!this.addLogDescriptor.equals(a.addLogDescriptor)) {
            return false;
        }

        // compare name or index
        if ((this.byName) && (a.byName)) {
            assert ((this.index == null) && (a.index == null));
            return this.nameToAddLog.equals(a.nameToAddLog);

        } else if ((!this.byName) && (!a.byName)) {
            assert ((this.nameToAddLog == null) && (a.nameToAddLog == null));
            return this.index.equals(a.index);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Index: " + this.index.toString() + "\nContent:\n" + this.addLogDescriptor.toString();
    }

    /**
     * Stores the details of the edited log to add a person's logs with, as well as the person's
     * original details.
     */
    public static class AddLogDescriptor {

        private LogName newTitle;
        private Description newDescription;

        /**
         * Constructs a new {@code AddLogDescriptor} object.
         */
        public AddLogDescriptor() {
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
         * Returns true if title has been edited.
         */
        public boolean isTitleEdited() {
            return this.newTitle != null;
        }

        /**
         * Returns a list of {@code Log} objects that include the {@code Person}'s original logs
         * as well as the new logs.
         */
        public List<Log> getLogsAfterAdd(Person personToEdit) throws CommandException {

            // sanity checks
            assert (this.newTitle != null);

            Log toAdd = new Log(this.newTitle, this.newDescription); // create log to be added
            if (personToEdit.containsLog(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_LOG); // ensure not a duplicate log being inserted
            }

            List<Log> newLogs = new ArrayList<>(personToEdit.getLogs());
            newLogs.add(toAdd); // add log

            return newLogs;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof
            if (!(other instanceof AddLogDescriptor)) {
                return false;
            }

            // cast and check by wrapping into log object
            AddLogDescriptor a = (AddLogDescriptor) other;
            Log thisLog = new Log(this.newTitle, this.newDescription);
            Log otherLog = new Log(a.newTitle, a.newDescription);
            return thisLog.equals(otherLog);
        }

        @Override
        public String toString() {
            return "Title: " + this.newTitle + "\nDescription: \n" + this.newDescription;
        }
    }


}
