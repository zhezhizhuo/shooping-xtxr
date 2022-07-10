package com.qgj.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.qgj.address.service.IUserAddressService;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.OrderUserEntity;
import com.qgj.common.core.domain.UserOrderEntity;
import com.qgj.common.core.domain.entity.OrderItem;
import com.qgj.common.core.domain.entity.Product;
import com.qgj.common.core.domain.entity.UserAddress;
import com.qgj.common.core.domain.model.LoginUser;
import com.qgj.common.utils.DateUtils;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.common.utils.uuid.IdUtils;
import com.qgj.common.utils.uuid.UUID;
import com.qgj.order.service.IOrderCartService;
import com.qgj.order.service.IOrderItemService;
import com.qgj.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.order.mapper.MasterOrderMapper;
import com.qgj.common.core.domain.entity.MasterOrder;
import com.qgj.order.service.IMasterOrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户订单表Service业务层处理
 *
 * @author qgj
 * @date 2022-04-25
 */
@Service
public class MasterOrderServiceImpl implements IMasterOrderService
{
    @Resource
    private MasterOrderMapper masterOrderMapper;

    @Resource
    private IUserAddressService   userAddressService;
    @Resource
    private IOrderItemService orderItemService;

    @Resource
    private IOrderCartService orderCartService;

    @Resource
    private IProductService  productService;

    /**
     * 查询用户订单表
     *
     * @param id 用户订单表主键
     * @return 用户订单表
     */
    @Override
    public MasterOrder selectMasterOrderById(Long id)
    {
        return masterOrderMapper.selectMasterOrderById(id);
    }

    /**
     * 查询用户订单表列表
     *
     * @param masterOrder 用户订单表
     * @return 用户订单表
     */
    @Override
    public List<MasterOrder> selectMasterOrderList(MasterOrder masterOrder)
    {
        return masterOrderMapper.selectMasterOrderList(masterOrder);
    }

    /**
     * 新增用户订单表
     *
     * @param masterOrder 用户订单表
     * @return 结果
     */
    @Override
    public int insertMasterOrder(MasterOrder masterOrder)
    {
        masterOrder.setCreateTime(DateUtils.getNowDate());
        return masterOrderMapper.insertMasterOrder(masterOrder);
    }

    /**
     * 修改用户订单表
     *
     * @param masterOrder 用户订单表
     * @return 结果
     */
    @Override
    public int updateMasterOrder(MasterOrder masterOrder)
    {
        return masterOrderMapper.updateMasterOrder(masterOrder);
    }

    /**
     * 批量删除用户订单表
     *
     * @param ids 需要删除的用户订单表主键
     * @return 结果
     */
    @Override
    public int deleteMasterOrderByIds(Long[] ids)
    {
        return masterOrderMapper.deleteMasterOrderByIds(ids);
    }

    /**
     * 删除用户订单表信息
     *
     * @param id 用户订单表主键
     * @return 结果
     */
    @Override
    public int deleteMasterOrderById(Long id)
    {
        return masterOrderMapper.deleteMasterOrderById(id);
    }

    @Transactional
    @Override
    public String userUpOrderByUser(OrderUserEntity order) {
        //设置购物车已经删除状态
        List<Long> carIds = order.getCarIds();
        orderCartService.updateUserCarDel(carIds);
        UserAddress address = userAddressService.selectUserAddressById(order.getAddressId());

        LoginUser loginUser = SecurityUtils.getLoginUser();


        //把商品 添加到 订单子表里面

        MasterOrder  masterOrder = new MasterOrder();
        String  orderSn = IdUtils.randomUUID().trim();
        masterOrder.setOrderSn(orderSn);
        order.getGoods().forEach(item ->{
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(0L);
            orderItem.setOrderSn(orderSn);
            orderItem.setProductId(item.getId());
            orderItem.setProductQuantity(item.getCount());
            //插入
            orderItemService.insertOrderItem(orderItem);
        });

        /** 1.生产全局的uuid
         *
         *   2.设置默认参数 保护数据可靠性
         *
         *              1.提交时间
         *              2.用户帐号
         *              3.支付方式
         *              4.订单状态
         *              5.确认收货状态
         *              6.订单删除状态：
         *   3.
         *
         *
         */
        //提交时间
        masterOrder.setCreateTime(DateUtils.getNowDate());
        //用户帐号
        masterOrder.setMemberUsername(loginUser.getUsername());
        //总金额订单总金额
        masterOrder.setTotalAmount(order.getTotalAmount());
        //订单总金额
        masterOrder.setPayAmount(order.getTotalAmount());
        //未支付
        masterOrder.setPayType(0);
        //待付款
        masterOrder.setStatus(0);
        //收入姓名
        masterOrder.setReceiverName(address.getName());
         masterOrder.setReceiverPhone(address.getPhone());
         masterOrder.setAddresssId(address.getId());
         masterOrder.setDeleteStatus(0);
         masterOrder.setConfirmStatus(0);
        int i = masterOrderMapper.insertMasterOrder(masterOrder);
        return masterOrder.getOrderSn();
    }

    @Override
    public MasterOrder selectOrderByOrderSn(String orderSn) {
        return masterOrderMapper.selectOrderByOrderSn(orderSn);
    }

    @Override
    public int updateOrderSetIsMenoru(String out_trade_no) {
        return masterOrderMapper.updateOrderSetIsMenoru(out_trade_no);
    }

    @Override
    public List<UserOrderEntity> selectUserOrder(String userId,String status) {
        List<UserOrderEntity> orders= masterOrderMapper.selectUserOrder(userId,status);
        orders.stream().forEach(item ->{
            List<OrderItem> orderItems = orderItemService.selectOrderItemByOrderSn(String.valueOf(item.getId()));
//            item.setGoods(orderItems.stream().map(order -> productService.selectProductByProductId(order.getProductId())).map(Goods::new).collect(Collectors.toList()));
            item.setGoods( orderItems.stream().map( order ->{
                Product product = productService.selectProductByProductId(order.getProductId());
                product.setCount(order.getProductQuantity());
                return  product;
            }).map(Goods::new).collect(Collectors.toList()));
        });
            return orders;
    }

    @Override
    public int UserDelOrderMaster(MasterOrder masterOrder) {
        return masterOrderMapper.UserDelOrderMaster(masterOrder);
    }
}
