package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class SetSkillsCommand extends Command {
    public static final String COMMAND_WORD = "setskills";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the persons identified "
        + "by the index number used in the displayed person list. "
        + "Existing skillsets will be overwritten by the input values.\n"
        + "Parameters: INDEX... (must be a positive integer) "
        + PREFIX_SKILL + "SKILL NAME_SKILL PROFICIENCY...\n"
        + "Example: " + COMMAND_WORD + " 1 2 3 "
        + PREFIX_SKILL + "s/java_100 s/c_30";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final List<Index> indices;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param indices of the persons to edit in the addressbook
     * @param editPersonDescriptor describing how the persons' skillset will change
     */
    public SetSkillsCommand(List<Index> indices, EditPersonDescriptor editPersonDescriptor) {
        this.indices = indices;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getDisplayPersonList();

        boolean isAllIndicesValid = true;
        for (Index index : indices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                isAllIndicesValid = false;
                continue;
            }
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);
            model.setPerson(personToEdit, editedPerson);
        }
        model.updateDisplayPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (!isAllIndicesValid) {
            throw new CommandException(Messages.MESSAGE_INVALID_SOME_PERSON_DISPLAYED_INDEX);
        }
        return null;
    }
}
