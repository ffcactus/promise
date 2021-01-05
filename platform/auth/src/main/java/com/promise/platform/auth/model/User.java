package com.promise.platform.auth.model;

import com.promise.platform.auth.sdk.dto.GetUserResponseV1;
import com.promise.platform.auth.sdk.jwt.PromiseUserDetails;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class User extends PromiseUserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -5031495025980981775L;

    @Getter
    @Setter
    @NotNull
    private String email;

    public User(
            Long id,
            String username,
            String password,
            String email,
            String company,
            List<String> roles,
            List<String> organizations) {
        super(id, username, company, roles, organizations, password);
        this.setPassword(password);
        this.email = email;
    }

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public GetUserResponseV1 toResponseV1() {
        final var ret = new GetUserResponseV1();
        ret.setId(this.id);
        ret.setUsername(this.username);
        ret.setEmail(this.email);
        return ret;
    }
}
