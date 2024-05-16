package com.se_b4.catchtable.config;

import com.se_b4.catchtable.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@ToString
public class CurrentUser extends User {
    private Long uuid;
    private String username;
    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UserEntity user) {
        super(username, password, authorities);
        this.uuid = user.getUuid();
        this.username = user.getUsername();
    }
}
