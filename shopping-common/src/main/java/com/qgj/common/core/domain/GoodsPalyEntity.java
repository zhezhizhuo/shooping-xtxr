package com.qgj.common.core.domain;

import java.io.Serializable;
import java.util.List;

public class GoodsPalyEntity  implements Serializable {
    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Goods_Detail> getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(List<Goods_Detail> goods_detail) {
        this.goods_detail = goods_detail;
    }

    private String out_trade_no;

    private String total_amount;
    private String subject;

    private String product_code ="FAST_INSTANT_TRADE_PAY";

    private String body;

    private List<Goods_Detail> goods_detail;
}
