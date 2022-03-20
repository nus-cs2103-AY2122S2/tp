package seedu.address.storage;

import static seedu.address.commons.util.AttendanceUtil.isValidIsPresentString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.AttendanceUtil;
import seedu.address.model.attendance.AbsentAttendanceEntry;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;

/**
 * Jackson-friendly version of {@link AttendanceEntry}.
 */
public class JsonAdaptedAttendance {

    public final String attendanceDate;
    public final String isPresent;
    public final String pickUpTime;
    public final String dropOffTime;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given pet attendance details.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("attendance date") String attendanceDate,
                                 @JsonProperty("isPresent") String isPresent,
                                 @JsonProperty("Pick up time") String pickUpTime,
                                 @JsonProperty("Drop off time") String dropOffTime) {
        this.attendanceDate = attendanceDate;
        this.isPresent = isPresent;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
    }

    /**
     * Constructs a given {@code Attendance} into this class for Jackson use.
     *
     * @param attendanceEntry the attendance of the pet.
     */
    public JsonAdaptedAttendance(AttendanceEntry attendanceEntry) {

        this.attendanceDate = attendanceEntry.getAttendanceDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.isPresent = attendanceEntry.getIsPresent().toString();

        Optional<LocalTime> optionalPickUpTime = attendanceEntry.getPickUpTime();
        Optional<LocalTime> optionalDropOffTime = attendanceEntry.getDropOffTime();

        this.pickUpTime = optionalPickUpTime.isEmpty()
                ? ""
                : optionalPickUpTime.map(t -> t.format(DateTimeFormatter.ISO_LOCAL_TIME)).get();
        this.dropOffTime = optionalDropOffTime.isEmpty()
                ? ""
                : optionalDropOffTime.map(t -> t.format(DateTimeFormatter.ISO_LOCAL_TIME)).get();
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance object.
     */
    public AttendanceEntry toModelType() throws IllegalValueException {

        LocalDate modelAttendanceDate;
        Boolean modelIsPresent;
        LocalTime modelPickUpTime;
        LocalTime modelDropOffTime;

        if (!isValidIsPresentString(isPresent)) {
            throw new IllegalValueException(AttendanceEntry.MESSAGE_INVALID_ISPRESENT);
        }

        modelIsPresent = Boolean.parseBoolean(isPresent);

        try {
            modelAttendanceDate = AttendanceUtil.convertToModelDate(attendanceDate);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(pe.getMessage());
        }

        if (!modelIsPresent) {
            return new AbsentAttendanceEntry(modelAttendanceDate);
        }

        try {
            modelPickUpTime = AttendanceUtil.convertToModelTime(pickUpTime);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(pe.getMessage());
        }

        try {
            modelDropOffTime = AttendanceUtil.convertToModelTime(dropOffTime);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(pe.getMessage());
        }

        if (!PresentAttendanceEntry.isValidTimings(modelPickUpTime, modelDropOffTime)) {
            throw new IllegalValueException(PresentAttendanceEntry.MESSAGE_TIME_CONSTRAINTS);
        }

        return new PresentAttendanceEntry(modelAttendanceDate, modelPickUpTime, modelDropOffTime);
    }
}
