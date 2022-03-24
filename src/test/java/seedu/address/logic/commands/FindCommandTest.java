package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
//import static seedu.address.testutil.TypicalPersons.FEREN;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.PERTY;
//import static seedu.address.testutil.TypicalPersons.PREF;
import static seedu.address.testutil.TypicalPersons.PROTY;
import static seedu.address.testutil.TypicalPersons.getVariousAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.predicates.PersonContainsKeywordsPredicate;
import seedu.address.model.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.predicates.PreferenceContainsKeywordsPredicate;
import seedu.address.model.predicates.PropertiesContainsKeywordsPredicate;
import seedu.address.model.predicates.UserTypeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getVariousAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getVariousAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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

    @Test
    public void execute_zeroPersonKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate = preparePersonPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePersonKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = preparePersonPredicate("Kurz 9482224 tokyo");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroNameKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPhoneKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PhoneContainsKeywordsPredicate predicate = preparePhonePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePhoneKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PhoneContainsKeywordsPredicate predicate = preparePhonePredicate("95352563 9482224 9482427");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroEmailKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmailKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        EmailContainsKeywordsPredicate predicate =
                prepareEmailPredicate("heinz@example.com werner@example.com lydia@example.com");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroAddressKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleAddressKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate("wall michegan tokyo");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroPropertiesKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PropertiesContainsKeywordsPredicate predicate = preparePropertiesPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /*
    @Test
    public void execute_multiplePropertiesKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PropertiesContainsKeywordsPredicate predicate =
                preparePropertiesPredicate("south 2-room $100000");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROTY, PERTY), model.getFilteredPersonList());
    }
    */

    @Test
    public void execute_zeroPreferenceKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PreferenceContainsKeywordsPredicate predicate = preparePreferencePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /*
    @Test
    public void execute_multiplePreferenceKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PreferenceContainsKeywordsPredicate predicate =
                preparePreferencePredicate("west $50000");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PREF, FEREN), model.getFilteredPersonList());
    }
    */

    @Test
    public void execute_zeroUserTypeKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        UserTypeContainsKeywordsPredicate predicate = prepareUserTypePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleUserTypeKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        UserTypeContainsKeywordsPredicate predicate = prepareUserTypePredicate("seller");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROTY, PERTY), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code PersonContainsKeywordsPredicate}.
     */
    private PersonContainsKeywordsPredicate preparePersonPredicate(String userInput) {
        return new PersonContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
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
     * Parses {@code userInput} into a {@code PropertiesContainsKeywordsPredicate}.
     */
    private PropertiesContainsKeywordsPredicate preparePropertiesPredicate(String userInput) {
        return new PropertiesContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PreferenceContainsKeywordsPredicate}.
     */
    private PreferenceContainsKeywordsPredicate preparePreferencePredicate(String userInput) {
        return new PreferenceContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code UserTypeContainsKeywordsPredicate}.
     */
    private UserTypeContainsKeywordsPredicate prepareUserTypePredicate(String userInput) {
        return new UserTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
