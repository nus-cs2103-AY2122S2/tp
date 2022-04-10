package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERTYPE_BUYER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;
import seedu.address.model.userimage.UserImage;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredAndSortedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Index indexLastPerson = Index.fromOneBased(model.getFilteredAndSortedPersonList().size());
        Index indexSecondLastPerson = Index.fromOneBased(model.getFilteredAndSortedPersonList().size() - 1);
        Person lastPerson = model.getFilteredAndSortedPersonList().get(indexSecondLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withUserType(VALID_USERTYPE_BUYER).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withUserType(VALID_USERTYPE_BUYER).withPreference(VALID_PREFERENCE_ALICE)
                .build();
        EditCommand editCommand = new EditCommand(indexSecondLastPerson, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredAndSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredAndSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                        .withPreference(VALID_PREFERENCE_ALICE).build());

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredAndSortedPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredAndSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAndSortedPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editBuyerToSeller_success() {
        // editing a buyer to be a seller (editing the buyer to have a property)
        Model separateModel = new ModelManager(getAddressBook(), new UserPrefs());

        Index indexFirstPerson = INDEX_FIRST_PERSON;

        Person personInList = separateModel.getAddressBook().getPersonList().get(indexFirstPerson.getZeroBased());

        Property property = new Property(Region.fromString("East"),
                new Address("John street block 123 #01-01"), Size.fromString("2-room"), new Price("$200000"));

        Person editedPerson = new Person(personInList.getName(), personInList.getPhone(), personInList.getEmail(),
                new Favourite(true), personInList.getAddress(), new HashSet<>(Arrays.asList(property)),
                Optional.empty(), new UserType("seller"), new HashSet<UserImage>());

        EditCommand editCommand = new EditCommand(indexFirstPerson,
                new EditPersonDescriptorBuilder()
                        .withProperties("East,John street block 123 #01-01,2-room,$200000").build());

        Model expectedModel = new ModelManager(new AddressBook(separateModel.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(separateModel.getFilteredAndSortedPersonList().get(0), editedPerson);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, separateModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editSellerToBuyer_success() {
        // editing a seller to be a buyer (editing the seller to have a preference)
        Model separateModel = new ModelManager(getAddressBook(), new UserPrefs());

        Index indexOfSeller = Index.fromOneBased(8);

        Person personInList = separateModel.getAddressBook().getPersonList().get(indexOfSeller.getZeroBased());

        Optional<Preference> preference = Optional.of(new Preference(
                Region.fromString("East"), Size.fromString("2-room"),
                new Price("$100000"), new Price("$200000")));

        Person editedPerson = new Person(personInList.getName(), personInList.getPhone(), personInList.getEmail(),
                personInList.getAddress(), new HashSet<Property>(), preference,
                new UserType("buyer"), new HashSet<UserImage>());

        EditCommand editCommand = new EditCommand(indexOfSeller,
                new EditPersonDescriptorBuilder().withPreference("East,2-room,$100000,$200000").build());

        Model expectedModel = new ModelManager(new AddressBook(separateModel.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(separateModel.getFilteredAndSortedPersonList().get(7), editedPerson);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, separateModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editSellerProperty_success() {
        // editing other details of the seller (editing the seller's phone number)
        Model separateModel = new ModelManager(getAddressBook(), new UserPrefs());
        Index indexOfSeller = Index.fromOneBased(8);

        Person personInList = separateModel.getAddressBook().getPersonList().get(indexOfSeller.getZeroBased());

        Property property = new Property(Region.fromString("East"),
                new Address("John street block 123 #01-01"), Size.fromString("2-room"), new Price("$200000"));

        Person editedPerson = new Person(personInList.getName(), personInList.getPhone(), personInList.getEmail(),
                personInList.getAddress(), new HashSet<>(Arrays.asList(property)), Optional.empty(),
                personInList.getUserType(), new HashSet<UserImage>());

        EditCommand editCommand = new EditCommand(indexOfSeller, new EditPersonDescriptorBuilder()
                .withProperties("East,John street block 123 #01-01,2-room,$200000").build());

        Model expectedModel = new ModelManager(new AddressBook(separateModel.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(separateModel.getFilteredAndSortedPersonList().get(7), editedPerson);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, separateModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editSellerOtherDetails_success() {
        // editing other details of the seller (editing the seller's phone number)
        Model separateModel = new ModelManager(getAddressBook(), new UserPrefs());
        Index indexOfSeller = Index.fromOneBased(8);

        Person personInList = separateModel.getAddressBook().getPersonList().get(indexOfSeller.getZeroBased());

        Phone phone = new Phone("98765432");

        Person editedPerson = new Person(personInList.getName(), phone, personInList.getEmail(),
                personInList.getAddress(), personInList.getProperties(), personInList.getPreference(),
                    personInList.getUserType(), new HashSet<UserImage>());

        EditCommand editCommand = new EditCommand(indexOfSeller,
                new EditPersonDescriptorBuilder().withPhone("98765432").build());

        Model expectedModel = new ModelManager(new AddressBook(separateModel.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(separateModel.getFilteredAndSortedPersonList().get(7), editedPerson);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(editCommand, separateModel, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
