package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.logic.commands.AbsentAttendanceCommand.AbsentAttendanceDescriptor;

public class AbsentAttendanceDescriptorBuilder {

    private AbsentAttendanceDescriptor descriptor;

    public AbsentAttendanceDescriptorBuilder() {
        descriptor = new AbsentAttendanceDescriptor();
    }

    /**
     * Sets the attendance date of the {@code AbsentAttendanceDescriptorBuilder} that we are building.
     */
    public AbsentAttendanceDescriptorBuilder withDate(String date) {
        descriptor.setAttendanceDate(LocalDate.parse(date));
        return this;
    }

    public AbsentAttendanceDescriptor build() {
        return descriptor;
    }
}
