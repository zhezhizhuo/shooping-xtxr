package com.qgj.order.mapper;

import java.util.List;
import com.qgj.common.core.domain.entity.OrderItem;

/**
 * 订单商品Mapper接口
 *
 * @author qgj
 * @date 2022-04-26
 */

public interface OrderItemMapper
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
     * 删除订单商品
     *
     * @param id 订单商品主键
     * @return 结果
     */
    public int deleteOrderItemById(Long id);

    /**
     * 批量删除订单商品
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderItemByIds(Long[] ids);

    List<OrderItem> selectOrderItemByOrderSn(String orderSn);
}
