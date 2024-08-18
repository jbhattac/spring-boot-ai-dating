package io.dating.tinder.ai.backend.conversation;

import java.util.List;

public record ConversationRequest(String profileId, List<ChatMessages> messages) {
}
