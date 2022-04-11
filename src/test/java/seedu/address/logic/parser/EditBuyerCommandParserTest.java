package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

class EditBuyerCommandParserTest {

    public static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE);

    public static final String VALID_NAME = "Bob";
    public static final String VALID_PHONE = "98765432";
    public static final String VALID_TAG = "friend";
    public static final String VALID_HOUSE_TYPE = VALID_HOUSE_TYPE_1;
    public static final String VALID_LOCATION = VALID_LOCATION_1;
    public static final String VALID_PRICE_RANGE = VALID_PR_2;


    public final EditBuyerCommandParser parser = new EditBuyerCommandParser();
    private EditBuyerCommand.EditBuyerDescriptor descriptorStub1 = new EditBuyerDescriptorBuilder()
            .withName(VALID_NAME).build();

    private EditBuyerCommand.EditBuyerDescriptor descriptorStub2 = new EditBuyerDescriptorBuilder()
            .withPhone(VALID_PHONE).build();

    private EditBuyerCommand.EditBuyerDescriptor descriptorStub3 = new EditBuyerDescriptorBuilder()
        .withTags(VALID_TAG).build();

    private EditBuyerCommand.EditBuyerDescriptor descriptorStub4 = new EditBuyerDescriptorBuilder()
        .withHouseType(VALID_HOUSE_TYPE_1).build();

    private EditBuyerCommand.EditBuyerDescriptor descriptorStub5 = new EditBuyerDescriptorBuilder()
        .withLocation(VALID_LOCATION_1).build();

    private EditBuyerCommand.EditBuyerDescriptor descriptorStub6 = new EditBuyerDescriptorBuilder()
        .withPriceRange(10000, 20000).build();

    @Test
    void parse_editBuyer_success() {
        Index stubIndex = Index.fromOneBased(2);
        String index = "2 ";
        assertParseSuccess(parser, index + PREFIX_NAME + VALID_NAME,
            new EditBuyerCommand(stubIndex, descriptorStub1));

        assertParseSuccess(parser, index + PREFIX_PHONE + VALID_PHONE,
            new EditBuyerCommand(stubIndex, descriptorStub2));

        assertParseSuccess(parser, index + PREFIX_TAG + VALID_TAG,
            new EditBuyerCommand(stubIndex, descriptorStub3));

        assertParseSuccess(parser, index + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE,
            new EditBuyerCommand(stubIndex, descriptorStub4));

        assertParseSuccess(parser, index + PREFIX_LOCATION + VALID_LOCATION,
            new EditBuyerCommand(stubIndex, descriptorStub5));

        assertParseSuccess(parser, index + PREFIX_PRICE_RANGE + VALID_PRICE_RANGE,
            new EditBuyerCommand(stubIndex, descriptorStub6));
    }

    @Test
    void parse_noFieldEdited_throwsParseException() {
        String index = "2 ";
        assertParseFailure(parser, index, EditBuyerCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    void parse_invalidIndex_throwsParseException() {
        String index = "-1 ";
        assertParseFailure(parser, index + PREFIX_NAME + VALID_NAME,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_weirdString_failure() {
        assertParseFailure(parser, "//!", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "This is weird", MESSAGE_INVALID_FORMAT);
    }

}
