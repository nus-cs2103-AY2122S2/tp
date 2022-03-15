package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;


/**
 * Deletes the specified field from the contact being displayed.
 */
public class DeleteFieldCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified field from the contact being displayed."
            + "If a label is provided, deletes information for only that label.\n"
            + "Parameters: FIELD [LABEL]\n"
            + "Example: " + COMMAND_WORD + " c/\n"
            + "Example: " + COMMAND_WORD + " ph/ personal";

    public static final String MESSAGE_DELETE_FIELD_SUCCESS = "Person after Field Delete: %1$s";

    public static final String MESSAGE_DELETE_NAME_FAILURE = "Name cannot be deleted";

    private final EditPersonDescriptor deleteFieldDescriptor;
    private final Person personToDeleteField;

    /**
     * Creates a command to facilitate deletion of fields in contact information.
     *
     * @param deleteFieldDescriptor EditPersonDescriptor.
     * @param personToDeleteField Person.
     */
    public DeleteFieldCommand(EditPersonDescriptor deleteFieldDescriptor, Person personToDeleteField) {
        requireNonNull(deleteFieldDescriptor);
        requireNonNull(personToDeleteField);
        this.personToDeleteField = personToDeleteField;
        this.deleteFieldDescriptor = deleteFieldDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDeleteField = this.personToDeleteField;
        Person updatedPerson = DeleteFieldCommand.createUpdatedPerson(personToDeleteField, deleteFieldDescriptor);

        if (!personToDeleteField.isSamePerson(updatedPerson) && model.hasPerson(updatedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToDeleteField, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS, updatedPerson), false, false,
                false, true, false, updatedPerson);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof DeleteFieldCommand)) {
            return false;
        }

        return personToDeleteField.equals(((DeleteFieldCommand) other).personToDeleteField)
                && deleteFieldDescriptor.equals(((DeleteFieldCommand) other).deleteFieldDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToDeleteField}
     * edited with {@code deleteFieldDescriptor}.Note that this is different from createEditedPerson as
     * createEditedPerson replaces all HashMaps and HashSet components of Person with that of the deleteFieldDescriptor
     * while this adds the values of the HashMaps and HashSet components of Person with that of the
     * editedPersonDescriptor.
     */
    public static Person createUpdatedPerson(Person personToDeleteField, EditPersonDescriptor deleteFieldDescriptor) {
        requireNonNull(personToDeleteField);

        Name updatedName = deleteFieldDescriptor.getName().orElse(null);
        Company updatedCompany = deleteFieldDescriptor.getCompany().orElse(null);
        JobTitle updatedJobTitle = deleteFieldDescriptor.getJobTitle().orElse(null);

        Map<String, Phone> updatedPhones =
                new HashMap<>(deleteFieldDescriptor.getNumbers().orElse(new HashMap<>()));

        Map<String, Email> updatedEmails =
                new HashMap<>(deleteFieldDescriptor.getEmails().orElse(new HashMap<>()));

        Map<String, Address> updatedAddresses =
                new HashMap<>(deleteFieldDescriptor.getAddresses().orElse(new HashMap<>()));

        Set<Pronoun> updatedPronouns =
                new HashSet<>(deleteFieldDescriptor.getPronouns().orElse(new HashSet<>()));

        Set<Tag> updatedTags =
                new HashSet<>(deleteFieldDescriptor.getTags().orElse(new HashSet<>()));

        return new Person(updatedName, updatedPhones, updatedEmails, updatedAddresses,
                updatedCompany, updatedJobTitle, updatedPronouns, updatedTags);
    }
}



