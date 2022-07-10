package com.qgj.foot.service;

import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.entity.UserFoot;

import java.util.List;

/**
 * 用户足迹Service接口
 *
 * @author qgj
 * @date 2022-04-19
 */
public interface IUserFootService
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
     * 批量删除用户足迹
     *
     * @param ftIds 需要删除的用户足迹主键集合
     * @return 结果
     */
    public int deleteUserFootByFtIds(Long[] ftIds);

    /**
     * 删除用户足迹信息
     *
     * @param ftId 用户足迹主键
     * @return 结果
     */
    public int deleteUserFootByFtId(Long ftId);

    List<Goods> selectUserFootListByUid(Long userId);

    int deleteUserFootByFtIds(Long id);
}
