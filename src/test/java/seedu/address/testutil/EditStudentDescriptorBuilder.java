package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditCommand.EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditCommand.EditStudentDescriptor descriptor) {
        this.descriptor = new EditCommand.EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditCommand.EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setTelegram(student.getTelegram());
        descriptor.setEmail(student.getEmail());
        descriptor.setGitHub(student.getGitHub());
        descriptor.setTutorialGroups(student.getTutorialGroups());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTelegram(String telegram) {
        descriptor.setTelegram(new Telegram(telegram));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code GitHub} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGitHub(String gitHub) {
        descriptor.setGitHub(new GitHub(gitHub));
        return this;
    }

    /**
     * Parses the {@code tutorialGroups} into a {@code Set<TutorialGroup>} and set it to the
     * {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTutorialGroup(String... tutorialGroups) {
        Set<TutorialGroup> tutorialGroupSet = Stream.of(tutorialGroups).map(TutorialGroup::new)
            .collect(Collectors.toSet());
        descriptor.setTutorialGroups(tutorialGroupSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
