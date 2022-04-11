package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastContacted;
import seedu.address.model.client.Name;
import seedu.address.model.client.Note;
import seedu.address.model.client.Phone;
import seedu.address.model.client.PreferenceMap;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.UniquePolicyList;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String lastContacted;
    private final List<JsonAdaptedPolicy> policies = new ArrayList<>();
    private final String note;
    private final JsonAdaptedPreferenceMap preferenceMap;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("birthday") String birthday,
                             @JsonProperty("lastContacted") String lastContacted,
                             @JsonProperty("policies") List<JsonAdaptedPolicy> policies,
                             @JsonProperty("note") String note,
                             @JsonProperty("preferenceMap") JsonAdaptedPreferenceMap preferenceMap) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.lastContacted = lastContacted;
        if (policies != null) {
            this.policies.addAll(policies);
        }
        if (note != null) {
            this.note = note;
        } else {
            this.note = "";
        }
        if (preferenceMap != null) {
            this.preferenceMap = preferenceMap;
        } else {
            this.preferenceMap = new JsonAdaptedPreferenceMap(new PreferenceMap());
        }
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().value;
        lastContacted = source.getLastContacted().value;
        policies.addAll(source.getPolicies().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedPolicy::new)
                .collect(Collectors.toList()));
        note = source.getNote().value;
        preferenceMap = new JsonAdaptedPreferenceMap(source.getPreferenceMap());
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Policy> clientPolicies = new ArrayList<>();
        for (JsonAdaptedPolicy policy: policies) {
            clientPolicies.add(policy.toModelType());
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

        Email modelEmail;

        if (email.equals("")) {
            modelEmail = new Email();
        } else {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            modelEmail = new Email(email);
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }

        final Address modelAddress;

        if (address.equals("")) {
            modelAddress = new Address();
        } else {
            if (!Address.isValidAddress(address)) {
                throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
            }
            modelAddress = new Address(address);
        }

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthday.class.getSimpleName()));
        }

        final Birthday modelBirthday;

        if (birthday == "") {
            modelBirthday = new Birthday();
        } else {
            if (!Birthday.isValidBirthday(birthday)) {
                throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
            }
            if (!Birthday.isPastBirthday(birthday)) {
                throw new IllegalValueException(Birthday.MESSAGE_FUTURE_DATE);
            }
            modelBirthday = new Birthday(birthday);
        }

        if (lastContacted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LastContacted.class.getSimpleName()));
        }

        final LastContacted modelLastContacted;

        if (lastContacted == "") {
            modelLastContacted = new LastContacted();
        } else {
            if (!LastContacted.isValidLastContacted(lastContacted)) {
                throw new IllegalValueException(LastContacted.MESSAGE_CONSTRAINTS);
            }
            if (!LastContacted.isPastLastContacted(lastContacted)) {
                throw new IllegalValueException(LastContacted.MESSAGE_FUTURE_DATETIME);
            }
            modelLastContacted = new LastContacted(lastContacted);
        }

        assert note != null;
        final Note modelNote = new Note(note);


        assert preferenceMap != null;
        final PreferenceMap modelPreferenceMap = preferenceMap.toModelType();

        final UniquePolicyList modelPolicies = new UniquePolicyList();
        modelPolicies.setPolicies(clientPolicies);

        return new Client(modelName, modelPhone, modelEmail, modelAddress, modelBirthday, modelLastContacted,
                modelPolicies, modelNote, modelPreferenceMap);
    }
}
