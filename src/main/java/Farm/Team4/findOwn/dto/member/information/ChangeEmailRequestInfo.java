package Farm.Team4.findOwn.dto.member.information;

import lombok.Data;

@Data
public class ChangeEmailRequestInfo {
    private String oldEmail;
    private String newEmail;
}
