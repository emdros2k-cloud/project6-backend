package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.User;
import com.aivle12.book_backend.dto.AvailabilityResponse;
import com.aivle12.book_backend.dto.LoginRequest;
import com.aivle12.book_backend.dto.LoginResponse;
import com.aivle12.book_backend.dto.SignupRequest;
import com.aivle12.book_backend.dto.UserResponse;
import com.aivle12.book_backend.repository.UserRepository;
import com.aivle12.book_backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "\uC774\uBBF8 \uC0AC\uC6A9 \uC911\uC778 \uC774\uBA54\uC77C\uC785\uB2C8\uB2E4.");
        }
        if (userRepository.existsByNickname(request.nickname())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "\uC774\uBBF8 \uC0AC\uC6A9 \uC911\uC778 \uB2C9\uB124\uC784\uC785\uB2C8\uB2E4.");
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.nickname()
        );

        return UserResponse.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "\uC774\uBA54\uC77C \uB610\uB294 \uBE44\uBC00\uBC88\uD638\uAC00 \uC62C\uBC14\uB974\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "\uC774\uBA54\uC77C \uB610\uB294 \uBE44\uBC00\uBC88\uD638\uAC00 \uC62C\uBC14\uB974\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
        }

        String accessToken = jwtTokenProvider.createToken(user.getId());

        return new LoginResponse(
                accessToken,
                "Bearer",
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }

    @Transactional(readOnly = true)
    public UserResponse getMe(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "\uC0AC\uC6A9\uC790\uB97C \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4."));

        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public AvailabilityResponse checkEmailAvailable(String email) {
        return new AvailabilityResponse(!userRepository.existsByEmail(email));
    }

    @Transactional(readOnly = true)
    public AvailabilityResponse checkNicknameAvailable(String nickname) {
        return new AvailabilityResponse(!userRepository.existsByNickname(nickname));
    }
}
