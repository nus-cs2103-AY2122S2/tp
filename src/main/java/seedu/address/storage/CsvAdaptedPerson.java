package seedu.address.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class CsvAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String insurancePackage;
    private final String address;
    private final List<CsvAdaptedTag> tagged = new ArrayList<>();

    /**
     * Converts a given {@code Person} into this class for CSV use.
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
     * Converts this CSV-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (CsvAdaptedTag tag : tagged) {
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

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelInsurancePackage, modelAddress, modelTags);
    }

//    public String escapeSpecialCharacters(String data) {
//        String escapedData = data.replaceAll("\\R", " ");
//        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
//            data = data.replace("\"", "\"\"");
//            escapedData = "\"" + data + "\"";
//        }
//        return escapedData;
//    }


    /**
     * Converts this CsvAdaptedPerson to a comma-separated string.
     *
     * @return The String representation of this person.
     */
    public String toCsvString() {
        String tags = getTagsAsString();
        return Stream.of(name, phone, email, insurancePackage, address, tags)
                .map(this::addQuotes)
                .collect(Collectors.joining(","));
    }

    /**
     * Converts a comma-separated string to a Person.
     *
     * @return The String representation of this person.
     */
    public static Person fromCsvString(String s) {

        String[] personDetails = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        Set<Tag> tagsSet = new HashSet<>();

        if (personDetails.length > 5 && personDetails[5].length() > 0) {
            String allTagsString = cleanup(personDetails[5]);

            String[] tags = allTagsString.split("\\|");
            for (String tagString : tags) {
                if (tagString.length() > 0) {
                    tagsSet.add(new Tag(tagString));
                }
            }
        }
        return new Person(
                new Name(cleanup(personDetails[0])),
                new Phone(cleanup(personDetails[1])),
                new Email(cleanup(personDetails[2])),
                new InsurancePackage(cleanup(personDetails[3])),
                new Address(cleanup(personDetails[4])),
                tagsSet);
    }


    public String getTagsAsString() {
        return tagged.stream()
                .map(CsvAdaptedTag::getTagName)
                .collect(Collectors.joining("|"));
    }

    public String addQuotes(String s) {
        return '"' + s + '"';
    }

    public static String cleanup(String s) {
        if (s.length() > 0 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
            return s.substring(1, s.length() - 1);
        } else {
            return s;
        }
    }
}
