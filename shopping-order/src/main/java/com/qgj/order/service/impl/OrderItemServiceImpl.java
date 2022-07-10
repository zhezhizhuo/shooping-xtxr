package com.qgj.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.order.mapper.OrderItemMapper;
import com.qgj.common.core.domain.entity.OrderItem;
import com.qgj.order.service.IOrderItemService;

/**
 * 订单商品Service业务层处理
 *
 * @author qgj
 * @date 2022-04-26
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService
{
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单商品
     *
     * @param id 订单商品主键
     * @return 订单商品
     */
    @Override
    public OrderItem selectOrderItemById(Long id)
    {
        return orderItemMapper.selectOrderItemById(id);
    }

    /**
     * 查询订单商品列表
     *
     * @param orderItem 订单商品
     * @return 订单商品
     */
    @Override
    public List<OrderItem> selectOrderItemList(OrderItem orderItem)
    {
        return orderItemMapper.selectOrderItemList(orderItem);
    }

    /**
     * 新增订单商品
     *
     * @param orderItem 订单商品
     * @return 结果
     */
    @Override
    public int insertOrderItem(OrderItem orderItem)
    {
        return orderItemMapper.insertOrderItem(orderItem);
    }

    /**
     * 修改订单商品
     *
     * @param orderItem 订单商品
     * @return 结果
     */
    @Override
    public int updateOrderItem(OrderItem orderItem)
    {
        return orderItemMapper.updateOrderItem(orderItem);
    }

    /**
     * 批量删除订单商品
     *
     * @param ids 需要删除的订单商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemByIds(Long[] ids)
    {
        return orderItemMapper.deleteOrderItemByIds(ids);
    }

    /**
     * 删除订单商品信息
     *
     * @param id 订单商品主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemById(Long id)
    {
        return orderItemMapper.deleteOrderItemById(id);
    }

    @Override
    public List<OrderItem> selectOrderItemByOrderSn(String orderSn) {
        return orderItemMapper.selectOrderItemByOrderSn(orderSn);
    }
}
