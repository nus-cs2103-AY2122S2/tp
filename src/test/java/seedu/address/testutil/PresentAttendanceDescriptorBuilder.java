package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;

/**
 * A utility class to help with building PresentAttendanceDescriptor objects.
 */
public class PresentAttendanceDescriptorBuilder {

    private PresentAttendanceDescriptor descriptor;

    public PresentAttendanceDescriptorBuilder() {
        descriptor = new PresentAttendanceDescriptor();
    }

    /**
     * Sets the attendance date of the {@code PresentAttendanceDescriptorBuilder} that we are building.
     */
    public PresentAttendanceDescriptorBuilder withDate(String date) {
        descriptor.setAttendanceDate(LocalDate.parse(date));
        return this;
    }

    /**
     * Sets the pick-up time of the {@code PresentAttendanceDescriptorBuilder} that we are building.
     */
    public PresentAttendanceDescriptorBuilder withPickUpTime(String pickUpTime) {
        descriptor.setPickUpTime(LocalTime.parse(pickUpTime));
        return this;
    }

    /**
     * Sets the drop-off time of the {@code PresentAttendanceDescriptorBuilder} that we are building.
     */
    public PresentAttendanceDescriptorBuilder withDropOffTime(String dropOffTime) {
        descriptor.setDropOffTime(LocalTime.parse(dropOffTime));
        return this;
    }

    public PresentAttendanceDescriptor build() {
        return descriptor;
    }
}
