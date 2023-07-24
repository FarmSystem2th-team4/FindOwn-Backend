package Farm.Team4.findOwn.service;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.FindPasswordRequestInfo;
import Farm.Team4.findOwn.dto.MemberRequestInfo;
import Farm.Team4.findOwn.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member saveMember(MemberRequestInfo tempMember){
        return memberRepository.save(tempMember.toMember());
    }
    public boolean duplicatedMember(String id){
        return memberRepository.existsById(id);
    }
    public boolean existedMember (String email){
        return memberRepository.existsByEmail(email);
    }
    @Transactional
    public Member changePassword(FindPasswordRequestInfo findPasswordRequestInfo){
        String memberEmail = findPasswordRequestInfo.getEmail();
        log.info("member email: " + memberEmail);
        String newPassword = findPasswordRequestInfo.getNewPassword();
        log.info("member new password: " + newPassword);
        Member findMember = memberRepository.findByEmail(memberEmail);
        findMember.changePassword(newPassword);
        return findMember;
    }
}
