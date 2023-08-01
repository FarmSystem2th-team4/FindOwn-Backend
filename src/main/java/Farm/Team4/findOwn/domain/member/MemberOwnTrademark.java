package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberOwnTrademark {
    @Id
    @OneToOne
    private Trademark trademark;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
