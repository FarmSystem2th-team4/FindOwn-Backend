package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
