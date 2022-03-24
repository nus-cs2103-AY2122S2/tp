package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DROP_OFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICK_UP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.storage.JsonAdaptedAttendance;

public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attd";

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

    public static final String MESSAGE_ATTENDANCE_SUCCESS = "Attendance updated for %1$s!";
    public static final String MESSAGE_PET_FILE_ERROR = "Seems like this pet is not in the address book yet! "
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

        return new CommandResult(String.format(MESSAGE_ATTENDANCE_SUCCESS, petToUpdateAttendance.getName()));
    }

    /**
     * Creates an entry in individual pet's JSON file and txt file.
     */
    private static void createAttendanceForPet(Pet petToEdit,
                           AttendanceCommand.PetAttendanceDescriptor petAttendanceDescriptor) throws CommandException {
        assert petToEdit != null;

        Name nameOfPet = petToEdit.getName();
        Phone phoneNumberOfOwner = petToEdit.getPhone();
        String jsonFilePath = "data/pets/"
                + nameOfPet.toString().replaceAll("\\s", "")
                + phoneNumberOfOwner.toString()
                + ".json";
        String txtFilePath = "data/pets/"
                + nameOfPet.toString().replaceAll("\\s", "")
                + phoneNumberOfOwner.toString()
                + ".txt";
        String attendanceDate = petAttendanceDescriptor.getAttendanceDateString();
        String pickUpTime = petAttendanceDescriptor.getPickUpTimeString();
        String dropOffTime = petAttendanceDescriptor.getDropOffTimeString();

        try {
            File file = new File(txtFilePath);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(attendanceDate + " " + pickUpTime + " " + dropOffTime + "\n");
            fileWriter.close();

            ArrayList<String> sortAttendance = new ArrayList<>();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String lineAttendance = fileScanner.nextLine();
                sortAttendance.add(lineAttendance);
            }
            fileScanner.close();

            sortTextFile(file, sortAttendance);
            createJsonFile(file, jsonFilePath);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_PET_FILE_ERROR);
        }

    }

    /**
     * Overwrites original text file with sorted dates.
     * @param file text file to be overwritten.
     * @param attendanceString ArrayList of Strings containing attendances.
     * @throws IOException Throws exception if error occurs during writing.
     */
    private static void sortTextFile(File file, ArrayList<String> attendanceString) throws IOException {
        Collections.sort(attendanceString);
        FileWriter sortedFileWriter = new FileWriter(file, false);
        for (String line : attendanceString) {
            sortedFileWriter.write(line + "\n");
        }
        sortedFileWriter.close();
    }

    /**
     * Overwrites existing JSON file of pet to give a new chronologically ordered file.
     * @param textFile Sorted text file to be processed.
     * @param jsonFilePath String path of JSON file to be overwritten.
     * @throws IOException Throws exception if error occurs during writing of file
     */
    private static void createJsonFile(File textFile, String jsonFilePath) throws IOException {
        ArrayList<JsonAdaptedAttendance> attendances = new ArrayList<>();
        Scanner sortedFileScanner = new Scanner(textFile);
        while (sortedFileScanner.hasNextLine()) {
            String lineAttendance = sortedFileScanner.nextLine();
            String[] separator = lineAttendance.split(" ", 3);
            String date = separator[0];
            String puTime = separator[1];
            String doTime = separator[2];
            JsonAdaptedAttendance currAttendance = new JsonAdaptedAttendance(date, puTime, doTime);
            attendances.add(currAttendance);
        }
        sortedFileScanner.close();

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        File jsonFile = new File(jsonFilePath);
        writer.writeValue(jsonFile, attendances);
    }

    /**
     * Stores the attendance details to edit the pet with.
     */
    public static class PetAttendanceDescriptor {
        private LocalDate attendanceDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;

        public PetAttendanceDescriptor() {}

        /**
         * Copy Constructor
         * @param petAttendanceDescriptor takes in another PetAttendanceDescriptor class
         */
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
