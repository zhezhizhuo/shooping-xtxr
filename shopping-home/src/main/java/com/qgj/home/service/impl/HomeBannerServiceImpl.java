package com.qgj.home.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.qgj.common.core.domain.entity.HomeBanner;
import com.qgj.common.core.domain.entity.IndexBean;
import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.common.utils.DateUtils;
import com.qgj.common.utils.StringUtils;
import com.qgj.common.utils.ip.IpUtils;
import com.qgj.common.utils.uuid.IdUtils;
import com.qgj.common.utils.uuid.UUID;
import com.qgj.home.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.home.mapper.HomeBannerMapper;
import com.qgj.home.service.IHomeBannerService;

/**
 * 主页轮播Service业务层处理
 *
 * @author qgj
 * @date 2022-04-18
 */
@Service
public class HomeBannerServiceImpl implements IHomeBannerService
{
    @Autowired
    private HomeBannerMapper homeBannerMapper;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MailService mailService;
    /**
     * 查询主页轮播
     *
     * @param id 主页轮播主键
     * @return 主页轮播
     */
    @Override
    public HomeBanner selectHomeBannerById(Long id)
    {
        return homeBannerMapper.selectHomeBannerById(id);
    }

    /**
     * 查询主页轮播列表
     *
     * @param homeBanner 主页轮播
     * @return 主页轮播
     */
    @Override
    public List<HomeBanner> selectHomeBannerList(HomeBanner homeBanner)
    {
        return homeBannerMapper.selectHomeBannerList(homeBanner);
    }

    /**
     * 新增主页轮播
     *
     * @param homeBanner 主页轮播
     * @return 结果
     */
    @Override
    public int insertHomeBanner(HomeBanner homeBanner)
    {
        homeBanner.setCreateTime(DateUtils.getNowDate());
        return homeBannerMapper.insertHomeBanner(homeBanner);
    }

    /**
     * 修改主页轮播
     *
     * @param homeBanner 主页轮播
     * @return 结果
     */
    @Override
    public int updateHomeBanner(HomeBanner homeBanner)
    {
        return homeBannerMapper.updateHomeBanner(homeBanner);
    }

    /**
     * 批量删除主页轮播
     *
     * @param ids 需要删除的主页轮播主键
     * @return 结果
     */
    @Override
    public int deleteHomeBannerByIds(Long[] ids)
    {
        return homeBannerMapper.deleteHomeBannerByIds(ids);
    }

    @Override
    public void VerifyUserEmail(String ip,SysUser user, String email) {
        // 生成随机的code 然后验证
//        UUID.getSecureRandom().ge
        String code = IdUtils.simpleUUID().substring(0,5);
        //输入code 码就可以添加邮箱
        //发送邮箱
        new Thread(()->        {
            mailService.sendMail(code,user,email);
            redisCache.setCacheObject(code,email,1, TimeUnit.MINUTES);
        }).start();
    }

    @Override
    public IndexBean getIndexHomeData() {
        return this.homeBannerMapper.getIndexHomeData();
    }

    /**
     * 删除主页轮播信息
     *
     * @param id 主页轮播主键
     * @return 结果
     */
    @Override
    public int deleteHomeBannerById(Long id)
    {
        return homeBannerMapper.deleteHomeBannerById(id);
    }
}
