package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
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
        return " " + AddCommand.COMMAND_WORD + " " + PREFIX_PLAYER + " " + getPersonDetails(person);
    }

    /**
     * Returns an edit command string for adding the {@code person}.
     */
    public static String getEditCommand(Person person) {
        return " " + EditCommand.COMMAND_WORD + " " + PREFIX_PLAYER + VALID_NAME_BOB + " " + getPersonDetails(person);
    }

    /**
     * Returns a delete command string for adding the {@code person}.
     */
    public static String getDeleteCommand(Person person) {
        return " " + DeleteCommand.COMMAND_WORD + " " + PREFIX_PLAYER + person.getName().fullName;
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_HEIGHT + person.getHeight().value + " ");
        sb.append(PREFIX_WEIGHT + person.getWeight().value + " ");
        sb.append(PREFIX_JERSEY_NUMBER + person.getJerseyNumber().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getJerseyNumber().ifPresent(jerseyNumber ->
                sb.append(PREFIX_JERSEY_NUMBER).append(jerseyNumber.value).append(" "));
        descriptor.getHeight().ifPresent(height -> sb.append(PREFIX_HEIGHT).append(height.value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
