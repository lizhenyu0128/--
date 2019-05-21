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
@RequestMapping("/joincode")
public class QRcodeController {

    @Autowired
    UserServiceImpl userService;

    // return MD5 join group QBcode
    @PutMapping("/joinGroup/{id}/{groupCreater}")
    public BackJSON joinGroup(@PathVariable int id,@PathVariable int groupCreater) {

        return userService.getQRcode(id, groupCreater);
    }
    // decode join QRcode
    @GetMapping("/joinGroup/{wxId}/{decode}")
    public BackJSON decodeJoinQR(@PathVariable String wxId,@PathVariable String decode){
        return userService.decodeJoinQR(wxId,decode);

    }

}
