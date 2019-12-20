package com.yfl.redis;

import com.yfl.DemoApplication;
import com.yfl.hello.dto.Rule;
import com.yfl.hello.service.ZSetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisZsetTest {

    @Autowired
    ZSetService zSetService;

    @Test
    public void save() {
        Arrays.asList(4,5,6).forEach(integer -> {
            Rule rule = new Rule(integer.longValue(),"jack"+ integer);
            zSetService.save(rule);
        });
    }
    @Test
    public void update(){
        List<Rule> list = zSetService.listByPage(3,3);
        System.out.println(list);
        Rule rule = new Rule(33L,"jack"+ 1);
        zSetService.update(rule);
        List<Rule> list2 = zSetService.listWithScore(3,3);
        System.out.println(list2);

    }

    @Test
    public void list(){
        List<Rule> list = zSetService.list();
        System.out.println(list);
    }

    @Test
    public void listByPage(){
        List<Rule> list = zSetService.listByPage(3,3);
        System.out.println(list);
    }

    @Test
    public void listWithScore(){
        List<Rule> list = zSetService.listWithScore();
        System.out.println(list);
    }
}
