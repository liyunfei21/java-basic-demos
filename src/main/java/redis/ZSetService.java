package redis;

import com.alibaba.fastjson.JSON;
import dto.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ZSetService {

    private final BoundZSetOperations<String,String> zSetOperations;
    private final BoundValueOperations valueOperations;

    @Autowired
    public ZSetService(RedisTemplate redisTemplate) {
        this.zSetOperations = redisTemplate.boundZSetOps("custom_rule_zset");
        this.valueOperations = redisTemplate.boundValueOps("incr_id");
    }

    private Long incrementId(){
        return valueOperations.increment(1);
    }

    public void save(Rule rule){
        zSetOperations.add(JSON.toJSONString(rule), incrementId());
    }

    public List<Rule> list(){
        Set<String> range = zSetOperations.range(0, -1);
        return range.stream().map(data -> JSON.parseObject(data, Rule.class)).collect(Collectors.toList());
    }

    public List<Rule> listByPage(int start, int end){
        Set<String> range = zSetOperations.rangeByScore(start, end);
        return range.stream().map(data -> JSON.parseObject(data, Rule.class)).collect(Collectors.toList());
    }

    public List<Rule> listWithScore(){
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.rangeWithScores(0, -1);
        return   typedTuples.stream().map(stringTypedTuple -> {
            return new Rule(stringTypedTuple.getScore().longValue(), JSON.parseObject(stringTypedTuple.getValue(), Rule.class).getName());
        }).collect(Collectors.toList());
    }

    public void update(Rule rule){
        zSetOperations.add(JSON.toJSONString(rule), rule.getId());
    }

    public List<Rule> listWithScore(int start, int end) {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.rangeByScoreWithScores(start, end);
        return   typedTuples.stream().map(stringTypedTuple -> {
            return new Rule(stringTypedTuple.getScore().longValue(), JSON.parseObject(stringTypedTuple.getValue(), Rule.class).getName());
        }).collect(Collectors.toList());
    }
}

