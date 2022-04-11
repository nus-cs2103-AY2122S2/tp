package seedu.contax.testutil;

import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private final EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details.
     * Does not set the person index in the edit descriptor.
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setName(appointment.getName());
        descriptor.setDuration(appointment.getDuration());
        descriptor.setStartDate(appointment.getStartDateTime().toLocalDate());
        descriptor.setStartTime(appointment.getStartDateTime().toLocalTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(DateUtil.parseDate(startDate).orElse(null));
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(DateUtil.parseTime(startTime).orElse(null));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDuration(int duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDuration(String duration) {
        return this.withDuration(Integer.parseInt(duration));
    }

    /**
     * Sets the {@code personIndex} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPerson(Index personIndex) {
        descriptor.setPersonIndex(personIndex);
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
