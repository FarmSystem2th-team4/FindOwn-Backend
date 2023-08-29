package Farm.Team4.findOwn.domain.trademark;

import Farm.Team4.findOwn.dto.member.right.trademark.request.UpdateMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.trademark.UpdateTrademarkRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trademark {
    @Id
    @Column(name = "trademark_id")
    private Long id;
    private String image;
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;// 상표 구분

    public Trademark(Long id, String image, String applicant, Long registerNum, String state, String classification) {
        this.id = id;
        this.image = image;
        this.applicant = applicant;
        this.registerNum = registerNum;
        this.state = state;
        this.classification = classification;
    }
    public Trademark updateTrademark(UpdateTrademarkRequest request){
        this.image = request.getImage();
        this.applicant = request.getApplicant();
        this.registerNum = request.getRegisterNum();
        this.state = request.getState();
        this.classification = request.getClassification();
        return this;
    }
}
