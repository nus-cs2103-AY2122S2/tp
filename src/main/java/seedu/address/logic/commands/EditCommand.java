package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.LineupPlayersList;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.JerseyNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.Team;
import seedu.address.model.team.TeamLineupList;
import seedu.address.model.team.TeamMemberList;
import seedu.address.model.team.TeamName;

/**
 * Edits the details of an existing person in MyGM.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_AGE + "AGE] "
            + "[" + PREFIX_HEIGHT + "HEIGHT] "
            + "[" + PREFIX_WEIGHT + "WEIGHT] "
            + "[" + PREFIX_JERSEY_NUMBER + "JERSEY_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Name targetPlayerName;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetPlayerName of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Name targetPlayerName, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(targetPlayerName);
        requireNonNull(editPersonDescriptor);

        this.targetPlayerName = targetPlayerName;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

//    public EditCommand(TeamName targetTeamName, EditTeamDescriptor editTeamDescriptor) {
//        requireNonNull(targetTeamName);
//        requireNonNull(editTeamDescriptor);
//
//        this.targetPlayerName = targetPlayerName;
//        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
//    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonInMyGM(targetPlayerName)) { // check if UPL name to person have targetPerson
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = model.getPersonFromMyGM(targetPlayerName); //get the person to edit from model (currently doesn't work)
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPersonInMyGM(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Age updatedAge = editPersonDescriptor.getAge().orElse(personToEdit.getAge());
        Height updatedHeight = editPersonDescriptor.getHeight().orElse(personToEdit.getHeight());
        Weight updatedWeight = editPersonDescriptor.getWeight().orElse(personToEdit.getWeight());
        JerseyNumber updatedJerseyNumber = editPersonDescriptor.getJerseyNumber()
                .orElse(personToEdit.getJerseyNumber());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAge,
                updatedHeight, updatedJerseyNumber, updatedTags, updatedWeight);
    }

    /**
     * Creates and returns a {@code Team} with the details of {@code teamToEdit}
     * edited with {@code editTeamDescriptor}.
     */
    private static Team createEditedTeam(Team teamToEdit, EditTeamDescriptor editTeamDescriptor) {
        assert teamToEdit != null;

        TeamName updatedTeamName = editTeamDescriptor.getTeamName().orElse(teamToEdit.getTeamName());
        TeamMemberList updatedTeamMemberList =
                editTeamDescriptor.getTeamMemberList().orElse(teamToEdit.getTeamMemberList());
        TeamLineupList updatedTeamLineupList =
                editTeamDescriptor.getTeamLineupList().orElse(teamToEdit.getTeamLineupList());

        return new Team(updatedTeamName, updatedTeamMemberList, updatedTeamLineupList);
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
        return editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Age age;
        private Height height;
        private JerseyNumber jerseyNumber;
        private Set<Tag> tags;
        private Weight weight;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAge(toCopy.age);
            setHeight(toCopy.height);
            setJerseyNumber(toCopy.jerseyNumber);
            setTags(toCopy.tags);
            setWeight(toCopy.weight);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, age, height, jerseyNumber, tags, weight);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setJerseyNumber(JerseyNumber jerseyNumber) {
            this.jerseyNumber = jerseyNumber;
        }

        public Optional<JerseyNumber> getJerseyNumber() {
            return Optional.ofNullable(jerseyNumber);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAge().equals(e.getAge())
                    && getHeight().equals(e.getHeight())
                    && getJerseyNumber().equals(e.getJerseyNumber())
                    && getWeight().equals(e.getWeight())
                    && getTags().equals(e.getTags());
        }
    }

    /**
     * Stores the details to edit the team with. Each non-empty field value will replace the
     * corresponding field value of the team.
     */
    public static class EditTeamDescriptor {
        private TeamName teamName;
        private TeamMemberList teamMemberList;
        private TeamLineupList teamLineupList;

        public EditTeamDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTeamDescriptor(EditTeamDescriptor toCopy) {
            setTeamName(toCopy.teamName);
            setTeamMemberList(toCopy.teamMemberList);
            setTimeLineupList(toCopy.teamLineupList);
        }

        /**
         * Returns true if team name is edited
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(teamName);
        }

        public void setTeamName(TeamName teamName) {
            this.teamName = teamName;
        }

        public Optional<TeamName> getTeamName() {
            return Optional.ofNullable(teamName);
        }

        public void setTeamMemberList(TeamMemberList teamMemberList) {
            this.teamMemberList = teamMemberList;
        }

        public Optional<TeamMemberList> getTeamMemberList() {
            return Optional.ofNullable(teamMemberList);
        }

        public void setTimeLineupList(TeamLineupList teamLineupList) {
            this.teamLineupList = teamLineupList;
        }

        public Optional<TeamLineupList> getTeamLineupList() {
            return Optional.ofNullable(teamLineupList);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTeamDescriptor)) {
                return false;
            }

            // state check
            EditTeamDescriptor e = (EditTeamDescriptor) other;

            return getTeamName().equals(e.getTeamName())
                    && getTeamMemberList().equals(e.getTeamMemberList())
                    && getTeamLineupList().equals(e.getTeamLineupList());
        }
    }
}
