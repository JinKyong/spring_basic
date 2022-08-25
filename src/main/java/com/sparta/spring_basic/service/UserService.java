package com.sparta.spring_basic.service;

import static com.sparta.spring_basic.ExceptionHandler.ErrorCode.*;

import com.sparta.spring_basic.dto.LoginRequestDto;
import com.sparta.spring_basic.dto.ResponseDto;
import com.sparta.spring_basic.dto.SignupRequestDto;
import com.sparta.spring_basic.dto.TokenDto;
import com.sparta.spring_basic.entity.User;
import com.sparta.spring_basic.repository.UserRepository;
import com.sparta.spring_basic.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(JwtProvider jwtProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseDto<User> signUp(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();

        // 회원 ID(닉네임) 중복 확인
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            return ResponseDto.fail(DUPLICATE_ID);
            //throw new CustomException(DUPLICATE_ID);
        }
        System.out.println(nickname);

        // ID, PW 조건(패턴)
        String idPattern = "^[a-zA-Z0-9]{4,12}$";
        String pwPattern = "^[a-z0-9]{4,32}$";

        // ID, PW 조건 확인
        if(!Pattern.matches(idPattern, nickname) || !Pattern.matches(pwPattern, password)) {
            return ResponseDto.fail(WRONG_PATTERN);
            //throw new CustomException(WRONG_PATTERN);
        }
        System.out.println(password);
        // 비밀번호 일치 확인
        if (!passwordConfirm.equals(password)) {
            return ResponseDto.fail(WRONG_PW_CONFIRM);
            //throw new CustomException(WRONG_PW_CONFIRM);
        }
        System.out.println(passwordConfirm);

        // 비밀번호 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(nickname, password);
        userRepository.save(user);

        return ResponseDto.success(user);
    }

    public ResponseDto<User> login(LoginRequestDto requestDto, HttpServletResponse response) {
        // 아이디 틀림
        Optional<User> optionalUser = userRepository.findByNickname(requestDto.getNickname());
        if(optionalUser.isEmpty()){
            return ResponseDto.fail(NO_USER);
        }

        User user = optionalUser.get();
        // 비밀번호 틀림
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            return ResponseDto.fail(WRONG_PASSWORD);
            //throw new CustomException(WRONG_PASSWORD);
        }

        TokenDto tokenDto = jwtProvider.generateTokenDto(user);

        //add header
        response.addHeader("access-token",tokenDto.getAccesstoken());
        response.addHeader("refresh-token",tokenDto.getRefreshtoken());

        return ResponseDto.success(user);
    }

    public ResponseDto<?> getAllUsers() {
        return ResponseDto.success(userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
