package Farm.Team4.findOwn.domain.board.post;

import Farm.Team4.findOwn.domain.board.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PostWithTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_with_tag")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;
}