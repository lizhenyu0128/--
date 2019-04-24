package azstudio.top.controller;

import azstudio.top.config.BackJSON;
import azstudio.top.config.wxLogin;

import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * Author:
 * Data:2019-04-23 11:37
 * Description:<>
 */


@RestController
public class WxLoginController {



    @PostMapping("/login")
    public BackJSON Login(@RequestBody Map<String,String> code){
        Map<String, Object> a = wxLogin.getjscode2session(code.get("code"));
        System.out.println(a);
        return null;
    }
}
