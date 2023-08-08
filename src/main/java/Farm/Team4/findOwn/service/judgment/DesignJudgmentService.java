package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.repository.judgment.DesignJudgmentRepository;
import Farm.Team4.findOwn.service.design.DesignService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DesignJudgmentService {
    private final MemberService memberService;
    private final DesignService designService;
    private final DesignJudgmentRepository designJudgmentRepository;

    
}
