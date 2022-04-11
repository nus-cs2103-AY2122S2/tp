package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG2;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.EVE;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StudentBuilder;

public class StudentHasTutorialNamePredicateTest {

    @Test
    public void equals() {
        StudentHasTutorialNamePredicate predFirst = new StudentHasTutorialNamePredicate(
                new TutorialName(VALID_TUTORIAL_NAME_TG1));
        StudentHasTutorialNamePredicate predSecond = new StudentHasTutorialNamePredicate(
                new TutorialName(VALID_TUTORIAL_NAME_TG2));

        // same object -> returns true
        assertTrue(predFirst.equals(predFirst));

        // same values -> returns true
        StudentHasTutorialNamePredicate predFirstCopy = new StudentHasTutorialNamePredicate(
                new TutorialName(VALID_TUTORIAL_NAME_TG1));
        assertTrue(predFirst.equals(predFirstCopy));

        // different types -> returns false
        assertFalse(predFirst.equals(1));

        // null -> returns false
        assertFalse(predFirst.equals(null));

        // different pred -> returns false
        assertFalse(predFirst.equals(predSecond));

    }

    @Test
    public void test() {
        StudentHasTutorialNamePredicate pred = new StudentHasTutorialNamePredicate(new TutorialName("TG01"));
        Person person = new PersonBuilder(ALICE).build();
        Student student = new StudentBuilder(ALEX).build();
        Student studentDifferentTutName = new StudentBuilder(EVE).build();

        // student - same tutorial name -> returns true
        assertTrue(pred.test(student));

        // student - different tutorial name -> returns false
        assertFalse(pred.test(studentDifferentTutName));

        // person -> returns false
        assertFalse(pred.test(person));

    }

}
