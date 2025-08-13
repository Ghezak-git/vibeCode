package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.UserProfile;
import com.example.demo.entity.MstUser;
import com.example.demo.repository.MstUserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MstUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        MstUser user = userRepository.findByUsername(request.getUsernameOrEmail())
                .orElseGet(() -> userRepository.findByEmail(request.getUsernameOrEmail()).orElse(null));
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Username/email atau password salah");
        }
        String token = jwtTokenProvider.generateToken(user.getUsername());
        UserProfile profile = new UserProfile();
        profile.setId(user.getId());
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        profile.setNamaLengkap(user.getNamaLengkap());
        profile.setNoHp(user.getNoHp());
        profile.setAlamat(user.getAlamat());
        profile.setLinkImage(user.getLinkImage());
        profile.setPathImage(user.getPathImage());
        profile.setRole(user.getAkses().getNamaAkses());
        LoginResponse resp = new LoginResponse();
        resp.setAccessToken(token);
        resp.setExpiresIn(3600L); // 1 jam
        resp.setUser(profile);
        return resp;
    }
}
