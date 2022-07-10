package com.qgj.paly.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgj.common.core.domain.GoodsPalyEntity;
import com.qgj.common.core.domain.Goods_Detail;
import com.qgj.common.core.domain.entity.MasterOrder;
import com.qgj.common.core.domain.entity.OrderItem;
import com.qgj.common.core.domain.entity.Product;
import com.qgj.common.utils.ip.IpUtils;
import com.qgj.order.service.IMasterOrderService;
import com.qgj.order.service.IOrderItemService;
import com.qgj.paly.config.AlipayConfig;
import com.qgj.paly.service.IUserPalyService;
import com.qgj.product.service.IProductService;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("userPalyService")
public class IUserPalyServiceImpl implements IUserPalyService {


    @Resource
    private  AlipayConfig alipayConfig;

    @Resource
    private IMasterOrderService orderService;


    @Resource
    private IOrderItemService  orderItemService;

    @Resource
    private IProductService productService;




    //查询该订单下面的所有商品

    @Override
    public String UserPay(HttpServletResponse response, String orderSn) {
        //根据 orderSn查询这个订单列表
        MasterOrder masterOrder = orderService.selectOrderByOrderSn(orderSn);

        //根据 orderSn查询这个订单下的所有商品
        List<OrderItem>   orderItems = orderItemService.selectOrderItemByOrderSn(orderSn);
        AlipayClient alipayClient = getClient();
        GoodsPalyEntity goodsPalyEntity  = getGoodsPaly(masterOrder,orderItems);
        //定义页面数据
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
         //处理json 告诉支付宝我们的订单金额  支付商品
        try {
            alipayRequest.setBizContent(new ObjectMapper().writeValueAsString(goodsPalyEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        alipayRequest.setReturnUrl(getIpPort()+"/pay/success");
        alipayRequest.setNotifyUrl(getIpPort()+"/pay/notify"); //在公共参数中设置回跳和通知地址
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf8");
        return form;
    }

    /**
     * 支付状态
     * @param out_trade_no
     * @return
     */
    @Override
    public String updateOreder(String out_trade_no) {
        //把订单状态改为已支付
        orderService.updateOrderSetIsMenoru(out_trade_no);
        return null;
    }


    /*
    生成 支付信息
     */
    private GoodsPalyEntity getGoodsPaly(MasterOrder masterOrder, List<OrderItem> orderItems) {
        GoodsPalyEntity goodsPalyEntity = new GoodsPalyEntity();
        /**
         * 	商户订单号。
         * 由商家自定义，6
         */
        goodsPalyEntity.setOut_trade_no(masterOrder.getOrderSn());
        /**
         * total_amount
         *
         * 订单总金额。
         * 单位为元，精确到小数点后两位，取值范围：[0.01,100000000] 。
         */
        goodsPalyEntity.setTotal_amount(masterOrder.getTotalAmount()+"");

        /**
         * 订单标题。
         * 注意：不可使用特殊字符，如 /，=，& 等。
         */
        goodsPalyEntity.setSubject("标题:小兔鲜儿支付 订单号为"+masterOrder.getOrderSn());
        /**
         * 订单附加信息。
         * 如果请求时传递了该参数，将在异步通知、对账单中原样返回，同时会在商户和用户的pc账单详情中作为交易描述展示
         */
        List<String> info = orderItems.stream().map(orderItem -> {
            Product product = productService.selectProductByProductId(orderItem.getProductId());
            /**
             * 	GoodsDetail[]
             *
             * 	订单包含的商品列表信息，json格式。
             */
            return "<div> 商品名称:"+product.getProductName() + "描述" + product.getDescript()+"  商品数量:"+orderItem.getProductQuantity()+"</div>";
        }).collect(Collectors.toList());
        StringBuffer  stringBuffer = new StringBuffer();
        info.forEach(item ->{
                    stringBuffer.append(item);
        });
        goodsPalyEntity.setBody(stringBuffer.toString());
        List<Goods_Detail> collect = orderItems.stream().map(item -> {
            Goods_Detail goods_detail = new Goods_Detail(productService.selectProductByProductId(item.getProductId()));

            goods_detail.setQuantity(Math.toIntExact(item.getProductQuantity()));
            return goods_detail;
        }).collect(Collectors.toList());
        /**
         * 订单包含的商品列表信息，json格式。
         */
//        try {
//            goodsPalyEntity.setGoods_detail(new ObjectMapper().writeValueAsString(collect));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
          goodsPalyEntity.setGoods_detail(collect);
        return  goodsPalyEntity;
    }


    //生产id 加端口号
    public String getIpPort(){
//                    return   "http://"+IpUtils.getHostIp()+":1001";
        return  "http://localhost:1001";
    }


    public AlipayClient getClient() {

        try {
            return new DefaultAlipayClient(alipayConfig.getGETWAYURL(), alipayConfig.getAPP_ID(), alipayConfig.getAPP_PRIVATE_KEY(), "json", alipayConfig.getCHARSET(), alipayConfig.getALIPAY_PUBLIC_KEY(), alipayConfig.getKEY_TYPE());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
