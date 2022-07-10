package com.qgj.foot.mapper;

import java.util.List;

import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.entity.UserFoot;

/**
 * 用户足迹Mapper接口
 *
 * @author qgj
 * @date 2022-04-21
 */

public interface UserFootMapper
{
    /**
     * 查询用户足迹
     *
     * @param ftId 用户足迹主键
     * @return 用户足迹
     */
    public UserFoot selectUserFootByFtId(Long ftId);

    /**
     * 查询用户足迹列表
     *
     * @param userFoot 用户足迹
     * @return 用户足迹集合
     */
    public List<UserFoot> selectUserFootList(UserFoot userFoot);

    /**
     * 新增用户足迹
     *
     * @param userFoot 用户足迹
     * @return 结果
     */
    public int insertUserFoot(UserFoot userFoot);

    /**
     * 修改用户足迹
     *
     * @param userFoot 用户足迹
     * @return 结果
     */
    public int updateUserFoot(UserFoot userFoot);

    /**
     * 删除用户足迹
     *
     * @param ftId 用户足迹主键
     * @return 结果
     */
    public int deleteUserFootByFtId(Long ftId);

    /**
     * 批量删除用户足迹
     *
     * @param ftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserFootByFtIds(Long[] ftIds);

    List<Goods> selectUserFootByUid(Long userId);
}
