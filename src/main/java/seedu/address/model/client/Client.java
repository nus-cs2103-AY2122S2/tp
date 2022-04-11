package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.model.policy.Policy;
import seedu.address.model.policy.UniquePolicyList;
import seedu.address.model.policy.exceptions.EmptyPolicyListException;
import seedu.address.model.policy.exceptions.InvalidPolicyIndexException;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private Birthday birthday;
    private LastContacted lastContacted;

    // Data fields
    private final UniquePolicyList policies = new UniquePolicyList();
    private Note note = new Note("");
    private final PreferenceMap preferences = new PreferenceMap();

    /**
     * Every field must be present and not null.
     *
     */
    public Client(Name name, Phone phone, Email email, Address address, Birthday birthday,
                  LastContacted lastContacted) {
        requireAllNonNull(name, phone, email, address, birthday, lastContacted);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.lastContacted = lastContacted;
    }

    /**
     * Every field must be present and not null.
     * Overloaded constructor for all commands.
     */
    public Client(Name name, Phone phone, Email email, Address address, Birthday birthday, LastContacted lastContacted,
                  UniquePolicyList policies, Note note, PreferenceMap preferences) {
        requireAllNonNull(name, phone, email, address, policies, note, preferences);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.lastContacted = lastContacted;
        this.policies.setPolicies(policies);
        this.note = note;
        this.preferences.addAllPreferences(preferences);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public int getAge() {
        return birthday.getAge();
    }

    public int getBirthdayMonth() {
        return birthday.getMonth();
    }

    public LastContacted getLastContacted() {
        return lastContacted;
    }

    public PreferenceMap getPreferenceMap() {
        return this.preferences;
    }

    /**
     * Returns an immutable policy list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public UniquePolicyList getPolicies() {
        return policies;
    }

    /**
     * Returns a note
     */
    public Note getNote() {
        return note;
    }
    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    public Policy getPolicy(int index) throws EmptyPolicyListException, InvalidPolicyIndexException {
        List<Policy> policyList = policies.asUnmodifiableObservableList();

        if (policyList.isEmpty()) {
            throw new EmptyPolicyListException();
        }
        if (index < 0 || index >= policyList.size()) {
            throw new InvalidPolicyIndexException();
        }
        return policyList.get(index);
    }

    /**
     * Add {@code policyToAdd} to this client
     */
    public Client addPolicy(Policy policyToAdd) {
        UniquePolicyList updatedPolicyList = new UniquePolicyList();
        updatedPolicyList.setPolicies(policies);

        updatedPolicyList.add(policyToAdd);
        return new Client(name, phone, email, address, birthday, lastContacted, updatedPolicyList, note,
                preferences);
    }

    /**
     * Update {@code editedPolicy} for this client
     */
    public Client setPolicy(int index, Policy editedPolicy) {
        UniquePolicyList updatedPolicyList = new UniquePolicyList();
        updatedPolicyList.setPolicies(policies);
        Policy prevPolicy = getPolicy(index);
        updatedPolicyList.setPolicy(prevPolicy, editedPolicy);
        return new Client(name, phone, email, address, birthday, lastContacted, updatedPolicyList, note,
                preferences);
    }

    /**
     * Remove {@code policyToRemove} from this client
     */
    public Client removePolicy(Policy policyToRemove) {
        UniquePolicyList updatedPolicyList = new UniquePolicyList();
        updatedPolicyList.setPolicies(policies);
        updatedPolicyList.remove(policyToRemove);
        return new Client(name, phone, email, address, birthday, lastContacted, updatedPolicyList, note,
                preferences);
    }

    /**
     * Adds the key, value pair to the PreferenceMap for this client
     */
    public void addPreference(String key, String value) {
        this.preferences.addPreference(key, value);
    }

    /**
     * Deletes the key from the PreferenceMap for this client
     * @param key - the key to be deleted from the PreferenceMap
     */
    public void deletePreference(String key) {
        this.preferences.deletePreference(key);
    }

    /**
     * Updates the lastContacted LastContacted of this client.
     */
    public Client updateLastContacted(LastContacted lastContacted) {
        return new Client(name, phone, email, address, birthday, lastContacted, policies, note, preferences);
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, lastContacted, policies, preferences, note);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Birthday: ")
                .append(getBirthday())
                .append("; Last Contacted: ")
                .append(getLastContacted())
                .append("; Policies: ")
                .append(getPolicies().asUnmodifiableObservableList().size())
                .append("; Note: ")
                .append(getNote())
                .append("; Preferences: ")
                .append(getPreferenceMap().size());
        return builder.toString();
    }
}
