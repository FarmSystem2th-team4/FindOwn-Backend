package Farm.Team4.findOwn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private Date membershipDate;
    protected Member(){} //JPA 사용을 위한 빈 생성자
    public String changePassword(String newPassword){
        this.password = newPassword;
        return this.id;
    }
    public String changeEmail(String newEmail){
        this.email = newEmail;
        return this.email;
    }

}
