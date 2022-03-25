package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.candidate.ApplicationStatus;
import seedu.address.model.candidate.Availability;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.Course;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.InterviewStatus;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.Seniority;
import seedu.address.model.candidate.StudentId;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditCandidateDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditCandidateDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditCandidateDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code candidate}'s details
     */
    public EditCandidateDescriptorBuilder(Candidate candidate) {
        descriptor = new EditPersonDescriptor();
        descriptor.setStudentId(candidate.getStudentId());
        descriptor.setName(candidate.getName());
        descriptor.setPhone(candidate.getPhone());
        descriptor.setEmail(candidate.getEmail());
        descriptor.setCourse(candidate.getCourse());
        descriptor.setSeniority(candidate.getSeniority());
        descriptor.setTags(candidate.getTags());
        descriptor.setApplicationStatus(candidate.getApplicationStatus());
        descriptor.setInterviewStatus(candidate.getInterviewStatus());
        descriptor.setAvailability(candidate.getAvailability());
    }

    /**
     * Sets the {@code StudentId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withStudentId(String id) {
        descriptor.setStudentId(new StudentId(id));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withCourse(String course) {
        descriptor.setCourse(new Course(course));
        return this;
    }

    /**
     * Sets the {@code Seniority} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withSeniority(String seniority) {
        descriptor.setSeniority(new Seniority(Integer.parseInt(seniority.substring(seniority.length() - 1))));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditCandidateDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withApplicationStatus(String applicationStatus) {
        descriptor.setApplicationStatus(new ApplicationStatus(applicationStatus));
        return this;
    }

    /**
     * Sets the {@code InterviewStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withInterviewStatus(String interviewStatus) {
        descriptor.setInterviewStatus(new InterviewStatus(interviewStatus));
        return this;
    }

    /**
     * Sets the {@code Availability} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCandidateDescriptorBuilder withAvailability(String availability) {
        descriptor.setAvailability(new Availability(availability));
        return this;
    }
}
