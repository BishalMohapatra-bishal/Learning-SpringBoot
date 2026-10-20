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

import java.util.List;
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
        return new UserResponseDto(savesUser.getId(), savesUser.getName(), profileId, bio);
    }

    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        if (id == null) {
            throw new IllegalArgumentException("No id found");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with this id " + id));

        if (userRequestDto.name() != null) {
            user.setName(userRequestDto.name());
        }

        if (userRequestDto.profileId() != null) {
            Profile profile = profileRepository.findById(userRequestDto.profileId())
                    .orElseThrow(() -> new RuntimeException("No profile associated with this provided id"));
            user.setProfile(profile);
        }

        User savedUser = userRepository.save(user);

        Long profileId = (savedUser.getProfile() != null) ? savedUser.getProfile().getId() : null;
        String bio = (savedUser.getProfile() != null) ? savedUser.getProfile().getBio() : null;
        return new UserResponseDto(savedUser.getId(), savedUser.getName(), profileId, bio);
    }

    public UserResponseDto getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Use ID cannot be null");
        }
        User savedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with ID" + id));

        Long profileId = (savedUser.getProfile() != null) ? savedUser.getProfile().getId() : null;
        String bio = (savedUser.getProfile() != null) ? savedUser.getProfile().getBio() : null;

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                profileId,
                bio);
    }

    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    Long profileId = (user.getProfile() != null) ? user.getProfile().getId() : null;
                    String bio = (user.getProfile() != null) ? user.getProfile().getBio() : null;
                    return new UserResponseDto(
                            user.getId(),
                            user.getName(),
                            profileId,
                            bio
                    );
                })
                .toList();
    }
}
