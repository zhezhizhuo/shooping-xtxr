package com.qgj.foot.service.impl;

import java.util.List;

import com.qgj.common.core.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.foot.mapper.UserFootMapper;
import com.qgj.common.core.domain.entity.UserFoot;
import com.qgj.foot.service.IUserFootService;

/**
 * 用户足迹Service业务层处理
 *
 * @author qgj
 * @date 2022-04-19
 */
@Service
public class UserFootServiceImpl implements IUserFootService
{
    @Autowired
    private UserFootMapper userFootMapper;

    /**
     * 查询用户足迹
     *
     * @param ftId 用户足迹主键
     * @return 用户足迹
     */
    @Override
    public UserFoot selectUserFootByFtId(Long ftId)
    {
        return userFootMapper.selectUserFootByFtId(ftId);
    }

    /**
     * 查询用户足迹列表
     *
     * @param userFoot 用户足迹
     * @return 用户足迹
     */
    @Override
    public List<UserFoot> selectUserFootList(UserFoot userFoot)
    {
        return userFootMapper.selectUserFootList(userFoot);
    }

    /**
     * 新增用户足迹
     *
     * @param userFoot 用户足迹
     * @return 结果
     */
    @Override
    public int insertUserFoot(UserFoot userFoot)
    {
        return userFootMapper.insertUserFoot(userFoot);
    }

    /**
     * 修改用户足迹
     *
     * @param userFoot 用户足迹
     * @return 结果
     */
    @Override
    public int updateUserFoot(UserFoot userFoot)
    {
        return userFootMapper.updateUserFoot(userFoot);
    }

    /**
     * 批量删除用户足迹
     *
     * @param ftIds 需要删除的用户足迹主键
     * @return 结果
     */
    @Override
    public int deleteUserFootByFtIds(Long[] ftIds)
    {
        return userFootMapper.deleteUserFootByFtIds(ftIds);
    }

    /**
     * 删除用户足迹信息
     *
     * @param ftId 用户足迹主键
     * @return 结果
     */
    @Override
    public int deleteUserFootByFtId(Long ftId)
    {
        return userFootMapper.deleteUserFootByFtId(ftId);
    }

    @Override
    public List<Goods> selectUserFootListByUid(Long userId) {
        return userFootMapper.selectUserFootByUid(userId);
    }

    @Override
    public int deleteUserFootByFtIds(Long id) {
        return  userFootMapper.deleteUserFootByFtId(id);
    }
}
