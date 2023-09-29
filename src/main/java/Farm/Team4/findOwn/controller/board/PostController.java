package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.dto.board.BoardPageDTO;
import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.request.UpdatePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.DetailPostDTO;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.dto.board.post.response.UpdatePostResponse;
import Farm.Team4.findOwn.service.board.TagService;
import Farm.Team4.findOwn.service.board.post.PostService;
import Farm.Team4.findOwn.service.board.post.PostWithTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final TagService tagService;
    private final PostWithTagService postWithTagService;

    @PostMapping("/post")
    public SavePostResponse savePost(@RequestBody SavePostRequest request) {
        return postService.savePost(request);
    }

    @GetMapping("/board")
    public BoardPageDTO showBoardPage(@RequestParam int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, 5, Sort.by("createdAt").descending());
        return new BoardPageDTO(postService.countPosts(), postService.startPagingBoard(pageRequest));
    }

    @GetMapping("/post/{postId}")
    public DetailPostDTO showPostDetail(@PathVariable Long postId) {
        return postService.findDetailPost(postId);
    }

    @PatchMapping("/post/{postId}")
    public void updatePost(@RequestBody UpdatePostRequest request) {
        Post updatedPost = postService.updatePost(request);// 게시글 수정
        tagService.saveNewTag(request.getTagNames()); // 새로운 태그 저장

        List<Long> oldAssociationIds = updatedPost.getTags().stream() // 기존에 유지된 태그
                .map(PostWithTag::getId).toList();
        oldAssociationIds.forEach( id -> {
            log.info("association id: " + id);
        });
        postWithTagService.deleteOldAssociation(oldAssociationIds);


    }
}
