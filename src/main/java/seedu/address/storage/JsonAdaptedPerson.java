package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Info;
import seedu.address.model.person.MeetingDate;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PrevDateMet;
import seedu.address.model.person.Salary;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String flag;
    private final String prevDateMet;
    private final String salary;
    private final String info;
    private final String scheduledMeeting;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("flag") String flag, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("prevDateMet") String prevDateMet, @JsonProperty("salary") String salary,
            @JsonProperty("info") String info, @JsonProperty("scheduledMeeting") String scheduledMeeting) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.flag = flag;
        this.prevDateMet = prevDateMet;
        this.salary = salary;
        this.info = info;
        this.scheduledMeeting = scheduledMeeting;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        flag = source.getFlag().toString();
        prevDateMet = source.getPrevDateMet().toString();
        salary = source.getSalary().value;
        info = source.getInfo().value;
        scheduledMeeting = source.getScheduledMeeting().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (flag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Flag.class.getSimpleName()));
        }
        if (!Flag.isValidFlag(flag)) {
            throw new IllegalValueException(Flag.MESSAGE_CONSTRAINTS);
        }
        final Flag modelFlag = new Flag(flag);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (prevDateMet == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        if (!PrevDateMet.isValidPrevDateMet(prevDateMet)) {
            throw new IllegalValueException(PrevDateMet.MESSAGE_CONSTRAINTS);
        }
        final PrevDateMet modelPrevDateMet = new PrevDateMet(prevDateMet);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        if (!Info.isValidInfo(info)) {
            throw new IllegalValueException(PrevDateMet.MESSAGE_CONSTRAINTS);
        }
        final Info modelInfo = new Info(info);

        if (scheduledMeeting == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        ScheduledMeeting meeting;
        if (scheduledMeeting.equals("No meeting scheduled")) {
            return new Person(modelName, modelPhone, modelEmail, modelAddress, modelFlag,
                    modelTags, modelPrevDateMet, modelSalary, modelInfo);
        } else {
            meeting = parseScheduledMeeting();
        }
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelFlag,
                modelTags, modelPrevDateMet, modelSalary, modelInfo, meeting);
    }

    private ScheduledMeeting parseScheduledMeeting() throws IllegalValueException {
        ScheduledMeeting meeting;
        String[] meetingSplit = scheduledMeeting.split(" ");
        String meetingDate = meetingSplit[0];
        String meetingTime = meetingSplit[1];
        if (!MeetingDate.isValidDate(meetingDate)) {
            throw new IllegalValueException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
        if (!MeetingTime.isValidTime(meetingTime)) {
            throw new IllegalValueException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        meeting = new ScheduledMeeting(new MeetingDate(meetingDate), new MeetingTime(meetingTime));
        return meeting;
    }

}
