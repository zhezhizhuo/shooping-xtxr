package com.qgj.paly.service;

import javax.servlet.http.HttpServletResponse;

public interface IUserPalyService {


    /**
     * 结算订单支付
     */

    public String UserPay(HttpServletResponse  response,String orderSn);

    String updateOreder(String out_trade_no);
}
