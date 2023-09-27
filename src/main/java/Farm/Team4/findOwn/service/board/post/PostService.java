package Farm.Team4.findOwn.service.board.post;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.dto.board.post.response.SimplePostDTO;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.PostRepository;
import Farm.Team4.findOwn.service.board.TagService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final TagService tagService;
    private final PostWithTagService postWithTagService;
    @Transactional
    public SavePostResponse savePost(SavePostRequest request){
        Post savedPost = postRepository.save(request.changeToPost(memberService.findById(request.getWriterId())));
        log.info("게시글 저장 완료");
        tagService.saveNewTag(request.getTagNames()); // 새로운 태그 저장 & 기존 태그는 저장은 X
        postWithTagService.saveAssociation(request.getTagNames(), savedPost);

        return new SavePostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getCreatedAt());
    }
    public Post findById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_POST));
    }
    public Long countPosts(){
        return postRepository.count();
    }
    public List<SimplePostDTO> startPagingBoard(PageRequest pageRequest){
        return postRepository.findAll(pageRequest).stream()
                .map(post -> new SimplePostDTO(
                        post.getId(),
                        post.getMember().getNickname(),
                        post.getTitle(),
                        post.getTags().stream()
                                .map(association -> association.getTag().getName())
                                .toList(),
                        post.getCreatedAt()
                )).toList();
    }
}
