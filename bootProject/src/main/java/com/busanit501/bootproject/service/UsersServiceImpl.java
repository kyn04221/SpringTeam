package com.busanit501.bootproject.service;

import com.busanit501.bootproject.domain.Users;
import com.busanit501.bootproject.dto.UsersDTO;
import com.busanit501.bootproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        if (usersRepository.findByEmail(usersDTO.getEmail()).isPresent()) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }
        Users user = Users.builder()
                .email(usersDTO.getEmail())
                .password(usersDTO.getPassword())
                .name(usersDTO.getName())
                .age(usersDTO.getAge())
                .gender(usersDTO.isGender())
                .address(usersDTO.getAddress())
                .profilePicture(usersDTO.getProfilePicture())
                .phoneNumber(usersDTO.getPhoneNumber())
                .isVerified(usersDTO.isVerified())
                .build();

        Users savedUser = usersRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public Optional<UsersDTO> getUserById(Long userId) {
        return  usersRepository.findById(userId).map(this::convertToDTO);
    }

    @Override
    public UsersDTO updateUser(Long userid, UsersDTO userDTO) {
        // 기존 사용자 조회
        Users existingUser = usersRepository.findById(userid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Users updataUser = Users.builder()
                .userId(existingUser.getUserId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .gender(userDTO.isGender())
                .address(userDTO.getAddress())
                .profilePicture(userDTO.getProfilePicture())
                .phoneNumber(userDTO.getPhoneNumber())
                .isVerified(userDTO.isVerified())
                .build();

        Users savedUser = usersRepository.save(updataUser);
        return convertToDTO(savedUser);
    }

    @Override
    public void deleteUser(Long userid) {
        usersRepository.deleteById(userid);
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDTO getUserByEmail(String email) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);


    }


    private UsersDTO convertToDTO(Users user) {
        if (user == null) {
            return null; // null 체크 (필요에 따라)
        }

        return UsersDTO.builder()
                .userid(user.getUserId())
                .email(user.getEmail())
                // 비밀번호는 반환하지 않음 (보안상 이유)
                .name(user.getName())
                .age(user.getAge())
                .gender(user.isGender())
                .address(user.getAddress())
                .profilePicture(user.getProfilePicture())
                .phoneNumber(user.getPhoneNumber())
                .isVerified(user.isVerified())
                .build();
    }
}
