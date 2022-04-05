package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    /**
     * Initializes the model and expectedModel before running each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getLastUsedPredicate());
    }

    /**
     * Test method that checks if the pet list gets sorted by owner name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByOwnerName() {
        String field = "owner";
        expectedModel.sortPetList(field);
        assertCommandSuccess(new SortCommand(field), model, SortCommand.MESSAGE_SUCCESS + field, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetName() {
        String field = "name";
        expectedModel.sortPetList(field);
        assertCommandSuccess(new SortCommand(field), model, SortCommand.MESSAGE_SUCCESS + field, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet appointment after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetAppointment() {
        String field = "app";
        expectedModel.sortPetList(field);
        assertCommandSuccess(new SortCommand(field), model, SortCommand.MESSAGE_SUCCESS + field, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetDropOffTime() {
        String field = "drop off";
        expectedModel.sortPetList(field);
        assertCommandSuccess(new SortCommand(field), model, SortCommand.MESSAGE_SUCCESS + field, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet appointment after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetPickUpTime() {
        String field = "pick up";
        expectedModel.sortPetList(field);
        assertCommandSuccess(new SortCommand(field), model, SortCommand.MESSAGE_SUCCESS + field, expectedModel);
    }

}
