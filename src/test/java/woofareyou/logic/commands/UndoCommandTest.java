package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import woofareyou.commons.core.index.Index;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;

public class UndoCommandTest {

    private static final String APPOINTMENT_DATE_TIME_STUB = "02-04-2022 09:30";
    private static final String APPOINTMENT_LOCATION_STUB = "NUS Vet Clinic";
    private static final String DATE_STUB = LocalDate.now().toString();
    private static final String DIET_STUB = "Some diet";
    private static final String PICKUP_TIME_STUB = "09:00";
    private static final String DROPOFF_TIME_STUB = "17:30";

    private Model model;
    private Model expectedModel;


    /**
     * Initializes the model and expectedModel before running each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPetBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getPetBook(), new UserPrefs());
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
        model.sortPetList("owner");

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * Test method that checks for a successful undo of an edit command.
     */
    @Test
    public void execute_listIsFiltered_undoEditCommand() {
        Index indexLastPet = Index.fromOneBased(model.getFilteredPetList().size());
        Pet lastPet = model.getFilteredPetList().get(indexLastPet.getZeroBased());

        PetBuilder petInList = new PetBuilder(lastPet);
        Pet editedPet = petInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        model.setPet(lastPet, editedPet);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * Test method that checks for a successful undo of the present attendance command.
     */
    @Test
    public void execute_listIsFiltered_undoAddAttendanceCommand() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB, PICKUP_TIME_STUB, DROPOFF_TIME_STUB)
                .build();

        model.setPet(firstPet, petToMarkPresent);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


    /**
     * Test method that checks for a successful undo of the absent attendance command.
     */
    @Test
    public void execute_listIsFiltered_undoRemoveAttendanceCommand() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(firstPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        model.setPet(firstPet, petToMarkAbsent);

        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
