package seedu.address.testutil;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Field;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

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
