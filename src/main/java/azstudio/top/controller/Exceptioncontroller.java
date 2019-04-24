package azstudio.top.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:
 * Data:2019-04-24 16:33
 * Description:<>
 */
@ControllerAdvice
@ResponseBody
public class Exceptioncontroller {

    @ExceptionHandler(Exception.class)
    public String exceptHand(Exception ex) {
        System.out.println("cuole ");
        ex.printStackTrace();
        return "{\"code\":500}";
    }
    @ExceptionHandler(AbstractMethodError.class)
    public String exceptHand2(Exception ex) {
        System.out.println("cuole ");
        ex.printStackTrace();
        return "{\"code\":500}";
    }
}