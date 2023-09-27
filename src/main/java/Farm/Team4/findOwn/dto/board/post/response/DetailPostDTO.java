package Farm.Team4.findOwn.dto.board.post.response;

import Farm.Team4.findOwn.dto.board.comment.response.CommentDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DetailPostDTO {
    private String nickname;
    private String title;
    private String content;
    private Date createdAt;
    private List<String> tagNames;
    private List<CommentDTO> comments;

}
