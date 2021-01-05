package com.promise.platform.gateway.filter;

import com.promise.platform.auth.sdk.dto.GetUserResponseV1;
import com.promise.platform.auth.sdk.exception.UnauthorizedException;
import com.promise.platform.auth.sdk.jwt.JwtTokenValidator;
import com.promise.platform.auth.sdk.jwt.JwtUser;
import com.promise.platform.gateway.client.AuthServiceClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter {
    private static final String TOKEN_KEY = "Authorization";
    private static final String BEARER_AND_SPACE = "Bearer ";
    private static final String LOGIN = "/login";
    private static final String REFRESH_TOKEN = "/refresh-token";
    private static final String ADMIN = "ADMIN";

    @Autowired
    private AuthServiceClient authServiceClient;
    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Don't check login
        if (exchange.getRequest().getPath().value().contains(LOGIN)) {
            return chain.filter(exchange);
        }

        // Analysis token
        JwtUser jwtUser = authServiceClient.parseToken(getToken(exchange));

        // Everyone taking a valid token can refresh their token
        if (exchange.getRequest().getPath().value().contains(REFRESH_TOKEN)) {
            return chain.filter(exchange);
        }

        // Everyone taking a valid token can update their information
        if (HttpMethod.PUT.equals(exchange.getRequest().getMethod())
                && exchange.getRequest().getPath().value().endsWith("/users/" + jwtUser.getId())) {
            return chain.filter(exchange);
        }

        GetUserResponseV1 user = authServiceClient.getUser(jwtUser.getId());
        if (needAdminAuth(exchange) && !ADMIN.equalsIgnoreCase(user.getRole())) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private boolean needAdminAuth(ServerWebExchange exchange) {

        return HttpMethod.POST.equals(exchange.getRequest().getMethod())
                || HttpMethod.PUT.equals(exchange.getRequest().getMethod())
                || HttpMethod.PATCH.equals(exchange.getRequest().getMethod())
                || HttpMethod.DELETE.equals(exchange.getRequest().getMethod());
    }

    private String getToken(ServerWebExchange exchange) {
        List<String> tokenList = exchange.getRequest().getHeaders().get(TOKEN_KEY);
        if (CollectionUtils.isEmpty(tokenList)) {
            throw new UnauthorizedException();
        }

        String tokenWithBearer = tokenList.get(0);
        if (!StringUtils.startsWith(tokenWithBearer, "Bearer")) {
            throw new UnauthorizedException();
        }

        return tokenWithBearer;
    }

}
