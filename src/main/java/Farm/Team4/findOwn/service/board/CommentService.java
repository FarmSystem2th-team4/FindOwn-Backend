package Farm.Team4.findOwn.service.board;

import Farm.Team4.findOwn.domain.board.Comment;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.board.comment.request.SaveCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.response.SaveCommentResponse;
import Farm.Team4.findOwn.repository.board.CommentRepository;
import Farm.Team4.findOwn.service.board.post.PostService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;
    @Transactional
    public SaveCommentResponse saveComment(SaveCommentRequest request){
        Post destPost = postService.findById(request.getPostId());
        Member writer = memberService.findByNickname(request.getNickname());
        Comment savedComment = commentRepository.save(new Comment(request.getContent(), writer, destPost));
        return new SaveCommentResponse(savedComment.getId(), writer.getNickname(), savedComment.getContent(), savedComment.getCreatedAt());
    }
}
