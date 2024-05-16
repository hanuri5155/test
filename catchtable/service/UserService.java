package com.se_b4.catchtable.service;

import com.se_b4.catchtable.entity.UserEntity;
import com.se_b4.catchtable.dto.UserDTO;
import com.se_b4.catchtable.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// 회원과 관련된 로직을 작성하는 클래스입니다.
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 새로운 유저를 DB 에 생성하는 코드입니다.
    // Spring Security 에서 제공하는 passwordEncoder로 패스워드를 암호화하여 저장합니다.

    public void create(
            String username, String password, String phoneNumber
    ){
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone_number(phoneNumber);

        this.userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserEntity userEntity: userEntityList) {
            userDTOList.add(UserDTO.toDTO(userEntity));
            // 아래랑 같은 의미
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }
        return userDTOList;
    }
}
