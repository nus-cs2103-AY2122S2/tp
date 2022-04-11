package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import manageezpz.commons.core.LogsCenter;
import manageezpz.logic.commands.FindTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.person.Name;
import manageezpz.model.task.Date;
import manageezpz.model.task.Description;
import manageezpz.model.task.Priority;
import manageezpz.model.task.TaskMultiplePredicate;

/**
 * Subclass of FindCommandParser which check if the options are valid for finding tasks.
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {
    private static final Prefix[] TASK_TYPES = {PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT};
    private static final Prefix[] VALID_OPTIONS = {PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT, PREFIX_DATE,
        PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_ASSIGNEES, PREFIX_IS_MARKED};
    private static final Logger logger = LogsCenter.getLogger(FindTaskCommandParser.class);

    private String errorMessage = "";
    private boolean hasError = false;

    /**
     * {@inheritDoc}
     */
    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, VALID_OPTIONS);

        checkIfHaveAtLeastOneOption(argMultiMap);
        Prefix taskType = getPrefix(argMultiMap);
        List<String> descriptions = getDescriptions(argMultiMap);
        Date date = getTaskDate(argMultiMap);
        Priority priority = getTaskPriority(argMultiMap);
        String assignee = getAssignee(argMultiMap);
        Boolean isMarked = getIsMarked(argMultiMap);

        checkIfTodoAndDateTogether(argMultiMap, taskType);

        if (hasError) {
            String finalMessage = errorMessage + FindTaskCommand.MESSAGE_USAGE;
            String displayedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, finalMessage);
            throw new ParseException(displayedMessage);
        } else {
            return new FindTaskCommand(new TaskMultiplePredicate(
                    taskType, descriptions, date, priority, assignee, isMarked));
        }
    }

    private void checkIfHaveAtLeastOneOption(ArgumentMultimap argMultiMap) {
        String noOptionEnteredMessage = "No option entered for find task command.";

        if (!isAtLeastOnePrefixPresent(argMultiMap, VALID_OPTIONS) || !argMultiMap.getPreamble().isEmpty()) {
            logger.warning(noOptionEnteredMessage);
            addErrorMessage(FindTaskCommand.NO_OPTIONS);
        }
    }

    private void checkIfTodoAndDateTogether(ArgumentMultimap argMultiMapProperties, Prefix taskTypes) {
        boolean isContainsTodo = taskTypes instanceof Prefix && taskTypes.equals(PREFIX_TODO);
        String todoAndDateOptionTogetherMessage = "todo/ and date/ are together";

        if (isContainsTodo && argMultiMapProperties.isPrefixExist(PREFIX_DATE)) {
            logger.warning(todoAndDateOptionTogetherMessage);
            addErrorMessage(FindTaskCommand.TODO_AND_DATE_OPTION_TOGETHER);
        }
    }

    private Prefix getPrefix(ArgumentMultimap argMultiMap) {
        List<Prefix> currentPrefixes = Arrays.stream(TASK_TYPES)
                .filter(prefix -> argMultiMap.isPrefixExist(prefix)).collect(Collectors.toList());
        String prefixEnteredMessage = "Prefix entered:";

        if (!currentPrefixes.isEmpty()) {
            logger.info(String.join(" ", prefixEnteredMessage, currentPrefixes.toString()));
        }

        if (currentPrefixes.size() > 1) {
            // If the user enters more than 1 task type
            String moreThanOneTaskTypeMessage = "More than one task type entered as options";
            logger.warning(moreThanOneTaskTypeMessage);
            addErrorMessage(FindTaskCommand.MORE_THAN_ONE_TASK_TYPE);
            return null;
        } else if (currentPrefixes.isEmpty()) {
            return null;
        } else {
            Prefix currentPrefix = currentPrefixes.get(0);
            return currentPrefix;
        }
    }

    private List<String> getDescriptions(ArgumentMultimap argMultiMap) {
        String namesMessage = "Description:";
        List<String> descriptions = null;
        if (argMultiMap.isPrefixExist(PREFIX_DESCRIPTION)) {
            String descriptionString = argMultiMap.getValue(PREFIX_DESCRIPTION).get();
            descriptions = List.of(descriptionString.split("\\s+"));
            logger.info(String.join(" ", namesMessage, descriptions.toString()));
            checkIfValidDescription(descriptions);
        }
        return descriptions;
    }

    private void checkIfValidDescription(List<String> description) {
        String invalidDescriptionFoundMessage = "Invalid Description found.";
        boolean isValid = description.stream().allMatch(name -> Description.isValidDescription(name));
        if (!isValid) {
            logger.warning(invalidDescriptionFoundMessage);
            addErrorMessage(FindTaskCommand.INVALID_DESCRIPTION);
        }
    }

    private Date getTaskDate(ArgumentMultimap argMultiMap) {
        String dateMessage = "Date:";
        Date date = null;
        if (argMultiMap.isPrefixExist(PREFIX_DATE)) {
            String dateString = argMultiMap.getValue(PREFIX_DATE).get().trim();
            logger.info(String.join(" ", dateMessage, dateString));
            boolean isDateValid = checkIfDateIsValid(dateString);
            if (isDateValid) {
                date = new Date(dateString);
            }
        }
        return date;
    }

    private boolean checkIfDateIsValid(String dateString) {
        String invalidDateMessage = "Invalid date entered";
        boolean isValidDate = Date.isValidDate(dateString);
        if (!isValidDate) {
            logger.warning(invalidDateMessage);
            addErrorMessage(FindTaskCommand.INVALID_DATE);
            return false;
        }
        return true;
    }

    private Priority getTaskPriority(ArgumentMultimap argMultiMap) {
        String prioritySelectedMessage = "Priority selected:";
        Priority priority = null;
        if (argMultiMap.isPrefixExist(PREFIX_PRIORITY)) {
            String priorityString = argMultiMap.getValue(PREFIX_PRIORITY).get().trim().toUpperCase();
            logger.info(String.join(" ", prioritySelectedMessage, priorityString));
            priority = checkPriority(priorityString);
        }
        return priority;
    }

    private Priority checkPriority(String priorityString) {
        String invalidPriorityStringMessage = "Invalid Priority String";
        switch (priorityString) {
        case "HIGH":
            return Priority.HIGH;
        case "MEDIUM":
            return Priority.MEDIUM;
        case "LOW":
            return Priority.LOW;
        case "NONE":
            return Priority.NONE;
        default:
            logger.warning(invalidPriorityStringMessage);
            addErrorMessage(FindTaskCommand.INVALID_PRIORITY);
            return null;
        }
    }

    private String getAssignee(ArgumentMultimap argMultiMap) {
        String assigneeMessage = "Assignee:";
        String assignee = null;
        if (argMultiMap.isPrefixExist(PREFIX_ASSIGNEES)) {
            assignee = argMultiMap.getValue(PREFIX_ASSIGNEES).get().trim();
            logger.info(String.join(" ", assigneeMessage, assignee));
            checkedIfNameValid(assignee);
        }
        return assignee;
    }

    private void checkedIfNameValid(String assignee) {
        String invalidNameMessage = "Invalid name";
        boolean isNameValid = Name.isValidName(assignee);
        if (!isNameValid) {
            logger.warning(invalidNameMessage);
            addErrorMessage(FindTaskCommand.INVALID_ASSIGNEE);
        }
    }

    private Boolean getIsMarked(ArgumentMultimap argMultiMap) {
        String booleanMessage = "Boolean entered:";
        Boolean isMarked = null;
        if (argMultiMap.isPrefixExist(PREFIX_IS_MARKED)) {
            String booleanString = argMultiMap.getValue(PREFIX_IS_MARKED).get().trim().toLowerCase();
            logger.info(String.join(" ", booleanMessage, booleanString));
            boolean isEitherTrueOrFalse = checkIfEitherTrueOrFalse(booleanString);
            if (isEitherTrueOrFalse) {
                isMarked = Boolean.valueOf(booleanString);
            }
        }
        return isMarked;
    }

    private boolean checkIfEitherTrueOrFalse(String booleanString) {
        String invalidBooleanMessage = "Invalid boolean";
        if (booleanString.equals("true") || booleanString.equals("false")) {
            return true;
        } else if (!booleanString.equals("true") && !booleanString.equals("false")) {
            logger.warning(invalidBooleanMessage);
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
