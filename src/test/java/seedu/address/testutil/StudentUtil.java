package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddStudentCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STUDENT_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_STUDENT_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_STUDENT_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_STUDENT_ADDRESS + student.getAddress().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_STUDENT_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_STUDENT_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_STUDENT_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_STUDENT_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_STUDENT_ADDRESS).append(
                address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_STUDENT_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_STUDENT_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
