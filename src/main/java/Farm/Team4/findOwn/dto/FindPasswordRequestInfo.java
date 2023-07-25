package Farm.Team4.findOwn.dto;

import lombok.Getter;

@Getter
public class FindPasswordRequestInfo {
    private String email;
    private String oldPassword;
    private String newPassword;
}
