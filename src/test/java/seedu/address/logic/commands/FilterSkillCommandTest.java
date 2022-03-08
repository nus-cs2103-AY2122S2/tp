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
import seedu.address.model.person.PersonContainsSkillPredicate;
import seedu.address.model.tag.Skill;

class FilterSkillCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsSkillPredicate firstPredicate =
                new PersonContainsSkillPredicate(new Skill("skill1"));
        PersonContainsSkillPredicate secondPredicate =
                new PersonContainsSkillPredicate(new Skill("skill2"));

        FilterSkillCommand filterFirstSkillCommand = new FilterSkillCommand(firstPredicate);
        FilterSkillCommand filterSecondSkillCommand = new FilterSkillCommand(secondPredicate);


        // same object -> returns true
        assertTrue(filterFirstSkillCommand.equals(filterFirstSkillCommand));

        // same values -> returns true
        FilterSkillCommand filterFirstSkillCommandCopy = new FilterSkillCommand(firstPredicate);
        assertTrue(filterFirstSkillCommandCopy.equals(filterFirstSkillCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstSkillCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstSkillCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstSkillCommand.equals(filterSecondSkillCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsSkillPredicate predicate = preparePredicate("NONE");
        FilterSkillCommand command = new FilterSkillCommand(predicate);
        expectedModel.updateDisplayPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getDisplayPersonList());
    }

    @Test
    public void execute_singleSkill_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsSkillPredicate predicate = preparePredicate("C");
        FilterSkillCommand command = new FilterSkillCommand(predicate);
        expectedModel.updateDisplayPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, GEORGE), model.getDisplayPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonContainsSkillPredicate preparePredicate(String userInput) {
        return new PersonContainsSkillPredicate(new Skill(userInput));
    }
}
