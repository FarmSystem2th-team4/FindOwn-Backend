package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberOwnTrademark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private Trademark trademark;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public MemberOwnTrademark(Trademark trademark, Member member) {
        this.trademark = trademark;
        this.member = member;
    }
}
