package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.participant.Participant;
import seedu.address.model.tag.Tag;

public class ParticipantUtil {

    /**
     * Returns an add command string for adding the {@code participant}.
     */
    public static String getAddCommand(Participant participant) {
        return AddCommand.COMMAND_WORD + " " + getParticipantDetails(participant);
    }

    /**
     * Returns the part of command string for the given {@code participant}'s details.
     */
    public static String getParticipantDetails(Participant participant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + participant.getName().fullName + " ");
        sb.append(PREFIX_PHONE + participant.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + participant.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + participant.getAddress().value + " ");
        participant.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditParticipantDescriptor}'s details.
     */
    public static String getEditParticipantDescriptorDetails(EditCommand.EditParticipantDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
