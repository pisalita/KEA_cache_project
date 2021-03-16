package kea.cache_project;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CacheController {
    Cache cache = new Cache();

    @GetMapping("/get-user-data")
    @ResponseBody
    public String getUserData(@RequestParam("id") int id) throws InterruptedException {

        if(cache.has(id)){
            return cache.get(id);
        }else{
            User user = new User(id);
            cache.set(id,user.getDataSlow());
        }

        return cache.get(id);
    }
}
