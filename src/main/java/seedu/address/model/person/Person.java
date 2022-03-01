package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.logic.field.FieldParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private static final Map<Prefix, FieldParser<? extends Field>> FIELD_PARSERS;
    public static final Prefix[] PREFIXES;
    public static final Prefix[] REQUIRED_PREFIXES;

    private final HashMap<Prefix, Field> fields = new HashMap<>();
    private final Set<Tag> tags = new HashSet<>();

    // Static initialisation.
    static {
        HashMap<Prefix, FieldParser<? extends Field>> fieldParsers = new HashMap<>();

        // -------------------------- Do not modify above. --------------------------

        // Declare your fields here.
        fieldParsers.put(CliSyntax.PREFIX_NAME, ParserUtil::parseName);
        fieldParsers.put(CliSyntax.PREFIX_PHONE, ParserUtil::parsePhone);
        fieldParsers.put(CliSyntax.PREFIX_EMAIL, ParserUtil::parseEmail);
        fieldParsers.put(CliSyntax.PREFIX_ADDRESS, ParserUtil::parseAddress);

        // -------------------------- Do not modify below. --------------------------
        FIELD_PARSERS = Collections.unmodifiableMap(fieldParsers);
        PREFIXES = FIELD_PARSERS.keySet().toArray(new Prefix[0]);
        REQUIRED_PREFIXES = Arrays.stream(PREFIXES).filter(Prefix::isRequired).toArray(Prefix[]::new);
    }

    public static <T extends Field> FieldParser<T> getParser(Prefix prefix) {
        return (FieldParser<T>) FIELD_PARSERS.get(prefix);
    }

    /**
     * Every field must be present and not null.
     */
    @Deprecated
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        addField(CliSyntax.PREFIX_NAME, name);
        addField(CliSyntax.PREFIX_PHONE, phone);
        addField(CliSyntax.PREFIX_EMAIL, email);
        addField(CliSyntax.PREFIX_ADDRESS, address);
        this.tags.addAll(tags);
    }

    public Person() {
    }

    public void addField(Prefix prefix, Field field) {
        requireAllNonNull(prefix, field);
        fields.put(prefix, field);
    }

    public void removeField(Prefix prefix) {
        requireAllNonNull(prefix);
        if (Arrays.asList(REQUIRED_PREFIXES).contains(prefix)) {
            throw new IllegalArgumentException("Cannot remove mandatory fields.");
        }
        fields.remove(prefix);
    }

    public <T extends Field> T getField(Prefix prefix) {
        requireAllNonNull(prefix);
        return (T) fields.get(prefix);
    }

    public Name getName() {
        return getField(CliSyntax.PREFIX_NAME);
    }

    public Phone getPhone() {
        return getField(CliSyntax.PREFIX_PHONE);
    }

    public Email getEmail() {
        return getField(CliSyntax.PREFIX_EMAIL);
    }

    public Address getAddress() {
        return getField(CliSyntax.PREFIX_ADDRESS);
    }

    public void addTags(Collection<Tag> tags) {
        this.tags.addAll(tags);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void clearTags() {
        this.tags.clear();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), tags);
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
