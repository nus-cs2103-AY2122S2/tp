package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

public class FindByPrefixCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void equals() {
        // name
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("firstName"), true);
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("secondName"), true);

        // phone
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("8123"));
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("8456"));

        // email
        EmailContainsKeywordsPredicate firstEmailPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("first@gmail.com"));
        EmailContainsKeywordsPredicate secondEmailPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("second@gmail.com"));

        // address
        AddressContainsKeywordsPredicate firstAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("first address"));
        AddressContainsKeywordsPredicate secondAddressPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("second address"));

        // tag
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("tag"));

        // student id
        StudentIdContainsKeywordsPredicate firstIdPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("e0123456"));
        StudentIdContainsKeywordsPredicate secondIdPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("e02345"));

        // tutorial name
        TutorialContainsKeywordsPredicate firstTutorialPredicate =
                new TutorialContainsKeywordsPredicate(Collections.singletonList("T01"));
        TutorialContainsKeywordsPredicate secondTutorialPredicate =
                new TutorialContainsKeywordsPredicate(Collections.singletonList("T02"));

        FindByPrefixCommand firstFindByPrefixCommand = new FindByPrefixCommand(firstNamePredicate, firstPhonePredicate,
                firstEmailPredicate, firstAddressPredicate, tagPredicate, firstIdPredicate, firstTutorialPredicate);
        FindByPrefixCommand secondFindByPrefixCommand = new FindByPrefixCommand(secondNamePredicate,
                secondPhonePredicate, secondEmailPredicate, secondAddressPredicate, tagPredicate, secondIdPredicate,
                secondTutorialPredicate);

        // same object -> returns true
        assertTrue(firstFindByPrefixCommand.equals(firstFindByPrefixCommand));

        // same values -> return true
        FindByPrefixCommand firstFindByPrefixCommandCopy = new FindByPrefixCommand(firstNamePredicate,
                firstPhonePredicate, firstEmailPredicate, firstAddressPredicate, tagPredicate, firstIdPredicate,
                firstTutorialPredicate);
        assertTrue(firstFindByPrefixCommand.equals(firstFindByPrefixCommandCopy));

        // different types -> return false
        assertFalse(firstFindByPrefixCommand.equals(1));

        // null -> return false
        assertFalse(firstFindByPrefixCommand.equals(null));

        // different object -> returns false
        assertFalse(firstFindByPrefixCommand.equals(secondFindByPrefixCommand));

    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePred = prepareNamePredicate(" ");
        PhoneContainsKeywordsPredicate phonePred = preparePhonePredicate(" ");
        EmailContainsKeywordsPredicate emailPred = prepareEmailPredicate(" ");
        AddressContainsKeywordsPredicate addressPred = prepareAddressPredicate(" ");
        TagContainsKeywordsPredicate tagPred = prepareTagPredicate(" ");
        StudentIdContainsKeywordsPredicate studentIdPred = prepareIdPredicate(" ");
        TutorialContainsKeywordsPredicate tutorialNamePred = prepareTutorialNamePredicate(" ");
        FindByPrefixCommand command = new FindByPrefixCommand(namePred, phonePred, emailPred, addressPred, tagPred,
                studentIdPred, tutorialNamePred);
        expectedModel.updateFilteredPersonList(namePred);
        expectedModel.updateFilteredPersonList(phonePred);
        expectedModel.updateFilteredPersonList(emailPred);
        expectedModel.updateFilteredPersonList(addressPred);
        expectedModel.updateFilteredPersonList(tagPred);
        expectedModel.updateFilteredPersonList(studentIdPred);
        expectedModel.updateFilteredPersonList(tutorialNamePred);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")), true);
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TutorialContainsKeywordsPredicate}.
     */
    private TutorialContainsKeywordsPredicate prepareTutorialNamePredicate(String userInput) {
        return new TutorialContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate prepareIdPredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
