package com.devhub.api.application.service;

import com.devhub.api.domain.model.User;
import com.devhub.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extrai os dados que o GitHub enviou
        String githubId = oAuth2User.getAttribute("id").toString();
        String name = oAuth2User.getAttribute("login");
        String email = oAuth2User.getAttribute("email");
        if (email == null){
            email = oAuth2User.getAttribute("login") + "@github.com";
        }
        String avatarUrl = oAuth2User.getAttribute("avatar_url");

        // Regra de Negócio: Busca se já existe, senão cria um novo
        String finalEmail = email;
        userRepository.findByGithubId(githubId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setGithubId(githubId);
                    newUser.setUsername(name);
                    newUser.setEmail(finalEmail != null ? finalEmail : name + "@github.com");
                    newUser.setAvatarUrl(avatarUrl);
                    return userRepository.save(newUser);
                });

        return oAuth2User;
    }
}