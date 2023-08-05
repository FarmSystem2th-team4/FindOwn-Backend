package Farm.Team4.findOwn.dto.member.response;

import lombok.Data;

@Data
public class ResponseMember<T> {
    private int status;
    private T data;

    public ResponseMember(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
