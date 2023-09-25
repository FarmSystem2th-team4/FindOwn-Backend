package Farm.Team4.findOwn.domain.board.post;

import Farm.Team4.findOwn.domain.board.Comment;
import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostWithTag> tags = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


}
