package org.example.onlineshopbackend.api.output;

import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.User;
import org.example.onlineshopbackend.model.entity.User.UserRole;

@Getter
@Setter
public class SecurityInfo {
        private Long id;
        private String name;
        private UserRole userRole;
        private String accessToken;
        private String refreshToken;


    public static SecurityInfo getSecurityInfo(User user, String jwtAccessToken, String jwtRefreshToken) {
        SecurityInfo securityInfo = new SecurityInfo();
        securityInfo.setId(user.getId());
        securityInfo.setName(user.getName());
        securityInfo.setUserRole(user.getUserRole());
        securityInfo.setAccessToken(jwtAccessToken);
        securityInfo.setRefreshToken(jwtRefreshToken);
        return securityInfo;
    }

}
