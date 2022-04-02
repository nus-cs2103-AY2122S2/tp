package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.SortCommandParser.ADDRESS_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.ADDRESS_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.EMAIL_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.EMAIL_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.FAVOURITE_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.FAVOURITE_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.NAME_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.NAME_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.PHONE_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.PHONE_COMPARATOR_REVERSE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonComparator;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_byName_alphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(NAME_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byNameReversed_reverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(NAME_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byAddress_lexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(ADDRESS_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, BENSON, GEORGE, FIONA, ELLE, CARL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byAddressReversed_reverseLexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(ADDRESS_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE, BENSON, ALICE, DANIEL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byEmail_lexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(EMAIL_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE, DANIEL, CARL, BENSON, FIONA, ELLE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byEmailReversed_reverseLexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(EMAIL_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, BENSON, CARL, DANIEL, GEORGE, ALICE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byPhone_numericalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(PHONE_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byPhoneReversed_reverseNumericalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(PHONE_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, GEORGE, FIONA, ELLE, ALICE, DANIEL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavourite_favouritesFirst() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(FAVOURITE_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAndSortedPersonList().subList(0, 4)
                .containsAll(Arrays.asList(ALICE, CARL, DANIEL, GEORGE)));
        assertTrue(model.getFilteredAndSortedPersonList().subList(4, 7)
                .containsAll(Arrays.asList(ELLE, FIONA, BENSON)));
    }

    @Test
    public void execute_byFavouriteReversed_favouritesLast() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(FAVOURITE_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredAndSortedPersonList().subList(0, 3)
                .containsAll(Arrays.asList(ELLE, FIONA, BENSON)));
        assertTrue(model.getFilteredAndSortedPersonList().subList(3, 7)
                .containsAll(Arrays.asList(ALICE, CARL, DANIEL, GEORGE)));
    }

    @Test
    public void execute_byFavouriteThenName_favouritesFirstAndAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(FAVOURITE_COMPARATOR, NAME_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, GEORGE, BENSON, ELLE, FIONA),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteThenNameReversed_favouritesFirstAndReverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(FAVOURITE_COMPARATOR, NAME_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, DANIEL, CARL, ALICE, FIONA, ELLE, BENSON),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteReversedThenName_favouritesLastAndAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator = new PersonComparator(List.of(FAVOURITE_COMPARATOR_REVERSE, NAME_COMPARATOR));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, FIONA, ALICE, CARL, DANIEL, GEORGE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteReversedThenNameReversed_favouritesLastAndReverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonComparator comparator =
                new PersonComparator(List.of(FAVOURITE_COMPARATOR_REVERSE, NAME_COMPARATOR_REVERSE));
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, ELLE, BENSON, GEORGE, DANIEL, CARL, ALICE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void equals() {
        PersonComparator nameComparator = new PersonComparator(List.of(NAME_COMPARATOR));
        PersonComparator addressComparator = new PersonComparator(List.of(ADDRESS_COMPARATOR));

        SortCommand sortNameCommand = new SortCommand(nameComparator);
        SortCommand sortAddressCommand = new SortCommand(addressComparator);

        // same object -> returns true
        assertTrue(sortNameCommand.equals(sortNameCommand));

        // same values -> returns true
        SortCommand sortNameCommandCopy = new SortCommand(nameComparator);
        assertTrue(sortNameCommand.equals(sortNameCommandCopy));

        // different types -> returns false
        assertFalse(sortNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortNameCommand.equals(null));

        // different comparator -> returns false
        assertFalse(sortNameCommand.equals(sortAddressCommand));
    }
}
