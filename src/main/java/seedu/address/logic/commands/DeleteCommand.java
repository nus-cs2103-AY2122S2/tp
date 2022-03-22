package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.TYPE_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author EvaderFati
/**
 * Deletes an entity identified using it's displayed index and entityType from TAssist.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an entity identified by the index number used in the displayed list\n"
            + "1. Deletes a student:\n"
            + "\tParameters: " + TYPE_STUDENT
            + " INDEX (must be a positive integer)\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_STUDENT + " 1\n"
            + "2. Deletes a module:\n"
            + "\tParameter: " + TYPE_MODULE
            + " INDEX (must be a positive integer)\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_MODULE + " 1\n"
            + "3. Deletes a class group:\n"
            + "\tParameters: " + TYPE_CLASS
            + " INDEX (must be a positive integer)\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_CLASS + " 1"
            + "4. Deletes an assessment:\n"
            + "\tParameters: " + TYPE_ASSESSMENT
            + " INDEX (must be a positive integer)\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_ASSESSMENT + " 1";

    public static final String MESSAGE_DELETE_ENTITY_SUCCESS = "Deleted Entity: %1$s";

    private final Index targetIndex;
    private final EntityType entityType;

    /**
     * Creates a DeleteCommand to delete the specified entity by {@code Index}
     * and {@code EntityType}
     */
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
        List<Assessment> lastShownAssessmentList = model.getFilteredAssessmentList();
        Entity entityToDelete;

        switch(entityType) {

        case STUDENT:
            if (targetIndex.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownStudentList.get(targetIndex.getZeroBased());
            break;

        case TA_MODULE:
            if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownModuleList.get(targetIndex.getZeroBased());
            break;

        case CLASS_GROUP:
            if (targetIndex.getZeroBased() >= lastShownClassGroupList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownClassGroupList.get(targetIndex.getZeroBased());
            break;

        case ASSESSMENT:
            if (targetIndex.getZeroBased() >= lastShownAssessmentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
            }
            entityToDelete = lastShownAssessmentList.get(targetIndex.getZeroBased());
            break;


        default:
            throw new UnknownEntityException();
        }

        model.deleteEntity(entityToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ENTITY_SUCCESS, entityToDelete),
                entityToDelete.getEntityType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteCommand
                && targetIndex.equals(((DeleteCommand) other).targetIndex)
                && entityType.equals(((DeleteCommand) other).entityType));
    }
}
