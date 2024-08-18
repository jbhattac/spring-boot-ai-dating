package io.dating.tinder.ai.backend.conversation;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends ReactiveMongoRepository<Conversation, String> {
}
