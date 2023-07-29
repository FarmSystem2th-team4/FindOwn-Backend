package Farm.Team4.findOwn.service.member;

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
    public Member findByEmail(String email){
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember == null || findMember.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        return findMember.get();
    }
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
    public Member changeEmail(String oldEmail, String newEmail){
        Optional<Member> findMember = memberRepository.findByEmail(oldEmail);
        if (findMember == null || findMember.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        findMember.get().changeEmail(newEmail);
        return findMember.get();
    }
    @Transactional
    public Member changePassword(String email, String newPassword){
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember == null || findMember.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        findMember.get().changePassword(newPassword);
        return findMember.get();
    }
}
