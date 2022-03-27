package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.Pet;


/**
 * Adds appointment details to a pet identified using it's displayed index from the address book.
 */
public class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "app";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the appointment by either adding or clearing the "
            + "appointment details of the pet identified by the index "
            + "number used in the last pet listing.\n"
            + "To add appointment: \n"
            + "Parameters: INDEX (must be a positive integer)"
            + "dt/[dd-MM-yyyy HH:mm] at/[location]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "date/04-03-2022 09:30 at/ NUS Vet Clinic\n"
            + "To clear appointment: \n"
            + "Parameters: INDEX (must be a positive integer) clear"
            + "Example: " + COMMAND_WORD + " 1 clear";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "Added appointment to Pet: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Removed appointment from Pet: %1$s";
    private final Index index;
    private final Appointment appointment;

    /**
     * @param index of the pet in the filtered pets list to edit the appointment.
     * @param appointment of the pet to be updated to.
     */
    public AppointmentCommand(Index index, Appointment appointment) {
        requireAllNonNull(index, appointment);

        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        Pet editedPet = new Pet(
                petToEdit.getName(), petToEdit.getOwnerName(), petToEdit.getPhone(),
                petToEdit.getAddress(), petToEdit.getTags(), petToEdit.getDiet(), appointment,
                petToEdit.getAttendanceHashMap());

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        return new CommandResult(generateSuccessMessage(editedPet));
    }

    /**
     * Generates a command execution success message based on whether
     * the appointment is added to or removed from
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Pet petToEdit) {
        String message = !appointment.value.isEmpty()
                ? MESSAGE_ADD_APPOINTMENT_SUCCESS : MESSAGE_DELETE_APPOINTMENT_SUCCESS;
        return String.format(message, petToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCommand)) {
            return false;
        }

        // state check
        AppointmentCommand e = (AppointmentCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }

}
