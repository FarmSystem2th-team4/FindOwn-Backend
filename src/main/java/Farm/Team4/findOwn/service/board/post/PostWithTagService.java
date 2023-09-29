package Farm.Team4.findOwn.service.board.post;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.PostWithTagRepository;
import Farm.Team4.findOwn.service.board.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostWithTagService {
    private final PostWithTagRepository postWithTagRepository;
    private final TagService tagService;
    @Transactional
    public void saveAssociation(Post post, Tag tag){
        postWithTagRepository.save(new PostWithTag(tag, post));
    }
    @Transactional
    public void saveAssociations(List<String> tagNames, Post newPost){
        List<PostWithTag> associations = tagNames.stream()
                .map(tagName -> new PostWithTag(tagService.findByTagName(tagName), newPost))
                .toList();
        postWithTagRepository.saveAll(associations);
    }
    @Transactional
    public void saveAssociationInUpdate(List<Tag> updateTags, Post post){
        for(Tag updateTag : updateTags){
            int check = 0;
            for(PostWithTag association : post.getTags()){
                if (association.getTag().equals(updateTag)) break;
                check++;
            }
            if (check == post.getTags().size())
                postWithTagRepository.save(new PostWithTag(updateTag, post));
        }
    }
    public Long countByTag(Tag tag){
        return postWithTagRepository.countByTag(tag);
    }
    public List<PostWithTag> findAssociations(Post post){
        return postWithTagRepository.findPostWithTagsByPost(post);
    }
    public PostWithTag findByPostAndTag(Post post, Tag tag){
        return postWithTagRepository.findByPostAndTag(post, tag)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_TAG)); // 수정 해야함
    }
    public List<Tag> findNewAssociation(List<Tag> originTags, List<Tag> newTags){
        List<Tag> saveTags = new ArrayList<>();
        for (Tag newTag : newTags) {
            int check = 0;
            for (Tag originTag : originTags) {
                if (newTag.equals(originTag)) break;
                check++;
            }
            if (check == originTags.size()) {
                saveTags.add(newTag);
            }
        }
        return saveTags;
    }
    public List<Tag> findDeleteTags(List<Tag> originTags, List<Tag> newTags){
        List<Tag> deleteTags = new ArrayList<>();
        for(Tag originTag : originTags){
            int check = 0;
            for(Tag newTag : newTags){
                if (originTag.equals(newTag)) break;
                check++;
            }
            if (check == newTags.size()){
                deleteTags.add(originTag);
            }
        }
        return deleteTags;
    }
    @Transactional
    public void updatingPostWithNewTag(Post post, List<Tag> saveTags){
        saveTags.forEach(saveTag -> saveAssociation(post, saveTag));
        log.info("신규 연관관계 저장완료");
    }
    @Transactional
    public void updatingPostWithOldTag(Post post, List<Tag> deleteTags){
        deleteTags.forEach(deleteTag -> {
            if (postWithTagRepository.countByTag(deleteTag) == 1)
                tagService.deleteTag(deleteTag);
//            PostWithTag findPostAndTag = postWithTagRepository.findByPostAndTag(post, deleteTag)
//                    .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 게시글 태그 관계입니다."));
//            deleteById(findPostAndTag.getId());
        });
    }
    @Transactional
    public void deleteOldAssociation(List<Long> oldAssociations){
        log.info("========================");
        oldAssociations.forEach(id -> deleteById(id));
        log.info("========================");
    }

    @Transactional
    public void updateAssociation(Post updatedPost, List<Tag> originTags, List<Tag> newTags){
        updatingPostWithNewTag(updatedPost, newTags);
        log.info("태그 삭제 로직 진입");
        updatingPostWithOldTag(updatedPost, originTags);
        log.info("제외된 태그 삭제");
    }
    @Transactional
    public void deleteById(Long associationId){
        log.info("========================");
        postWithTagRepository.deleteById(associationId);
        log.info("========================");
    }

}
