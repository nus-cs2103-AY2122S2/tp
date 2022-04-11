package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.comparators.PersonMeetingComparator;
import seedu.address.model.person.comparators.PersonNameComparator;
import seedu.address.model.person.comparators.PersonPrevDateMetComparator;
import seedu.address.model.person.comparators.PersonSalaryComparator;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHustleBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getHustleBook(), new UserPrefs());
    }

    @Test
    public void execute_sortName() {
        SortCommand command = new SortCommand(new PersonNameComparator());
        expectedModel.sortPersonListBy(new PersonNameComparator());
        assertCommandSuccess(command, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_sortPrev() {
        SortCommand command = new SortCommand(new PersonPrevDateMetComparator());
        expectedModel.sortPersonListBy(new PersonPrevDateMetComparator());
        assertCommandSuccess(command, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, BENSON, ALICE, GEORGE, ELLE, FIONA),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_sortMeeting() {
        SortCommand command = new SortCommand(new PersonMeetingComparator());
        expectedModel.sortPersonListBy(new PersonMeetingComparator());
        assertCommandSuccess(command, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(BENSON, ALICE, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_sortSalary() {
        SortCommand command = new SortCommand(new PersonSalaryComparator());
        expectedModel.sortPersonListBy(new PersonSalaryComparator());
        assertCommandSuccess(command, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, CARL, ELLE, GEORGE, FIONA),
                model.getFilteredPersonList());
    }
}
