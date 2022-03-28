package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    public static final Comparator<Person> DEFAULT_COMPARATOR = (p1, p2) -> 0;
    public static final Comparator<Person> NAME_COMPARATOR =
            Comparator.comparing(person -> person.getName().fullName.toLowerCase());
    public static final Comparator<Person> ADDRESS_COMPARATOR =
            Comparator.comparing(person -> person.getAddress().value.toLowerCase());
    public static final Comparator<Person> EMAIL_COMPARATOR =
            Comparator.comparing(person -> person.getEmail().value.toLowerCase());
    public static final Comparator<Person> PHONE_COMPARATOR =
            Comparator.comparing(person -> person.getPhone().value.toLowerCase());
    public static final Comparator<Person> FAVOURITE_COMPARATOR =
            Comparator.comparing(person -> person.getFavourite().isUnfavourited());
    public static final Comparator<Person> USER_TYPE_COMPARATOR =
            Comparator.comparing(person -> person.getUserType().value.toLowerCase());
    public static final Comparator<Person> NUM_PROPERTIES_COMPARATOR =
            Comparator.comparingInt(person -> person.getProperties().size());

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_default_noChange() {
        List<Person> originalList = new ArrayList<>(model.getFilteredAndSortedPersonList());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = DEFAULT_COMPARATOR;
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(originalList, model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byName_alphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = NAME_COMPARATOR;
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byNameReversed_reverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = NAME_COMPARATOR.reversed();
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byAddress_lexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = ADDRESS_COMPARATOR;
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, BENSON, GEORGE, FIONA, ELLE, CARL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byAddressReversed_reverseLexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = ADDRESS_COMPARATOR.reversed();
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE, BENSON, ALICE, DANIEL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byEmail_lexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = EMAIL_COMPARATOR;
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE, DANIEL, CARL, BENSON, FIONA, ELLE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byEmailReversed_reverseLexicographicalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = EMAIL_COMPARATOR.reversed();
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, BENSON, CARL, DANIEL, GEORGE, ALICE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byPhone_numericalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = PHONE_COMPARATOR;
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byPhoneReversed_reverseNumericalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = PHONE_COMPARATOR.reversed();
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL, GEORGE, FIONA, ELLE, ALICE, DANIEL),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavourite_favouritesFirst() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = FAVOURITE_COMPARATOR;
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
        Comparator<Person> comparator = FAVOURITE_COMPARATOR.reversed();
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
        Comparator<Person> comparator = FAVOURITE_COMPARATOR.thenComparing(NAME_COMPARATOR);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, DANIEL, GEORGE, BENSON, ELLE, FIONA),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteThenNameReversed_favouritesFirstAndReverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = FAVOURITE_COMPARATOR.thenComparing(NAME_COMPARATOR.reversed());
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, DANIEL, CARL, ALICE, FIONA, ELLE, BENSON),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteReversedThenName_favouritesLastAndAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = FAVOURITE_COMPARATOR.reversed().thenComparing(NAME_COMPARATOR);
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, FIONA, ALICE, CARL, DANIEL, GEORGE),
                model.getFilteredAndSortedPersonList());
    }

    @Test
    public void execute_byFavouriteReversedThenNameReversed_favouritesLastAndReverseAlphabeticalOrder() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        Comparator<Person> comparator = FAVOURITE_COMPARATOR.reversed().thenComparing(NAME_COMPARATOR.reversed());
        SortCommand command = new SortCommand(comparator);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, ELLE, BENSON, GEORGE, DANIEL, CARL, ALICE),
                model.getFilteredAndSortedPersonList());
    }
}
