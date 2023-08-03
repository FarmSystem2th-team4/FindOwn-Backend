package Farm.Team4.findOwn.domain.judgment;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class DesignJudgment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null; // 저장 순서
    private Date savedDate; // 저장 날짜
    private String result; // 결과
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "design_id")
    private Design design;
}
