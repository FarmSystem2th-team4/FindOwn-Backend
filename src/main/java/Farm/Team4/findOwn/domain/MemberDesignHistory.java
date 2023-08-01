package Farm.Team4.findOwn.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberDesignHistory {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    private Design design;
}
