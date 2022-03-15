//package seedu.address.logic.parser;
//
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.HelpCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//
//public class HelpCommandParser implements Parser<HelpCommand> {
//    @Override
//    public HelpCommand parse(String args) throws ParseException {
//        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
//        Class<? extends Command> commandClass;
//        try {
//            commandClass = ParserUtil.parseCommand(argMultimap.getPreamble());
//        } catch (ParseException pe) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), pe);
//        }
//
//        return new HelpCommand(commandClass);
//    }
//}
