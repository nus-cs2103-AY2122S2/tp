package seedu.trackbeau.testutil;

import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import java.util.Set;

import seedu.trackbeau.logic.commands.AddCommand;
import seedu.trackbeau.logic.commands.EditCommand;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.tag.Tag;

/**
 * A utility class for Customer.
 */
public class CustomerUtil {

    /**
     * Returns an add command string for adding the {@code customer}.
     */
    public static String getAddCommand(Customer customer) {
        return AddCommand.COMMAND_WORD + " " + getCustomerDetails(customer);
    }

    /**
     * Returns the part of command string for the given {@code customer}'s details.
     */
    public static String getCustomerDetails(Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + customer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + customer.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + customer.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + customer.getAddress().value + " ");
        sb.append(PREFIX_SKINTYPE + customer.getSkinType().value + " ");
        sb.append(PREFIX_HAIRTYPE + customer.getHairType().value + " ");
        sb.append(PREFIX_BIRTHDATE + customer.getBirthdate().toString() + " ");
        sb.append(PREFIX_REGDATE + customer.getRegDate().toString() + " ");
        customer.getStaffs().stream().forEach(
            s -> sb.append(PREFIX_STAFFS + s.tagName + " ")
        );
        customer.getServices().stream()
                .forEach(s -> sb.append(PREFIX_SERVICES + s.tagName + " "));
        customer.getAllergies().stream()
                .forEach(s -> sb.append(PREFIX_ALLERGIES + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCustomerDescriptor}'s details.
     */
    public static String getEditCustomerDescriptorDetails(EditCommand.EditCustomerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getSkinType().ifPresent(skinType -> sb.append(PREFIX_SKINTYPE).append(skinType.value).append(" "));
        descriptor.getHairType().ifPresent(hairType -> sb.append(PREFIX_HAIRTYPE).append(hairType.value).append(" "));
        descriptor.getBirthdate().ifPresent(birthdate -> sb.append(PREFIX_BIRTHDATE)
                .append(birthdate.toString()).append(" "));
        descriptor.getRegistrationDate().ifPresent(regDate -> sb.append(PREFIX_REGDATE)
                .append(regDate.toString()).append(" "));
        if (descriptor.getStaffs().isPresent()) {
            Set<Tag> tags = descriptor.getStaffs().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_STAFFS);
            } else {
                tags.forEach(s -> sb.append(PREFIX_STAFFS).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getServices().isPresent()) {
            Set<Tag> tags = descriptor.getServices().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_SERVICES);
            } else {
                tags.forEach(s -> sb.append(PREFIX_SERVICES).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getAllergies().isPresent()) {
            Set<Tag> tags = descriptor.getAllergies().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_ALLERGIES);
            } else {
                tags.forEach(s -> sb.append(PREFIX_ALLERGIES).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
