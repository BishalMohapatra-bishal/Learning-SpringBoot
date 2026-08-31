package com.JpaAllRelationship.JpaAllRelationship.service;

import com.JpaAllRelationship.JpaAllRelationship.dto.ProfileRequestDto;
import com.JpaAllRelationship.JpaAllRelationship.dto.ProfileResponseDto;
import com.JpaAllRelationship.JpaAllRelationship.entity.Profile;
import com.JpaAllRelationship.JpaAllRelationship.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {
        if (profileRequestDto == null) {
            throw new IllegalArgumentException("Request can not be null");
        }

        Profile profile = new Profile();
        profile.setBio(profileRequestDto.bio());

        Profile savedProfile = profileRepository.save(profile);
        return new ProfileResponseDto(savedProfile.getId(), savedProfile.getBio());


    }
}
