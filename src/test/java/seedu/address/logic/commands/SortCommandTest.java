package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.SortUtil;
import seedu.address.testutil.TypicalPersons;

class SortCommandTest {

    private final Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortNameDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);

        List<Person> sortedPersonListByNameDescending = new ArrayList<>(
                Arrays.asList(TypicalPersons.IDA, TypicalPersons.GEORGE, TypicalPersons.FIONA, TypicalPersons.ELLE,
                        TypicalPersons.DANIEL, TypicalPersons.CARL, TypicalPersons.BENSON, TypicalPersons.ALICE,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByNameDescending,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortNameDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.HOON, TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL,
                        TypicalPersons.DANIEL, TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE,
                        TypicalPersons.IDA));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAddressDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.DANIEL, TypicalPersons.HOON, TypicalPersons.ALICE, TypicalPersons.BENSON,
                        TypicalPersons.GEORGE, TypicalPersons.FIONA, TypicalPersons.ELLE, TypicalPersons.IDA,
                        TypicalPersons.CARL));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortAddressDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.CARL, TypicalPersons.IDA, TypicalPersons.ELLE, TypicalPersons.FIONA,
                        TypicalPersons.GEORGE, TypicalPersons.BENSON, TypicalPersons.ALICE, TypicalPersons.HOON,
                        TypicalPersons.DANIEL));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortEmailDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL, TypicalPersons.DANIEL,
                        TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE, TypicalPersons.HOON,
                        TypicalPersons.IDA));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortEmailDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.IDA, TypicalPersons.HOON, TypicalPersons.GEORGE, TypicalPersons.FIONA,
                        TypicalPersons.ELLE, TypicalPersons.DANIEL, TypicalPersons.CARL, TypicalPersons.BENSON,
                        TypicalPersons.ALICE));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortPhoneDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.PHONE_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.HOON, TypicalPersons.IDA, TypicalPersons.DANIEL, TypicalPersons.ALICE,
                        TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE, TypicalPersons.CARL,
                        TypicalPersons.BENSON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortPhoneDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.PHONE_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.BENSON, TypicalPersons.CARL, TypicalPersons.GEORGE, TypicalPersons.FIONA,
                        TypicalPersons.ELLE, TypicalPersons.ALICE, TypicalPersons.DANIEL, TypicalPersons.IDA,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortRemarkDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL, TypicalPersons.ELLE,
                        TypicalPersons.DANIEL, TypicalPersons.FIONA, TypicalPersons.GEORGE, TypicalPersons.IDA,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortRemarkDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.REMARK_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.ELLE, TypicalPersons.CARL, TypicalPersons.BENSON, TypicalPersons.ALICE,
                        TypicalPersons.DANIEL, TypicalPersons.FIONA, TypicalPersons.GEORGE, TypicalPersons.IDA,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_sortBirthdayDefault_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.ELLE, TypicalPersons.CARL, TypicalPersons.ALICE, TypicalPersons.BENSON,
                        TypicalPersons.GEORGE, TypicalPersons.DANIEL, TypicalPersons.FIONA, TypicalPersons.IDA,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortBirthdayDescending_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.GEORGE, TypicalPersons.BENSON, TypicalPersons.ALICE, TypicalPersons.CARL,
                        TypicalPersons.ELLE, TypicalPersons.DANIEL, TypicalPersons.FIONA, TypicalPersons.IDA,
                        TypicalPersons.HOON));

        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortMultipleFields_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);

        List<Person> sortedPersonListByName = new ArrayList<>(
                Arrays.asList(TypicalPersons.GEORGE, TypicalPersons.BENSON, TypicalPersons.ALICE, TypicalPersons.CARL,
                        TypicalPersons.ELLE, TypicalPersons.HOON, TypicalPersons.DANIEL, TypicalPersons.FIONA,
                        TypicalPersons.IDA));
        SortCommand sortCommand = new SortCommand(fieldSortOrder);
        Model expectedModel = new ModelManager(new AddressBook(sortedPersonListByName,
                model.getFilteredTransactionList()), new UserPrefs());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_failure() {
        SortCommand sortCommand = new SortCommand(new ArrayList<>());
        String expectedMessage = String.format(SortCommand.MESSAGE_EMPTY_ERROR);

        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
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
        fieldSortOrderDiff.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DESC);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        SortCommand commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));

        //missing values from standard
        fieldSortOrderDiff = new ArrayList<>();
        fieldSortOrderDiff.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));

        //same values as standard but diff order. order matters
        fieldSortOrderDiff = new ArrayList<>();
        fieldSortOrderDiff.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrderDiff.add(SortUtil.BIRTHDAY_SORT_DESC);
        fieldSortOrderDiff.add(SortUtil.REMARK_SORT_DESC);
        commandWithDiffValues = new SortCommand(new ArrayList<>(fieldSortOrderDiff));
        assertFalse(standardCommand.equals(commandWithDiffValues));
    }

}
