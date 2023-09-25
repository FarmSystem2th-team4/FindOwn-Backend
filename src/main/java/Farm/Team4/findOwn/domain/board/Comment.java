package Farm.Team4.findOwn.domain.board;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member")
    private Member writer;
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

}
