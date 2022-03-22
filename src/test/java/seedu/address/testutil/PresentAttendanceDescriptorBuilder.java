package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.logic.commands.PresentAttendanceCommand.PetAttendanceDescriptor;

/**
 * A utility class to help with building PresentAttendanceEntry objects.
 */
public class PresentAttendanceDescriptorBuilder {

    private PetAttendanceDescriptor descriptor;

    public PresentAttendanceDescriptorBuilder() {
        descriptor = new PetAttendanceDescriptor();
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

    public PetAttendanceDescriptor build() {
        return descriptor;
    }
}
