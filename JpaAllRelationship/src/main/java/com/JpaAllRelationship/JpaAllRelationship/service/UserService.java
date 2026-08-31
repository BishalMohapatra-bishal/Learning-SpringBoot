package com.JpaAllRelationship.JpaAllRelationship.service;

import com.JpaAllRelationship.JpaAllRelationship.dto.ProfileResponseDto;
import com.JpaAllRelationship.JpaAllRelationship.dto.UserRequestDto;
import com.JpaAllRelationship.JpaAllRelationship.dto.UserResponseDto;
import com.JpaAllRelationship.JpaAllRelationship.entity.Profile;
import com.JpaAllRelationship.JpaAllRelationship.entity.User;
import com.JpaAllRelationship.JpaAllRelationship.repository.ProfileRepository;
import com.JpaAllRelationship.JpaAllRelationship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.name());

        if (requestDto.profileId() != null) {
            Profile profile = profileRepository.findById(requestDto.profileId())
                    .orElseThrow(() -> new RuntimeException("Profile not found with id: " + requestDto.profileId()));
            user.setProfile(profile);
        }
        User savesUser = userRepository.save(user);

        Long profileId = (savesUser.getProfile() != null)  ? savesUser.getProfile().getId() : null;
        String bio = (savesUser.getProfile() != null) ? savesUser.getProfile().getBio() : null;
        return new UserResponseDto(savesUser.getId(), savesUser.getName(),
                profileId, bio);
    }

    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        if (id == null) {
            throw new NullPointerException("No id found");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with this is." + id));
        user.setName(userRequestDto.name());

        Profile profile = profileRepository.findById(userRequestDto.profileId())
                .orElseThrow(() -> new RuntimeException("No profile associated with this provided id"));

//        user.setProfile();
        return null;


    }
}
