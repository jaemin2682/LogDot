package com.papple.blog.social;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.papple.blog.models.ERole;
import com.papple.blog.models.Role;
import com.papple.blog.models.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey, email, picture;
    
    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.picture = picture;
    }
    public static OAuthAttributes of(String registrationId,String userNameAttributeName, Map<String, Object> attributes) {

        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        if("github".equals(registrationId)) {
            return ofGithub(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }
   
    private static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email((String) attributes.get("node_id"))
                .picture((String) attributes.get("avatar_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        return OAuthAttributes
                        .builder()
                        .email((String) attributes.get("email"))
                        .picture((String) attributes.get("picture"))
                        .attributes(attributes)
                        .nameAttributeKey(userNameAttributeName)
                        .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName,Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");

    return OAuthAttributes
                .builder()
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role(1,ERole.ROLE_USER);
        roles.add(userRole);
        
        return User.builder()
                .email(email)
                .profile(picture)
                .roles(roles)
                .build();
    }
}