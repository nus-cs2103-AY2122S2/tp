package seedu.address.logic.parser;

import static seedu.address.commons.core.DataTypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.DataTypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.DataTypeFlags.FLAG_POSITION;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ExportCsvCommand;
import seedu.address.logic.commands.applicant.ExportApplicantCsvCommand;
import seedu.address.logic.commands.interview.ExportInterviewCsvCommand;
import seedu.address.logic.commands.position.ExportPositionCsvCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ExportCsvCommandParser implements Parser<ExportCsvCommand> {

    private final Logger logger = LogsCenter.getLogger(ExportCsvCommandParser.class);

    @Override
    public ExportCsvCommand parse(String args) throws ParseException {
        char flag = ArgumentTokenizer.getFlag(args.trim());

        if (flag == FLAG_APPLICANT) {
            logger.log(Level.INFO, "Export csv for applicant");
            return new ExportApplicantCsvCommand();
        } else if (flag == FLAG_INTERVIEW) {
            logger.log(Level.INFO, "Export csv for interview");
            return new ExportInterviewCsvCommand();
        } else if (flag == FLAG_POSITION) {
            logger.log(Level.INFO, "Export csv for position");
            return new ExportPositionCsvCommand();
        }
        logger.log(Level.WARNING, "Export command did not find valid flag and did not throw an exception");
        return null;
    }
}
