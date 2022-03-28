package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertResponseSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SummariseCommand.
 */
public class SummariseCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsCorrectResponse() {
        assertResponseSuccess(new SummariseCommand(), model,
                SummariseCommand.MESSAGE_SUMMARISE_PERSON_FAILURE, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsCorrectResponse() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertResponseSuccess(new SummariseCommand(), model,
                SummariseCommand.MESSAGE_SUMMARISE_PERSON_FAILURE, expectedModel);
    }

    @Test
    public void filterByFaculty_onEmptyList_showsEmptyString() {
        List<Person> emptyList = new ArrayList<>();
        String actual = new SummariseCommand().filterByFaculty(emptyList);
        assertEquals(actual, "");
    }

    @Test
    public void filterByFaculty_onValidList_showsNonEmptyString() {
        List<Person> validList = model.getFilteredPersonList();
        String actual = new SummariseCommand().filterByFaculty(validList);
        assertTrue(actual.length() > 0);
    }

    @Test
    public void filterByBlock_onEmptyList_showsEmptyString() {
        List<Person> emptyList = new ArrayList<>();
        String actual = new SummariseCommand().filterByBlock(emptyList);
        assertEquals(actual, "");
    }

    @Test
    public void filterByBlock_onValidList_showsNonEmptyString() {
        List<Person> validList = model.getFilteredPersonList();
        String actual = new SummariseCommand().filterByBlock(validList);
        assertTrue(actual.length() > 0);
    }

    @Test
    public void test_summariseCommandWordIsCorrect() {
        assertTrue(SummariseCommand.COMMAND_WORD.equals("summarise"));
    }
}
