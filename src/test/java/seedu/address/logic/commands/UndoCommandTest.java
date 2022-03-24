package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;

public class UndoCommandTest {
    private static final String APPOINTMENT_DATE_TIME_STUB = "02-04-2022 09:30";
    private static final String APPOINTMENT_LOCATION_STUB = "NUS Vet Clinic";
    private static final String DIET_STUB = "Some diet";

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
     * Test method that checks for a successful undo of a delete command
     */
    @Test
    public void execute_listIsFiltered_undoDeleteCommand() {
        // Model result
        Pet petToDelete = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        model.deletePet(petToDelete);
        UndoCommand undoCommand = new UndoCommand();

        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks for a successful undo of an add command
     */
    @Test
    public void execute_listIsFiltered_undoAddCommand() {
        Pet alice = new PetBuilder().withName("Alice").build();
        model.addPet(alice);
        UndoCommand undoCommand = new UndoCommand();

        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Test method that checks for a successful undo of an appointment command
     */
    @Test
    public void execute_listIsFiltered_undoAppointmentCommand() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet)
                .withAppointment(APPOINTMENT_DATE_TIME_STUB,
                        APPOINTMENT_LOCATION_STUB).build();
        model.setPet(firstPet, editedPet);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * Test method that checks for a successful undo of a diet command
     */
    @Test
    public void execute_listIsFiltered_undoDietCommand() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet)
                .withDiet(DIET_STUB).build();
        model.setPet(firstPet, editedPet);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * Test method that checks for a successful undo of a sort command
     */
    @Test
    public void execute_listIsFiltered_undoSortCommand() {
        model.sortPetList("/o");

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
