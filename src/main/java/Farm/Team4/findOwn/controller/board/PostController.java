package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.dto.board.BoardPageDTO;
import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.service.board.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    @PostMapping("/post")
    public SavePostResponse savePost(@RequestBody SavePostRequest request){
        return postService.savePost(request);
    }
    @GetMapping("/board")
    public BoardPageDTO showBoardPage(@RequestParam int pageNum){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, 5, Sort.by("createdAt").descending());
        return new BoardPageDTO(postService.countPosts(), postService.startPagingBoard(pageRequest));
    }
}
