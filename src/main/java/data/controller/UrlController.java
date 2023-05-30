package data.controller;

import data.service.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UrlController {
    @Autowired
    private UrlServiceImpl urlService;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String convert(@RequestParam String longurl, Model model) throws IllegalAccessException {
        System.out.println(longurl);
        model.addAttribute("longurl",longurl);
        model.addAttribute("shorturl",urlService.generateShortUrl(longurl));
        System.out.println(model);
        return "index";
    }

    @GetMapping("/{shorturl}")
    public String redirect(HttpServletResponse response, @PathVariable String shorturl){
        String longurl= urlService.
                getLongUrlByShortUrl(shorturl.replace("http://localhost:8080/",""));
        if(longurl != null){
            return "redirect:"+"http://"+longurl;
        }
        return "index";
    }
}
