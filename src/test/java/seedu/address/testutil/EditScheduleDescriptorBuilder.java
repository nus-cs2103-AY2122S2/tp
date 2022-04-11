package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;


public class EditScheduleDescriptorBuilder {
    private EditCommand.EditScheduleDescriptor descriptor;

    public EditScheduleDescriptorBuilder() {
        descriptor = new EditCommand.EditScheduleDescriptor();
    }

    public EditScheduleDescriptorBuilder(EditCommand.EditScheduleDescriptor descriptor) {
        this.descriptor = new EditCommand.EditScheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditScheduleDescriptor} with fields containing {@code schedule}'s details
     */
    public EditScheduleDescriptorBuilder(Schedule schedule) {
        descriptor = new EditCommand.EditScheduleDescriptor();
        descriptor.setScheduleName(schedule.getScheduleName());
        descriptor.setScheduleDescription(schedule.getScheduleDescription());
        descriptor.setScheduleDateTime(schedule.getScheduleDateTime());
    }

    /**
     * Sets the {@code ScheduleName} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withName(String name) {
        descriptor.setScheduleName(new ScheduleName(name));
        return this;
    }

    /**
     * Sets the {@code ScheduleDescription} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withDescription(String description) {
        descriptor.setScheduleDescription(new ScheduleDescription(description));
        return this;
    }

    /**
     * Sets the {@code ScheduleDateTime} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setScheduleDateTime(new ScheduleDateTime(dateTime));
        return this;
    }

    public EditCommand.EditScheduleDescriptor build() {
        return descriptor;
    }

}
