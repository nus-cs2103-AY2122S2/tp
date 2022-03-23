package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICKUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.AttendanceUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.PresentAttendanceEntry;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Pet;

/**
 * Marks the attendance of an existing pet in WoofAreYou as present on a particular date.
 */
public class PresentAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "present";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an attendance of a pet as present "
            + "by the index number used in the displayed pet list. \n"
            + "Includes the date, pick up time and drop off time (if any). \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE OF ATTENDANCE IN DD/MM/YYYY] "
            + "[" + PREFIX_PICKUP + "PICK UP TIME IN HH:MM] "
            + "[" + PREFIX_DROPOFF + "DROP OFF TIME IN HH:MM] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "15-03-2022 "
            + PREFIX_PICKUP + "09:15 "
            + PREFIX_DROPOFF + "18:00";

    public static final String MESSAGE_PRESENT_ATTENDANCE_SUCCESS = "Successfully marked %1$s as present on %2$s!";
    public static final String MESSAGE_PRESENT_ATTENDANCE_FAILURE =
            "Seems like you have already marked %1$s as present on %2$s!";

    private final Index index;
    private final PresentAttendanceDescriptor presentAttendanceDescriptor;

    /**
     * @param index of the pet in the filtered pets list to mark as present.
     * @param presentAttendanceDescriptor the present attendance of the pet to be stored.
     */
    public PresentAttendanceCommand(Index index, PresentAttendanceDescriptor presentAttendanceDescriptor) {
        requireAllNonNull(index, presentAttendanceDescriptor);

        this.index = index;
        this.presentAttendanceDescriptor = presentAttendanceDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        AttendanceHashMap targetAttendanceHashMap = petToEdit.getAttendanceHashMap();

        LocalDate attendanceDate = presentAttendanceDescriptor.getAttendanceDate();
        LocalTime pickUpTime = presentAttendanceDescriptor.getPickUpTime().orElse(null);
        LocalTime dropOffTime = presentAttendanceDescriptor.getDropOffTime().orElse(null);

        String attendanceDateString = attendanceDate.format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);

        PresentAttendanceEntry presentAttendance = new PresentAttendanceEntry(attendanceDate,
                pickUpTime, dropOffTime);

        if (targetAttendanceHashMap.containsAttendance(presentAttendance)) {
            throw new CommandException(String.format(MESSAGE_PRESENT_ATTENDANCE_FAILURE, petToEdit.getName(),
                    attendanceDateString));
        }

        if (!presentAttendance.isValidTimings()) {
            throw new CommandException(PresentAttendanceEntry.MESSAGE_TIME_CONSTRAINTS);
        }

        targetAttendanceHashMap.addAttendance(presentAttendance);

        Pet editedPet = new Pet(
                petToEdit.getName(), petToEdit.getOwnerName(), petToEdit.getPhone(),
                petToEdit.getAddress(), petToEdit.getTags(), petToEdit.getDiet(),
                petToEdit.getAppointment(), targetAttendanceHashMap);

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        return new CommandResult(generateSuccessMessage(editedPet, attendanceDateString));
    }

    /**
     * Generates a command execution success message based on the
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Pet petToEdit, String attendanceDate) {
        return String.format(MESSAGE_PRESENT_ATTENDANCE_SUCCESS, petToEdit.getName(), attendanceDate);
    }

    /**
     * Stores the present attendance details to edit the pet with.
     */
    public static class PresentAttendanceDescriptor {
        private LocalDate attendanceDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;

        public PresentAttendanceDescriptor() {
        }

        public void setAttendanceDate(LocalDate attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public void setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
        }

        public void setDropOffTime(LocalTime dropOffTime) {
            this.dropOffTime = dropOffTime;
        }

        public LocalDate getAttendanceDate() {
            return attendanceDate;
        }

        public Optional<LocalTime> getPickUpTime() {
            return Optional.ofNullable(pickUpTime);
        }

        public Optional<LocalTime> getDropOffTime() {
            return Optional.ofNullable(dropOffTime);
        }
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PresentAttendanceCommand)) {
            return false;
        }

        // state check
        PresentAttendanceCommand e = (PresentAttendanceCommand) other;
        return index.equals(e.index)
                && presentAttendanceDescriptor.equals(e.presentAttendanceDescriptor);
    }
}
