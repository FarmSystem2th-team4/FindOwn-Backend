package Farm.Team4.findOwn.dto;

import lombok.Data;

@Data
public class VerifyMemberRequestInfo {
    private String email;
    private String code;
}
