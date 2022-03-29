package seedu.address.logic.commands;

import static seedu.address.commons.util.AttendanceUtil.ATTENDANCE_DATE_FORMATTER;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.AttendanceUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.AbsentAttendanceEntry;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Pet;

/**
 * Marks the attendance of an existing pet in WoofAreYou as absent on a particular date.
 */
public class AbsentAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "absent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an attendance of a pet as absent "
            + "by the index number used in the displayed pet list. \n"
            + "Includes the date, pick up time and drop off time (if any). \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "15-03-2022 ";

    public static final String MESSAGE_ABSENT_ATTENDANCE_SUCCESS =
        "Successfully marked %1$s as absent on %2$s!\n"
        + "New entry:\n"
        + "%3$s";
    public static final String MESSAGE_ABSENT_ATTENDANCE_FAILURE =
        "Seems like you have already marked %1$s as absent on %2$s!\n"
        + "Existing entry:\n"
        + "%3$s";

    private final Index index;
    private final AbsentAttendanceDescriptor absentAttendanceDescriptor;

    /**
     * @param index of the pet in the filtered pets list to mark as absent.
     * @param absentAttendanceDescriptor details of the absent attendance to be stored.
     */
    public AbsentAttendanceCommand(Index index, AbsentAttendanceDescriptor absentAttendanceDescriptor) {
        requireAllNonNull(index, absentAttendanceDescriptor);

        this.index = index;
        this.absentAttendanceDescriptor = absentAttendanceDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        AttendanceHashMap targetAttendanceHashMap = petToEdit.getAttendanceHashMap();

        LocalDate attendanceDate = absentAttendanceDescriptor.getAttendanceDate();
        String attendanceDateString = attendanceDate.format(ATTENDANCE_DATE_FORMATTER);
        AbsentAttendanceEntry absentAttendance = new AbsentAttendanceEntry(attendanceDate);

        if (targetAttendanceHashMap.containsAttendance(absentAttendance)) {
            throw new CommandException(generateFailureMessage(petToEdit, attendanceDateString, absentAttendance));
        }

        targetAttendanceHashMap.addAttendance(absentAttendance);

        Pet editedPet = new Pet(
                petToEdit.getName(), petToEdit.getOwnerName(), petToEdit.getPhone(),
                petToEdit.getAddress(), petToEdit.getTags(), petToEdit.getDiet(),
                petToEdit.getAppointment(), targetAttendanceHashMap);

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        return new CommandResult(generateSuccessMessage(editedPet, attendanceDateString, absentAttendance));
    }

    /**
     * Stores the absent attendance details to edit the pet with.
     */
    public static class AbsentAttendanceDescriptor {
        private LocalDate attendanceDate;

        public AbsentAttendanceDescriptor() {
        }

        public void setAttendanceDate(LocalDate attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public LocalDate getAttendanceDate() {
            return attendanceDate;
        }

        @Override
        public String toString() {
            String date = getAttendanceDate().format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);
            return "Absent | " + date;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AbsentAttendanceDescriptor)) {
                return false;
            }

            // state check
            AbsentAttendanceDescriptor otherDescriptor = (AbsentAttendanceDescriptor) other;

            return attendanceDate.equals(otherDescriptor.attendanceDate);
        }
    }

    /**
     * Generates a command execution success message based on the
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Pet petToEdit, String attendanceDate, AbsentAttendanceEntry entry) {
        return String.format(
            MESSAGE_ABSENT_ATTENDANCE_SUCCESS, petToEdit.getName(), attendanceDate, entry.toString());
    }

    /**
     * Generates a command execution failure message based on the
     * {@code petToEdit}.
     */
    private String generateFailureMessage(Pet petToEdit, String attendanceDate, AbsentAttendanceEntry entry) {
        return String.format(
            MESSAGE_ABSENT_ATTENDANCE_FAILURE, petToEdit.getName(), attendanceDate, entry.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AbsentAttendanceCommand)) {
            return false;
        }

        // state check
        AbsentAttendanceCommand e = (AbsentAttendanceCommand) other;
        return index.equals(e.index)
                && absentAttendanceDescriptor.equals(e.absentAttendanceDescriptor);
    }
}
