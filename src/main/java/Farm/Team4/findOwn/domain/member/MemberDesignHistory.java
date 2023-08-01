package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.design.Design;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberDesignHistory {
    @Id
    @OneToOne
    private Design design;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
