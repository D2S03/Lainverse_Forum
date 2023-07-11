package com.imageBoardAI.boardai.Controllers;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "errorPage";
    }

    @RequestMapping("/posts/thread/error")
    public String handleThreadError() {
        return "errorPage";
    }
}
