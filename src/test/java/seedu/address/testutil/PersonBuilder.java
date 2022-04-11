package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.common.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniqueLogList;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DESCRIPTION = null;

    private FriendName name;
    private Phone phone;
    private Email email;
    private Address address;
    private Description description;
    private Set<Tag> tags;
    private List<Log> logs;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new FriendName(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        logs = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        description = personToCopy.getDescription();
        tags = new HashSet<>(personToCopy.getTags());
        logs = personToCopy.getLogs();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new FriendName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Log} of the {@code Person} that we are building.
     */
    public PersonBuilder withLogs(Log ... logs) {
        UniqueLogList uniqueLogList = new UniqueLogList();
        uniqueLogList.setLogs(List.of(logs));
        this.logs = uniqueLogList.asUnmodifiableObservableList();
        return this;
    }

    /**
     * Sets the {@code Log} of the {@code Person} that we are building.
     */
    public PersonBuilder withLogs(List<Log> logs) {
        UniqueLogList uniqueLogList = new UniqueLogList();
        uniqueLogList.setLogs(logs);
        this.logs = uniqueLogList.asUnmodifiableObservableList();
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, description, tags, logs);
    }
}
