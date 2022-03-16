package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.SortUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.SortUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.*;

class SortCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortNameDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(NAME_SORT_DESC);

        List<Person> sortedPersonListByNameDescending = new ArrayList<>(Arrays.asList(IDA, GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE, HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByNameDescending), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortNameDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(NAME_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(HOON, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, IDA));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAddressDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(ADDRESS_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(DANIEL, HOON, ALICE, BENSON, GEORGE, FIONA, ELLE, IDA, CARL));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAddressDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(ADDRESS_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(CARL, IDA, ELLE, FIONA, GEORGE, BENSON, ALICE, HOON, DANIEL));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortEmailDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(EMAIL_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortEmailDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(EMAIL_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(IDA, HOON, GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortRemarkDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(REMARK_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, ELLE, DANIEL, FIONA, GEORGE, IDA, HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortRemarkDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(REMARK_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(ELLE, CARL, BENSON, ALICE, DANIEL, FIONA, GEORGE, IDA, HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_sortBirthdayDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(BIRTHDAY_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(ELLE, CARL, ALICE, BENSON, GEORGE, DANIEL, FIONA, IDA, HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortBirthdayDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(BIRTHDAY_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(Arrays.asList(GEORGE, BENSON, ALICE, CARL, ELLE, DANIEL, FIONA, IDA, HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_noFieldSpecified_Failure() {
        SortCommand sortCommand = new SortCommand(new ArrayList<>());
        String expectedMessage = String.format(SortCommand.MESSAGE_EMPTY_ERROR);

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(NAME_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DESC);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);

        final SortCommand standardCommand = new SortCommand(new ArrayList<>(fieldSortOrder));

        SortCommand commandWithSameValues = new SortCommand(new ArrayList<>(fieldSortOrder));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        //different values
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrderDiff;
        fieldSortOrderDiff = new ArrayList<>();
        fieldSortOrderDiff.add(NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DESC);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        SortCommand commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));

        //missing values from standard
        fieldSortOrderDiff = new ArrayList<>();
        fieldSortOrderDiff.add(NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));

        //same values as standard but diff order. order matters
        fieldSortOrderDiff = new ArrayList<>();
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DESC);
        commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));
    }

}