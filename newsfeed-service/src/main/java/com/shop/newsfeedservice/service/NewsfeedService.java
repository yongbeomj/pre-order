package com.shop.newsfeedservice.service;

import com.shop.newsfeedservice.domain.Newsfeed;
import com.shop.newsfeedservice.domain.NewsfeedType;
import com.shop.newsfeedservice.dto.response.NewsfeedResponse;
import com.shop.newsfeedservice.repository.NewsfeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;

    // 뉴스피드 생성 (저장)
    public void createNewsfeed(Long activityUserId, Long targetUserId, Long targetId, NewsfeedType newsfeedType) {
        // TODO : 유저 정보(유저 서비스), 활동 정보(활동 서비스) 가져오기

        Newsfeed newsfeed = Newsfeed.builder()
                .activityUserId(activityUserId)
                .targetUserId(targetUserId)
                .targetId(targetId)
                .newsfeedType(newsfeedType)
                .build();

        newsfeedRepository.save(newsfeed);
    }

    // 뉴스피드 조회
    public List<Newsfeed> searchNewsfeed(Long loginUserId) {
        // TODO : 로그인 유저가 팔로우한 정보 가져오기 (활동 서비스)
//        List<Follow> fromFollow = followRepository.findAllByFromUserId(fromUserId);

        // TODO : 로그인 유저가 팔로우한 유저가 활동한 내역 뉴스피드 테이블에서 조회
//        return fromFollow.stream()
//                .map(Follow::getToUser)
//                .flatMap(toUser -> newsfeedRepository.findAllByActivityUserId(toUser.getId()).stream()
//                        .sorted(Comparator.comparing(Newsfeed::getCreatedAt).reversed()))
//                .collect(Collectors.toList());
        return null;
    }

    // 뉴스피드 타입에 따른 세부 데이터
    public NewsfeedResponse newsfeedDetails(Newsfeed newsfeed, Long loginUserId) {
        Object newsfeedResult = null;
        // TODO : newsfeed type에 따라 활동 정보 가져오기 (활동 서비스)
//        switch (newsfeed.getNewsfeedType()) {
//            case FOLLOW:
//                Follow findFollow = followRepository.findById(newsfeed.getTargetId()).orElse(null);
//                if (findFollow != null) {
//                    newsfeedResult = FollowResponse.of(findFollow);
//                }
//                break;
//            case POST:
//                Post findPost = postRepository.findById(newsfeed.getTargetId()).orElse(null);
//                if (findPost != null) {
//                    newsfeedResult = PostWriteResponse.of(findPost);
//                }
//                break;
//            case POST_LIKE:
//                PostLike findPostLike = postLikeRepository.findById(newsfeed.getTargetId()).orElse(null);
//                if (findPostLike != null) {
//                    newsfeedResult = PostLikeResponse.of(findPostLike);
//                }
//                break;
//            case COMMENT:
//                Comment findComment = commentRepository.findById(newsfeed.getTargetId()).orElse(null);
//                if (findComment != null) {
//                    newsfeedResult = CommentWriteResponse.of(findComment);
//                }
//                break;
//            case COMMENT_LIKE:
//                CommentLike findCommentLike = commentLikeRepository.findById(newsfeed.getTargetId()).orElse(null);
//                if (findCommentLike != null) {
//                    newsfeedResult = CommentLikeResponse.of(findCommentLike);
//                }
//                break;
//        }

        return (newsfeedResult != null) ? NewsfeedResponse.of(newsfeed, loginUserId, newsfeedResult) : null;
    }

}
