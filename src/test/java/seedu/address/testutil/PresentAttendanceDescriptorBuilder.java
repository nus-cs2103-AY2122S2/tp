package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.logic.commands.PresentAttendanceCommand.PetAttendanceDescriptor;

public class PresentAttendanceDescriptorBuilder {

    private PetAttendanceDescriptor descriptor;

    public PresentAttendanceDescriptorBuilder() {
        descriptor = new PetAttendanceDescriptor();
    }

    public PresentAttendanceDescriptorBuilder(PetAttendanceDescriptor petAttendanceDescriptor) {
        descriptor = petAttendanceDescriptor;
    }

    public PresentAttendanceDescriptorBuilder withDate(String date) {
        descriptor.setAttendanceDate(LocalDate.parse(date));
        return this;
    }

    public PresentAttendanceDescriptorBuilder withPickUpTime(String pickUpTime) {
        descriptor.setPickUpTime(LocalTime.parse(pickUpTime));
        return this;
    }

    public PresentAttendanceDescriptorBuilder withDropOffTime(String dropOffTime) {
        descriptor.setDropOffTime(LocalTime.parse(dropOffTime));
        return this;
    }

    public PetAttendanceDescriptor build() {
        return descriptor;
    }
}
