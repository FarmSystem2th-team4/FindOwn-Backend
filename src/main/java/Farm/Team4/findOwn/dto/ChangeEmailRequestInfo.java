package Farm.Team4.findOwn.dto;

import lombok.Data;

@Data
public class ChangeEmailRequestInfo {
    private String oldEmail;
    private String newEmail;
}
