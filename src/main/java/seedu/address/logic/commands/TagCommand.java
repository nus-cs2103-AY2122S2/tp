package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Tags additional information of an existing person in the address book.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags additional information to the person identified "
            + "by the index number used in the last person listing. "
            + "If there are existing tags, the new tags will be appended to them.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_EDUCATION + "[EDUCATION]... "
            + PREFIX_CCA + "[CCA]... "
            + PREFIX_INTERNSHIP + "[INTERNSHIP]... "
            + PREFIX_MODULE + "[MODULE]..."
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_EDUCATION + "Computer Science "
            + PREFIX_CCA + "Bouldering "
            + PREFIX_INTERNSHIP + "GIC "
            + PREFIX_MODULE + "CS2040S";
    public static final String MESSAGE_NO_PARAMETERS = "At least 1 field must be used and not blank.";
    public static final String MESSAGE_ARGUMENTS = "TAGGED TO %1$s \n-Current Tags- \nEducation: %2$s"
            + "\nInternship: %3$s " + "\nModule: %4$s " + "\nCCA: %5$s";

    private final Index index;
    private final List<Tag> educations;
    private final List<Tag> internships;
    private final List<Tag> modules;
    private final List<Tag> ccas;

    /**
     * @param index of the person in the filtered person list to tag
     * @param education of the person to be updated to
     * @param internship of the person to be updated to
     * @param module of the person to be updated to
     * @param cca of the person to be updated to
     */
    public TagCommand(Index index, List<Tag> education, List<Tag> internship, List<Tag> module, List<Tag> cca) {
        requireAllNonNull(index, education, internship, module, cca);

        this.index = index;
        this.educations = education;
        this.internships = internship;
        this.modules = module;
        this.ccas = cca;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (isAllEmpty()) {
            throw new CommandException(MESSAGE_NO_PARAMETERS);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> currEducations = new HashSet<>(personToEdit.getEducations());
        Set<Tag> currInternships = new HashSet<>(personToEdit.getInternships());
        Set<Tag> currModules = new HashSet<>(personToEdit.getModules());
        Set<Tag> currCcas = new HashSet<>(personToEdit.getCcas());

        currEducations.addAll(educations);
        currInternships.addAll(internships);
        currModules.addAll(modules);
        currCcas.addAll(ccas);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getAddress(),
                new ArrayList<>(currEducations), new ArrayList<>(currInternships),
                new ArrayList<>(currModules), new ArrayList<>(currCcas));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);


        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private boolean isAllEmpty() {
        return educations.isEmpty() && internships.isEmpty() && modules.isEmpty() && ccas.isEmpty();
    }

    /**
     * Generates a command execution success message based on whether
     * the tag is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ARGUMENTS,
                    personToEdit.getName(),
                    personToEdit.getEducations(),
                    personToEdit.getInternships(),
                    personToEdit.getModules(),
                    personToEdit.getCcas());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index) && educations.equals(e.educations) && internships.equals(e.internships)
                && modules.equals(e.modules) && ccas.equals(e.ccas);
    }
}
