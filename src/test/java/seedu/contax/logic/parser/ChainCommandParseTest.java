package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.contax.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.contax.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.contax.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.AddCommand;
import seedu.contax.logic.commands.ChainCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.EditCommand;
import seedu.contax.testutil.EditPersonDescriptorBuilder;
import seedu.contax.testutil.PersonBuilder;


public class ChainCommandParseTest {

    private final ChainCommandParser parser = new ChainCommandParser();

    @Test
    public void parse_editThenAddAllFieldsPresent_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        List<Command> commandList = new ArrayList<>();
        commandList.add(expectedCommand);
        commandList.add(new AddCommand(new PersonBuilder().build()));

        assertParseSuccess(parser, "edit " + userInput
                        + "&& add n/Amy Bee p/85355255 e/amy@gmail.com a/123, Jurong West Ave 6, #08-111",
                new ChainCommand(commandList));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "-5" + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChainCommand.MESSAGE_USAGE));
    }
}
