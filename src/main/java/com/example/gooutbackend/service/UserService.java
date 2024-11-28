package com.example.gooutbackend.service;

import com.example.gooutbackend.dto.playground.PlaygroundEventDto;
import com.example.gooutbackend.dto.sport.SportWithLevelDto;
import com.example.gooutbackend.dto.user.UserDtoIn;
import com.example.gooutbackend.dto.user.UserInformationDto;
import com.example.gooutbackend.entity.Achievement;
import com.example.gooutbackend.entity.User;
import com.example.gooutbackend.entity.UserEvent;
import com.example.gooutbackend.entity.UserSport;
import com.example.gooutbackend.mapper.UserMapper;
import com.example.gooutbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserSportRepository userSportRepository;
    private final SportRepository sportRepository;
    private final AchievementRepository achievementRepository;
    private final PlaygroundEventRepository playgroundEventRepository;
    private final UserMapper userMapper;


    public UserInformationDto getUserInformation(Long userId) {
        // Получаем пользователя
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<UserSport> userSports = userSportRepository.findUserSportByUserId(userId);

        // Получаем спортивные интересы пользователя с уровнем
        List<SportWithLevelDto> sportsWithLevels = userSports.stream()
                .map(userSport -> new SportWithLevelDto(userSport.getSport().getName(), userSport.getLevel())).toList();

        // Получаем достижения пользователя
        List<String> achievements = achievementRepository.findAchievementsByUserId(userId);

        // Получаем мероприятия, на которые пользователь собирается
        List<PlaygroundEventDto> playgroundEventDtoList = playgroundEventRepository
                .findActualPlaygroundEventsByUserId(userId);

        // Преобразуем в UserInformationDto
        return userMapper.toUserInformationDto(user, sportsWithLevels, achievements, playgroundEventDtoList);
    }

    public void updateUser(Long id, UserDtoIn userDtoIn) {
        User user = userRepository.getReferenceById(id);
        user.setPriorityArea(userDtoIn.getPriorityArea());
        saveAchievements(userDtoIn.getAchievements(), user);
        saveSports(userDtoIn.getSportsIds(), user);
    }

    private void saveAchievements(List<String> achievementsInfo, User user) {
        for (String achievementInfo : achievementsInfo) {
            Achievement achievement = new Achievement();
            achievement.setUser(user);
            achievement.setInfo(achievementInfo);
            achievementRepository.save(achievement);
        }
    }

    private void saveSports(List<Long> sportIds, User user) {
        for (Long sportId : sportIds) {
            UserSport userSport = new UserSport();
            userSport.setUser(user);
            userSport.setSport(sportRepository.getReferenceById(sportId));
            userSport.setLevel(1);
            userSportRepository.save(userSport);
        }
    }
}
