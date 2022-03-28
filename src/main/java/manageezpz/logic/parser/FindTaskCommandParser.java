package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import manageezpz.logic.commands.FindTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.person.Name;
import manageezpz.model.task.Date;
import manageezpz.model.task.Priority;
import manageezpz.model.task.TaskMultiplePredicate;

/**
 * Subclass of FindCommandParser which check if the options are valid for finding tasks.
 */
class FindTaskCommandParser implements Parser<FindTaskCommand> {
    private static final Prefix[] TASK_TYPES = {PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT};
    private static final Prefix[] VALID_OPTIONS = {PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT, PREFIX_DATE,
            PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_ASSIGNEES, PREFIX_IS_MARKED};

    private String errorMessage = "";
    private boolean hasError = false;

    protected FindTaskCommandParser() {}

    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, VALID_OPTIONS);

        List<Prefix> taskTypes = getPrefix(argMultiMap);
        List<String> descriptions = getDescriptions(argMultiMap);
        Date date = getTaskDate(argMultiMap);
        Priority priority = getTaskPriority(argMultiMap);
        String assignee = getAssignee(argMultiMap);
        Boolean isMarked = getIsMarked(argMultiMap);

        checkIfTodoAndDateTogether(argMultiMap, taskTypes);

        if (hasError) {
            errorMessage = errorMessage + FindTaskCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        } else {
            return new FindTaskCommand(new TaskMultiplePredicate(
                    taskTypes, descriptions, date, priority, assignee, isMarked));
        }
    }

    private void checkIfTodoAndDateTogether(ArgumentMultimap argMultiMapProperties, List<Prefix> taskTypes) {
        boolean isContainsTodo = taskTypes.contains(PREFIX_TODO);
        if (isContainsTodo && argMultiMapProperties.isPrefixExist(PREFIX_DATE)) {
            addErrorMessage(FindTaskCommand.TODO_AND_DATE_OPTION_TOGETHER);
        }
    }

    private List<Prefix> getPrefix(ArgumentMultimap argMultiMap) {
        List<Prefix> currentPrefixes = Arrays.stream(TASK_TYPES)
                .filter(prefix -> argMultiMap.isPrefixExist(prefix)).collect(Collectors.toList());
        return currentPrefixes;
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
            } else if (!Name.isValidName(assignee)) {
                addErrorMessage(Name.MESSAGE_CONSTRAINTS);
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
            addErrorMessage(FindTaskCommand.INVALID_DATE);
            return false;
        }
        if (!Date.isValidDate(dateString)) {
            addErrorMessage(Date.MESSAGE_CONSTRAINTS + "\n");
            return false;
        }
        return true;
    }

    private Priority getTaskPriority(ArgumentMultimap argMultiMap) {
        Priority priority = null;
        if (argMultiMap.isPrefixExist(PREFIX_PRIORITY)) {
            String priorityString = argMultiMap.getValue(PREFIX_PRIORITY).get().trim().toUpperCase();
            try {
                priority = Priority.valueOf(priorityString);
            } catch (IllegalArgumentException e) {
                addErrorMessage(FindTaskCommand.INVALID_PRIORITY);
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
        if (booleanString.equals("true") || booleanString.equals("false")) {
            return true;
        } else if (!booleanString.equals("true") && !booleanString.equals("false")) {
            addErrorMessage(FindTaskCommand.INVALID_BOOLEAN);
            return false;
        }
        assert false : "Error in checkIfEitherTrueOrFalse";
        return false;
    }

    /**
     * Collates all the errors and shows the UI after processing all properties.
     * @param errorMessage Error message from each checking to be added to the overall error message.
     */
    private void addErrorMessage(String errorMessage) {
        hasError = true;
        this.errorMessage = this.errorMessage + errorMessage;
    }
}
