package seedu.tinner.testutil;

import static seedu.tinner.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_STIPEND;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import seedu.tinner.commons.core.index.Index;
import seedu.tinner.logic.commands.AddRoleCommand;
import seedu.tinner.logic.commands.EditRoleCommand;
import seedu.tinner.model.role.Role;

/**
 * A utility class for Role.
 */
public class RoleUtil {

    public static final DateTimeFormatter VALIDATION_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Returns an add command string for adding the {@code role}.
     */
    public static String getAddRoleCommand(Role role, Index index) {
        return AddRoleCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getRoleDetails(role);
    }

    /**
     * Formats the reminder date as a string to be used as input to generate the {@code addRoleCommand}.
     */
    public static String formatReminderDate(LocalDateTime reminderDate) {
        return reminderDate.format(VALIDATION_FORMATTER);
    }

    /**
     * Returns the part of command string for the given {@code role}'s details.
     */
    public static String getRoleDetails(Role role) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + role.getName().value + " ");
        sb.append(PREFIX_STATUS + role.getStatus().value + " ");
        sb.append(PREFIX_STIPEND + role.getStipend().value + " ");
        sb.append(PREFIX_DESCRIPTION + role.getDescription().value + " ");
        sb.append(PREFIX_REMINDER_DATE + formatReminderDate(role.getReminderDate().value) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRoleDescriptor}'s details.
     */
    public static String getEditRoleDescriptorDetails(EditRoleCommand.EditRoleDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.value).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.value).append(" "));
        descriptor.getStipend().ifPresent(stipend -> sb.append(PREFIX_STIPEND).append(stipend.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION).append(description.value)
                .append(" "));
        descriptor.getReminderDate().ifPresent(reminderDate -> sb.append(PREFIX_REMINDER_DATE)
                .append(formatReminderDate(reminderDate.value)).append(" "));
        return sb.toString();
    }
}

