package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;

import java.util.List;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an entity identified by the index number used in the displayed list\n"
            + "1. Deletes a student: "
            + "Parameters: " + TYPE_STUDENT
            +  " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + TYPE_STUDENT + " 1\n"
            + "2. Adds a module: "
            + "Parameter: " + TYPE_MODULE
            + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + TYPE_MODULE + " 1\n"
            + "3. Adds a class group: "
            + "Parameters: " + TYPE_CLASS
            + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + TYPE_CLASS + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Entity: %1$s";

    private final Index targetIndex;
    private final EntityType entityType;

    public DeleteCommand(Index targetIndex, EntityType entityType) {
        this.targetIndex = targetIndex;
        this.entityType = entityType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<TaModule> lastShownModuleList = model.getFilteredModuleList();
        List<ClassGroup> lastShownClassGroupList = model.getFilteredClassGroupList();
        Entity entityToDelete;

        switch(entityType) {

        case STUDENT:
            if (targetIndex.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownStudentList.get(targetIndex.getZeroBased());
            break;

        case TA_MODULE:
            if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownStudentList.get(targetIndex.getZeroBased());
            break;

        case CLASS_GROUP:
            if (targetIndex.getZeroBased() >= lastShownClassGroupList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownStudentList.get(targetIndex.getZeroBased());
            break;

        default:
            throw new UnknownEntityException();
        }

        model.deleteEntity(entityToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, entityToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteCommand
                && targetIndex.equals(((DeleteCommand) other).targetIndex)
                && entityType.equals(((DeleteCommand) other).entityType));
    }
}
