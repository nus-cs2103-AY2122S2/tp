package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.AttendanceUtil;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Diet;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Pet}.
 */
class JsonAdaptedPet {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pet's %s field is missing!";

    private final String name;
    private final String ownerName;
    private final String phone;
    private final String address;
    private final String diet;
    private final String appointment;
    private final List<JsonAdaptedAttendance> attendance = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPet} with the given pet details.
     */
    @JsonCreator
    public JsonAdaptedPet(@JsonProperty("name") String name, @JsonProperty("ownerName") String ownerName,
                          @JsonProperty("phone") String phone, @JsonProperty("address") String address,
                          @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("diet") String diet,
                          @JsonProperty("appointment") String appointment,
                          @JsonProperty("attendance") List<JsonAdaptedAttendance> attendance) {
        this.name = name;
        this.ownerName = ownerName;
        this.phone = phone;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.diet = diet;
        this.appointment = appointment;
        if (attendance != null) {
            this.attendance.addAll(attendance);
        }
    }

    /**
     * Converts a given {@code Pet} into this class for Jackson use.
     */
    public JsonAdaptedPet(Pet source) {
        name = source.getName().fullName;
        ownerName = source.getOwnerName().value;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        diet = source.getDiet().value;
        appointment = source.getAppointment().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attendance.addAll(source.getAttendanceHashMap()
                .toCollection()
                .stream()
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted pet object into the model's {@code Pet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pet.
     */
    public Pet toModelType() throws IllegalValueException {
        final List<Tag> petTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            petTags.add(tag.toModelType());
        }

        final HashMap<LocalDate, AttendanceEntry> attendanceList = new HashMap<>();
        for (JsonAdaptedAttendance attendance : attendance) {
            LocalDate attendanceDateKey = AttendanceUtil.convertToModelDate(attendance.attendanceDate);
            attendanceList.put(attendanceDateKey, attendance.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (ownerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OwnerName.class.getSimpleName()));
        }
        if (!OwnerName.isValidOwnerName(ownerName)) {
            throw new IllegalValueException(OwnerName.MESSAGE_CONSTRAINTS);
        }
        final OwnerName modelOwnerName = new OwnerName(ownerName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (diet == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Diet.class.getSimpleName()));
        }
        final Diet modelDiet = new Diet(diet);

        if (appointment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }
        final Appointment modelAppointment = new Appointment(appointment);

        final Set<Tag> modelTags = new HashSet<>(petTags);

        final AttendanceHashMap modelAttendanceHashMap = new AttendanceHashMap(attendanceList);

        return new Pet(modelName, modelOwnerName, modelPhone, modelAddress, modelTags, modelDiet, modelAppointment,
                modelAttendanceHashMap);
    }

}
