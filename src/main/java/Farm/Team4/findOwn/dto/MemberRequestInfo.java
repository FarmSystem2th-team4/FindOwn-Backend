package Farm.Team4.findOwn.dto;

import Farm.Team4.findOwn.domain.Member;
import lombok.Getter;

@Getter
public class MemberRequestInfo {
    private String id;
    private String passWord;
    private String name;
    private String phoneNumber;
    private String email;
    public Member toMember(){
        return new Member(
                this.id,
                this.passWord,
                this.name,
                this.phoneNumber,
                this.email);
    }
}
