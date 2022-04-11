package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.TYPE_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author EvaderFati
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %s";
    public static final String MESSAGE_STUDENTS = "students";
    public static final String MESSAGE_MODULES = "modules";
    public static final String MESSAGE_CLASS_GROUPS = "class groups";
    public static final String MESSAGE_ASSESSMENTS = "assessments";
    public static final String MESSAGE_INVALID_INDEX = "index does not exist";
    public static final String MESSAGE_INVALID_FILTER = "The specified filter is invalid.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": list all instances of an entity, with optional filtering\n"
            + "1. list/filter students:\n"
            + "\tParameters: " + TYPE_STUDENT
            + " [{m/MODULE_INDEX | c/CLASS_GROUP_INDEX}]\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_STUDENT + " /c 1\n"
            + "2. list modules:\n"
            + "\tParameter: " + TYPE_MODULE + "\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_MODULE + "\n"
            + "3. list/filter class groups:\n"
            + "\tParameters: " + TYPE_CLASS
            + " [m/MODULE_INDEX]\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_CLASS + " /m 1\n"
            + "4. list/filter assessments:\n"
            + "\tParameters: " + TYPE_ASSESSMENT
            + " [m/MODULE_INDEX]\n"
            + "\tExample: " + COMMAND_WORD + " "
            + TYPE_ASSESSMENT + " /m 1";

    private EntityType entityType;
    private Optional<EntityType> filterEntityType;
    private Optional<Index> filterEntityIndex;

    /**
     * Creates a ListCommand to list the specified entity, Optionally filtering by other entities
     */
    public ListCommand(EntityType entityType, Optional<EntityType> filterEntityType,
                       Optional<Index> filterEntityIndex) {
        this.entityType = entityType;
        this.filterEntityType = filterEntityType;
        this.filterEntityIndex = filterEntityIndex;
    }

    //@@author Gernene
    /**
     * Executes the list command.
     *
     * @param model ModelManager.
     * @return Command result.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String result;
        switch(entityType) {
        case STUDENT:
            result = updateStudentList(model);
            break;
        case TA_MODULE:
            result = updateModuleList(model);
            break;
        case CLASS_GROUP:
            result = updateClassGroupList(model);
            break;
        case ASSESSMENT:
            result = updateAssessmentList(model);
            break;
        default:
            throw new UnknownEntityException();
        }
        return new CommandResult(result, entityType);
    }

    //@@author Gernene
    /**
     * Updates filtered student list in ModelManager.
     *
     * @param model ModelManager.
     * @return Result string.
     */
    private String updateStudentList(Model model) {
        String result;
        try {
            if (filterEntityType.isEmpty()) {
                model.updateFilteredStudentList(PREDICATE_SHOW_ALL);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_STUDENTS);
            } else if (filterEntityType.get().equals(EntityType.CLASS_GROUP)) {
                Predicate<Student> filter = filterStudentsByClassGroup(model);
                model.updateFilteredStudentList(filter);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_STUDENTS);
            } else if (filterEntityType.get().equals(EntityType.TA_MODULE)) {
                Predicate<Student> filter = filterStudentsByModule(model);
                model.updateFilteredStudentList(filter);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_STUDENTS);
            } else {
                result = MESSAGE_INVALID_FILTER;
            }
        } catch (IndexOutOfBoundsException e) {
            result = MESSAGE_INVALID_INDEX;
        }
        return result;
    }

    //@@author Gernene
    /**
     * Updates filtered class group list in ModelManager.
     *
     * @param model ModelManager.
     * @return Result string.
     */
    private String updateClassGroupList(Model model) {
        String result;
        try {
            if (filterEntityType.isEmpty()) {
                model.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_CLASS_GROUPS);
            } else if (filterEntityType.get().equals(EntityType.TA_MODULE)) {
                Predicate<ClassGroup> filter = filterClassGroupsByModule(model);
                model.updateFilteredClassGroupList(filter);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_CLASS_GROUPS);
            } else {
                result = MESSAGE_INVALID_FILTER;
            }
        } catch (IndexOutOfBoundsException e) {
            result = MESSAGE_INVALID_INDEX;
        }
        return result;
    }

    //@@author Gernene
    /**
     * Updates filtered module list in ModelManager.
     *
     * @param model ModelManager.
     * @return Result string.
     */
    private String updateModuleList(Model model) {
        String result = String.format(MESSAGE_SUCCESS, MESSAGE_MODULES);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL);
        return result;
    }

    //@author Gernene
    /**
     * Updates filtered assessment list in ModelManager.
     *
     * @param model ModelManager.
     * @return Result string.
     */
    private String updateAssessmentList(Model model) {
        String result;
        try {
            if (filterEntityType.isEmpty()) {
                model.updateFilteredAssessmentList(PREDICATE_SHOW_ALL);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_ASSESSMENTS);
            } else if (filterEntityType.get().equals(EntityType.TA_MODULE)) {
                Predicate<Assessment> filter = filterAssessmentsByModule(model);
                model.updateFilteredAssessmentList(filter);
                result = String.format(MESSAGE_SUCCESS, MESSAGE_ASSESSMENTS);
            } else {
                result = MESSAGE_INVALID_FILTER;
            }
        } catch (IndexOutOfBoundsException e) {
            result = MESSAGE_INVALID_INDEX;
        }
        return result;
    }

    //@@author Gernene
    /**
     * Filters all students enroled in the ClassGroup specified by filterEntityIndex.
     *
     * @param model ModelManager.
     * @return Predicate that filters students.
     */
    private Predicate<Student> filterStudentsByClassGroup(Model model) {
        ObservableList<ClassGroup> classGroups = model.getUnfilteredClassGroupList();
        int classGroupIndex = filterEntityIndex.get().getZeroBased();
        ClassGroup classGroup = classGroups.get(classGroupIndex);
        return (Student student) -> {
            if (classGroup.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    //@@author Gernene
    /**
     * Filters all students enroled in the TaModule specified by filterEntityIndex.
     *
     * @param model ModelManager.
     * @return Predicate that filters students.
     */
    private Predicate<Student> filterStudentsByModule(Model model) {
        ObservableList<TaModule> modules = model.getUnfilteredModuleList();
        int moduleIndex = filterEntityIndex.get().getZeroBased();
        TaModule module = modules.get(moduleIndex);
        return (Student student) -> {
            if (module.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    //@@author Gernene
    /**
     * Filters all class groups that belong to the TaModule specified by filterEntityIndex.
     *
     * @param model ModelManager.
     * @return Predicate that filters class groups.
     */
    private Predicate<ClassGroup> filterClassGroupsByModule(Model model) {
        ObservableList<TaModule> modules = model.getUnfilteredModuleList();
        int moduleIndex = filterEntityIndex.get().getZeroBased();
        TaModule module = modules.get(moduleIndex);
        return (ClassGroup classGroup) -> {
            if (module.equals(classGroup.getModule())) {
                return true;
            }
            return false;
        };
    }

    //@@author Gernene
    /**
     * Filters all assessments that belong to the TaModule specified by filterEntityIndex.
     *
     * @param model ModelManager.
     * @return Predicate that filters assessments.
     */
    private Predicate<Assessment> filterAssessmentsByModule(Model model) {
        ObservableList<TaModule> modules = model.getUnfilteredModuleList();
        int moduleIndex = filterEntityIndex.get().getZeroBased();
        TaModule module = modules.get(moduleIndex);
        return (Assessment assessment) -> {
            if (module.equals(assessment.getModule())) {
                return true;
            }
            return false;
        };
    }

}
