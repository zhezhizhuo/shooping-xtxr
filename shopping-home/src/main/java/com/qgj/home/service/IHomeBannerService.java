package com.qgj.home.service;

import java.util.List;
import com.qgj.common.core.domain.entity.HomeBanner;
import com.qgj.common.core.domain.entity.IndexBean;
import com.qgj.common.core.domain.entity.SysUser;


/**
 * 主页轮播Service接口
 *
 * @author qgj
 * @date 2022-04-18
 */
public interface IHomeBannerService
{
    /**
     * 查询主页轮播
     *
     * @param id 主页轮播主键
     * @return 主页轮播
     */
    public HomeBanner selectHomeBannerById(Long id);

    /**
     * 查询主页轮播列表
     *
     * @param homeBanner 主页轮播
     * @return 主页轮播集合
     */
    public List<HomeBanner> selectHomeBannerList(HomeBanner homeBanner);

    /**
     * 新增主页轮播
     *
     * @param homeBanner 主页轮播
     * @return 结果
     */
    public int insertHomeBanner(HomeBanner homeBanner);

    /**
     * 修改主页轮播
     *
     * @param homeBanner 主页轮播
     * @return 结果
     */
    public int updateHomeBanner(HomeBanner homeBanner);

    /**
     * 批量删除主页轮播
     *
     * @param ids 需要删除的主页轮播主键集合
     * @return 结果
     */
    public int deleteHomeBannerByIds(Long[] ids);

    /**
     * 删除主页轮播信息
     *
     * @param id 主页轮播主键
     * @return 结果
     */
    public int deleteHomeBannerById(Long id);
    /**
     * 添加邮箱 验证邮箱号码
     *
     */
    public void VerifyUserEmail(String ip,SysUser user,String email);

    IndexBean getIndexHomeData();
}
