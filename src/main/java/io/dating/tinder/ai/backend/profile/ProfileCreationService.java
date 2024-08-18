package io.dating.tinder.ai.backend.profile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.io.*;

import java.util.*;



@Service
@Slf4j
public class ProfileCreationService {

    private static final String PROFILES_FILE_PATH = "profiles.json";

    @Value("${startup-actions.initializeProfiles}")
    private Boolean initializeProfiles;

    @Value("${tinderai.lookingForGender}")
    private String lookingForGender;

    @Value("#{${tinderai.character.user}}")
    private Map<String, String> userProfileProperties;

    private ProfileRepository profileRepository;


    public ProfileCreationService(ProfileRepository profileRepository) {

        this.profileRepository = profileRepository;



    }


    public void saveProfilesToDB() {
        Gson gson = new Gson();
        try {
            List<Profile> existingProfiles = gson.fromJson(
                    new FileReader(PROFILES_FILE_PATH),
                    new TypeToken<ArrayList<Profile>>() {}.getType()
            );
            profileRepository.deleteAll();
            profileRepository.saveAll(existingProfiles).blockFirst();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Profile profile = new Profile(
                userProfileProperties.get("id"),
                userProfileProperties.get("firstName"),
                userProfileProperties.get("lastName"),
                Integer.parseInt(userProfileProperties.get("age")),
                Gender.valueOf(userProfileProperties.get("gender")),
                userProfileProperties.get("ethnicity"),
                userProfileProperties.get("bio"),
                userProfileProperties.get("imageUrl"),
                userProfileProperties.get("myersBriggsPersonalityType")
        );
        log.info("saving profile {}", profile);
        profileRepository.save(profile);

    }
}
