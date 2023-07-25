package Farm.Team4.findOwn.repository;

import Farm.Team4.findOwn.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);
    Member findByEmail(String email);
    boolean existsById(String s);
    boolean existsByEmail(String email);
}
