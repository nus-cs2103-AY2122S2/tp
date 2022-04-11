package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
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
    public void execute_duplicatePersonWithThreeNonUniqueAttributes_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Phone, Email and Matriculation Number"));
    }

    @Test
    public void execute_duplicatePersonWithTwoNonUniqueAttributes_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        PersonBuilder personBuilder = new PersonBuilder(personInList);

        String differentPhone = personInList.getPhone() + "1";
        String differentEmail = personInList.getEmail() + "m";
        String matriculationNumber = personInList.getMatriculationNumber().value;
        String differentMatriculationNumber = matriculationNumber.charAt(0)
                + (Integer.parseInt(matriculationNumber.substring(1, 2) + 1) % 10
                + matriculationNumber.substring(2));

        // case 1: non-unique phone and email (unique matriculation number)
        personBuilder.withMatriculationNumber(differentMatriculationNumber);
        Person personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Phone and Email"));

        // case 2: non-unique phone and matriculation number (unique email)
        personBuilder = new PersonBuilder(personInList);
        personBuilder.withEmail(differentEmail);
        personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Phone and Matriculation Number"));

        // case 2: non-unique email and matriculation number (unique phone)
        personBuilder = new PersonBuilder(personInList);
        personBuilder.withPhone(differentPhone);
        personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Email and Matriculation Number"));
    }

    @Test
    public void execute_duplicatePersonWithTwoUniquePhoneAttribute_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        PersonBuilder personBuilder = new PersonBuilder(personInList);

        String differentEmail = personInList.getEmail() + "m";
        String matriculationNumber = personInList.getMatriculationNumber().value;
        String differentMatriculationNumber = matriculationNumber.charAt(0)
                + (Integer.parseInt(matriculationNumber.substring(1, 2) + 1) % 10
                + matriculationNumber.substring(2));

        personBuilder.withEmail(differentEmail).withMatriculationNumber(differentMatriculationNumber);
        Person personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Phone"));
    }

    @Test
    public void execute_duplicatePersonWithTwoUniqueEmailAttribute_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        PersonBuilder personBuilder = new PersonBuilder(personInList);

        String differentPhone = personInList.getPhone() + "1";
        String matriculationNumber = personInList.getMatriculationNumber().value;
        String differentMatriculationNumber = matriculationNumber.charAt(0)
                + (Integer.parseInt(matriculationNumber.substring(1, 2) + 1) % 10
                + matriculationNumber.substring(2));

        personBuilder.withPhone(differentPhone).withMatriculationNumber(differentMatriculationNumber);
        Person personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Email"));
    }

    @Test
    public void execute_duplicatePersonWithTwoUniqueMatriculationNumberAttribute_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        PersonBuilder personBuilder = new PersonBuilder(personInList);

        String differentPhone = personInList.getPhone() + "1";
        String differentEmail = personInList.getEmail() + "m";

        personBuilder.withPhone(differentPhone).withEmail(differentEmail);
        Person personToTest = personBuilder.build();
        assertCommandFailure(new AddCommand(personToTest), model,
                String.format(AddCommand.MESSAGE_DUPLICATE_PERSON, "Matriculation Number"));
    }

}
