package com.est.helllow.service;

import com.est.helllow.domain.User;
import com.est.helllow.domain.enum_class.UserGrade;
import com.est.helllow.exception.BaseException;
import com.est.helllow.exception.BaseExceptionCode;
import com.est.helllow.repository.LikePostRepository;
import com.est.helllow.repository.PostRepository;
import com.est.helllow.repository.ReplyRepository;
import com.est.helllow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.est.helllow.domain.enum_class.UserGrade.*;

@Service
@RequiredArgsConstructor
public class UserGradeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final LikePostRepository likePostRepository;


    // 유저가 게시물,댓글,좋아요 추가 시 변경점 파악 후 등급 수정
    @Transactional
    public void upgradeUserGrade(String userId) throws BaseException {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseExceptionCode.NOT_INVALID_USER));

        long postCount = postRepository.countAllByUser_userId(userId);
        long replyCount = replyRepository.countAllByUser_userId(userId);
        long likeCount = likePostRepository.countAllByUser_userId(userId);

        UserGrade newGrade = calculateGrade(postCount, replyCount, likeCount);

        if (user.getUserGrade() != newGrade) {
            user.setUserGrade(newGrade);
            userRepository.save(user);
            refreshUserAuthentication(user);
        }
    }

    // 등급 변경 계산
    private UserGrade calculateGrade(long postCount, long replyCount, long likeCount) {
        if (postCount >= 10 && replyCount >= 10 && likeCount >= 10) {
            return SBD_500;
        } else if (postCount >= 5 && replyCount >= 5 && likeCount >= 5) {
            return SBD_400;
        } else {
            return SBD_300;
        }
    }

    // 사용자 정보가 변경되었을 때 세션에 변경점 반영하는 메서드
    private void refreshUserAuthentication(User user) {

        //현재 인증 정보
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        // 변경된 사용자 객체 생성
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                user, currentAuth.getCredentials(), currentAuth.getAuthorities());
        // 변경된 사용자 객체 적용
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
