package com.qgj.order.mapper;

import java.util.List;

import com.qgj.common.core.domain.UserOrderEntity;
import com.qgj.common.core.domain.entity.MasterOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 用户订单表Mapper接口
 *
 * @author qgj
 * @date 2022-04-25
 */

public interface MasterOrderMapper
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
     * 删除用户订单表
     *
     * @param id 用户订单表主键
     * @return 结果
     */
    public int deleteMasterOrderById(Long id);

    /**
     * 批量删除用户订单表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMasterOrderByIds(Long[] ids);

    MasterOrder selectOrderByOrderSn(String orderSn);

    int updateOrderSetIsMenoru(String out_trade_no);

    List<UserOrderEntity> selectUserOrder(@Param("userId") String userId,@Param("status") String status);

    int UserDelOrderMaster(MasterOrder masterOrder);
}
