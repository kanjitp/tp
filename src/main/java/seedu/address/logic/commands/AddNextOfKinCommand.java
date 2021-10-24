package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Participant;

public class AddNextOfKinCommand extends Command {

    public static final String COMMAND_WORD = "addNok";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a next of kin to a participant with specified "
            + "index\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_TAG + "TAG"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Jennette Doe "
            + PREFIX_PHONE + "98234532 "
            + PREFIX_TAG + "Spouse ";

    public static final String MESSAGE_SUCCESS = "Added next of kin:\n%1$s \nto %2$s successfully";

    private final Index participantIndex;
    private final NextOfKin nextOfKin;

    /**
     * This is a constructor for AddNextOfKinCommand.
     *
     * @param nextOfKin to be added.
     */
    public AddNextOfKinCommand(Index ParticipantIndex, NextOfKin nextOfKin) {
        requireNonNull(ParticipantIndex);
        requireNonNull(nextOfKin);
        this.participantIndex = ParticipantIndex;
        this.nextOfKin = nextOfKin;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Participant> lastShownParticipantList = model.getFilteredParticipantList();

        Participant selectedParticipant;

        try {
            selectedParticipant = lastShownParticipantList.get(participantIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
        }

        if (selectedParticipant.hasNextOfKin(nextOfKin)) {
            throw new CommandException(Messages.showNextOfKinExists(nextOfKin.getFullName(),
                    selectedParticipant.getFullName()));
        }

        selectedParticipant.addNextOfKin(nextOfKin);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                nextOfKin, selectedParticipant.getFullName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddNextOfKinCommand
                && participantIndex.equals(((AddNextOfKinCommand) other).participantIndex))
                && nextOfKin.equals(((AddNextOfKinCommand) other).nextOfKin);
    }
}