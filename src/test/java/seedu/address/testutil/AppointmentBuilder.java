package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_DESCRIPTION = "Meet James at UTown";
    public static final String DEFAULT_DATETIME = "08-08-2022 19:00";

    private Description description;
    private DateTime dateTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        dateTime = new DateTime(DEFAULT_DATETIME);
    }
    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        description = appointmentToCopy.getDescription();
        dateTime = appointmentToCopy.getDateTime();
    }
    /**
     * Sets the {@code Description} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    public Appointment build() {
        return new Appointment(description, dateTime);
    }
}
