package com.jittrack.gts.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
       authentication.getOAuth2Request().getExtensions().put("account", "abc");
       authentication.getOAuth2Request().getExtensions().put("acl", "allowRead");
       return accessToken;
    }

}
