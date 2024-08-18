package io.dating.tinder.ai.backend.conversation;

import io.dating.tinder.ai.backend.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController(value = "/conversation")
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    @PostMapping("create")
    public Mono<ResponseEntity<Conversation>> createNewConversation(@RequestBody ConversationRequest conversationRequest) {
        return profileRepository.findById(conversationRequest.profileId())
                .flatMap(profile -> {
                    var conversation = Conversation.builder()
                            .id(UUID.randomUUID().toString())
                            .profileId(profile.id())
                            .messages(conversationRequest.messages())
                            .build();
                    return conversationRepository.save(conversation)
                            .map(ResponseEntity::ok);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{conversationId}")
    public Mono<ResponseEntity<Conversation>> addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessages chatMessages) {
       return conversationRepository.findById(conversationId).flatMap(conversation -> {
                    conversation.messages().add(chatMessages);
                    return conversationRepository.save(conversation).map(ResponseEntity::ok);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{conversationId}")
    public Mono<ResponseEntity<Conversation>> getConversation(@PathVariable String conversationId){
        return conversationRepository.findById(conversationId).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
