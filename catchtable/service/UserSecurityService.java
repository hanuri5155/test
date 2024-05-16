package com.se_b4.catchtable.service;

import com.se_b4.catchtable.config.CurrentUser;
import com.se_b4.catchtable.entity.UserEntity;
import com.se_b4.catchtable.authority.UserAuthority;
import com.se_b4.catchtable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> _users = this.userRepository.findByUsername(username);
        if (_users.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        UserEntity user = _users.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserAuthority.ADMIN.getName()));
        } else if ("owner".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserAuthority.OWNER.getName()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserAuthority.USER.getName()));
        }
        return new CurrentUser(user.getUsername(), user.getPassword(), authorities, user);
    }
}
