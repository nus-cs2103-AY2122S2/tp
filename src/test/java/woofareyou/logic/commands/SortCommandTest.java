package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.testutil.TypicalPets;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    /**
     * Initializes the model and expectedModel before running each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    /**
     * Test method that checks if the pet list gets sorted by owner name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByOwnerName() {
        expectedModel.sortPetList("owner");
        CommandTestUtil.assertCommandSuccess(new SortCommand("owner"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetName() {
        expectedModel.sortPetList("name");
        CommandTestUtil.assertCommandSuccess(new SortCommand("name"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet appointment after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetAppointment() {
        expectedModel.sortPetList("app");
        CommandTestUtil.assertCommandSuccess(new SortCommand("app"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet name after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetDropOffTime() {
        expectedModel.sortPetList("drop off");
        CommandTestUtil.assertCommandSuccess(new SortCommand("drop off"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks if the pet list gets sorted by pet appointment after calling the sort command.
     */
    @Test
    public void execute_listIsNotFiltered_showsSortedListByPetPickUpTime() {
        expectedModel.sortPetList("pick up");
        CommandTestUtil.assertCommandSuccess(new SortCommand("pick up"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
