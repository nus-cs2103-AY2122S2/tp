package seedu.address.testutil;

import java.util.List;

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
import seedu.address.model.policy.Premium;
import seedu.address.model.policy.UniquePolicyList;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "21-03-1999";
    public static final String DEFAULT_LAST_CONTACTED = "21-03-1999 21:03";

    public static final String DEFAULT_POLICY_NAME = "Policy";
    public static final String DEFAULT_COMPANY = "Policy Company";
    public static final String DEFAULT_POLICY_MANAGER = "Vijay";
    public static final String DEFAULT_PREMIUM = "100";
    public static final String DEFAULT_NOTE = "Test Note";
    public static final String DEFAULT_PREFERENCE_CAT = "Drink";
    public static final String DEFAULT_PREFERENCE_VALUE = "Coke";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthday birthday;
    private LastContacted lastContacted;
    private UniquePolicyList policies = new UniquePolicyList();
    private Note note;
    private PreferenceMap preferences = new PreferenceMap();

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        lastContacted = new LastContacted(DEFAULT_LAST_CONTACTED);

        Policy defaultPolicy = new Policy(new Name(DEFAULT_POLICY_NAME), new Name(DEFAULT_COMPANY),
                new Name(DEFAULT_POLICY_MANAGER),
                new Premium(DEFAULT_PREMIUM));
        policies.add(defaultPolicy);
        note = new Note(DEFAULT_NOTE);
        preferences.addPreference(DEFAULT_PREFERENCE_CAT, DEFAULT_PREFERENCE_VALUE);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        birthday = clientToCopy.getBirthday();
        lastContacted = clientToCopy.getLastContacted();
        policies.setPolicies(clientToCopy.getPolicies());
        note = clientToCopy.getNote();
        preferences.addAllPreferences(clientToCopy.getPreferenceMap());
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withAddress() {
        this.address = new Address();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = address.isEmpty() ? new Address() : new Address(address);
        return this;
    }

    /**
     * Sets the {@code birthday} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withBirthday() {
        this.birthday = new Birthday();
        return this;
    }

    /**
     * Sets the {@code birthday} of the {@code Client} that we are building.
     */
    public ClientBuilder withBirthday(String birthday) {
        this.birthday = birthday == "" ? new Birthday() : new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code lastContacted} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withLastContacted() {
        this.lastContacted = new LastContacted();
        return this;
    }

    /**
     * Sets the {@code lastContacted} of the {@code Client} that we are building.
     */
    public ClientBuilder withLastContacted(String lastContacted) {
        this.lastContacted = lastContacted.isEmpty() ? new LastContacted() : new LastContacted(lastContacted);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building to an empty string.
     */
    public ClientBuilder withEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = email.isEmpty() ? new Email() : new Email(email);
        return this;
    }

    /**
     * Parses the {@code policies} into a {@code Set<Policy>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withPolicies(List<Policy> policyList) {
        this.policies.setPolicies(policyList);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Client} that we are building.
     */
    public ClientBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Adds a {@code preference} to the {@code PreferenceMap} of the {@code Client} that we are building
     */
    public ClientBuilder withPreference(String category, String preference) {
        this.preferences.addPreference(category, preference);
        return this;
    }

    public Client build() {
        return new Client(name, phone, email, address, birthday, lastContacted, policies, note, preferences);
    }

}
