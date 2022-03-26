package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Person ALICE = new Person(
        List.of(
            new Name("Alice Pauline"),
            new Address("123, Jurong West Ave 6, #08-111"),
            new Email("alice@example.com"),
            new Phone("94351253"),
            new Remark("Adventuring is life."),
            new Birthday("2000-10-10")),
        List.of(
            new Tag("friends")));

    public static final Person BENSON = new Person(
        List.of(
            new Name("Benson Meier"),
            new Address("311, Clementi Ave 2, #02-25"),
            new Email("benson@example.com"),
            new Phone("98765432"),
            new Remark("Hates to swim."),
            new Birthday("2000-11-15")),
        List.of(
            new Tag("friends"),
            new Tag("owesMoney")));

    public static final Person CARL = new Person(
        List.of(
            new Name("Carl Kurz"),
            new Address("Wall Street"),
            new Email("carl@example.com"),
            new Phone("95352563"),
            new Remark("Likes dogs."),
            new Birthday("1998-11-15")),
        List.of());

    public static final Person DANIEL = new Person(
        List.of(
            new Name("Daniel Meier"),
            new Address("10th Street"),
            new Email("daniel@example.com"),
            new Phone("87652533")),
        List.of(
            new Tag("friends")
        ));

    public static final Person ELLE = new Person(
        List.of(
            new Name("Elle Meyer"),
            new Address("Michegan Ave"),
            new Email("elle@example.com"),
            new Phone("9482224"),
            new Remark("Reads a lot."),
            new Birthday("1978-11-15")),
        List.of());

    public static final Person FIONA = new Person(
        List.of(
            new Name("Fiona Watanabe"),
            new Address("Little Tokyo"),
            new Email("fiona@example.com"),
            new Phone("9482427")),
        List.of());

    public static final Person GEORGE = new Person(
        List.of(
            new Name("George Best"),
            new Address("4th Street"),
            new Email("george@placeholder.net"),
            new Phone("9482442"),
            new Birthday("2001-01-10")),
        List.of());

    public static final Person IDA = new Person(
        List.of(
            new Name("Ida Ho"),
            new Address("Townsville Lane 3"),
            new Email("ida@placeholder.net"),
            new Phone("8482131")),
        List.of(
            new Tag("colleagues")
        ));

    public static final Person HOON = new Person(
        List.of(
            new Name("Ah Hoon"),
            new Address("123 Sesame Street"),
            new Email("hoon@placeholder.net"),
            new Phone("62353535")),
        List.of());

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        return new AddressBook(getTypicalPersons(), getEmptyTransactions());
    }

    public static List<Transaction> getEmptyTransactions() {
        return new ArrayList<>();
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, IDA, HOON));
    }
}
