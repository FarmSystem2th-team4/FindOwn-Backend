package Farm.Team4.findOwn.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {
    NOT_FOUND_MEMBER(400, "존재하지 않는 회원입니다."),
    NOT_FOUND_DESIGN(400, "존재하지 않는 디자인입니다."),
    NOT_FOUND_TRADEMARK(400, "존재하지 않는 상표권입니다."),
    NOT_FOUND_DESIGN_OF_MEMBER(400, "존재하지 않는 회원 소유 디자인입니다."),
    NOT_FOUND_TRADEMARK_OF_MEMBER(400, "존재하지 않는 회원 소유 상표권입니다"),
    NOT_MATCH_PASSWORD(400, "기존 비밀번호와 일치하지 않습니다."),
    NOT_MATCH_MEMBER(400, "작성자와 수정자가 일치하지 않습니다.");
    private int status;
    private String errorMessage;
    CustomErrorCode(int status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}