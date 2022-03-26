package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;

public class GradeLabCommand extends EditLabCommand {

    public static final String COMMAND_WORD = "labgrad";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the specified lab of the "
            + "student identified by the index number used in the displayed student list to 'GRADED' "
            + "and initializes the marks to the marks specified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LAB + "LAB_NUMBER "
            + PREFIX_LABMARK + "LAB_MARKS\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LAB + "1 " + PREFIX_LABMARK + "10";

    public static final String MESSAGE_GRADE_LAB_SUCCESS = "Graded Lab %1$s for %2$s";

    public GradeLabCommand(Index index, int labNumber, LabMark labMark) {
        super(index, labNumber, labMark);
    }

    @Override
    public boolean isValidCommand(EditLabCommand e) {
        return e.newStatus == LabStatus.GRADED && !e.newMark.isEmpty();
    }

    @Override
    public boolean isLabEditableByCurrentCommand(Lab l) {
        return l.labStatus != LabStatus.GRADED;
    }

    @Override
    public String getExecutionSuccessMessage() {
        return MESSAGE_GRADE_LAB_SUCCESS;
    }

}
