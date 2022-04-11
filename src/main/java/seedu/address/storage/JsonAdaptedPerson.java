package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String id;
    private final String name;
    private final String company;
    private final String jobTitle;
    private final HashMap<String, JsonAdaptedPhone> numbers;
    private final HashMap<String, JsonAdaptedEmail> emails;
    private final HashMap<String, JsonAdaptedAddress> addresses;
    private final List<JsonAdaptedPronoun> pronouns = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("company") String company,
            @JsonProperty("jobTitle") String jobTitle,
            @JsonProperty("numbers") HashMap<String, JsonAdaptedPhone> numbers,
            @JsonProperty("emails") HashMap<String, JsonAdaptedEmail> emails,
            @JsonProperty("addresses") HashMap<String, JsonAdaptedAddress> addresses,
            @JsonProperty("pronouns") List<JsonAdaptedPronoun> pronouns,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.jobTitle = jobTitle;
        this.numbers = numbers;
        this.emails = emails;
        this.addresses = addresses;
        if (pronouns != null) {
            this.pronouns.addAll(pronouns);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        id = source.getId().toString();
        name = source.getName().fullName;
        company = source.getCompany().map(c -> c.company).orElse(null);
        jobTitle = source.getJobTitle().map(j -> j.jobTitle).orElse(null);

        HashMap<String, JsonAdaptedPhone> numbersMap = new HashMap<String, JsonAdaptedPhone>();
        for (Label key : source.getNumbers().keySet()) {
            numbersMap.put(key.label, new JsonAdaptedPhone(source.getNumbers().get(key)));
        }
        numbers = numbersMap;

        HashMap<String, JsonAdaptedEmail> emailsMap = new HashMap<String, JsonAdaptedEmail>();
        for (Label key : source.getEmails().keySet()) {
            emailsMap.put(key.label, new JsonAdaptedEmail(source.getEmails().get(key)));
        }
        emails = emailsMap;

        HashMap<String, JsonAdaptedAddress> addressesMap = new HashMap<String, JsonAdaptedAddress>();
        for (Label key : source.getAddresses().keySet()) {
            addressesMap.put(key.label, new JsonAdaptedAddress(source.getAddresses().get(key)));
        }
        addresses = addressesMap;
        pronouns.addAll(source.getPronouns().stream()
                .map(JsonAdaptedPronoun::new)
                .collect(Collectors.toList()));

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
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!(company == null || Company.isValidCompany(company))) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        if (!(jobTitle == null || JobTitle.isValidJobTitle(jobTitle))) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }

        final Id modelId = new Id(id);
        final Name modelName = new Name(name);
        final Company modelCompany = company != null ? new Company(company) : null;
        final JobTitle modelJobTitle = jobTitle != null ? new JobTitle(jobTitle) : null;

        TreeMap<Label, Phone> modelNumbers = new TreeMap<>();
        if (numbers != null) {
            for (Map.Entry<String, JsonAdaptedPhone> mapElement : numbers.entrySet()) {
                String key = mapElement.getKey();
                modelNumbers.put(new Label(key, false), mapElement.getValue().toModelType());
            }
        }

        TreeMap<Label, Email> modelEmails = new TreeMap<>();
        if (emails != null) {
            for (Map.Entry<String, JsonAdaptedEmail> mapElement : emails.entrySet()) {
                String key = mapElement.getKey();
                modelEmails.put(new Label(key, false), mapElement.getValue().toModelType());
            }
        }

        TreeMap<Label, Address> modelAddresses = new TreeMap<>();
        if (addresses != null) {
            for (Map.Entry<String, JsonAdaptedAddress> mapElement : addresses.entrySet()) {
                String key = mapElement.getKey();
                modelAddresses.put(new Label(key, false), mapElement.getValue().toModelType());
            }
        }

        final List<Pronoun> personPronouns = new ArrayList<>();
        for (JsonAdaptedPronoun pronoun : pronouns) {
            personPronouns.add(pronoun.toModelType());
        }
        final Set<Pronoun> modelPronouns = new HashSet<>(personPronouns);

        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelId, modelName, modelNumbers, modelEmails, modelAddresses,
                modelCompany, modelJobTitle, modelPronouns, modelTags);
    }

    @Override
    public String toString() {
        return "JsonAdaptedPerson{"
                + "ID='" + id + '\''
                + "name='" + name + '\''
                + ", company='" + company + '\''
                + ", jobTitle='" + jobTitle + '\''
                + ", phone='" + numbers + '\''
                + ", email='" + emails + '\''
                + ", addresses='" + addresses + '\''
                + ", pronouns=" + pronouns
                + ", tagged=" + tagged
                + '}';
    }
}
