package com.qgj.web.controller.paly;

import com.qgj.common.core.controller.BaseController;
import com.qgj.paly.service.IUserPalyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(tags = "结算页面")
@RequestMapping("/pay")
public class UserPalyController extends BaseController {

    @Resource
    private IUserPalyService userPalyService;

    /**
     * 结算支付
     */
    @GetMapping("/money")
    @ApiOperation(value = "支付")
    public String userPay(@RequestParam String orderSn, HttpServletResponse response) {
        return userPalyService.UserPay(response,orderSn);
    }

    @GetMapping("/success")
    @ApiOperation(value = "回调")
    public String success(@RequestParam String out_trade_no,HttpServletResponse response) {
        String str = userPalyService.updateOreder(out_trade_no);
        try {
            response.sendRedirect("http://localhost:8080/member/order");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
