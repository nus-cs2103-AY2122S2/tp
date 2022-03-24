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
        this.isPresent = attendanceEntry.getIsPresent().get().toString();

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
        if (!isValidIsPresentString(isPresent)) {
            throw new IllegalValueException(AttendanceEntry.MESSAGE_INVALID_ISPRESENT);
        }

        boolean modelIsPresent = Boolean.parseBoolean(isPresent);

        if (!modelIsPresent) {
            return convertToAbsentEntry(attendanceDate); // absent attendance entry
        }

        PresentAttendanceEntry presentAttendanceEntry = convertToPresentEntry(attendanceDate, pickUpTime, dropOffTime);

        if (!presentAttendanceEntry.isValidTimings()) {
            throw new IllegalValueException(PresentAttendanceEntry.MESSAGE_TIME_CONSTRAINTS);
        }

        return presentAttendanceEntry; // present attendance entry
    }

    /**
     * Creates an {@code AbsentAttendanceEntry} object for model use.
     * @param attendanceDate the attendance date in string format.
     * @return an {@code AbsentAttendanceEntry} object.
     * @throws IllegalValueException if the given attendance date string is invalid.
     */
    private AbsentAttendanceEntry convertToAbsentEntry(String attendanceDate) throws IllegalValueException {
        LocalDate modelAttendanceDate;

        try {
            modelAttendanceDate = AttendanceUtil.convertToModelDate(attendanceDate);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(pe.getMessage());
        }

        return new AbsentAttendanceEntry(modelAttendanceDate);
    }

    /**
     * Creates a {@code PresentAttendanceEntry} object for model use.
     * @param attendanceDate the attendance date in string format.
     * @param pickUpTime the pick-up time in string format.
     * @param dropOffTime the drop-off time in string format.
     * @return a {@code PresentAttendanceEntry} object.
     * @throws IllegalValueException if the given attendance date, pick-up time or drop-off time string is invalid.
     */
    private PresentAttendanceEntry convertToPresentEntry(String attendanceDate, String pickUpTime, String dropOffTime)
        throws IllegalValueException {
        LocalDate modelAttendanceDate;
        LocalTime modelPickUpTime;
        LocalTime modelDropOffTime;

        try {
            modelAttendanceDate = AttendanceUtil.convertToModelDate(attendanceDate);
            modelPickUpTime = AttendanceUtil.convertToModelTime(pickUpTime);
            modelDropOffTime = AttendanceUtil.convertToModelTime(dropOffTime);
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(pe.getMessage());
        }

        return new PresentAttendanceEntry(modelAttendanceDate, modelPickUpTime, modelDropOffTime);
    }
}
