package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
