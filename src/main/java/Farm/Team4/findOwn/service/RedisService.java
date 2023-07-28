package Farm.Team4.findOwn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate stringRedisTemplate;

    // redis에서 데이터 가져오기
    // key를 주면 value, value 주면 key 반환
    public String getData(String key){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    //redis에 데이터 추가 (key, value, 유효시간)
    public void setDataExpire(String key, String value, Long seconds){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireTime = Duration.ofSeconds(seconds);
        valueOperations.set(key, value, expireTime);
    }
    //redis에 데이터 삭제
    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }
}
