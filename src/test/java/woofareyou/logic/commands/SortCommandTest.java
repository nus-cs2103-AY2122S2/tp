package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    /**
     * Initializes the model and expectedModel before running each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPetBook(), new UserPrefs());
        expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
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
