package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAdaptedAttendance {

    public final String attendanceDate;
    public final String pickUpTime;
    public final String dropOffTime;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given pet attendance details.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("attendance date") String attendanceDate,
                                 @JsonProperty("Pick up time") String pickUpTime,
                                 @JsonProperty("Drop off time") String dropOffTime) {
        this.attendanceDate = attendanceDate;
        this.pickUpTime = pickUpTime;
        this.dropOffTime = dropOffTime;
    }
}
