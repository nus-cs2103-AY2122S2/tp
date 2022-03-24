package seedu.contax.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.Priority;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.person.Person;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public static final String INVALID_DATETIME_MESSAGE = "Appointment's StartDateTime is invalid!";
    public static final String INVALID_DURATION_MESSAGE = "Appointment's Duration is not a positive integer!";
    public static final String INVALID_PERSON_MESSAGE = "Appointment's person cannot be found!";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"; // ISO-8601 Specification
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    private final String name;
    private final String startDateTime;
    private final int duration;
    private final String person;
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name,
                                  @JsonProperty("startDateTime") String startDateTime,
                                  @JsonProperty("duration") int duration,
                                  @JsonProperty("person") String person,
                                  @JsonProperty("priority") String priority) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.person = person;
        this.priority = priority;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().name;
        startDateTime = DATETIME_FORMATTER.format(source.getStartDateTimeObject().value);
        duration = source.getDuration().value;
        person = (source.getPerson() != null) ? source.getPerson().getName().fullName : null;
        priority = source.getPriority().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @param addressBook The current up-to-date address book for matching the person field in appointments.
     * @throws IllegalValueException If there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartDateTime.class.getSimpleName()));
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(startDateTime, DATETIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalValueException(INVALID_DATETIME_MESSAGE);
        }

        final StartDateTime modelStartDateTime = new StartDateTime(dateTime);

        if (duration <= 0) {
            throw new IllegalValueException(INVALID_DURATION_MESSAGE);
        }

        final Duration modelDuration = new Duration(duration);

        Person modelPerson = null;
        if (person != null) {
            modelPerson = addressBook.getPersonList().stream()
                    .filter(x -> x.getName().fullName.equals(person))
                    .findFirst().orElseThrow(() -> new IllegalValueException(INVALID_PERSON_MESSAGE));
        }

        Priority modelPriority = Priority.LOW;
        if (priority != null) {
            switch (priority) {
            case "HIGH":
                modelPriority = Priority.HIGH;
                break;
            case "MEDIUM":
                modelPriority = Priority.MEDIUM;
                break;
            default:
                break;
            }
        }

        return new Appointment(modelName, modelStartDateTime, modelDuration, modelPerson, modelPriority);
    }

}
