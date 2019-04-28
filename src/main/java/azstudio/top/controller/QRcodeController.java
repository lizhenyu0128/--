package azstudio.top.controller;

import azstudio.top.Service.UserServiceImpl;
import azstudio.top.config.BackJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:
 * Data:2019-04-28 11:28
 * Description:<>
 */

@RestController
@RequestMapping("/test")
public class QRcodeController {

    @Autowired
    UserServiceImpl userService;

    // return MD5 join group QBcode
    @PutMapping("/joinGroup")
    public BackJSON joinGroup(@RequestBody Map<String, Object> par) {
        HashMap<String, String> ss = new HashMap<>();
        return userService.getQRcode(par);
    }
    // decode join QRcode
    @GetMapping("/joinGroup")
    public BackJSON decodeJoinQR(@RequestBody Map<String,String> par){
        return userService.decodeJoinQR(par);

    }

}
