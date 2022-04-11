package seedu.unite.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.unite.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.unite.model.person.Address;
import seedu.unite.model.person.Course;
import seedu.unite.model.person.Email;
import seedu.unite.model.person.MatricCard;
import seedu.unite.model.person.Name;
import seedu.unite.model.person.Person;
import seedu.unite.model.person.Phone;
import seedu.unite.model.person.Telegram;
import seedu.unite.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private final EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setCourse(person.getCourse());
        descriptor.setMatricCard(person.getMatricCard());
        descriptor.setTelegram(person.getTelegram());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCourse(String course) {
        descriptor.setCourse(new Course(course));
        return this;
    }

    /**
     * Sets the {@code matricCard} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMatricCard(String matricCard) {
        descriptor.setMatricCard(new MatricCard(matricCard));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }



    public EditPersonDescriptor build() {
        return descriptor;
    }


}
