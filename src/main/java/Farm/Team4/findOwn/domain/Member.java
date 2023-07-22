package Farm.Team4.findOwn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {
    @Id
    private String id;
    private String passWord;
    private String name;
    private String phoneNumber;
    private String email;
}
