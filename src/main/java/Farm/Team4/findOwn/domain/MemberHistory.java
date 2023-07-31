package Farm.Team4.findOwn.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberHistory {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int saveSequence;

}
