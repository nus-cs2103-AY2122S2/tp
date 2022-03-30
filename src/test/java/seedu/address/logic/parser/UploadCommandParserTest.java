package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UploadCommand;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

public class UploadCommandParserTest {

    private UploadCommandParser parser = new UploadCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsUploadCommand() {
        UserImage testImage1 = new UserImage(new FilePath("./src/test/resources/images/success.png"), "");
        UserImage testImage2 = new UserImage(new FilePath("./src/test/resources/images/fail.png"), "fail");
        Set<UserImage> userImages = new LinkedHashSet<>(Arrays.asList(testImage1, testImage2));
        UploadCommand expectedUploadCommand =
              new UploadCommand(INDEX_FIRST_PERSON, userImages);
        assertParseSuccess(parser, "1 i/./src/test/resources/images/success.png"
                            + " i/./src/test/resources/images/fail.png:fail", expectedUploadCommand);
    }
}
