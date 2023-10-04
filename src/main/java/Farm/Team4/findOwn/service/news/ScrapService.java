package Farm.Team4.findOwn.service.news;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.news.News;
import Farm.Team4.findOwn.domain.news.Scrap;
import Farm.Team4.findOwn.dto.news.scrap.request.SaveScrapRequest;
import Farm.Team4.findOwn.dto.news.scrap.response.SaveScrapResponse;
import Farm.Team4.findOwn.repository.news.ScrapRepository;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final MemberService memberService;
    private final NewsService newsService;
    @Transactional
    public SaveScrapResponse saveScrap(SaveScrapRequest request){
        Member findMember = memberService.findById(request.getMemberId());
        News findNews = newsService.findById(request.getNewsId());
        Scrap savedScrap = scrapRepository.save(new Scrap(findMember, findNews));
        return new SaveScrapResponse(savedScrap.getId(), savedScrap.getMember().getId(), savedScrap.getNews().getId());
    }
}
