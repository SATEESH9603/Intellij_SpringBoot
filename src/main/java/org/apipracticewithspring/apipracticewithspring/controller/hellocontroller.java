package org.apipracticewithspring.apipracticewithspring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping ("/hello")
public class hellocontroller {
    @GetMapping("/say/{name}/{times}")
    public String sayhello(@PathVariable("name") String name, @PathVariable("times") int times){
        String answer = "";
        for(int i=1;i<times;i++){
            answer +="Hello "+name;
            answer +="<br>";
        }
        return answer;

    }

}
