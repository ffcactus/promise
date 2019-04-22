package com.promise.platform.auth.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.promise.platform.sdk.dto.auth.RegisterUserRequestV1;
import com.promise.platform.sdk.dto.auth.GetUserResponseV1;
import com.promise.platform.sdk.model.PromiseUserDetails;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
public class User extends PromiseUserDetails
{

    /**
     *
     */
    private static final long serialVersionUID = -5031495025980981775L;
    @Getter
    @Setter
    @NonNull
    @Id
    private String id;

    @Getter
    @Setter
    @NotNull
    private String email;

    public User(
            String id,
            String username,
            String password,
            String email,
            String company,
            List<String> roles,
            List<String> organizations)
    {
        super(username, company, roles, organizations, password);
        this.setPassword(password);
        this.id = id;
        this.email = email;
    }

    public User(RegisterUserRequestV1 request)
    {
        this(
                UUID.randomUUID().toString(),
                request.username,
                request.password,
                request.email,
                null,
                null,
                null);
    }

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public GetUserResponseV1 toResponseV1()
    {
        final var ret = new GetUserResponseV1();
        ret.id = this.id;
        ret.username = this.username;
        ret.email = this.email;
        return ret;
    }
}
