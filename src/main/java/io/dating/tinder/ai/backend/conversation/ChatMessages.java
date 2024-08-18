package io.dating.tinder.ai.backend.conversation;

import java.time.LocalDateTime;

public record ChatMessages(String message, String authorId, LocalDateTime messageTime) {
}
