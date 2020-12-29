package com.promise.platform.auth.entity;

import com.promise.platform.auth.model.User;
import com.promise.platform.common.entity.ResourceEntity;
import com.promise.platform.sdk.dto.auth.GetUserResponseV1;
import com.promise.platform.sdk.dto.auth.RegisterUserRequestV1;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class UserEntity extends ResourceEntity {
    private String email;
    private String password;
    private String role;

    public UserEntity(RegisterUserRequestV1 request) {
        this.setName(request.getUsername());
        this.setPassword(request.getPassword());
        this.setEmail(request.getEmail());
    }

    public User toModelUser() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getName());

        List<String> roles  = new ArrayList<>();
        roles.add(this.getRole());
        user.setRoles(roles);

        user.setEmail(this.getEmail());

        return user;
    }

    public GetUserResponseV1 toResponseV1() {
        final var ret = new GetUserResponseV1();
        ret.setId(this.getId());
        ret.setUsername(this.getName());
        ret.setEmail(this.getEmail());
        ret.setDescription(this.getDescription());
        ret.setRole(this.getRole());
        return ret;
    }

}
