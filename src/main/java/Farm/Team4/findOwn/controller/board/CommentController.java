package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.dto.board.comment.request.SaveCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.response.SaveCommentResponse;
import Farm.Team4.findOwn.service.board.CommentService;
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
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public SaveCommentResponse saveComment(@RequestBody SaveCommentRequest request){
        return commentService.saveComment(request);
    }
}
