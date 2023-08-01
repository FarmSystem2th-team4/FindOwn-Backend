package Farm.Team4.findOwn.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    private List<String> classification = new ArrayList<>(); // 상표 구분

}
