package Farm.Team4.findOwn.service.member.information;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequestInfo;
import Farm.Team4.findOwn.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member saveMember(SaveMemberRequestInfo tempMember){
        return memberRepository.save(tempMember.toMember(new Date()));
    }
    public boolean existedMemberById(String memberId){return memberRepository.existsById(memberId);}
    public boolean existedMemberByEmail(String email){
        return memberRepository.existsByEmail(email);
    }
    public Member findById(String id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
    }
    @Transactional
    public void deleteMember (Member member){
        memberRepository.delete(member);
    }
    @Transactional
    public Member changeEmail(String oldEmail, String newEmail){
        Member member = memberRepository.findByEmail(oldEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.changeEmail(newEmail);
        return member;
    }
    @Transactional
    public Member changePassword(String id, String newPassword){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.changePassword(newPassword);
        return member;
    }

    @Transactional(readOnly = true)
    public Member loginMember(Member member) {
        return memberRepository.findByIdAndPassword(member.getId(), member.getPassword());
    }
}
