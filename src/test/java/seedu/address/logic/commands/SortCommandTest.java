package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    /**
     * Test method that checks if the pet list gets sorted by owner name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByOwnerName() {
        expectedModel.sortPetList("owner");
        assertCommandSuccess(new SortCommand("owner"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetName() {
        expectedModel.sortPetList("name");
        assertCommandSuccess(new SortCommand("name"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet appointment after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetAppointment() {
        expectedModel.sortPetList("app");
        assertCommandSuccess(new SortCommand("app"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
