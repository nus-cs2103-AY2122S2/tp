package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand.AddTutorialGroupDescriptor;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class AddTutorialGroupDescriptorBuilder {

    public static final AddTutorialGroupDescriptor VALID_TUTORIAL_GROUP_DESCRIPTOR_AMY =
            new AddTutorialGroupDescriptorBuilder(AMY).build();
    public static final AddTutorialGroupDescriptor VALID_TUTORIAL_GROUP_DESCRIPTOR_BOB =
            new AddTutorialGroupDescriptorBuilder(BOB).build();
    private AddTutorialGroupDescriptor descriptor;

    public AddTutorialGroupDescriptorBuilder() {
        descriptor = new AddTutorialGroupCommand.AddTutorialGroupDescriptor();
    }

    /**
     * Builds a AddTutorialGroupDescriptorBuilder based
     * an existing AddTutorialGroupDescriptor
     *
     * @param descriptor to copy
     */
    public AddTutorialGroupDescriptorBuilder(AddTutorialGroupCommand.AddTutorialGroupDescriptor descriptor) {
        this.descriptor = new AddTutorialGroupCommand.AddTutorialGroupDescriptor(descriptor);
    }

    /**
     * Builds a AddTutorialGroupDescriptorBuilder based
     * on the tutorial group input
     *
     * @param tg tutorial group(s) to be added
     */
    public AddTutorialGroupDescriptorBuilder(Set<TutorialGroup> tg) {
        this.descriptor = new AddTutorialGroupCommand.AddTutorialGroupDescriptor();
        descriptor.setTutorialGroups(tg);
    }

    /**
     * Builds a AddTutorialGroupDescriptorBuilder based
     * on an existing Student
     *
     * @param student to copy
     */
    public AddTutorialGroupDescriptorBuilder(Student student) {
        descriptor = new AddTutorialGroupCommand.AddTutorialGroupDescriptor();
        descriptor.setTutorialGroups(student.getTutorialGroups());
    }

    /**
     * Parses the {@code tutorialGroups} into a {@code Set<TutorialGroup>} and set it to the
     * {@code AddTutorialGroupDescriptor} that we are building.
     */
    public AddTutorialGroupDescriptorBuilder withTutorialGroup(String... tutorialGroups) {
        Set<TutorialGroup> tutorialGroupSet = Stream.of(tutorialGroups).map(TutorialGroup::new)
                .collect(Collectors.toSet());
        descriptor.setTutorialGroups(tutorialGroupSet);
        return this;
    }

    /**
     * @return the AddTutorialGroupDescriptor built
     */
    public AddTutorialGroupCommand.AddTutorialGroupDescriptor build() {
        return descriptor;
    }
}
