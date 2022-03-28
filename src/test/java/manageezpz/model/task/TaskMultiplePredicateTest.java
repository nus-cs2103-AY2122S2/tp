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

import org.junit.jupiter.api.Test;

import manageezpz.testutil.DeadlineBuilder;

class TaskMultiplePredicateTest {
    private static final String userInput = "Play Genshin Impact";
    private static final Deadline deadline = new DeadlineBuilder().withDescription(userInput)
            .withDate("2022-04-01").withTime("0000").build();

    @Test
    void taskMultiplePredicate_equalThisObject_true() {
        TaskMultiplePredicate predicateWithNoOptions = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, null, null,
                null);

        // This -> true
        assertTrue(predicateWithNoOptions.equals(predicateWithNoOptions));
    }

    @Test
    void taskMultiplePredicate_equalTaskTypes() {
        TaskMultiplePredicate predicateTaskType = new TaskMultiplePredicate(List.of(PREFIX_DEADLINE), null,
                null, null, null, null);
        TaskMultiplePredicate predicateTaskTypeSame = new TaskMultiplePredicate(List.of(PREFIX_DEADLINE),
                null, null, null, null, null);
        TaskMultiplePredicate predicateTaskTypeDifferent = new TaskMultiplePredicate(List.of(PREFIX_EVENT),
                null, null, null, null, null);

        // Same TaskType -> true
        assertTrue(predicateTaskType.equals(predicateTaskTypeSame));

        // Different TaskType -> false
        assertFalse(predicateTaskType.equals(predicateTaskTypeDifferent));
    }

    @Test
    void taskMultiplePredicate_equalDescription() {
        TaskMultiplePredicate predicateDescription = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, List.of("Word1"), null, null, null,
                null);
        TaskMultiplePredicate predicateDescriptionSame = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, List.of("Word1"), null, null, null,
                null);
        TaskMultiplePredicate predicateDescriptionDifferent = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, List.of("Word2"), null, null, null,
                null);

        // Same Description -> true
        assertTrue(predicateDescription.equals(predicateDescriptionSame));

        // Different Description -> false
        assertFalse(predicateDescription.equals(predicateDescriptionDifferent));
    }

    @Test
    void taskMultiplePredicate_equalDate() {
        TaskMultiplePredicate predicateDate = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, new Date("1900-01-01"), null, null, null);
        TaskMultiplePredicate predicateDateSame = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, new Date("1900-01-01"), null, null, null);
        TaskMultiplePredicate predicateDateDifferent = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, new Date("1900-01-02"), null,
                null, null);

        // Same date -> true
        assertTrue(predicateDate.equals(predicateDateSame));

        // Different date -> false
        assertFalse(predicateDate.equals(predicateDateDifferent));
    }

    @Test
    void taskMultiplePredicate_equalPriority() {
        TaskMultiplePredicate predicatePriority = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, Priority.HIGH, null, null);
        TaskMultiplePredicate predicatePrioritySame = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, Priority.HIGH, null,
                null);
        TaskMultiplePredicate predicatePriorityDifferent = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, Priority.LOW, null,
                null);

        // Same priority -> true
        assertTrue(predicatePriority.equals(predicatePrioritySame));

        // Different priority -> false
        assertFalse(predicatePriority.equals(predicatePriorityDifferent));
    }

    @Test
    void taskMultiplePredicate_equalAssignee() {
        TaskMultiplePredicate predicateAssignee = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, ALICE.getName().toString(), null);
        TaskMultiplePredicate predicateAssigneeSame = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, null,
                ALICE.getName().toString(), null);
        TaskMultiplePredicate predicateAssigneeDifferent = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, null,
                BENSON.getName().toString(), null);

        // Same person -> true
        assertTrue(predicateAssignee.equals(predicateAssigneeSame));

        // Different persons -> false
        assertFalse(predicateAssignee.equals(predicateAssigneeDifferent));
    }

    @Test
    void taskMultiplePredicate_equalIsMarked() {
        TaskMultiplePredicate predicateIsMarked = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, Boolean.TRUE);
        TaskMultiplePredicate predicateIsMarkedSame = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, null, null,
                Boolean.TRUE);
        TaskMultiplePredicate predicateIsMarkedDifferent = new TaskMultiplePredicate(
                TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE, null, null, null, null,
                Boolean.FALSE);

        // Same isMarked Boolean -> true
        assertTrue(predicateIsMarked.equals(predicateIsMarkedSame));

        // Different isMarked Boolean -> false
        assertFalse(predicateIsMarked.equals(predicateIsMarkedDifferent));
    }

    @Test
    void taskMultiplePredicate_containsTaskType_true() {
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(List.of(PREFIX_DEADLINE), null,
                null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(List.of(PREFIX_TODO, PREFIX_DEADLINE),
                null, null, null, null, null);
        assertTrue(predicateCombo2.test(deadline));

        TaskMultiplePredicate predicateCombo3 = new TaskMultiplePredicate(List.of(PREFIX_TODO, PREFIX_DEADLINE,
                PREFIX_EVENT), null, null, null, null, null);
        assertTrue(predicateCombo3.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsTaskType_false() {
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(List.of(PREFIX_TODO), null,
                null, null, null, null);
        assertFalse(predicateCombo1.test(deadline));

        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(List.of(PREFIX_EVENT, PREFIX_TODO),
                null, null, null, null, null);
        assertFalse(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDescription_true() {
        // Description not specified
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // Only 1 word
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                List.of("Play"), null, null, null, null);
        assertTrue(predicateCombo2.test(deadline));

        // More than 1 word
        TaskMultiplePredicate predicateCombo3 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                List.of("Genshin", "Impact"), null, null, null, null);
        assertTrue(predicateCombo3.test(deadline));

        // Mixed cases
        TaskMultiplePredicate predicateCombo4 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                List.of("GeNshIn", "IMPact"), null, null, null, null);
        assertTrue(predicateCombo4.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDescription_false() {
        // None of the words found
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                List.of("P1ay"), null, null, null, null);
        assertFalse(predicateCombo1.test(deadline));

        // No keywords same despite all other same
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(List.of(PREFIX_DEADLINE),
                List.of("P1ay"), new Date("2022-04-01"), Priority.NONE, null, Boolean.FALSE);
        assertFalse(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDate_true() {
        // null date
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // Same date
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, new Date("2022-04-01"), null, null, null);
        assertTrue(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsDate_false() {
        // Wrong date
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, new Date("2022-04-02"), null, null, null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsPriority_true() {
        deadline.setPriority("HIGH");
        // Priority null
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // Same priority
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, Priority.HIGH, null, null);
        assertTrue(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsPriority_false() {
        deadline.setPriority("HIGH");

        // Different priority
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, Priority.LOW, null, null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsAssignee_true() {
        deadline.addAssignees(ALICE);
        deadline.addAssignees(BENSON);
        deadline.addAssignees(CARL);

        // Assignee null
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // Same assignee
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, ALICE.getName().toString(), null);
        assertTrue(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsAssignee_false() {
        deadline.addAssignees(ALICE);
        deadline.addAssignees(BENSON);
        deadline.addAssignees(CARL);

        // Different assignee
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, DANIEL.getName().toString(), null);
        assertFalse(predicateCombo1.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsIsMarked_true() {
        deadline.setTaskDone();

        // Is Marked is null
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, null);
        assertTrue(predicateCombo1.test(deadline));

        // Is Marked is the same
        TaskMultiplePredicate predicateCombo2 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, Boolean.TRUE);
        assertTrue(predicateCombo2.test(deadline));
    }

    @Test
    void taskMultiplePredicate_containsIsMarked_false() {
        deadline.setTaskDone();

        // Is Marked boolean different
        TaskMultiplePredicate predicateCombo1 = new TaskMultiplePredicate(TaskMultiplePredicate.NO_SPECIFIC_TASK_TYPE,
                null, null, null, null, Boolean.FALSE);
        assertFalse(predicateCombo1.test(deadline));
    }
}