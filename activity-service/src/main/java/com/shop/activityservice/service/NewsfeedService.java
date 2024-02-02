package com.shop.activityservice.service;

import com.shop.activityservice.domain.*;
import com.shop.activityservice.dto.response.*;
import com.shop.activityservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    // 뉴스피드 생성 (저장)
    public void createNewsfeed(Long activityUserId, Long targetUserId, Long targetId, NewsfeedType newsfeedType) {
        User activityUser = userRepository.findById(activityUserId).orElse(null);
        User targetUser = userRepository.findById(targetUserId).orElse(null);

        Newsfeed newsfeed = Newsfeed.builder()
                .activityUser(activityUser)
                .targetUser(targetUser)
                .targetId(targetId)
                .newsfeedType(newsfeedType)
                .build();

        newsfeedRepository.save(newsfeed);
    }

    // 뉴스피드 조회
    public List<Newsfeed> searchNewsfeed(Long fromUserId) {
        List<Follow> fromFollow = followRepository.findAllByFromUserId(fromUserId);

        return fromFollow.stream()
                .map(Follow::getToUser)
                .flatMap(toUser -> newsfeedRepository.findAllByActivityUserId(toUser.getId()).stream()
                        .sorted(Comparator.comparing(Newsfeed::getCreatedAt).reversed()))
                .collect(Collectors.toList());
    }

    // 뉴스피드 타입에 따른 세부 데이터
    public NewsfeedResponse newsfeedDetails(Newsfeed newsfeed, Long currentUserId) {
        Object newsfeedResult = null;
        switch (newsfeed.getNewsfeedType()) {
            case FOLLOW:
                Follow findFollow = followRepository.findById(newsfeed.getTargetId()).orElse(null);
                if (findFollow != null) {
                    newsfeedResult = FollowResponse.of(findFollow);
                }
                break;
            case POST:
                Post findPost = postRepository.findById(newsfeed.getTargetId()).orElse(null);
                if (findPost != null) {
                    newsfeedResult = PostWriteResponse.of(findPost);
                }
                break;
            case POST_LIKE:
                PostLike findPostLike = postLikeRepository.findById(newsfeed.getTargetId()).orElse(null);
                if (findPostLike != null) {
                    newsfeedResult = PostLikeResponse.of(findPostLike);
                }
                break;
            case COMMENT:
                Comment findComment = commentRepository.findById(newsfeed.getTargetId()).orElse(null);
                if (findComment != null) {
                    newsfeedResult = CommentWriteResponse.of(findComment);
                }
                break;
            case COMMENT_LIKE:
                CommentLike findCommentLike = commentLikeRepository.findById(newsfeed.getTargetId()).orElse(null);
                if (findCommentLike != null) {
                    newsfeedResult = CommentLikeResponse.of(findCommentLike);
                }
                break;
        }

        return (newsfeedResult != null) ? NewsfeedResponse.of(newsfeed, currentUserId, newsfeedResult) : null;
    }

}
