package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.logic.LabelUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the newly added person "
            + " or an existing person being viewed in the displayed contact details list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters (Any Order):"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "%1$s's information has been updated";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final EditPersonDescriptor editPersonDescriptor;
    private final Person personToEdit;

    /**
     * @param editPersonDescriptor details to edit the person with
     * @param personToEdit         the target person
     */
    public EditCommand(EditPersonDescriptor editPersonDescriptor, Person personToEdit) {
        requireNonNull(editPersonDescriptor);
        requireNonNull(personToEdit);
        this.personToEdit = personToEdit;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = this.personToEdit;
        Person editedPerson = createUpdatedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_DETAILS);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson.getName().fullName),
                false, false, false, true, false, false,
                editedPerson);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.Note that this is different from createEditedPerson as
     * createEditedPerson replaces all HashMaps and HashSet components of Person with that of the editPersonDescriptor
     * while this adds the values of the HashMaps and HashSet components of Person with that of the
     * editedPersonDescriptor.
     */
    public static Person createUpdatedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(personToEdit);

        Id id = personToEdit.getId();
        Name updatedName = editPersonDescriptor.getName()
                .orElse(personToEdit.getName());
        Company updatedCompany = editPersonDescriptor.getCompany()
                .orElse(personToEdit.getCompany().orElse(null));
        JobTitle updatedJobTitle = editPersonDescriptor.getJobTitle()
                .orElse(personToEdit.getJobTitle().orElse(null));

        //New HashMaps are created to remove Unmodifiable Map's limitation
        Map<Label, Phone> updatedPhones = new HashMap<>(personToEdit.getNumbers());
        updatedPhones.putAll(editPersonDescriptor.getNumbers().orElse(new HashMap<>()));
        updatedPhones = LabelUtil.replacePhonePlaceholders(updatedPhones);

        Map<Label, Email> updatedEmails = new HashMap<>(personToEdit.getEmails());
        updatedEmails.putAll(editPersonDescriptor.getEmails().orElse(new HashMap<>()));
        updatedEmails = LabelUtil.replaceEmailPlaceholders(updatedEmails);

        Map<Label, Address> updatedAddresses = new HashMap<>(personToEdit.getAddresses());
        updatedAddresses.putAll(editPersonDescriptor.getAddresses().orElse(new HashMap<>()));
        updatedAddresses = LabelUtil.replaceAddressPlaceholders(updatedAddresses);

        Set<Pronoun> updatedPronouns = new HashSet<>(personToEdit.getPronouns());
        updatedPronouns.addAll(editPersonDescriptor.getPronouns().orElse(new HashSet<>()));

        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.addAll(editPersonDescriptor.getTags().orElse(new HashSet<>()));

        return new Person(id, updatedName, updatedPhones, updatedEmails, updatedAddresses,
                updatedCompany, updatedJobTitle, updatedPronouns, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return personToEdit.equals(e.personToEdit)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return "Edit " + personToEdit + ": " + editPersonDescriptor;
    }

}
