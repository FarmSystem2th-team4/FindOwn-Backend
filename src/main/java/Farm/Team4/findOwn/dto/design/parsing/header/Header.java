package Farm.Team4.findOwn.dto.design.parsing.header;

import lombok.Data;

@Data
public class Header {

    private String requestMsgID;
    private String responseTime;
    private String responseMsgID;
    private String successYN;
    private String resultCode;
    private String resultMsg;
}
