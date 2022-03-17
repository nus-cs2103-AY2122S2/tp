package seedu.address.commons.util;

import static seedu.address.commons.util.StringUtil.removeAllWhiteSpaces;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import seedu.address.model.pet.Pet;

/**
 * A container for Attendance-related methods and functionality.
 */
public class AttendanceUtil {
    public static final String ATTENDANCE_DATE_FORMAT = "dd/MM";
    public static final DateTimeFormatter ATTENDANCE_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT);

    /**
     * Gets a stream of strings containing the attendance data of the pet.
     * @param pet the pet to get the attendance data of.
     * @return a Stream containing the string attendance data of the pet.
     */
    public static Stream<String> getAttendanceStringStream(Pet pet) {
        String filePath = "data/pets/" + removeAllWhiteSpaces(pet.getName().fullName) +
                pet.getPhone().value + ".txt";

        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException e) {
            //TODO Refactor this method into the storage manager for the attendance files and class
            return Stream.empty();
        }
    }
}
