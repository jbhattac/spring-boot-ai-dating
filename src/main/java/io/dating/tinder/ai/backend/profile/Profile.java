package io.dating.tinder.ai.backend.profile;

import groovy.transform.ToString;

@ToString
public record Profile(String id,
                      String firstName,
                      String lastName,
                      Integer age,
                      Gender gender,
                      String bio,
                      String imageUrl,
                      String ethnicity,
                      String myersBriggsPersonalityType) {
}
