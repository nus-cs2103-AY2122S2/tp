package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Address;
import seedu.address.model.application.Application;
import seedu.address.model.application.Details;
import seedu.address.model.application.Email;
import seedu.address.model.application.InterviewSlot;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;

/**
 * Edits the details of an existing application in InternApply.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the application identified "
            + "by the index number used in the displayed internship application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_JOBTITLE + "JOB TITLE] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_INTERVIEW_SLOT + "[INTERVIEW SLOT (format: " + InterviewSlot.FORMAT_DATETIME_INPUT + "] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_PRIORITY_TAG + "PRIORITY_TAG] "
            + "[" + PREFIX_APPLICATION_STATUS_TAG + "APPLICATION_STATUS_TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoeinc@privateltd.com "
            + PREFIX_INTERVIEW_SLOT + "25-03-2022 13:30 "
            + PREFIX_APPLICATION_STATUS_TAG + "INTERVIEWED";

    public static final String MESSAGE_EDIT_APPLICATION_SUCCESS = "Edited Application: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "A duplicate application with the same name, job title "
            + "and optional tags already exists in InternApply";

    private final Index index;
    private final EditApplicationDescriptor editApplicationDescriptor;

    /**
     * @param index of the application in the filtered application list to edit
     * @param editApplicationDescriptor details to edit the application with
     */
    public EditCommand(Index index, EditApplicationDescriptor editApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editApplicationDescriptor);

        this.index = index;
        this.editApplicationDescriptor = new EditApplicationDescriptor(editApplicationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
        }

        Application applicationToEdit = lastShownList.get(index.getZeroBased());
        Application editedApplication = createEditedPerson(applicationToEdit, editApplicationDescriptor);

        if (!applicationToEdit.isSameApplication(editedApplication) && model.hasApplication(editedApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.setApplication(applicationToEdit, editedApplication);
        model.updateFilteredApplicationList();
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication));
    }

    /**
     * Creates and returns a {@code Application} with the details of {@code applicationToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Application createEditedPerson(Application applicationToEdit,
                                                  EditApplicationDescriptor editApplicationDescriptor) {
        assert applicationToEdit != null;

        Name updatedName = editApplicationDescriptor.getName().orElse(applicationToEdit.getName());
        JobTitle updatedJobTitle = editApplicationDescriptor.getJobTitle().orElse(applicationToEdit.getJobTitle());
        Phone updatedPhone = editApplicationDescriptor.getPhone().orElse(applicationToEdit.getPhone());
        Email updatedEmail = editApplicationDescriptor.getEmail().orElse(applicationToEdit.getEmail());
        Address updatedAddress = editApplicationDescriptor.getAddress().orElse(applicationToEdit.getAddress());
        Set<Tag> updatedTags = editApplicationDescriptor.getTags(applicationToEdit.getTags())
                .orElse(applicationToEdit.getTags());
        InterviewSlot updatedInterviewSlot = editApplicationDescriptor.getInterviewSlot()
                .orElse(applicationToEdit.getInterviewSlot());
        Details updatedDetail = editApplicationDescriptor.getDetails().orElse(applicationToEdit.getDetails());

        return new Application(updatedName, updatedJobTitle, updatedPhone, updatedEmail, updatedAddress,
                updatedInterviewSlot, updatedDetail, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editApplicationDescriptor.equals(e.editApplicationDescriptor);
    }

    /**
     * Stores the details to edit the application with. Each non-empty field value will replace the
     * corresponding field value of the application.
     */
    public static class EditApplicationDescriptor {
        private Name name;
        private JobTitle jobTitle;
        private Phone phone;
        private Email email;
        private Address address;
        private InterviewSlot interviewSlot;
        private Details details;
        private Set<Tag> tags;

        public EditApplicationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicationDescriptor(EditApplicationDescriptor toCopy) {
            setName(toCopy.name);
            setJobTitle(toCopy.jobTitle);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setInterviewSlot(toCopy.interviewSlot);
            setDetails(toCopy.details);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, jobTitle, phone, email, address, tags, interviewSlot, details);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setInterviewSlot(InterviewSlot interviewSlot) {
            this.interviewSlot = interviewSlot;
        }

        public Optional<InterviewSlot> getInterviewSlot() {
            return Optional.ofNullable(interviewSlot);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        public Optional<Details> getDetails() {
            return Optional.ofNullable(details);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public Optional<Set<Tag>> getTags(Set<Tag> applicationToEditTags) {
            // Check if tags is null.
            if (tags == null) {
                return Optional.empty();
            }

            Set<Tag> emptyTagSet = new HashSet<>();

            Set<Tag> reconstructedTagSet = new HashSet<>();

            Predicate<Tag> removeGenericTags = tag -> tag.getTagType().equals(TagType.JOB_SCOPE)
                    && tag.getTagName().toUpperCase().equals("REMOVETAGS");
            Predicate<Tag> removePriorityTags = tag -> tag.getTagType().equals(TagType.JOB_SCOPE)
                    && tag.getTagName().toUpperCase().equals("REMOVEPRIORITY");
            Predicate<Tag> removeApplicationStatusTags = tag -> tag.getTagType().equals(TagType.JOB_SCOPE)
                    && tag.getTagName().toUpperCase().equals("REMOVESTATUS");

            Predicate<Tag> jobScope = new TagSetContainsTagTypePredicate(TagType.JOB_SCOPE);
            Predicate<Tag> priority = new TagSetContainsTagTypePredicate(TagType.PRIORITY);
            Predicate<Tag> applicationStatus = new TagSetContainsTagTypePredicate(TagType.APPLICATION_STATUS);

            // Check if tags contains any Tag with TagType.JOB_SCOPE; this refers to a Generic tag
            if (tags.stream().anyMatch(removeGenericTags)) {
                // Mechanism that removes all Generic tags from the existing Application
                reconstructedTagSet = findMatchAndCopy(emptyTagSet, reconstructedTagSet, TagType.JOB_SCOPE);
            } else if (tags.stream().anyMatch(jobScope)
                    && tags.stream().noneMatch(removePriorityTags)
                    && tags.stream().noneMatch(removeApplicationStatusTags)) {
                // Mechanism that copies EditApplicationDescriptor's Generic tag to the new Edit Application
                reconstructedTagSet = findMatchAndCopy(tags, reconstructedTagSet, TagType.JOB_SCOPE);
            } else {
                // Mechanism that copies existing Generic tag to the new Edit Application
                if (applicationToEditTags.stream().anyMatch(jobScope)) {
                    reconstructedTagSet = findMatchAndCopy(applicationToEditTags, reconstructedTagSet,
                            TagType.JOB_SCOPE);
                }
            }

            // Check if tags contains any Tag with TagType.PRIORITY; this refers to a Priority tag
            if (tags.stream().anyMatch(removePriorityTags)) {
                // Mechanism that removes all Priority tags from the existing Application
                reconstructedTagSet = findMatchAndCopy(emptyTagSet, reconstructedTagSet, TagType.PRIORITY);
            } else if (tags.stream().anyMatch(priority)) {
                // Mechanism that copies EditApplicationDescriptor's Priority tag to the new Edit Application
                reconstructedTagSet = findMatchAndCopy(tags, reconstructedTagSet, TagType.PRIORITY);
            } else {
                // Mechanism that copies existing Priority tag to the new Edit Application
                if (applicationToEditTags.stream().anyMatch(priority)) {
                    reconstructedTagSet = findMatchAndCopy(applicationToEditTags, reconstructedTagSet,
                            TagType.PRIORITY);
                }
            }

            // Check if tags contains any Tag with TagType.APPLICATION_STATUS; this refers to Application Status tag
            if (tags.stream().anyMatch(removeApplicationStatusTags)) {
                // Mechanism that removes all Application Status tags from the existing Application
                reconstructedTagSet = findMatchAndCopy(emptyTagSet, reconstructedTagSet, TagType.APPLICATION_STATUS);
            } else if (tags.stream().anyMatch(applicationStatus)) {
                // Mechanism that copies EditApplicationDescriptor's Application Status tag to the new Edit Application
                reconstructedTagSet = findMatchAndCopy(tags, reconstructedTagSet, TagType.APPLICATION_STATUS);
            } else {
                // Mechanism that copies existing Application Status tag to the new Edit Application
                if (applicationToEditTags.stream().anyMatch(applicationStatus)) {
                    reconstructedTagSet = findMatchAndCopy(applicationToEditTags, reconstructedTagSet,
                            TagType.APPLICATION_STATUS);
                }
            }

            return Optional.of(reconstructedTagSet);

        }

        /**
         * Returns a {@code Set<Tag> destination} that contains all the Tags of a particular {@code TagType} found
         * within a given {@code Set<Tag> source}.
         */
        public Set<Tag> findMatchAndCopy(Set<Tag> source, Set<Tag> destination, TagType tagType) {
            Iterator<Tag> temp = source.iterator();
            while (temp.hasNext()) {
                Tag tempTag = temp.next();
                if (tempTag.getTagName().toUpperCase().equals("REMOVEPRIORITY")) {
                    // Skips this exact input
                    continue;
                }
                if (tempTag.getTagName().toUpperCase().equals("REMOVESTATUS")) {
                    // Skips this exact input
                    continue;
                }
                if (tempTag.getTagType() == tagType) {
                    destination.add(tempTag);
                }
            }
            return destination;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicationDescriptor)) {
                return false;
            }

            // state check
            EditApplicationDescriptor e = (EditApplicationDescriptor) other;

            return getName().equals(e.getName())
                    && getJobTitle().equals(e.getJobTitle())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getInterviewSlot().equals(e.getInterviewSlot())
                    && getDetails().equals(e.getDetails());
        }
    }

    public static class TagSetContainsTagTypePredicate implements Predicate<Tag> {

        private final TagType tagType;

        public TagSetContainsTagTypePredicate(TagType tagType) {
            this.tagType = tagType;
        }

        @Override
        public boolean test(Tag tag) {
            return tagType == tag.getTagType();
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof TagSetContainsTagTypePredicate // instanceof handles nulls
                    && tagType.equals(((TagSetContainsTagTypePredicate) other).tagType)); // state check
        }
    }
}
