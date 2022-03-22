package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.lab.Lab;
import seedu.address.model.student.lab.LabStatus;
import seedu.address.model.student.lab.StudentHasLabPredicate;



public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentHasLabPredicate firstPredicate =
                new StudentHasLabPredicate((new Lab("1")).thatIs(LabStatus.GRADED));
        StudentHasLabPredicate secondPredicate =
                new StudentHasLabPredicate((new Lab("1")).thatIs(LabStatus.UNSUBMITTED));
        StudentHasLabPredicate thirdPredicate =
                new StudentHasLabPredicate((new Lab("3")).thatIs(LabStatus.GRADED));
        StudentHasLabPredicate fourthPredicate =
                new StudentHasLabPredicate((new Lab("4")).thatIs(LabStatus.SUBMITTED));

        FilterCommand firstFilterCommand = new FilterCommand(firstPredicate);
        FilterCommand secondFilterCommand = new FilterCommand(secondPredicate);
        FilterCommand thirdFilterCommand = new FilterCommand(thirdPredicate);
        FilterCommand fourthFilterCommand = new FilterCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // same labNumber different labStatus -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));

        // different labNumber same labStatus -> returns false
        assertFalse(firstFilterCommand.equals(thirdFilterCommand));

        // different labNumber different labStatus -> returns false
        assertFalse(firstFilterCommand.equals(fourthFilterCommand));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));
    }

    @Test
    public void execute_success_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 3);
        StudentHasLabPredicate predicate = preparePredicate("l/1 s/u");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL), model.getFilteredStudentList());
    }

    private StudentHasLabPredicate preparePredicate(String userInput) {
        Pattern pattern = Pattern.compile("\\s*l/(?<labNum>\\d+)\\s+s/(?<labStat>[usg])");
        Matcher matcher = pattern.matcher(userInput);
        assertTrue(matcher.matches());
        Hashtable<String, LabStatus> mapper = new Hashtable<>() { {
                put("u", LabStatus.UNSUBMITTED);
                put("s", LabStatus.SUBMITTED);
                put("g", LabStatus.GRADED);
            }};

        Lab lab = new Lab(matcher.group("labNum"));
        LabStatus labStatus = mapper.get(matcher.group("labStat"));

        return new StudentHasLabPredicate(lab.thatIs(labStatus));
    }
}
