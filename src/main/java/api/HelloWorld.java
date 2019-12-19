package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.HelloWorldService;

import java.util.List;

@RestController
@RequestMapping
public class HelloWorld {

    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public String index(){
        return "hello world";
    }

    @GetMapping("/projects")
    public List list(){
        return helloWorldService.list();
    }
}
