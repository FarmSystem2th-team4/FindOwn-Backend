package Farm.Team4.findOwn.dto.member.right.trademark;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import lombok.Data;

@Data
public class SaveMemberTrademarkRequestInfo {
    private String memberId;
    private Long trademarkId;
    private String image;
    private String applicant;
    private String markClass;
    private Long registerNum;
    private String state;
    private String classification;
    public Trademark changeToTrademark(){
        return new Trademark(this.trademarkId, this.image, this.applicant, this.markClass, this.registerNum, this.state, this.classification);
    }
}
