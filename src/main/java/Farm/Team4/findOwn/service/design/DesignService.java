package Farm.Team4.findOwn.service.design;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.repository.design.DesignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;
    @Transactional
    public Long saveDesign(Design design){
        Design saveDesign = designRepository.save(design);
        return saveDesign.getId();
    }
}
