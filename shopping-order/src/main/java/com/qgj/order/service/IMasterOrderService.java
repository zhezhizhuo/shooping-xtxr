package com.qgj.order.service;

import java.util.List;

import com.qgj.common.core.domain.OrderUserEntity;
import com.qgj.common.core.domain.UserOrderEntity;
import com.qgj.common.core.domain.entity.MasterOrder;

/**
 * 用户订单表Service接口
 *
 * @author qgj
 * @date 2022-04-25
 */
public interface IMasterOrderService
{
    /**
     * 查询用户订单表
     *
     * @param id 用户订单表主键
     * @return 用户订单表
     */
    public MasterOrder selectMasterOrderById(Long id);

    /**
     * 查询用户订单表列表
     *
     * @param masterOrder 用户订单表
     * @return 用户订单表集合
     */
    public List<MasterOrder> selectMasterOrderList(MasterOrder masterOrder);

    /**
     * 新增用户订单表
     *
     * @param masterOrder 用户订单表
     * @return 结果
     */
    public int insertMasterOrder(MasterOrder masterOrder);

    /**
     * 修改用户订单表
     *
     * @param masterOrder 用户订单表
     * @return 结果
     */
    public int updateMasterOrder(MasterOrder masterOrder);

    /**
     * 批量删除用户订单表
     *
     * @param ids 需要删除的用户订单表主键集合
     * @return 结果
     */
    public int deleteMasterOrderByIds(Long[] ids);

    /**
     * 删除用户订单表信息
     *
     * @param id 用户订单表主键
     * @return 结果
     */
    public int deleteMasterOrderById(Long id);

    String userUpOrderByUser(OrderUserEntity order);

    MasterOrder selectOrderByOrderSn(String orderSn);

    int updateOrderSetIsMenoru(String out_trade_no);

    List<UserOrderEntity> selectUserOrder(String userId,String status);

    int UserDelOrderMaster(MasterOrder masterOrder);
}
