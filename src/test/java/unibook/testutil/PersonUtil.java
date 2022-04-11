package unibook.testutil;

import java.util.Set;

import unibook.logic.commands.AddCommand;
import unibook.logic.commands.EditCommand.EditPersonDescriptor;
import unibook.logic.parser.CliSyntax;
import unibook.model.module.Module;
import unibook.model.person.Person;
import unibook.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_OPTION + "student " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + person.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_EMAIL + person.getEmail().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        person.getModules().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_MODULE + s.getModuleCode().toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(CliSyntax.PREFIX_EMAIL).append(email.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getModules().isPresent()) {
            Set<Module> modules = descriptor.getModules().get();
            if (modules.isEmpty()) {
                sb.append(CliSyntax.PREFIX_NEWMOD).append(" ");
            } else {
                modules.forEach(s -> sb.append(CliSyntax.PREFIX_NEWMOD)
                    .append(s.getModuleCode().toString())
                    .append(" "));
            }
        }
        return sb.toString();
    }
}
