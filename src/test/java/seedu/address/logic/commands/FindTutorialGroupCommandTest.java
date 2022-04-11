package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.GEORGE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorialgroup.TutorialGroupKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTutorialGroupCommand}.
 */
public class FindTutorialGroupCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TutorialGroupKeywordsPredicate firstPredicate = new TutorialGroupKeywordsPredicate("CS2103 W13-2");
        TutorialGroupKeywordsPredicate secondPredicate = new TutorialGroupKeywordsPredicate("CS2106 T02");

        FindTutorialGroupCommand findFirstTutorialGroupCommand = new FindTutorialGroupCommand(firstPredicate);
        FindTutorialGroupCommand findSecondTutorialGroupCommand = new FindTutorialGroupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstTutorialGroupCommand.equals(findFirstTutorialGroupCommand));

        // same values -> returns true
        FindTutorialGroupCommand findFirstTutorialGroupCommandCopy = new FindTutorialGroupCommand(firstPredicate);
        assertTrue(findFirstTutorialGroupCommand.equals(findFirstTutorialGroupCommandCopy));

        // different types -> returns false
        assertFalse(findFirstTutorialGroupCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstTutorialGroupCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstTutorialGroupCommand.equals(findSecondTutorialGroupCommand));

    }

    @Test
    public void execute_tutorialGroupNotFound_noStudentList() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        TutorialGroupKeywordsPredicate predicate = preparePredicate("MA1000 A08");
        FindTutorialGroupCommand command = new FindTutorialGroupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_tutorialGroupFound_multipleStudentsList() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 5);
        TutorialGroupKeywordsPredicate predicate = preparePredicate("CS2106 T02");
        FindTutorialGroupCommand command = new FindTutorialGroupCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, ELLE, FIONA, GEORGE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code TutorialGroupKeywordsPredicate}.
     */
    private TutorialGroupKeywordsPredicate preparePredicate(String userInput) {
        return new TutorialGroupKeywordsPredicate(userInput);
    }

}
