package unibook.logic.parser;

import static java.util.Objects.requireNonNull;
import static unibook.commons.core.Messages.MESSAGE_MISSING_OPTION;
import static unibook.logic.commands.EditCommand.MESSAGE_WRONG_FIELDS;
import static unibook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static unibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static unibook.logic.parser.CliSyntax.PREFIX_GROUP;
import static unibook.logic.parser.CliSyntax.PREFIX_KEYEVENT;
import static unibook.logic.parser.CliSyntax.PREFIX_MEETINGTIME;
import static unibook.logic.parser.CliSyntax.PREFIX_MODULE;
import static unibook.logic.parser.CliSyntax.PREFIX_NAME;
import static unibook.logic.parser.CliSyntax.PREFIX_NEWMOD;
import static unibook.logic.parser.CliSyntax.PREFIX_OFFICE;
import static unibook.logic.parser.CliSyntax.PREFIX_OPTION;
import static unibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static unibook.logic.parser.CliSyntax.PREFIX_TAG;
import static unibook.logic.parser.CliSyntax.PREFIX_TYPE;
import static unibook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import unibook.commons.core.LogsCenter;
import unibook.commons.core.index.Index;
import unibook.logic.LogicManager;
import unibook.logic.commands.EditCommand;
import unibook.logic.commands.EditCommand.EditGroupDescriptor;
import unibook.logic.commands.EditCommand.EditModuleDescriptor;
import unibook.logic.commands.EditCommand.EditPersonDescriptor;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OPTION,
                        PREFIX_NAME,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_TAG,
                        PREFIX_MODULE,
                        PREFIX_NEWMOD,
                        PREFIX_GROUP,
                        PREFIX_MEETINGTIME,
                        PREFIX_TYPE,
                        PREFIX_DATETIME,
                        PREFIX_KEYEVENT,
                        PREFIX_OFFICE);

        Index index;

        // Checks if there is proper indexing + proper option
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            logger.info(index.toString());
        } catch (ParseException pe) {
            if (argMultimap.getValue(PREFIX_OPTION).equals(Optional.empty())) {
                throw new ParseException((MESSAGE_INVALID_INDEX + "\n" + EditCommand.MESSAGE_OPTION_NOT_FOUND));
            } else {
                if (argMultimap.getValue(PREFIX_OPTION).get().equals("person")) {
                    throw new ParseException(MESSAGE_INVALID_INDEX + "\n" + EditCommand.PERSON_MESSAGE_USAGE, pe);
                } else if (argMultimap.getValue(PREFIX_OPTION).get().equals("module")) {
                    throw new ParseException(MESSAGE_INVALID_INDEX + "\n" + EditCommand.MODULE_MESSAGE_USAGE, pe);
                } else if (argMultimap.getValue(PREFIX_OPTION).get().equals("group")) {
                    throw new ParseException(MESSAGE_INVALID_INDEX + "\n" + EditCommand.GROUP_MESSAGE_USAGE, pe);
                } else {
                    throw new ParseException(MESSAGE_INVALID_INDEX + "\n" + EditCommand.KEYEVENT_MESSAGE_USAGE, pe);
                }
            }

        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        EditGroupDescriptor editGroupDescriptor = new EditGroupDescriptor();

        if (argMultimap.getValue(PREFIX_OPTION).equals(Optional.empty())) {
            throw new ParseException((MESSAGE_MISSING_OPTION));
        }

        if (argMultimap.getValue(PREFIX_OPTION).get().equals("person")) {
            if (argMultimap.getValue(PREFIX_MEETINGTIME).isPresent()
                    || argMultimap.getValue(PREFIX_DATETIME).isPresent()
                    || argMultimap.getValue(PREFIX_KEYEVENT).isPresent()) {
                throw new ParseException(MESSAGE_WRONG_FIELDS + EditCommand.PERSON_MESSAGE_USAGE);
            }

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }

            if (argMultimap.getValue(PREFIX_OFFICE).isPresent()) {
                editPersonDescriptor.setOffice(ParserUtil.parseOffice(argMultimap.getValue(PREFIX_OFFICE).get()));
            }


            Optional<ModuleCode> moduleCodeToAdd;
            Optional<String> groupName;
            if (argMultimap.getValue(PREFIX_MODULE).isPresent() && argMultimap.getValue(PREFIX_GROUP).isEmpty()
                || argMultimap.getValue(PREFIX_GROUP).isPresent() && argMultimap.getValue(PREFIX_MODULE).isEmpty()) {
                throw new ParseException(EditCommand.MESSAGE_ADDTOGROUP_WRONG_FORMAT);
            } else if (argMultimap.getValue(PREFIX_MODULE).isPresent()
                    && argMultimap.getValue(PREFIX_GROUP).isPresent()) {
                moduleCodeToAdd = Optional.of(ParserUtil.parseModuleCode(argMultimap
                        .getValue(PREFIX_MODULE)
                        .get()));
                groupName = Optional.of(ParserUtil.parseGroupString(argMultimap.getValue(PREFIX_GROUP).get()));
                editPersonDescriptor.setModCode(moduleCodeToAdd);
                editPersonDescriptor.setGroupName(groupName);
            } else { }

            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

            Optional<Set<ModuleCode>> module = argMultimap.getValue(PREFIX_NEWMOD).isPresent()
                    ? parseModulesForEdit(argMultimap.getValue(PREFIX_NEWMOD).get().toUpperCase())
                    : Optional.empty();
            if (module.isEmpty() && !editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            if (module.isPresent()) {
                return new EditCommand(index, editPersonDescriptor, module.get().iterator().next());
            }
            return new EditCommand(index, editPersonDescriptor);
        } else if (argMultimap.getValue(PREFIX_OPTION).get().equals("module")) {
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()
                    || argMultimap.getValue(PREFIX_EMAIL).isPresent()
                    || argMultimap.getValue(PREFIX_OFFICE).isPresent()
                    || argMultimap.getValue(PREFIX_NEWMOD).isPresent()) {
                throw new ParseException(MESSAGE_WRONG_FIELDS + EditCommand.MODULE_MESSAGE_USAGE);
            }
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editModuleDescriptor.setModuleName(ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get()));
            }
            if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
                editModuleDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap
                        .getValue(PREFIX_MODULE).get()));
            }

            if (!editModuleDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return new EditCommand(index, editModuleDescriptor);
        } else if (argMultimap.getValue(PREFIX_OPTION).get().equals("group")) {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()
                    || argMultimap.getValue(PREFIX_DATETIME).isPresent()
                    || argMultimap.getValue(PREFIX_KEYEVENT).isPresent()
                    || argMultimap.getValue(PREFIX_PHONE).isPresent()
                    || argMultimap.getValue(PREFIX_EMAIL).isPresent()
                    || argMultimap.getValue(PREFIX_OFFICE).isPresent()
                    || argMultimap.getValue(PREFIX_NEWMOD).isPresent()) {
                throw new ParseException(MESSAGE_WRONG_FIELDS + EditCommand.GROUP_MESSAGE_USAGE);
            }

            if (!argMultimap.getValue(PREFIX_MODULE).isPresent()) {
                throw new ParseException(EditCommand.MESSAGE_EDIT_MISSING);
            } else {
                ModuleCode modCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
                editGroupDescriptor.setModuleCode(modCode);

                if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
                    editGroupDescriptor.setGroupName(ParserUtil.parseGroupString(argMultimap
                            .getValue(PREFIX_GROUP).get()));
                }
                if (argMultimap.getValue(PREFIX_MEETINGTIME).isPresent()) {
                    try {
                        editGroupDescriptor.setMeetingTimes(ParserUtil.parseMeetingTime(argMultimap
                                .getValue(PREFIX_MEETINGTIME).get()));
                    } catch (DateTimeParseException e) {
                        throw new ParseException(EditCommand.MESSAGE_WRONG_DATE_FORMAT);
                    }
                }

                if (!editGroupDescriptor.isAnyFieldEdited()) {
                    throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
                }

                editModuleDescriptor.setGroups(editGroupDescriptor);
                editPersonDescriptor.setGroups(editGroupDescriptor);

                // this index is for the index of the group within the module
                return new EditCommand(index, editPersonDescriptor, editModuleDescriptor);
            }
        } else if (argMultimap.getValue(PREFIX_OPTION).get().equals("keyevent")) {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()
                    || argMultimap.getValue(PREFIX_PHONE).isPresent()
                    || argMultimap.getValue(PREFIX_EMAIL).isPresent()
                    || argMultimap.getValue(PREFIX_OFFICE).isPresent()
                    || argMultimap.getValue(PREFIX_NEWMOD).isPresent()
                    || argMultimap.getValue(PREFIX_GROUP).isPresent()
                    || argMultimap.getValue(PREFIX_MODULE).isPresent()
                    || argMultimap.getValue(PREFIX_MEETINGTIME).isPresent()) {
                throw new ParseException(MESSAGE_WRONG_FIELDS + EditCommand.KEYEVENT_MESSAGE_USAGE);
            }

            ModuleKeyEvent mod = new ModuleKeyEvent();
            Optional<ModuleKeyEvent.KeyEventType> eventType = Optional.empty();
            Optional<LocalDateTime> dt = Optional.empty();

            if (!argMultimap.getValue(PREFIX_KEYEVENT).isPresent()) {
                throw new ParseException(EditCommand.MESSAGE_KEYEVENT_INDEX_MISSING);
            } else {
                logger.info(argMultimap.getValue(PREFIX_KEYEVENT).get());
                Index i = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_KEYEVENT).get());
                int integer = i.getZeroBased();
                editModuleDescriptor.setIdx(integer);
            }

            if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
                eventType = Optional.of(ParserUtil.parseKeyEventTypeString(argMultimap.getValue(PREFIX_TYPE).get()));
            }
            if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
                dt = Optional.of(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
            }
            if (eventType.isEmpty() && dt.isEmpty()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            } else if (eventType.isPresent() && dt.isEmpty()) {
                mod.setKeyDateType(eventType.get());
                editModuleDescriptor.setKeyEvents(mod);
            } else if (dt.isPresent() && eventType.isEmpty()) {
                mod.setDateTime(dt.get());
                editModuleDescriptor.setKeyEvents(mod);
            } else {
                mod.setDateTime(dt.get());
                mod.setKeyDateType(eventType.get());
                editModuleDescriptor.setKeyEvents(mod);
            }
            return new EditCommand(index, editModuleDescriptor);

        } else {
            throw new ParseException(EditCommand.MESSAGE_OPTION_NOT_FOUND);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<Module>} if {@code modules} is non-empty.
     * If {@code modules} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Module>} containing zero modules.
     */
    private Optional<Set<ModuleCode>> parseModulesForEdit(String modules) throws ParseException {
        if (modules == " ") {
            return Optional.empty();
        }
        Set<ModuleCode> moduleSet = new HashSet<ModuleCode>();
        ModuleCode modCode = ParserUtil.parseModuleCode(modules);
        if (!ModuleCode.isValidModuleCode(modCode.toString())) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        moduleSet.add(modCode);
        return Optional.of(moduleSet);
    }
}
