package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.person.Description;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setDateTime(event.getDateTime());
        descriptor.setDescription(event.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code addFriends} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withAddFriend(String... addFriend) {
        Set<Name> nameSet = Stream.of(addFriend).map(Name::new).collect(Collectors.toSet());
        descriptor.setAddFriendNames(nameSet);
        return this;
    }

    /**
     * Sets the {@code removeFriends} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withRemoveFriend(String... removeFriend) {
        Set<Name> nameSet = Stream.of(removeFriend).map(Name::new).collect(Collectors.toSet());
        descriptor.setAddFriendNames(nameSet);
        return this;
    }



    public EditEventDescriptor build() {
        return descriptor;
    }
}
