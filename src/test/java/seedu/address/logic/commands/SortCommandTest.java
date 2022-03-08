package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonBySkillProficiencyComparator;
import seedu.address.model.person.PersonContainsSkillPredicate;
import seedu.address.model.tag.Skill;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
class SortCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Skill firstSkill = new Skill("Java");
        Skill secondSkill = new Skill("C");

        PersonContainsSkillPredicate firstPredicate = new PersonContainsSkillPredicate(firstSkill);
        PersonContainsSkillPredicate secondPredicate = new PersonContainsSkillPredicate(secondSkill);

        PersonBySkillProficiencyComparator firstComp = new PersonBySkillProficiencyComparator(firstSkill);
        PersonBySkillProficiencyComparator secondComp = new PersonBySkillProficiencyComparator(secondSkill);

        SortCommand sortCommand = new SortCommand(firstPredicate, firstComp);

        // same object -> returns true
        assertTrue(sortCommand.equals(sortCommand));

        // different types -> returns false
        assertFalse(sortCommand.equals(1));

        // same predicate and comparator -> returns true
        assertTrue(new SortCommand(firstPredicate, firstComp).equals(sortCommand));

        // diff predicate but same comparator -> returns false
        assertFalse(new SortCommand(secondPredicate, firstComp).equals(sortCommand));

        // same predicate but diff comparator -> returns false
        assertFalse(new SortCommand(firstPredicate, secondComp).equals(sortCommand));

        // diff predicate and diff comparator -> returns false
        assertFalse(new SortCommand(secondPredicate, secondComp).equals(sortCommand));
    }

    @Test
    public void execute_invalidSkill_noPersonFound() {
        Skill invalidSkill = new Skill("abcdef");
        PersonContainsSkillPredicate predicate = new PersonContainsSkillPredicate(invalidSkill);
        PersonBySkillProficiencyComparator comp = new PersonBySkillProficiencyComparator(invalidSkill);
        SortCommand command = new SortCommand(predicate, comp);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        expectedModel.updateDisplayPersonList(predicate, comp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayPersonList());
    }

    @Test
    public void execute_validSkill_filtersAndSortsDisplayPersonList() {
        Skill skill = new Skill("C");
        PersonContainsSkillPredicate predicate = new PersonContainsSkillPredicate(skill);
        PersonBySkillProficiencyComparator comp = new PersonBySkillProficiencyComparator(skill);
        SortCommand command = new SortCommand(predicate, comp);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.updateDisplayPersonList(predicate, comp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE, ALICE), model.getDisplayPersonList());
    }
}
