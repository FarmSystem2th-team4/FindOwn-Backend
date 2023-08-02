package Farm.Team4.findOwn.service.member.right;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.dto.member.right.design.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.repository.design.DesignRepository;
import Farm.Team4.findOwn.repository.member.MemberOwnDesignRepository;
import Farm.Team4.findOwn.repository.member.MemberOwnTrademarkRepository;
import Farm.Team4.findOwn.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberRightService {
    private final MemberRepository memberRepository;
    private final DesignRepository designRepository;
    private final MemberOwnDesignRepository memberOwnDesignRepository;
    private final MemberOwnTrademarkRepository memberOwnTrademarkRepository;
    public Long saveMemberOwnDesign(SaveMemberDesignRequestInfo request){
        Design ownDesign = request.changeToDesign();
        designRepository.save(ownDesign); //  임시 코드
        Optional<Member> tempMember = memberRepository.findById(request.getMemberId());
        if (tempMember == null || tempMember.isEmpty())
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        Member theMember = tempMember.get();

        MemberOwnDesign savedInfo = memberOwnDesignRepository.save(new MemberOwnDesign(ownDesign, theMember));
        return savedInfo.getId();
    }
}
