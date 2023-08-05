package Farm.Team4.findOwn.dto.trademark.parsing.body;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String agentName;
    private String appReferenceNumber;
    private String applicationName;
    private String applicationDate;
    private String applicationNumber;
    private String applicationStatus;
    private String bigDrawing;
    private String classificationCode;
    private String drawing;
    private char fullText;
    private int indexNo;
    private String internationalRegisterDate;
    private String internationalRegisterNumber;
    private String priorityDate;
    private String priorityNumber;
    private String publicationDate;
    private String publicationNumber;
    private String regPrivilegeName;
    private String regReferenceNumber;
    private String registrationDate;
    private String registrationNumber;
    private String registrationPublicDate;
    private String registrationPublicNumber;
    private String title;
    private String viennaCod;
}
