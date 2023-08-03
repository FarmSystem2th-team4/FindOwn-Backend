package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.design.Design;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberOwnDesign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "design_id")
    private Design design;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberOwnDesign(Design design, Member member) {
        this.design = design;
        this.member = member;
    }
}
