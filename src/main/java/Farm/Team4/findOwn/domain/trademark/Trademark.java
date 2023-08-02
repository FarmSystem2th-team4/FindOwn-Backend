package Farm.Team4.findOwn.domain.trademark;

import jakarta.persistence.Column;
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
    @Column(name = "trademark_id")
    private Long id;
    private String image;
    private String applicant;
    private String markClass;
    private Long registerNum;
    private String state;
    private String classification;// 상표 구분

    public Trademark(Long id, String image, String applicant, String markClass, Long registerNum, String state, String classification) {
        this.id = id;
        this.image = image;
        this.applicant = applicant;
        this.markClass = markClass;
        this.registerNum = registerNum;
        this.state = state;
        this.classification = classification;
    }
}
