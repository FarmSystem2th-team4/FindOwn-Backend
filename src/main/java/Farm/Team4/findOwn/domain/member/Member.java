package Farm.Team4.findOwn.domain.member;

import Farm.Team4.findOwn.domain.judgment.DesignJudgment;
import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private Date membershipDate;
    @OneToMany(mappedBy = "member")
    private List<DesignJudgment> designJudgments = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<TrademarkJudgment> trademarkJudgments = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<MemberOwnDesign> ownDesigns = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<MemberOwnTrademark> ownTrademarks = new ArrayList<>();
    public Member(String id, String password, String name, String phoneNumber, String email, Date now){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipDate = now;
    }
    public String changePassword(String newPassword){
        this.password = newPassword;
        return this.id;
    }
    public String changeEmail(String newEmail){
        this.email = newEmail;
        return this.email;
    }

}
