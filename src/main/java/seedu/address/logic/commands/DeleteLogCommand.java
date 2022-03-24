package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DeleteLogCommandParser;
import seedu.address.model.Model;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes a log from a person in the address book.
 */
public class DeleteLogCommand extends ByIndexByNameCommand {

    public static final String COMMAND_WORD = "deletelog";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a log from an existing friend in Amigos. "
            + "Parameters: "
            + "[INDEX ? " + PREFIX_NAME + "NAME] ["
            + PREFIX_LOG_INDEX + "LOG_INDEX]"
            + " [" + FLAG_ALL + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_LOG_INDEX + "2";

    public static final String MESSAGE_DELETE_LOG_SUCCESS = "Log deleted.";
    public static final String MESSAGE_LOG_NOT_FOUND = "The specified log does not exist!";

    // data fields
    private final DeleteLogDescriptor descriptor;

    /**
     * Creates a {@code DeleteLogCommand} object.
     */
    public DeleteLogCommand(DeleteLogDescriptor descriptor) {
        requireNonNull(descriptor);
        this.descriptor = descriptor;
    }

    /**
     * Creates a {@code DeleteLogCommand} object.
     */
    public DeleteLogCommand(boolean isForOnePerson, boolean isForDeletingAllLogs,
                            Index personIndex, Index logIndex) {
        requireAllNonNull(isForOnePerson, isForDeletingAllLogs);
        this.descriptor = new DeleteLogDescriptor(isForOnePerson,
                isForDeletingAllLogs, personIndex, null, logIndex, false);
    }

    /**
     * Creates a {@code DeleteLogCommand} object.
     */
    public DeleteLogCommand(boolean isForOnePerson, boolean isForDeletingAllLogs,
                            FriendName personName, Index logIndex) {
        requireAllNonNull(isForOnePerson, isForDeletingAllLogs);
        this.descriptor = new DeleteLogDescriptor(isForOnePerson,
                isForDeletingAllLogs, null, personName, logIndex, true);
    }

    /**
     * Creates a {@code DeleteLogCommand} object, specifically one for
     * deleting all logs of all persons.
     */
    public DeleteLogCommand(boolean isForDeletingAllLogs) {
        assert (isForDeletingAllLogs);
        this.descriptor = new DeleteLogDescriptor(false,
                true, null, null, null, false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // apply delete to model
        return this.descriptor.applyDelete(model);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteLogCommand)) {
            return false;
        }

        // cast
        DeleteLogCommand d = (DeleteLogCommand) other;
        return this.descriptor.equals(d.descriptor);
    }

    @Override
    public String toString() {
        return this.descriptor.toString();
    }

    /**
     * Stores the details of the nature of deletion, whether it is to
     * delete a specific log, all logs of a person or all logs of all persons.
     */
    public static class DeleteLogDescriptor {

        private final boolean isForOnePerson;
        private final boolean isForDeletingAllLogs;
        private final Index personIndex;
        private final Name nameToDeleteLog;
        private final Index logIndex;
        private final boolean byName;

        /**
         * Creates a {@code} DeleteLogDescriptor} object that wraps the details of deletion.
         */
        private DeleteLogDescriptor(boolean isForOnePerson, boolean isForDeletingAllLogs,
                                    Index personIndex, FriendName name, Index logIndex, boolean byName) {
            this.isForOnePerson = isForOnePerson;
            this.isForDeletingAllLogs = isForDeletingAllLogs;
            this.personIndex = personIndex;
            this.nameToDeleteLog = isNull(name) ? null : name;
            this.logIndex = logIndex;
            this.byName = byName;

            // sanity checks
            assert (!(this.personIndex != null && this.nameToDeleteLog != null)); // cannot be overdefined
            if (!this.byName) {
                assert (((logIndex == null
                        || (!isForDeletingAllLogs && isForOnePerson && personIndex != null)))
                        && ((!isForOnePerson && personIndex == null)
                        || isForOnePerson && personIndex != null));

            } else {
                assert (((logIndex == null
                        || (!isForDeletingAllLogs && isForOnePerson && nameToDeleteLog != null)))
                        && ((!isForOnePerson && nameToDeleteLog == null)
                        || isForOnePerson && nameToDeleteLog != null));
            }
        }

        /**
         * Applies the deletion logic to the model and returns as
         * {@code CommandResult} object.
         *
         * @throws CommandException if invalid data state encountered.
         */
        public CommandResult applyDelete(Model model) throws CommandException {

            CommandResult result;

            if (isForOnePerson && !isForDeletingAllLogs) {
                // case 1: delete specific log of specific person
                result = this.deleteSpecificPersonLog(model);

            } else if (isForOnePerson && isForDeletingAllLogs) {
                // case 2: delete all logs of specific person
                result = this.deleteAllLogsOfPerson(model);

            } else if (!isForOnePerson && isForDeletingAllLogs) {
                // case 3: delete all logs all persons
                result = this.deleteAllLogs(model);

            } else {
                throw new CommandException(DeleteLogCommandParser.MESSAGE_INVALID_FORMAT);
            }
            return result;
        }

        /**
         * Deletes all {@code Logs} objects of all persons in the address book.
         **/
        public CommandResult deleteAllLogs(Model model) {

            // sanity check
            assert (this.isForDeletingAllLogs && !this.isForOnePerson
                    && this.personIndex == null && this.logIndex == null);

            // get all persons and delete all
            List<Person> allPersonsList = model.getAddressBook().getPersonList();
            for (Person person : allPersonsList) {
                Person editedPerson = copyPersonButWithEmptyLogs(person);
                model.setPerson(person, editedPerson);
            }
            return new CommandResult(MESSAGE_DELETE_LOG_SUCCESS);
        }

        private Person getPersonToEdit(Model model) throws CommandException {

            // sanity checks
            assert (this.personIndex != null || this.nameToDeleteLog != null);

            // get person to edit
            Person personToEdit = null;

            if (this.byName) {
                // sanity check
                requireNonNull(this.nameToDeleteLog);
                personToEdit = DeleteLogCommand.getPersonByName(model, this.nameToDeleteLog);

            } else {
                // sanity check
                requireNonNull(this.personIndex);

                personToEdit = DeleteLogCommand.getPersonByFilteredIndex(model, this.personIndex);
            }
            // another sanity check
            requireNonNull(personToEdit);
            return personToEdit;
        }

        /**
         * Carries out deletion logic for deletion of all {@code Log} objects of a specified person.
         *
         * @throws CommandException if person specified is not in address book.
         */
        public CommandResult deleteAllLogsOfPerson(Model model) throws CommandException {

            // sanity checks
            assert (this.isForDeletingAllLogs && this.isForOnePerson && this.logIndex == null);
            assert (this.personIndex != null || this.nameToDeleteLog != null);

            // ===== GET PERSON =====

            Person personToEdit = this.getPersonToEdit(model);
            Person deletedLogsPerson = copyPersonButWithEmptyLogs(personToEdit);

            // add to address book
            model.setPerson(personToEdit, deletedLogsPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(MESSAGE_DELETE_LOG_SUCCESS);

        }

        /**
         * Returns a {@code Person} object that is identical except without any logs.
         * A helper method.
         */
        public static Person copyPersonButWithEmptyLogs(Person personToEdit) {
            requireAllNonNull(personToEdit);
            FriendName name = personToEdit.getName();
            Phone phone = personToEdit.getPhone();
            Email email = personToEdit.getEmail();
            Address address = personToEdit.getAddress();
            Description description = personToEdit.getDescription();
            Set<Tag> tags = personToEdit.getTags();
            List<Log> emptyLogs = new ArrayList<>(); // main logic encompassed here
            return new Person(name, phone, email, address, description, tags, emptyLogs);
        }

        /**
         * Deletes a specific {@code Log} of a specified person.
         *
         * @throws CommandException if {@code Person} or {@code Log} is not found.
         */
        public CommandResult deleteSpecificPersonLog(Model model) throws CommandException {

            // sanity checks
            requireNonNull(model);
            assert (this.isForOnePerson
                    && !this.isForDeletingAllLogs
                    && this.logIndex != null);

            assert (!((this.personIndex == null && this.nameToDeleteLog == null)
                    || (this.personIndex != null && this.nameToDeleteLog != null)));

            // ===== GET PERSON =====
            Person personToEdit = this.getPersonToEdit(model);

            Person deletedLogPerson = createdDeletedLogPerson(personToEdit, this.logIndex);

            // add to address book
            model.setPerson(personToEdit, deletedLogPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_DELETE_LOG_SUCCESS);
        }

        /**
         * Creates a {@code Person} with the details of {@code personToEdit}, with log equal to
         * {@code toDelete} removed.
         *
         * @throws CommandException if {@code toDelete} does not exist in the logs of {@code personToEdit}
         */
        public static Person createdDeletedLogPerson(Person personToEdit, Index toDelete) throws CommandException {
            requireAllNonNull(personToEdit, toDelete);
            FriendName name = personToEdit.getName();
            Phone phone = personToEdit.getPhone();
            Email email = personToEdit.getEmail();
            Address address = personToEdit.getAddress();
            Set<Tag> tags = personToEdit.getTags();
            Description description = personToEdit.getDescription();
            List<Log> updatedLogs = getLogsAfterDelete(personToEdit, toDelete); // main logic encompassed here
            return new Person(name, phone, email, address, description, tags, updatedLogs);
        }

        /**
         * Returns a list of {@code Log} objects that include the {@code Person}'s original logs
         * less the specified {@code Log} to be deleted.
         */
        public static List<Log> getLogsAfterDelete(Person personToEdit, Index toDeleteIndex) throws CommandException {
            requireAllNonNull(personToEdit, toDeleteIndex);

            // check that safe to remove
            List<Log> logs = new ArrayList<Log>(personToEdit.getLogs());
            if (toDeleteIndex.getZeroBased() >= logs.size()) {
                throw new CommandException(MESSAGE_LOG_NOT_FOUND);
            }

            // remove by index
            logs.remove(toDeleteIndex.getZeroBased());

            return logs;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteLogDescriptor)) {
                return false;
            }

            // cast
            DeleteLogDescriptor d = (DeleteLogDescriptor) other;

            // person index must be same
            boolean isSamePerson = bothNullOrEqual(this.personIndex, d.personIndex);

            // log index must be same
            boolean isSameLog = bothNullOrEqual(this.logIndex, d.logIndex);

            // person to delete must be same
            boolean isSamePersonByName = bothNullOrEqual(this.nameToDeleteLog, d.nameToDeleteLog);

            // remaining must be same
            return (isSameLog && isSamePerson && isSamePersonByName
                    && this.isForOnePerson == d.isForOnePerson
                    && this.isForDeletingAllLogs == d.isForDeletingAllLogs);
        }

        private static boolean bothNullOrEqual(Object propertyOfOne, Object propertyOfOther) {
            if ((propertyOfOne == null && propertyOfOther != null)
                    || propertyOfOne != null && propertyOfOther == null) {
                return false;
            }
            return (propertyOfOne == null && propertyOfOther == null
                    || propertyOfOne.equals(propertyOfOther));

        }

        @Override
        public String toString() {
            return "For one person: " + this.isForOnePerson
                    + "\nFor all logs: " + this.isForDeletingAllLogs
                    + "\nBy Name: " + this.byName
                    + "\nPerson with name: " + this.nameToDeleteLog
                    + "\nPerson index: " + this.personIndex
                    + "\nLog Index: " + this.logIndex;
        }
    }

}





