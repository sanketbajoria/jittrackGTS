package com.jittrack.gts.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

public class CustomMethodSecurityExpressionHandler extends OAuth2MethodSecurityExpressionHandler {

    public CustomMethodSecurityExpressionHandler(PermissionEvaluator permissionEvaluator) {
        super();
        setPermissionEvaluator(permissionEvaluator);
    }
    
}
