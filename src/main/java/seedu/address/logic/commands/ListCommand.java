package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
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

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String result = "";
        switch(entityType) {
        case STUDENT:
            updateStudentList(model, result);
            break;
        case TA_MODULE:
            updateModuleList(model, result);
            break;
        case CLASS_GROUP:
            updateClassGroupList(model, result);
            break;
        case ASSESSMENT:
            updateAssessmentList(model, result);
            break;
        default:
            throw new UnknownEntityException();
        }
        return new CommandResult(result, entityType);
    }

    /**
     * Updates filtered student list in ModelManager.
     * 
     * @param model ModelManager.
     * @param result Result string to update.
     */
    private void updateStudentList(Model model, String result) {
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
    }

    /**
     * Updates filtered student list in ModelManager.
     * 
     * @param model ModelManager.
     * @param result Result string to update.
     */
    private void updateClassGroupList(Model model, String result) {
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
    }

    /**
     * Updates filtered module list in ModelManager.
     * 
     * @param model ModelManager.
     * @param result Result string to update.
     */
    private void updateModuleList(Model model, String result) {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL);
        result = String.format(MESSAGE_SUCCESS, MESSAGE_MODULES);
    }

    /**
     * Updates filtered assessment list in ModelManager.
     * 
     * @param model ModelManager.
     * @param result Result string to update.
     */
    private void updateAssessmentList(Model model, String result) {
        model.updateFilteredAssessmentList(PREDICATE_SHOW_ALL);
        result = String.format(MESSAGE_SUCCESS, MESSAGE_ASSESSMENTS);
    }

    /**
     * Filters all students enroled in the ClassGroup specified by filterEntityIndex.
     * 
     * @param model ModelManager.
     * @return Predicate that filters students.
     */
    private Predicate<Student> filterStudentsByClassGroup(Model model) {
        ObservableList<ClassGroup> classGroups = model.getUnfilteredClassGroupList();
        ClassGroup classGroup = classGroups.get(filterEntityIndex.get().getZeroBased());
        return (Student student) -> {
            if (classGroup.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    /**
     * Filters all students enroled in the TaModule specified by filterEntityIndex.
     * 
     * @param model ModelManager.
     * @return Predicate that filters students.
     */
    private Predicate<Student> filterStudentsByModule(Model model) {
        ObservableList<TaModule> modules = model.getUnfilteredModuleList();
        TaModule module = modules.get(filterEntityIndex.get().getZeroBased());
        return (Student student) -> {
            if (module.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    /**
     * Filters all class groups that belong to the TaModule specified by filterEntityIndex.
     * 
     * @param model ModelManager.
     * @return Predicate that filters class groups.
     */
    private Predicate<ClassGroup> filterClassGroupsByModule(Model model) {
        ObservableList<TaModule> modules = model.getUnfilteredModuleList();
        TaModule module = modules.get(filterEntityIndex.get().getZeroBased());
        return (ClassGroup classGroup) -> {
            if (module.equals(classGroup.getModule())) {
                return true;
            }
            return false;
        };
    }

}
