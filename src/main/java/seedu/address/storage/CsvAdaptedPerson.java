package seedu.address.storage;

import static seedu.address.storage.CsvAdaptedTag.STRING_PRIORITY_MAP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * CSV-friendly version of {@link Person}.
 */
public class CsvAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String insurancePackage;
    private final String address;
    private final List<CsvAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code CsvAdaptedPerson} with the given person details.
     */
    public CsvAdaptedPerson(String name, String phone, String email, String insurancePackage, String address,
                            List<CsvAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.insurancePackage = insurancePackage;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for CSV use.
     * @param source The Person object to convert from.
     */
    public CsvAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        insurancePackage = source.getInsurancePackage().packageName;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(CsvAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * A constructor that takes in a comma-separated string to a CsvAdaptedPerson.
     * @param s The String representation of this person.
     */
    public CsvAdaptedPerson(String s) {

        String[] personDetails = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        name = cleanup(personDetails[0]);
        phone = cleanup(personDetails[1]);
        email = cleanup(personDetails[2]);
        insurancePackage = cleanup(personDetails[3]);
        address = cleanup(personDetails[4]);

        // if the tags column in the CSV is empty, personDetails.length == 5
        // checking is needed to ensure that there are tags in the CSV for this person
        if (personDetails.length > 5 && personDetails[5].length() > 0) {
            String allTagsString = cleanup(personDetails[5]);

            // tags are internally separated by |
            String[] tags = allTagsString.split("\\|");
            for (String tagString : tags) {
                String possiblePriority = tagString.length() > 4
                        ? tagString.substring(tagString.length() - 4) : "";
                Priority p = STRING_PRIORITY_MAP.get(possiblePriority);
                if (p != null) {
                    tagString = tagString.substring(0, tagString.length() - 4);
                }

                if (tagString.length() > 0) {
                    tagged.add(new CsvAdaptedTag(tagString, p));
                }
            }
        }

    }

    /**
     * Converts this CSV-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (CsvAdaptedTag tag : tagged) { //change here
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

        if (insurancePackage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InsurancePackage.class.getSimpleName()));
        }
        if (!InsurancePackage.isValidInsurancePackage(insurancePackage)) {
            throw new IllegalValueException(InsurancePackage.MESSAGE_CONSTRAINTS);
        }
        final InsurancePackage modelInsurancePackage = new InsurancePackage(insurancePackage);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final ArrayList<Tag> modelTags = new ArrayList<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelInsurancePackage, modelAddress, modelTags);
    }


    /**
     * Converts this CsvAdaptedPerson to a comma-separated string.
     * To be used for storing a CsvAdaptedPerson into a CSV file.
     *
     * @return The String representation of this person.
     */
    public String toCsvString() {
        String tags = getTagsAsString(tagged);
        return Stream.of(name, phone, email, insurancePackage, address, tags)
                .map(CsvAdaptedPerson::addQuotes)
                .collect(Collectors.joining(","));
    }

    /**
     * Converts the List of Tags into a single string, to be stored in the CSV file/
     *
     * @param tags the tags to convert to String.
     * @return a single String representing the tags associated with this CsvAdaptedPerson.
     */
    public static String getTagsAsString(List<CsvAdaptedTag> tags) {
        return tags.stream()
                .map(CsvAdaptedTag::getTagNameString)
                .collect(Collectors.joining("|"));
    }

    /**
     * Adds quotes around a CsvAdaptedPerson's field, for the purposes of storing it into CSV.
     * This is necessary in case the existing field value has commas inside.
     *
     * @param s the String to add double quotes around.
     * @return the CSV-friendly version of the string.
     */
    public static String addQuotes(String s) {
        return '"' + s + '"';
    }

    /**
     * Removes quotes around the field read from the CSV file, if exists.
     *
     * @param s the raw String read from the CSV file.
     * @return the actual value of the field.
     */
    public static String cleanup(String s) {
        if (s.length() > 0 // if the string has characters
                && s.charAt(0) == '"' // if the string starts with "
                && s.charAt(s.length() - 1) == '"') { // if the string ends with "
            return s.substring(1, s.length() - 1);
        } else {
            return s;
        }
    }

    /**
     * Returns true if both CsvAdaptedPersons have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CsvAdaptedPerson)) {
            return false;
        }

        CsvAdaptedPerson otherPerson = (CsvAdaptedPerson) other;
        return otherPerson.name.equals(name)
                && otherPerson.phone.equals(phone)
                && otherPerson.email.equals(email)
                && otherPerson.insurancePackage.equals(insurancePackage)
                && otherPerson.address.equals(address)
                && new HashSet<>(otherPerson.tagged).equals(new HashSet<>(tagged));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, tagged);
    }
}
