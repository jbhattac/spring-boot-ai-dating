package io.dating.tinder.ai.backend.conversation;

import lombok.Builder;

import java.util.List;
@Builder
public record Conversation(String id, String profileId, List<ChatMessages> messages) {
}
