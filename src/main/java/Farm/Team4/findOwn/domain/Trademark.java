package Farm.Team4.findOwn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trademark {
    @Id
    private Long id;
    private String image;
    private String applicant;
    private String markClass;
    private Long registerNum;
    private String state;
    /*
    private List<String> classification = new ArrayList<>(); // 상표 구분

     */

}
