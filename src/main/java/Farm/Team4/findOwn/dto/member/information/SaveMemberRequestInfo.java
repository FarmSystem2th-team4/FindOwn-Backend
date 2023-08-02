package Farm.Team4.findOwn.dto.member.information;

import Farm.Team4.findOwn.domain.member.Member;
import lombok.Data;

import java.util.Date;

@Data
public class SaveMemberRequestInfo {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private Date membershipDate;
    public Member toMember(Date now){
        return new Member(
                this.id,
                this.password,
                this.name,
                this.phoneNumber,
                this.email,
                now);
    }
}
