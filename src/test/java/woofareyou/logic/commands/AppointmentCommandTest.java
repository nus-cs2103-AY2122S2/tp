package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Appointment;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;



class AppointmentCommandTest {

    private static final String APPOINTMENT_DATE_TIME_STUB = "02-04-2022 09:30";
    private static final String APPOINTMENT_LOCATION_STUB = "NUS Vet Clinic";

    private Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @Test
    public void execute_addAppointmentUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet)
                .withAppointment(APPOINTMENT_DATE_TIME_STUB,
                        APPOINTMENT_LOCATION_STUB).build();

        AppointmentCommand appointmentCommand =
                new AppointmentCommand(INDEX_FIRST_PET,
                        new Appointment(editedPet.getAppointment().getDateTime(),
                                editedPet.getAppointment().getLocation()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearAppointmentUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet).withAppointment().build();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PET,
                editedPet.getAppointment());

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased()))
                .withAppointment(APPOINTMENT_DATE_TIME_STUB, APPOINTMENT_LOCATION_STUB).build();

        AppointmentCommand appointmentCommand = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment(editedPet.getAppointment().getDateTime(),
                        editedPet.getAppointment().getLocation()));

        String expectedMessage = String.format(AppointmentCommand.MESSAGE_ADD_APPOINTMENT_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(appointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPetIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        AppointmentCommand appointmentCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment());

        assertCommandFailure(appointmentCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**s
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of pet book
     */
    @Test
    public void execute_invalidPetIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of pet book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetBook().getPetList().size());

        AppointmentCommand appointmentCommand = new AppointmentCommand(outOfBoundIndex,
                new Appointment());

        assertCommandFailure(appointmentCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AppointmentCommand standardCommand = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment());
        // same values -> returns true
        AppointmentCommand commandWithSameValues = new AppointmentCommand(INDEX_FIRST_PET,
                new Appointment());
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new AppointmentCommand(INDEX_SECOND_PET,
                new Appointment())));
    }

}
