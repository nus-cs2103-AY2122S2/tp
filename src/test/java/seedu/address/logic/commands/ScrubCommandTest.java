package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ScrubPersonPredicate;

class ScrubCommandTest {

    @Test
    void execute_partialEmail() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 7);
        String partialEmailDescription = " e/@example";
        ArgumentMultimap partialEmailDescriptor = ArgumentTokenizer.tokenize(partialEmailDescription, ARRAY_OF_PREFIX);
        testExecuteMethod(partialEmailDescriptor, expectedMessage);
    }

    @Test
    void execute_fullEmail() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 7);
        String fullEmailDescription = " e/@example.com";
        ArgumentMultimap fullEmailDescriptor = ArgumentTokenizer.tokenize(fullEmailDescription, ARRAY_OF_PREFIX);
        testExecuteMethod(fullEmailDescriptor, expectedMessage);
    }

    @Test
    void execute_singlePhone() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 1);
        String phoneDescription = " p/94351253";
        ArgumentMultimap phoneDescriptor = ArgumentTokenizer.tokenize(phoneDescription, ARRAY_OF_PREFIX);
        testExecuteMethod(phoneDescriptor, expectedMessage);
    }

    private void testExecuteMethod(ArgumentMultimap phoneDescriptor, String expectedMessage) {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ScrubPersonPredicate predicate = new ScrubPersonPredicate(phoneDescriptor);
        ScrubCommand command = new ScrubCommand(predicate);
        deletePersonProtocol(expectedModel, predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    private void deletePersonProtocol(Model expectedModel, ScrubPersonPredicate predicate) {
        expectedModel.updateFilteredPersonList(predicate);
        ObservableList<Person> personQueueForDeletion = expectedModel.getFilteredPersonList();
        expectedModel.deleteAllPerson(personQueueForDeletion);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.saveAddressBookState();
    }

    @Test
    void execute_multiplePhone() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 2);
        String multiplePhoneDescription = " p/94351253 p/98765432";
        ArgumentMultimap multiplePhoneDescriptor = ArgumentTokenizer
                .tokenize(multiplePhoneDescription, ARRAY_OF_PREFIX);
        testExecuteMethod(multiplePhoneDescriptor, expectedMessage);
    }

    @Test
    void execute_partialPhone_noDeletion() {
        String expectedMessage = Messages.MESSAGE_EMPTY_SEARCH;
        String partialPhone = " p/9435";
        ArgumentMultimap partialPhoneDescriptor = ArgumentTokenizer.tokenize(partialPhone, ARRAY_OF_PREFIX);
        testExecuteMethod(partialPhoneDescriptor, expectedMessage);
    }

    @Test
    void testEquals() {
        String description = " e/@example";
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(description, ARRAY_OF_PREFIX);
        ScrubPersonPredicate predicate = new ScrubPersonPredicate(argumentMultimap);
        ScrubCommand scrubCommand = new ScrubCommand(predicate);
        assertEquals(scrubCommand, new ScrubCommand(predicate));
    }
}
