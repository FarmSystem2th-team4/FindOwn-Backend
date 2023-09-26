package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithTagRepository extends JpaRepository<PostWithTag, Long> {
}
