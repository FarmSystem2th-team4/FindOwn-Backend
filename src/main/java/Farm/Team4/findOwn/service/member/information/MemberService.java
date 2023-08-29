package Farm.Team4.findOwn.service.member.information;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.member.information.DeleteMemberRequestInfo;
import Farm.Team4.findOwn.dto.member.information.SaveMemberRequestInfo;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    public Member findById(String id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_MEMBER));
    }
    @Transactional
    public Member changeEmail(String oldEmail, String newEmail){
        Member member = findByEmail(oldEmail);
        member.changeEmail(newEmail);
        return member;
    }
    @Transactional
    public String changeTempPassword(String id, String tempPassword){
        Member member = findById(id);
        return member.changePassword(tempPassword);
    }
    @Transactional
    public String changeNewPassword(String memberId, String originPassword, String newPassword){
        if (!findById(memberId).getPassword().equals(originPassword))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_PASSWORD);
        log.info("기존 비밀번호와 일치 여부 확인 완료");

        Member findMember = findById(memberId);
        return findMember.changePassword(newPassword);
    }
    @Transactional
    public void deleteMember (Member member, DeleteMemberRequestInfo request){
        if (!member.getPassword().equals(request.getPassword()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_PASSWORD);
        memberRepository.delete(member);
    }
}
