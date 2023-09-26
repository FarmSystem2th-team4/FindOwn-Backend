package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.service.board.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final PostService postService;
    @PostMapping("/post")
    public SavePostResponse savePost(@RequestBody SavePostRequest request){
        return postService.savePost(request);
    }
}
