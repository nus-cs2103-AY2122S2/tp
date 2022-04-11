package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_OWESMONEY;
import static seedu.address.testutil.TypicalTags.VALID_TAG_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWESMONEY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void equals() {
        List<String> firstKeywords = new ArrayList<>();
        firstKeywords.add("first");
        List<String> secondKeywords = new ArrayList<>();
        secondKeywords.add("second");

        FindTagCommand findFirstCommand = new FindTagCommand(firstKeywords);
        FindTagCommand findSecondCommand = new FindTagCommand(secondKeywords);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTagCommand findFirstCommandCopy = new FindTagCommand(firstKeywords);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_singleKeywordNoActivatedTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        model.addTag(VALID_TAG_COLLEAGUES);
        List<String> singleKeyword = new ArrayList<>();
        singleKeyword.add(VALID_TAGNAME_COLLEAGUES);
        FindTagCommand command = new FindTagCommand(singleKeyword);
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(singleKeyword);
        expectedModel.addTag(VALID_TAG_COLLEAGUES);
        expectedModel.addActivatedTag(VALID_TAG_COLLEAGUES);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroKeywordsNoActivatedTags_throwsCommandException() {
        String expectedMessage = FindTagCommand.MESSAGE_USAGE;
        List<String> emptyKeywords = new ArrayList<>();
        FindTagCommand command = new FindTagCommand(emptyKeywords);
        assertCommandFailure(command, model, expectedMessage);

    }

    @Test
    public void execute_singleKeywordNoActivatedTags_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<String> singleKeyword = new ArrayList<>();
        singleKeyword.add(VALID_TAGNAME_FRIENDS);
        FindTagCommand command = new FindTagCommand(singleKeyword);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends");
        expectedModel.addActivatedTag(VALID_TAG_FRIENDS);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeywordHasActivatedTags_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<String> singleKeyword = new ArrayList<>();
        singleKeyword.add(VALID_TAGNAME_OWESMONEY);
        Tag activatedTag = VALID_TAG_FRIENDS;
        model.addActivatedTag(activatedTag);
        FindTagCommand command = new FindTagCommand(singleKeyword);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends owesMoney");
        expectedModel.addActivatedTag(activatedTag);
        expectedModel.addActivatedTag(VALID_TAG_OWESMONEY);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());

    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
