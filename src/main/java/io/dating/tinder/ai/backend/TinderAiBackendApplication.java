package io.dating.tinder.ai.backend;


import io.dating.tinder.ai.backend.conversation.ConversationRepository;
import io.dating.tinder.ai.backend.profile.ProfileCreationService;
import io.dating.tinder.ai.backend.profile.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private ConversationRepository convRepository;

	@Autowired
	private ProfileCreationService profileCreationService;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		profileCreationService.saveProfilesToDB();
	/*	Profile profile = new Profile("1",
				"Joydeep",
				"Bhattacharjee",
				44,
				Gender.MALE,
				"My bio",
				"http://image.url",
				"scope");
		profileRepository.save(profile).block();
		profileRepository.findAll()
				.doOnNext(x -> log.info("test fetch profile: " + x))
				.subscribe();
		Conversation c = new Conversation("1", profile.id(), List.of(new ChatMessages("Hi","1", LocalDateTime.now())));
		convRepository.save(c).block();
		convRepository.findAll().doOnNext(x -> log.info("test fetch conv: " + x))
				.subscribe();*/
	}
}
