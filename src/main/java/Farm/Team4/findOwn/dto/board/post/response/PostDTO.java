package Farm.Team4.findOwn.dto.board.post.response;

import lombok.Data;

import java.util.List;
@Data
public class PostDTO {
    private Long postId;
    private String writerId;
    private String title;
    private String content;
    private List<String> tagNames;

    public PostDTO(Long postId, String writerId, String title, String content, List<String> tagNames) {
        this.postId = postId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
