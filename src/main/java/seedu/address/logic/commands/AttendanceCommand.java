package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.storage.JsonAdaptedAttendance;
import seedu.address.ui.PetCard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceCommand extends Command{

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attendance of a pet to the database "
            + "by the index number used in the displayed pet list. \n"
            + "Includes the date, pick up time and drop off time (if any). \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ATTENDANCE_DATE + "DATE OF ATTENDANCE IN DD/MM/YYYY] "
            + "[" + PREFIX_PICK_UP + "PICK UP TIME IN HH:MM] "
            + "[" + PREFIX_DROP_OFF + "DROP OFF TIME IN HH:MM] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ATTENDANCE_DATE + "15/03/2022 "
            + PREFIX_PICK_UP + "09:15 "
            + PREFIX_DROP_OFF + "18:00";

    public static final String MESSAGE_ATTENDANCE_SUCCESS = "Attendance updated: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the address book.";
    public static final String MESSAGE_PET_NOT_ADDED = "Seems like this pet is not in the address book yet! "
                                                        + "Make sure to add the pet in first!";

    private final Index index;
    private final PetAttendanceDescriptor attendanceDescriptor;


    /**
     * @param index of the pet in the filtered pet list to edit
     * @param attendanceDescriptor details of attendance to edit the pet with
     */
    public AttendanceCommand(Index index, PetAttendanceDescriptor attendanceDescriptor) {
        requireNonNull(index);
        requireNonNull(attendanceDescriptor);

        this.index = index;
        this.attendanceDescriptor = new PetAttendanceDescriptor(attendanceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToUpdateAttendance = lastShownList.get(index.getZeroBased());
        createAttendanceForPet(petToUpdateAttendance, attendanceDescriptor);

        return new CommandResult(MESSAGE_ATTENDANCE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Pet} with the attendance details of {@code petToEdit}
     * edited with {@code editPetDescriptor}.
     */
    private static void createAttendanceForPet(Pet petToEdit, AttendanceCommand.PetAttendanceDescriptor petAttendanceDescriptor) throws CommandException {
        assert petToEdit != null;

        Name nameOfPet = petToEdit.getName();
        Phone phoneNumberOfOwner = petToEdit.getPhone();
        String filePath = "data/pets/"
                + nameOfPet.toString().replaceAll("\\s", "")
                + phoneNumberOfOwner.toString()
                + ".json";
        String attendanceDate = petAttendanceDescriptor.getAttendanceDateString();
        String pickUpTime = petAttendanceDescriptor.getPickUpTimeString();
        String dropOffTime = petAttendanceDescriptor.getDropOffTimeString();

        try {
            BufferedWriter attendanceWriter = new BufferedWriter(new FileWriter(filePath, true));
//            String toWrite = attendanceDate + " " + pickUpTime + " " + dropOffTime;
//            attendanceWriter.write(toWrite);
//            attendanceWriter.close();
//
//            File
            Map<String, Object> map = new HashMap<>();
            map.put("attendance date", attendanceDate);
            map.put("pick up time", pickUpTime);
            map.put("drop off time", dropOffTime);

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(attendanceWriter, map);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_PET_NOT_ADDED);
        }

    }

    /**
     * Stores the details to edit the pet with. Each non-empty field value will replace the
     * corresponding field value of the pet.
     */
    public static class PetAttendanceDescriptor {
        private LocalDate attendanceDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;

        public PetAttendanceDescriptor() {}

        public PetAttendanceDescriptor(PetAttendanceDescriptor petAttendanceDescriptor) {
            setAttendanceDate(petAttendanceDescriptor.attendanceDate);
            setPickUpTime(petAttendanceDescriptor.pickUpTime);
            setDropOffTime(petAttendanceDescriptor.dropOffTime);
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

        public String getAttendanceDateString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return this.attendanceDate.format(formatter);
        }

        public String getPickUpTimeString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return this.pickUpTime.format(formatter);
        }

        public String getDropOffTimeString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return this.dropOffTime.format(formatter);
        }

    }
}
