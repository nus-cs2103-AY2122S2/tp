package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.common.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;



/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateNameDifferentCase_throwsCommandException() {
        //eg Cannot add a friend called alex yeoh if Alex Yeoh already exists in Amigos
        Person personInList = model.getAddressBook().getPersonList().get(0);
        Person duplicatePerson = new PersonBuilder(personInList).withName(personInList.getName()
                .fullName.toUpperCase()).build();

        assertCommandFailure(new AddCommand(duplicatePerson), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }


    @Test
    public void execute_duplicateNameSomeOtherFieldsDifferent_throwsCommandException() {

        Person person = new Person(new FriendName("Peter Pan"), new Phone("97875337"),
                new Email("abc@gmail.com"), new Address("Neverland"), new Description("Likes to fly!"),
                new HashSet<>(), new ArrayList<>());

        model.addPerson(person);

        //even if some fields are different, as long as the name is the same, an error will be thrown

        //address different
        Person duplicatePerson = new PersonBuilder(person).withAddress(null).build();
        assertFalse(duplicatePerson.getAddress().equals(person.getAddress()));
        assertCommandFailure(new AddCommand(duplicatePerson), model, AddCommand.MESSAGE_DUPLICATE_PERSON);

        //phone and email different
        duplicatePerson = new PersonBuilder(person).withEmail(null).withPhone(null).build();
        assertFalse(duplicatePerson.getEmail().equals(person.getEmail()));
        assertFalse(duplicatePerson.getPhone().equals(person.getPhone()));
        assertCommandFailure(new AddCommand(duplicatePerson), model, AddCommand.MESSAGE_DUPLICATE_PERSON);

        //description different
        duplicatePerson = new PersonBuilder(person).withDescription("Test").build();
        assertFalse(duplicatePerson.getDescription().equals(person.getDescription()));
        assertCommandFailure(new AddCommand(duplicatePerson), model, AddCommand.MESSAGE_DUPLICATE_PERSON);

    }

    @Test
    public void execute_allFieldsSameExceptName_success() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        Person personToAdd = new PersonBuilder(personInList).withName("Max").build();

        assertFalse(personToAdd.getName().equals(personInList.getName()));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(personToAdd);

        assertCommandSuccess(new AddCommand(personToAdd), model,
                String.format(AddCommand.MESSAGE_SUCCESS, personToAdd), expectedModel);

    }

}
