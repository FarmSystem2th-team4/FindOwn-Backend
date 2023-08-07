package Farm.Team4.findOwn.dto.design;

import Farm.Team4.findOwn.dto.design.parsing.body.Body;
import Farm.Team4.findOwn.dto.design.parsing.count.Count;
import Farm.Team4.findOwn.dto.design.parsing.header.Header;
import lombok.Data;

@Data
public class Response {
    private Header header;
    private Body body;
    private Count count;
}
