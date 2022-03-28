package seedu.contax.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
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

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name,
                                  @JsonProperty("startDateTime") String startDateTime,
                                  @JsonProperty("duration") int duration,
                                  @JsonProperty("person") String person) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.person = person;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().name;
        startDateTime = DATETIME_FORMATTER.format(source.getStartDateTimeObject().value);
        duration = source.getDuration().value;
        person = (source.getPerson() != null) ? source.getPerson().getName().fullName : null;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @param personsList The current up-to-date persons list in the address book for matching the person
     *                    field in appointments.
     * @throws IllegalValueException If there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType(List<Person> personsList) throws IllegalValueException {

        final Name modelName = parseNameModel();
        final StartDateTime modelStartDateTime = parseStartDateTimeModel();
        final Duration modelDuration = parseDurationModel();
        final Person modelPerson = parsePersonModel(personsList);

        return new Appointment(modelName, modelStartDateTime, modelDuration, modelPerson);
    }

    /**
     * Performs the validation and parsing of Appointment name into a {@code Name} model.
     */
    private Name parseNameModel() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    /**
     * Performs the validation and parsing of Appointment start date-time into a {@code StartDateTime} model.
     */
    private StartDateTime parseStartDateTimeModel() throws IllegalValueException {
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
        return new StartDateTime(dateTime);
    }

    /**
     * Performs the validation and parsing of Appointment duration into a {@code Duration} model.
     */
    private Duration parseDurationModel() throws IllegalValueException {
        if (duration <= 0) {
            throw new IllegalValueException(INVALID_DURATION_MESSAGE);
        }
        return new Duration(duration);
    }

    /**
     * Performs the validation and parsing the person name associated with the appointment into a
     * {@code Person} model from the supplied personsList.
     */
    private Person parsePersonModel(List<Person> personsList) throws IllegalValueException {
        if (person != null) {
            return personsList.stream()
                    .filter(x -> x.getName().fullName.equals(person))
                    .findFirst().orElseThrow(() -> new IllegalValueException(INVALID_PERSON_MESSAGE));
        }
        return null;
    }

}
