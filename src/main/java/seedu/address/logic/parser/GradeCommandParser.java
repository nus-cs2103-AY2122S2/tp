package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIMPLE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.Grade;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;


/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    public static final String MESSAGE_ASSESSMENT_NOT_FOUND =
                "Unable to find assessment using the given the module index and simple name!";

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns a GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public GradeCommand parse(String args, Model model) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_GRADE, PREFIX_MODULE_INDEX, PREFIX_ASSESSMENT_INDEX, PREFIX_SIMPLE_NAME, PREFIX_STUDENT);

        if ((!arePrefixesPresent(argMultimap, PREFIX_ASSESSMENT_INDEX, PREFIX_MODULE_INDEX,
                PREFIX_SIMPLE_NAME, PREFIX_STUDENT)) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE));
        }

        try {
            Assessment assessment;
            if (argMultimap.getValue(PREFIX_ASSESSMENT_INDEX).isPresent()) {
                Index assessmentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSESSMENT_INDEX).get());
                if (!ParserUtil.checkValidIndex(assessmentIndex, model.getUnfilteredAssessmentList().size())) {
                    throw new ParseException(MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX);
                }
                assessment = model.getUnfilteredAssessmentList().get(assessmentIndex.getZeroBased());
            } else {
                Index moduleIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MODULE_INDEX).get());
                SimpleName simpleName = ParserUtil.parseSimpleName(argMultimap.getValue(PREFIX_SIMPLE_NAME)).get();
                if (!ParserUtil.checkValidIndex(moduleIndex, model.getUnfilteredModuleList().size())) {
                    throw new ParseException(MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
                }
                TaModule module = model.getUnfilteredModuleList().get(moduleIndex.getZeroBased());
                assessment = model.getUnfilteredAssessmentList().stream()
                        .filter(a -> a.getModule().isSameModule(module)
                        && a.getSimpleName().equals(simpleName)).findFirst().orElseThrow(() ->
                new ParseException(MESSAGE_ASSESSMENT_NOT_FOUND));
            }

            Optional<Grade> grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE));
            ObservableList<Student> students = ParserUtil.parseStudents(
                    argMultimap.getValue(PREFIX_STUDENT).get(), model);
            return new GradeCommand(assessment, grade, students);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format("%s\n%s", pe.getMessage(), GradeCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix assessmentIndex,
                                              Prefix moduleIndex, Prefix simpleName, Prefix student) {
        if (argumentMultimap.getValue(assessmentIndex).isPresent()
                && (argumentMultimap.getValue(moduleIndex).isPresent()
                || argumentMultimap.getValue(simpleName).isPresent())) {
            return false;
        } else if (argumentMultimap.getValue(assessmentIndex).isPresent()
                && argumentMultimap.getValue(student).isPresent()) {
            return true;
        } else if (argumentMultimap.getValue(moduleIndex).isPresent()
                && argumentMultimap.getValue(simpleName).isPresent()
                && argumentMultimap.getValue(student).isPresent()) {
            return true;
        }
        return false;
    }

}
