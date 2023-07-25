package Farm.Team4.findOwn.dto;

import Farm.Team4.findOwn.domain.Member;
import lombok.Getter;

@Getter
public class SaveMemberRequestInfo {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    public Member toMember(){
        return new Member(
                this.id,
                this.password,
                this.name,
                this.phoneNumber,
                this.email);
    }
}
