package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
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
import seedu.address.model.ModelManager;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * Edits the details of an existing person in HackNet.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String RESET_ARG = "r";
    public static final String MESSAGE_USAGE_OPTIONS =
        "The prefix " + PREFIX_COMMAND_OPTION
            + " is used to declare options for edit command.\n"
            + "The only available option is 'r', which makes the edit command execute in reset mode.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person(s) identified "
        + "by the index number(s) used in the displayed person list.\n"
        + "Existing values will be overwritten by the input values.\n"
        + "Index numbers should be separated by a white space,"
        + "as opposed to other teams and skill values that should be separated by a comma."
        + "\n"
        + "By default, Teams will be appended.\n"
        + "However in reset mode, editing Teams will purge all previous data.\n"
        + "This means declaring t/ with no values in reset mode deletes all teams from the person(s) in reset mode.\n"
        + "The concept of reset mode applies the same to Skills.\n"
        + "Only Teams and Skills will be changed when editing multiple persons in batch.\n"
        + "Other changes such as Name and Email will be silently ignored, if provided.\n"
        + "Parameters: INDEX [INDEX...] (must be a positive integer) "
        + "[ " + PREFIX_COMMAND_OPTION + "r](activates reset mode)"
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_GITHUB_USERNAME + "GITHUB USERNAME] "
        + "[" + PREFIX_TEAM + "TEAM...]"
        + "[" + PREFIX_SKILL + "SKILL NAME_SKILL PROFICIENCY...]\n"
        + "Example 1: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com" + "\n"
        + "Example 2: " + COMMAND_WORD + " 1 "
        + PREFIX_TEAM + "System Maintenance, Python "
        + PREFIX_SKILL;

    public static final String MESSAGE_EDIT_SINGLE_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS = "Edited Persons: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in HackNet.";

    private final List<Index> indicesToEdit;
    private final EditPersonDescriptor editPersonDescriptor;
    private final boolean isResetMode;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor, boolean isResetMode) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.indicesToEdit = new ArrayList<>();
        indicesToEdit.add(index);
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.isResetMode = isResetMode;
    }

    /**
     * Constructs EditCommand that edits multiple Persons in batch.
     *
     * @param indices              List of indices of Persons to be edited in the displayed list
     * @param editPersonDescriptor details to edit the person with
     * @param isResetMode          of the person in the filtered person list to edit
     */
    public EditCommand(List<Index> indices, EditPersonDescriptor editPersonDescriptor, boolean isResetMode) {
        requireNonNull(indices);
        requireNonNull(editPersonDescriptor);

        this.indicesToEdit = indices;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.isResetMode = isResetMode;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor,
                                            boolean isResetMode) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        GithubUsername updatedUsername = editPersonDescriptor.getGithubUsername()
            .orElse(personToEdit.getGithubUsername());
        Set<Team> updatedTeams;
        SkillSet updatedSkillSet;
        if (isResetMode) {
            updatedTeams = editPersonDescriptor.getTeams().orElse(personToEdit.getTeams());
            updatedSkillSet = editPersonDescriptor.getSkillSet().orElse(personToEdit.getSkillSet());
        } else {
            Set<Team> previousTeams = personToEdit.getTeams();
            Optional<Set<Team>> teamsToAdd = editPersonDescriptor.getTeams();
            updatedTeams = new HashSet<>();
            updatedTeams.addAll(previousTeams);
            if (teamsToAdd.isPresent()) {
                updatedTeams.addAll(teamsToAdd.get());
            }

            SkillSet previousSkillSet = personToEdit.getSkillSet();
            updatedSkillSet = new SkillSet();
            updatedSkillSet.addAll(previousSkillSet);
            Optional<SkillSet> skillSetToAdd = editPersonDescriptor.getSkillSet();
            if (skillSetToAdd.isPresent()) {
                updatedSkillSet.addAll(skillSetToAdd.get());
            }
        }
        // Potential teammate field to be unchanged on edit command
        boolean isPotentialTeammate = personToEdit.isPotentialTeammate();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedUsername,
            updatedTeams, updatedSkillSet, isPotentialTeammate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDisplayPersonList();
        boolean isSingleEdit = this.indicesToEdit.size() == 1;
        CommandResult commandResult;
        if (isSingleEdit) {
            commandResult = executeSingleEdit(model, lastShownList);
        } else {
            commandResult = executeBatchEdit(model, lastShownList);
        }
        model.commitAddressBook();
        return commandResult;
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
        return indicesToEdit.equals(e.indicesToEdit)
            && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    private CommandResult executeSingleEdit(Model model, List<Person> lastShownList) throws CommandException {
        Index index = indicesToEdit.get(0);
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_FOR_PERSON);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor, isResetMode);
        Model modelToCheckAgainst = new ModelManager(model.getAddressBook(), model.getUserPrefs());
        boolean isSuccessfullyRemoved = modelToCheckAgainst.safeDeletePerson(personToEdit);
        if (!isSuccessfullyRemoved) {
            throw new CommandException("Error: Unable to edit; please contact the developers");
        }
        if (modelToCheckAgainst.hasPerson(editedPerson)) {
            String duplicatedField = modelToCheckAgainst.getDuplicateField(editedPerson);
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateDisplayPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_SINGLE_PERSON_SUCCESS, editedPerson));
    }

    private EditPersonDescriptor extractOnlyTeamsAndSkillSet(EditPersonDescriptor editPersonDescriptor) {
        EditPersonDescriptor editOnlyTeamsAndSkills = new EditPersonDescriptor();

        if (editPersonDescriptor.getTeams().isPresent()) {
            editOnlyTeamsAndSkills.setTeams(editPersonDescriptor.getTeams().get());
        }
        if (editPersonDescriptor.getSkillSet().isPresent()) {
            editOnlyTeamsAndSkills.setSkillSet(editPersonDescriptor.getSkillSet().get());
        }
        return editOnlyTeamsAndSkills;
    }

    private CommandResult executeBatchEdit(Model model, List<Person> lastShownList) {

        boolean isAllIndicesValid = true;
        List<Name> editedNames = new ArrayList<>();
        EditPersonDescriptor onlyTeamAndSkillsetDescriptor = extractOnlyTeamsAndSkillSet(editPersonDescriptor);
        for (Index index : indicesToEdit) {
            if (index.getZeroBased() >= lastShownList.size()) {
                isAllIndicesValid = false;
                continue;
            }
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson =
                EditCommand.createEditedPerson(personToEdit, onlyTeamAndSkillsetDescriptor, isResetMode);
            editedNames.add(personToEdit.getName());
            model.setPerson(personToEdit, editedPerson);
        }
        model.updateDisplayPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // Throwing error after editing for valid indices allows the successful edit for least the valid indices.
        if (!isAllIndicesValid) {
            return new CommandResult(
                String.format(
                    Messages.MESSAGE_INVALID_INDEX_FOR_SOME_PERSON
                        + "\n"
                        + MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS,
                    editedNames));
        }
        return new CommandResult(String.format(MESSAGE_EDIT_MULTIPLE_PERSON_SUCCESS, editedNames));
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private GithubUsername username;
        private Set<Team> teams;
        private SkillSet skillSet;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code teams} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGithubUsername(toCopy.username);
            setTeams(toCopy.teams);
            setSkillSet(toCopy.skillSet);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, username, teams, skillSet);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<GithubUsername> getGithubUsername() {
            return Optional.ofNullable(username);
        }

        public void setGithubUsername(GithubUsername username) {
            this.username = username;
        }

        /**
         * Returns an unmodifiable team set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code teams} is null.
         */
        public Optional<Set<Team>> getTeams() {
            return (teams != null) ? Optional.of(Collections.unmodifiableSet(teams)) : Optional.empty();
        }

        /**
         * Sets {@code teams} to this object's {@code teams}.
         * A defensive copy of {@code teams} is used internally.
         */
        public void setTeams(Set<Team> teams) {
            this.teams = (teams != null) ? new HashSet<>(teams) : null;
        }

        public Optional<SkillSet> getSkillSet() {
            return (this.skillSet != null && skillSet.getSkillSet() != null)
                ? Optional.of(new SkillSet(Collections.unmodifiableSet(skillSet.getSkillSet()))) : Optional.empty();
        }

        public void setSkillSet(SkillSet skillSet) {
            this.skillSet = (skillSet != null) ? new SkillSet(new HashSet<>(skillSet.getSkillSet())) : null;
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
                && getGithubUsername().equals(e.getGithubUsername())
                && getTeams().equals(e.getTeams())
                && getSkillSet().equals(e.getSkillSet());
        }

    }
}
