package manageezpz.model.task;

import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;
import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalPersons.BENSON;
import static manageezpz.testutil.TypicalPersons.CARL;
import static manageezpz.testutil.TypicalPersons.DANIEL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manageezpz.testutil.DeadlineBuilder;

class TaskMultiplePredicateTest {
    private static final String userInput = "Play Genshin Impact";
    private Deadline deadline;

    @BeforeEach
    void setDeadline() {
        deadline = new DeadlineBuilder().withDescription(userInput).withDate("2022-04-01").withTime("0000").build();
    }

    // Testing equal method
    @Test
    void taskMultiplePredicate_equalThisObject_true() {
        TaskMultiplePredicate predicate1 = new TaskMultiplePredicate(PREFIX_TODO, null, null,
                null, null, null);
        assertTrue(predicate1.equals(predicate1));
    }

    @Test
    void taskMultiplePredicate_equalSomeOtherObject_false() {
        Object otherObject = new Object();
        TaskMultiplePredicate predicateWithNoOptions = new TaskMultiplePredicate(PREFIX_TODO, null,
                null, null, null, null);
        assertFalse(predicateWithNoOptions.equals(otherObject));
    }

    @Test
    void taskMultiplePredicate_equalTaskTypes() {
        TaskMultiplePredicate predicateTaskType = new TaskMultiplePredicate(PREFIX_DEADLINE, null,
                null, null, null, null);
        TaskMultiplePredicate predicateTaskTypeSame = new TaskMultiplePredicate(PREFIX_DEADLINE,
                null, null, null, null, null);
        TaskMultiplePredicate predicateTaskTypeDifferent = new TaskMultiplePredicate(PREFIX_EVENT,
                null, null, null, null, null);

        // Same TaskType -> true
        assertTrue(predicateTaskType.equals(predicateTaskTypeSame));

        // Different TaskType -> false
        assertFalse(predicateTaskType.equals(predicateTaskTypeDifferent));
    }

    @Test
    void taskMultiplePredicate_equalDescription() {
        TaskMultiplePredicate predicateDescription = new TaskMultiplePredicate(
                null, List.of("Word1"), null, null, null,
                null);
        TaskMultiplePredicate predicateDescriptionSame = new TaskMultiplePredicate(
                null, List.of("Word1"), null, null, null,
                null);
        TaskMultiplePredicate predicateDescriptionDifferent = new TaskMultiplePredicate(
                null, List.of("Word2"), null, null, null,
                null);

        // Same Description -> true
        assertTrue(predicateDescription.equals(predicateDescriptionSame));

        // Different Description -> false
        assertFalse(predicateDescription.equals(predicateDescriptionDifferent));
    }

    @Test
    void taskMultiplePredicate_equalDate() {
        TaskMultiplePredicate predicateDate = new TaskMultiplePredicate(null,
                null, new Date("1900-01-01"), null, null, null);
        TaskMultiplePredicate predicateDateSame = new TaskMultiplePredicate(null,
                null, new Date("1900-01-01"), null, null, null);
        TaskMultiplePredicate predicateDateDifferent = new TaskMultiplePredicate(
                null, null, new Date("1900-01-02"), null,
                null, null);

        // Same date -> true
        assertTrue(predicateDate.equals(predicateDateSame));

        // Different date -> false
        assertFalse(predicateDate.equals(predicateDateDifferent));
    }

    @Test
    void taskMultiplePredicate_equalPriority() {
        TaskMultiplePredicate predicatePriority = new TaskMultiplePredicate(null,
                null, null, Priority.HIGH, null, null);
        TaskMultiplePredicate predicatePrioritySame = new TaskMultiplePredicate(
                null, null, null, Priority.HIGH, null,
                null);
        TaskMultiplePredicate predicatePriorityDifferent = new TaskMultiplePredicate(
                null, null, null, Priority.LOW, null,
                null);

        // Same priority -> true
        assertTrue(predicatePriority.equals(predicatePrioritySame));

        // Different priority -> false
        assertFalse(predicatePriority.equals(predicatePriorityDifferent));
    }

    @Test
    void taskMultiplePredicate_equalAssignee() {
        TaskMultiplePredicate predicateAssignee = new TaskMultiplePredicate(null,
                null, null, null, ALICE.getName().toString(), null);
        TaskMultiplePredicate predicateAssigneeSame = new TaskMultiplePredicate(
                null, null, null, null,
                ALICE.getName().toString(), null);
        TaskMultiplePredicate predicateAssigneeDifferent = new TaskMultiplePredicate(
                null, null, null, null,
                BENSON.getName().toString(), null);

        // Same person -> true
        assertTrue(predicateAssignee.equals(predicateAssigneeSame));

        // Different persons -> false
        assertFalse(predicateAssignee.equals(predicateAssigneeDifferent));
    }

    @Test
    void taskMultiplePredicate_equalIsMarked() {
        TaskMultiplePredicate predicateIsMarked = new TaskMultiplePredicate(null,
                null, null, null, null, Boolean.TRUE);
        TaskMultiplePredicate predicateIsMarkedSame = new TaskMultiplePredicate(
                null, null, null, null, null,
                Boolean.TRUE);
        TaskMultiplePredicate predicateIsMarkedDifferent = new TaskMultiplePredicate(
                null, null, null, null, null,
                Boolean.FALSE);

        // Same isMarked Boolean -> true
        assertTrue(predicateIsMarked.equals(predicateIsMarkedSame));

        // Different isMarked Boolean -> false
        assertFalse(predicateIsMarked.equals(predicateIsMarkedDifferent));
    }

    // Test with task objects
    @Test
    void taskMultiplePredicate_containsSameTaskType_true() {
        // Task type same
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(PREFIX_DEADLINE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDifferentTaskType_false() {
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(PREFIX_TODO,
                null, null, null, null, null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsWordsInDescription_true() {
        // Only 1 word
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                List.of("Play"), null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // More than 1 word
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(null,
                List.of("Genshin", "Impact"), null, null, null, null);
        assertTrue(predicateCombo2.test(deadline));

        // Mixed cases
        TaskMultiplePredicate predicateCombo3 = new TaskMultiplePredicate(null,
                List.of("GeNshIn", "IMPact"), null, null, null, null);
        assertTrue(predicateCombo3.test(deadline));
    }

    @Test
    void taskMultiplePredicate_doesNotContainWordsInDescription_false() {
        // None of the words found
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                List.of("P1ay"), null, null, null, null);
        assertFalse(predicateCombo1.test(deadline));

        // No keywords same despite all other options same
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(PREFIX_DEADLINE,
                List.of("P1ay"), new Date("2022-04-01"), Priority.NONE, null, Boolean.FALSE);
        assertFalse(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsSameDate_true() {
        // Same date
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, new Date("2022-04-01"), null, null, null);
        assertTrue(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDifferentDate_false() {
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, new Date("2022-04-02"), null, null, null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsSamePriority_true() {
        deadline.setPriority("HIGH");

        // Same priority
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, Priority.HIGH, null, null);
        assertTrue(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDifferentPriority_false() {
        deadline.setPriority("HIGH");

        // Different priority
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, Priority.LOW, null, null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsAssignee_true() {
        deadline.addAssignees(ALICE);
        deadline.addAssignees(BENSON);
        deadline.addAssignees(CARL);

        // Same assignee
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, null, ALICE.getName().toString(), null);
        assertTrue(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_noContainAssignee_false() {
        deadline.addAssignees(ALICE);
        deadline.addAssignees(BENSON);
        deadline.addAssignees(CARL);

        // Different assignee
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, null, DANIEL.getName().toString(), null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsSameIsMarked_true() {
        deadline.setTaskDone();

        // Is Marked is the same
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, null, null, Boolean.TRUE);
        assertTrue(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDifferentIsMarked_false() {
        deadline.setTaskDone();

        // Is Marked boolean different
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(null,
                null, null, null, null, Boolean.FALSE);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_multipleOptions_true() {
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(PREFIX_DEADLINE,
                List.of("Genshin"), new Date("2022-04-01"), Priority.NONE, null, Boolean.FALSE);
        assertTrue(predicateCombo1.test(deadline));
    }
}
