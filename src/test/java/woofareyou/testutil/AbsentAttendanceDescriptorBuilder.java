package woofareyou.testutil;

import java.time.LocalDate;

import woofareyou.logic.commands.AbsentAttendanceCommand;

public class AbsentAttendanceDescriptorBuilder {

    private AbsentAttendanceCommand.AbsentAttendanceDescriptor descriptor;

    public AbsentAttendanceDescriptorBuilder() {
        descriptor = new AbsentAttendanceCommand.AbsentAttendanceDescriptor();
    }

    /**
     * Sets the attendance date of the {@code AbsentAttendanceDescriptorBuilder} that we are building.
     */
    public AbsentAttendanceDescriptorBuilder withDate(String date) {
        descriptor.setAttendanceDate(LocalDate.parse(date));
        return this;
    }

    public AbsentAttendanceCommand.AbsentAttendanceDescriptor build() {
        return descriptor;
    }
}
