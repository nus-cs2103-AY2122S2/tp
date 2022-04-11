package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.AttendanceList;

public class JsonAdaptedAttendanceList {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Weeks is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Weeks must be numeric and range from 1-60!";

    private final String weeks;
    private final List<JsonAdaptedAttendance> attendances = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAttendanceList} from the given details.
     */
    @JsonCreator
    public JsonAdaptedAttendanceList(@JsonProperty("weeks") String weeks,
                                        @JsonProperty("attendances") List<JsonAdaptedAttendance> attendances) {
        this.weeks = weeks;
        if (attendances != null) {
            this.attendances.addAll(attendances);
        }
    }

    /**
     * Converts a given {@code AttendanceList} into a {@code JsonAdaptedAttendanceList} for Jackson use.
     */
    public JsonAdaptedAttendanceList(AttendanceList source) {
        weeks = String.valueOf(source.getWeeks());
        attendances.addAll(source.getAttendances()
            .stream()
            .map(JsonAdaptedAttendance::new)
            .collect(Collectors.toList()));
    }

    public int getWeeks() throws IllegalValueException {
        if (weeks == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }
        if (!weeks.chars().allMatch(Character::isDigit) || !(Integer.parseInt(weeks) > 0)
                || !(Integer.parseInt(weeks) <= 60)) {
            throw new IllegalValueException(INVALID_FIELD_MESSAGE_FORMAT);
        }
        return Integer.parseInt(weeks);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code AttendanceList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance list.
     */
    public AttendanceList toModelType() throws IllegalValueException {
        final AttendanceList modelAttendanceList = new AttendanceList(new ArrayList<>(), getWeeks());

        for (JsonAdaptedAttendance studentAttendance : attendances) {
            modelAttendanceList.getAttendances().add(studentAttendance.toModelType());
        }

        return modelAttendanceList;
    }
}
