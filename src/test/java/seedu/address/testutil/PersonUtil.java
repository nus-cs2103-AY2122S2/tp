package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_BOB = "Likes rock climbing.";
    public static final String VALID_REMARK_AMY = "Likes adventure.";
    public static final String VALID_TAG_COWORKER = "coworker";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + Name.PREFIX + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + Name.PREFIX + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + Phone.PREFIX + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + Phone.PREFIX + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + Email.PREFIX + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + Email.PREFIX + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + Address.PREFIX + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + Address.PREFIX + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = " " + Remark.PREFIX + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + Remark.PREFIX + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + Tag.PREFIX + VALID_TAG_FRIEND;
    public static final String TAG_DESC_COWORKER = " " + Tag.PREFIX + VALID_TAG_COWORKER;

    public static final String INVALID_NAME_DESC = " " + Name.PREFIX + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + Phone.PREFIX + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + Email.PREFIX + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + Address.PREFIX; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + Tag.PREFIX + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final Person AMY = new Person(
        List.of(
            new Name(VALID_NAME_AMY),
            new Address(VALID_ADDRESS_AMY),
            new Email(VALID_EMAIL_AMY),
            new Phone(VALID_PHONE_AMY),
            new Remark(VALID_REMARK_AMY)),
        List.of(
            new Tag(VALID_TAG_FRIEND)
        ));

    public static final Person BOB = new Person(
        List.of(
            new Name(VALID_NAME_BOB),
            new Address(VALID_ADDRESS_BOB),
            new Email(VALID_EMAIL_BOB),
            new Phone(VALID_PHONE_BOB),
            new Remark(VALID_REMARK_BOB)),
        List.of(
            new Tag(VALID_TAG_FRIEND),
            new Tag(VALID_TAG_COWORKER)
        ));

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();

        for (Field f : person.getFields()) {
            sb.append(f.prefix).append(f.toString()).append(" ");
        }
        person.getTags().stream().forEach(
            s -> sb.append(Tag.PREFIX).append(s.value).append(" ")
        );
        return sb.toString();
    }
}
