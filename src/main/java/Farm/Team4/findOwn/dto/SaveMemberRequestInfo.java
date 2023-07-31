package Farm.Team4.findOwn.dto;

import Farm.Team4.findOwn.domain.Member;
import lombok.Data;
import lombok.Getter;

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
