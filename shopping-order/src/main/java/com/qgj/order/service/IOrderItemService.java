package com.qgj.order.service;

import java.util.List;
import com.qgj.common.core.domain.entity.OrderItem;

/**
 * 订单商品Service接口
 *
 * @author qgj
 * @date 2022-04-26
 */
public interface IOrderItemService
{
    /**
     * 查询订单商品
     *
     * @param id 订单商品主键
     * @return 订单商品
     */
    public OrderItem selectOrderItemById(Long id);

    /**
     * 查询订单商品列表
     *
     * @param orderItem 订单商品
     * @return 订单商品集合
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 新增订单商品
     *
     * @param orderItem 订单商品
     * @return 结果
     */
    public int insertOrderItem(OrderItem orderItem);

    /**
     * 修改订单商品
     *
     * @param orderItem 订单商品
     * @return 结果
     */
    public int updateOrderItem(OrderItem orderItem);

    /**
     * 批量删除订单商品
     *
     * @param ids 需要删除的订单商品主键集合
     * @return 结果
     */
    public int deleteOrderItemByIds(Long[] ids);

    /**
     * 删除订单商品信息
     *
     * @param id 订单商品主键
     * @return 结果
     */
    public int deleteOrderItemById(Long id);

    List<OrderItem> selectOrderItemByOrderSn(String orderSn);
}
