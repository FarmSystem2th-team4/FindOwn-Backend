package Farm.Team4.findOwn.service;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.MemberRequestInfo;
import Farm.Team4.findOwn.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member saveMember(MemberRequestInfo tempMember){
        return memberRepository.save(tempMember.toMember());
    }
}
