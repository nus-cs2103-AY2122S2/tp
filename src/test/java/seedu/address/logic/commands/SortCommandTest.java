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
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
class SortCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SkillSet skillSet1 = new SkillSet();
        SkillSet skillSet2 = new SkillSet();

        Skill firstSkill = new Skill("Java");
        Skill secondSkill = new Skill("C");

        skillSet1.add(firstSkill);
        skillSet2.add(secondSkill);

        PersonContainsSkillPredicate firstPredicate = new PersonContainsSkillPredicate(skillSet1);
        PersonContainsSkillPredicate secondPredicate = new PersonContainsSkillPredicate(skillSet2);

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
        SkillSet invalidSkillSet = new SkillSet();
        Skill invalidSkill = new Skill("abcdef");
        invalidSkillSet.add(invalidSkill);

        PersonContainsSkillPredicate predicate = new PersonContainsSkillPredicate(invalidSkillSet);
        PersonBySkillProficiencyComparator comp = new PersonBySkillProficiencyComparator(invalidSkill);
        SortCommand command = new SortCommand(predicate, comp);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        expectedModel.updateDisplayPersonList(predicate, comp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayPersonList());
    }

    @Test
    public void execute_validSkill_filtersAndSortsDisplayPersonList() {
        SkillSet skillSet = new SkillSet();
        Skill skill = new Skill("C");
        skillSet.add(skill);

        PersonContainsSkillPredicate predicate = new PersonContainsSkillPredicate(skillSet);
        PersonBySkillProficiencyComparator comp = new PersonBySkillProficiencyComparator(skill);
        SortCommand command = new SortCommand(predicate, comp);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        expectedModel.updateDisplayPersonList(predicate, comp);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE, ALICE), model.getDisplayPersonList());
    }
}
