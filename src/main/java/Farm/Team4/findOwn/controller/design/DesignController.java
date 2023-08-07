package Farm.Team4.findOwn.controller.design;

import Farm.Team4.findOwn.service.design.DesignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class DesignController {
    private final DesignService designService;
    @GetMapping("/find/design")
    public ResponseEntity<String> findDesign(@RequestParam String articleName) throws MalformedURLException {
        return designService.findDesign(articleName);
    }
}
