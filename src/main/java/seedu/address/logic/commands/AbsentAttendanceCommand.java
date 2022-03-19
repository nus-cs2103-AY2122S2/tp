package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.AbsentAttendance;
import seedu.address.model.attendance.PresentAttendance;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Pet;

public class AbsentAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "absent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an attendance of a pet as absent "
            + "by the index number used in the displayed pet list. \n"
            + "Includes the date, pick up time and drop off time (if any). \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "15/03/2022 ";

    public static final String MESSAGE_ABSENT_ATTENDANCE_SUCCESS = "Successfully marked %1$s! as absent!";
    public static final String MESSAGE_ABSENT_ATTENDANCE_FAILURE =
            "Seems like you have already marked %1$s as absent!";

    private final Index index;
    private final PetAttendanceDescriptor petAttendanceDescriptor;

    /**
     * @param index of the pet in the filtered pets list to mark as present.
     * @param petAttendanceDescriptor the absent attendance of the pet to be stored.
     */
    public AbsentAttendanceCommand(Index index, PetAttendanceDescriptor petAttendanceDescriptor) {
        requireAllNonNull(index, petAttendanceDescriptor);

        this.index = index;
        this.petAttendanceDescriptor = petAttendanceDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        AttendanceHashMap targetAttendanceHashMap = petToEdit.getAttendanceHashMap();

        AbsentAttendance absentAttendance = new AbsentAttendance(petAttendanceDescriptor.getAttendanceDate());


        if (targetAttendanceHashMap.containsAttendance(absentAttendance)) {
            throw new CommandException(MESSAGE_ABSENT_ATTENDANCE_FAILURE);
        }

        AttendanceHashMap editedAttendanceHashMap = targetAttendanceHashMap.addAttendance(absentAttendance);

        Pet editedPet = new Pet(
                petToEdit.getName(), petToEdit.getOwnerName(), petToEdit.getPhone(),
                petToEdit.getAddress(), petToEdit.getTags(), petToEdit.getDiet(),
                petToEdit.getAppointment(), editedAttendanceHashMap);

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);

        return new CommandResult(generateSuccessMessage(editedPet));
    }

    /**
     * Stores the attendance details to edit the pet with.
     */
    public static class PetAttendanceDescriptor {
        private LocalDate attendanceDate;

        public PetAttendanceDescriptor() {
        }

        /**
         * Copy Constructor
         *
         * @param petAttendanceDescriptor takes in another PetAttendanceDescriptor class
         */
        public PetAttendanceDescriptor(PetAttendanceDescriptor petAttendanceDescriptor) {
            setAttendanceDate(petAttendanceDescriptor.attendanceDate);
        }


        public void setAttendanceDate(LocalDate attendanceDate) {
            this.attendanceDate = attendanceDate;
        }

        public LocalDate getAttendanceDate() {
            return attendanceDate;
        }

    }

    /**
     * Generates a command execution success message based on the
     * {@code petToEdit}.
     */
    private String generateSuccessMessage(Pet petToEdit) {
        return String.format(MESSAGE_ABSENT_ATTENDANCE_SUCCESS, petToEdit);
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
                && petAttendanceDescriptor.equals(e.petAttendanceDescriptor);
    }


}
