package woofareyou.testutil;

import java.util.Set;

import woofareyou.logic.commands.AddCommand;
import woofareyou.logic.commands.EditCommand;
import woofareyou.logic.parser.CliSyntax;
import woofareyou.model.pet.Pet;
import woofareyou.model.tag.Tag;


/**
 * A utility class for Pet.
 */
public class PetUtil {

    /**
     * Returns an add command string for adding the {@code pet}.
     */
    public static String getAddCommand(Pet pet) {
        return AddCommand.COMMAND_WORD + " " + getPetDetails(pet);
    }

    /**
     * Returns the part of command string for the given {@code pet}'s details.
     */
    public static String getPetDetails(Pet pet) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + pet.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + pet.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_OWNER_NAME + pet.getOwnerName().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + pet.getAddress().value + " ");
        pet.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPetDescriptor}'s details.
     */
    public static String getEditPetDescriptorDetails(EditCommand.EditPetDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getOwnerName().ifPresent(ownerName -> sb.append(CliSyntax.PREFIX_OWNER_NAME).append(ownerName.value)
                .append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(CliSyntax.PREFIX_ADDRESS)
                .append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
