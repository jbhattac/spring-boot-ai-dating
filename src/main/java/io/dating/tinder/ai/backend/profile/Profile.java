package io.dating.tinder.ai.backend.profile;

public record Profile(String id,
                      String firstName,
                      String lastName,
                      Integer age,
                      Gender gender,
                      String bio,
                      String imageUrl,
                      String myBriggsPersonalityType) {
}
