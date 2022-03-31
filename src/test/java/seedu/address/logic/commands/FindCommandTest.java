package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.JAMES;
import static seedu.address.testutil.TypicalPersons.LAUREN;
import static seedu.address.testutil.TypicalPersons.MAVIS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FriendFilterPredicate;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.LogName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FriendFilterPredicateBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        HashSet<FriendName> friendNamesOne = new HashSet<>();
        friendNamesOne.add(new FriendName("One"));

        HashSet<FriendName> friendNamesTwo = new HashSet<>();
        friendNamesTwo.add(new FriendName("Two"));

        FriendFilterPredicate firstPredicate =
                new FriendFilterPredicate(friendNamesOne, new HashSet<LogName>(), new HashSet<Tag>());
        FriendFilterPredicate secondPredicate =
                new FriendFilterPredicate(friendNamesTwo, new HashSet<LogName>(), new HashSet<Tag>());

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Generates a FindCommand and updates the person list based on the predicate.
     */
    public FindCommand generateFindCommandAndFilterList(FriendFilterPredicate predicate) {
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        return command;
    }


    @Test
    public void execute_multipleNameSubstrings_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withNameSubstring("Kurz").withNameSubstring("Meyer").withNameSubstring("Kunz").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleTag_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withTagSubstring("owesmoney").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withTagSubstring("owesmoney").withTagSubstring("friends").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleLogSubstring_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withLogTitleSubstring("2013").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JAMES, MAVIS), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleLogSubstrings_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withLogTitleSubstring("2013").withLogTitleSubstring("birthday").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JAMES, LAUREN, MAVIS), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameTagLogCombination_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 6);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withNameSubstring("Benson").withTagSubstring("friends").withLogTitleSubstring("2013")
                .withLogTitleSubstring("birthday").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, JAMES, LAUREN, MAVIS), model.getFilteredPersonList());
    }

    @Test
    public void execute_caseInsensitiveMatches_success() {

        //input 'benson' matches to a person named Benson Meier
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommand command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withNameSubstring("benson").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());

        //input 'FRIENDS' matches to tag 'friends'
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withTagSubstring("FRIENDS").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());

        //input 'birThDaY' matches to log title 'birthday coming up'
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        command = generateFindCommandAndFilterList((new FriendFilterPredicateBuilder()
                .withLogTitleSubstring("birThDay").build()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LAUREN, MAVIS), model.getFilteredPersonList());
    }

}
