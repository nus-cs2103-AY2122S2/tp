package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.Arrays;
import java.util.List;

import manageezpz.logic.commands.FindCommand;
import manageezpz.logic.commands.FindTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.task.Date;
import manageezpz.model.task.Task;
import manageezpz.model.task.TaskMultiplePredicate;

class FindTaskCommandParser extends FindCommandParser {
    private static final Prefix[] TASK_TYPES = {PREFIX_TASK, PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT};
    private static final Prefix[] TASK_PROPERTIES
            = {PREFIX_DATE, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_ASSIGNEES, PREFIX_IS_MARKED};

    private String errorMessage = "";
    private boolean hasError = false;

    protected FindTaskCommandParser() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMapTaskType = ArgumentTokenizer.tokenize(args, TASK_TYPES);
        ArgumentMultimap argMultiMapProperties = ArgumentTokenizer.tokenize(args, TASK_PROPERTIES);

        Prefix taskType = getPrefix(argMultiMapTaskType);
        List<String> descriptions = getDescriptions(argMultiMapProperties);
        Date date = getTaskDate(argMultiMapProperties);
        Task.Priority priority = getTaskPriority(argMultiMapProperties);
        String assignee = getAssignee(argMultiMapProperties);
        Boolean isMarked = getIsMarked(argMultiMapProperties);

        if (taskType.equals(PREFIX_TODO) && argMultiMapProperties.isPrefixExist(PREFIX_DATE)) {
            addErrorMessage(FindTaskCommand.TODO_AND_DATE_OPTION_TOGETHER);
        }

        if (hasError) {
            errorMessage = errorMessage + FindCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        } else {
            return new FindTaskCommand(new TaskMultiplePredicate(
                    taskType, descriptions, date, priority, assignee, isMarked));
        }
    }

    private Prefix getPrefix(ArgumentMultimap argMultiMap) {
        for (Prefix prefix : TASK_TYPES) {
            if (argMultiMap.isPrefixExist(prefix)) {
                return prefix;
            }
        }
        assert false : "String argument must have a task type prefix";
        return null;
    }

    private List<String> getDescriptions(ArgumentMultimap argMultiMap) {
        List<String> keyword = null;
        if (argMultiMap.isPrefixExist(PREFIX_DESCRIPTION)) {
            String trimmedArgs = argMultiMap.getValue(PREFIX_DESCRIPTION).get().trim();
            boolean isArgEmpty = checkIfArgsEmpty(trimmedArgs, FindTaskCommand.EMPTY_KEYWORD);
            if (!isArgEmpty) {
                String[] taskAssignees = trimmedArgs.split("\\s+");
                keyword = Arrays.asList(taskAssignees);
            }
        }
        return keyword;
    }

    private String getAssignee(ArgumentMultimap argMultiMap) {
        String assignee = null;
        if (argMultiMap.isPrefixExist(PREFIX_ASSIGNEES)) {
            assignee = argMultiMap.getValue(PREFIX_ASSIGNEES).get().trim();
            if (assignee.isEmpty()) {
                addErrorMessage(FindTaskCommand.EMPTY_ASSIGNEE);
            }
        }
        return assignee;
    }

    private Boolean getIsMarked(ArgumentMultimap argMultiMap) {
        Boolean isMarked = null;
        if (argMultiMap.isPrefixExist(PREFIX_IS_MARKED)) {
            String booleanString = argMultiMap.getValue(PREFIX_IS_MARKED).get().trim().toLowerCase();
            boolean isEitherTrueOrFalse = checkIfEitherTrueOrFalse(booleanString);
            if (isEitherTrueOrFalse) {
                isMarked = Boolean.valueOf(booleanString);
            }
        }
        return isMarked;
    }

    private Date getTaskDate(ArgumentMultimap argMultiMap) {
        Date date = null;
        if (argMultiMap.isPrefixExist(PREFIX_DATE)) {
            String dateString = argMultiMap.getValue(PREFIX_DATE).get().trim();
            boolean isDateValid = checkIfDateIsValid(dateString);
            if (isDateValid) {
                date = new Date(dateString);
            }
        }
        return date;
    }

    private boolean checkIfDateIsValid(String dateString) {
        if (dateString.isEmpty()) {
            addErrorMessage(FindTaskCommand.EMPTY_DATE);
            return false;
        }
        if (!Date.isValidDate(dateString)) {
            addErrorMessage(Date.MESSAGE_CONSTRAINTS + "\n");
            return false;
        }
        return true;
    }

    private Task.Priority getTaskPriority(ArgumentMultimap argMultiMap) {
        Task.Priority priority = null;
        if (argMultiMap.isPrefixExist(PREFIX_PRIORITY)) {
            String priorityString = argMultiMap.getValue(PREFIX_PRIORITY).get().trim().toUpperCase();
            if (priorityString.isEmpty()) {
                addErrorMessage(FindTaskCommand.EMPTY_PRIORITY);
            } else {
                try {
                    priority = Task.Priority.valueOf(priorityString);
                } catch (IllegalArgumentException e) {
                    addErrorMessage(FindTaskCommand.INVALID_PRIORITY);
                }
            }
        }
        return priority;
    }

    private boolean checkIfArgsEmpty(String trimmedArgs, String emptyKeyword) {
        if (trimmedArgs.isEmpty()) {
            addErrorMessage(emptyKeyword);
            return true;
        }
        return false;
    }

    private boolean checkIfEitherTrueOrFalse(String booleanString) {
        if (booleanString.isEmpty()) {
            addErrorMessage(FindTaskCommand.EMPTY_BOOLEAN);
            return false;
        } else if (booleanString.equals("true") || booleanString.equals("false")) {
            return true;
        } else if (!booleanString.equals("true") && !booleanString.equals("false")) {
            addErrorMessage(FindTaskCommand.INVALID_BOOLEAN);
            return false;
        }
        assert false : "Error in checkIfEitherTrueOrFalse";
        return false;
    }

    private void addErrorMessage(String errorMessage) {
        hasError = true;
        this.errorMessage = this.errorMessage + errorMessage;
    }
}
