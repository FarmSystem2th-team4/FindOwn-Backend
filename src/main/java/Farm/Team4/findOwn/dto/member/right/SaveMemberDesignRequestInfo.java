package Farm.Team4.findOwn.dto.member.right;

import lombok.Data;

@Data
public class SaveMemberDesignRequestInfo {
    /*
        이미지 어떻게 할지 추후 논의
     */
    private Long id;
    private String applicant;
    private String designClass; //  한국분류(디자인 분류에는 국제분류도 있고 한국 분류도 있다), 국제분류를 따르는 경우 한국분류 값이 없는 디자인권들이 존재
    private Long registerNum; // 등록 번호, '-'을 제외한 출원번호 전체
    private String state; // 법적 상태
    private String classification; // 디자인구분
}
