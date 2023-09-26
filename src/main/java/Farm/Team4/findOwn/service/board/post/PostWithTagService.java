package Farm.Team4.findOwn.service.board.post;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.repository.board.PostWithTagRepository;
import Farm.Team4.findOwn.service.board.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostWithTagService {
    private final PostWithTagRepository postWithTagRepository;
    private final TagService tagService;
    @Transactional
    public void saveAssociation(List<String> tagNames, Post newPost){
        List<PostWithTag> associations = tagNames.stream()
                .map(tagName -> new PostWithTag(tagService.findByTagName(tagName), newPost))
                .toList();
        postWithTagRepository.saveAll(associations);
    }
}
