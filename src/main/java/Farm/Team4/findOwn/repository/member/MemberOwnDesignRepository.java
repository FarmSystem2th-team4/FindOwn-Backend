package Farm.Team4.findOwn.repository.member;

import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberOwnDesignRepository extends JpaRepository<MemberOwnDesign, Long> {
    List<MemberOwnDesign> findMemberOwnDesignByMember_Id(String memberId);
}
