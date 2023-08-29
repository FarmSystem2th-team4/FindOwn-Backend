package Farm.Team4.findOwn.exception;

public enum CustomErrorCode {
    NOT_FOUND_MEMBER(400, "존재하지 않는 회원입니다");
    private int errorCode;
    private String errorMessage;
    CustomErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
