package io.dating.tinder.ai.backend;

import io.dating.tinder.ai.backend.profile.Profile;
import io.dating.tinder.ai.backend.profile.Gender;
import io.dating.tinder.ai.backend.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(namedQueriesLocation = "io...profile")
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Profile profile = new Profile("1",
				"Joydeep",
				"Bhattacharjee",
				44,
				Gender.MALE,
				"My bio",
				"http://image.url",
				"scope");
		profileRepository.save(profile);
		//profileRepository.findAll().
	}
}
