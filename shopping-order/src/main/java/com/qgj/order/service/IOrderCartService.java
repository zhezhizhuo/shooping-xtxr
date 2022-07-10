package com.qgj.order.service;

import java.util.List;

import com.qgj.common.core.domain.UserCartEntity;
import com.qgj.common.core.domain.entity.OrderCart;
import com.qgj.common.core.domain.model.UserCard;

/**
 * 用户购物车Service接口
 *
 * @author qgj
 * @date 2022-04-23
 */
public interface IOrderCartService
{
    /**
     * 查询用户购物车
     *
     * @param id 用户购物车主键
     * @return 用户购物车
     */
    public OrderCart selectOrderCartById(Long id);

    /**
     * 查询用户购物车列表
     *
     * @param orderCart 用户购物车
     * @return 用户购物车集合
     */
    public List<OrderCart> selectOrderCartList(OrderCart orderCart);

    /**
     * 新增用户购物车
     *
     * @param orderCart 用户购物车
     * @return 结果
     */
    public int insertOrderCart(OrderCart orderCart);

    /**
     * 修改用户购物车
     *
     * @param orderCart 用户购物车
     * @return 结果
     */
    public int updateOrderCart(OrderCart orderCart);

    /**
     * 批量删除用户购物车
     *
     * @param ids 需要删除的用户购物车主键集合
     * @return 结果
     */
    public int deleteOrderCartByIds(Long[] ids);

    /**
     * 删除用户购物车信息
     *
     * @param id 用户购物车主键
     * @return 结果
     */
    public int deleteOrderCartById(Long id);

    List<UserCartEntity> selectCarByUserId(Long userId);

    int UserAddCart(UserCard userCard);


    int selectUserCartCount(Long userId);

    int UserAddCartList(List<UserCard> userCards);

    int updateUserCarDel(List<Long> carIds);
}
