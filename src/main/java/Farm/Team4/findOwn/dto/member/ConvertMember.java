package Farm.Team4.findOwn.dto.member;

import lombok.Data;

import java.util.Date;

@Data
public class ConvertMember {
    private String memberId;
    private String name;
    private Date membershipDate;

    public ConvertMember(String memberId, String name, Date membershipDate) {
        this.memberId = memberId;
        this.name = name;
        this.membershipDate = membershipDate;
    }
}
