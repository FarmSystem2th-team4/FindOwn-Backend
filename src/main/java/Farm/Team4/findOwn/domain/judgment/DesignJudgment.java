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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 저장 순서
    private Date savedDate; // 저장 날짜
    private String result; // 결과
    @OneToOne
    private Member member;
    @OneToOne
    private Design design;
}
