package Farm.Team4.findOwn.service;

import Farm.Team4.findOwn.domain.Member;
import Farm.Team4.findOwn.dto.ChangePasswordRequestInfo;
import Farm.Team4.findOwn.dto.SaveMemberRequestInfo;
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
    public Member saveMember(SaveMemberRequestInfo tempMember){
        return memberRepository.save(tempMember.toMember());
    }
    public boolean duplicatedMember(String id){
        return memberRepository.existsById(id);
    }
    public boolean existedMember(String email){
        return memberRepository.existsByEmail(email);
    }
    public Member findByEmail(String email){return memberRepository.findByEmail(email);}
    public Member findById(String id){
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember == null || findMember.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        return findMember.get();
    }
    @Transactional
    public void deleteMember (Member member){
        memberRepository.delete(member);
    }
    @Transactional
    public Member changePassword(String email, String newPassword){
        Member findMember = memberRepository.findByEmail(email);
        findMember.changePassword(newPassword);
        return findMember;
    }
}
