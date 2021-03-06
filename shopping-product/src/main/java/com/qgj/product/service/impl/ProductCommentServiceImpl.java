package com.qgj.product.service.impl;

import java.security.Security;
import java.sql.Date;
import java.util.List;

import com.qgj.common.core.domain.CommentEntity;
import com.qgj.common.core.domain.entity.CommentPost;
import com.qgj.common.utils.DateUtils;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.product.service.ICommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.product.mapper.ProductCommentMapper;
import com.qgj.common.core.domain.entity.ProductComment;
import com.qgj.product.service.IProductCommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 评论表 用户评论Service业务层处理
 *
 * @author qgj
 * @date 2022-04-21
 */
@Service
public class ProductCommentServiceImpl implements IProductCommentService
{
    @Autowired
    private ProductCommentMapper productCommentMapper;

    @Resource
    private ICommentPostService  commentPostService;
    /**
     * 查询评论表 用户评论
     *
     * @param comId 评论表 用户评论主键
     * @return 评论表 用户评论
     */
    @Override
    public ProductComment selectProductCommentByComId(Long comId)
    {
        return productCommentMapper.selectProductCommentByComId(comId);
    }

    /**
     * 查询评论表 用户评论列表
     *
     * @param productComment 评论表 用户评论
     * @return 评论表 用户评论
     */
    @Override
    public List<ProductComment> selectProductCommentList(ProductComment productComment)
    {
        return productCommentMapper.selectProductCommentList(productComment);
    }

    /**
     * 新增评论表 用户评论
     *
     * @param productComment 评论表 用户评论
     * @return 结果
     */
    @Override
    public int insertProductComment(ProductComment productComment)
    {
        productComment.setCreateTime(DateUtils.getNowDate());
        return productCommentMapper.insertProductComment(productComment);
    }

    /**
     * 修改评论表 用户评论
     *
     * @param productComment 评论表 用户评论
     * @return 结果
     */
    @Override
    public int updateProductComment(ProductComment productComment)
    {
        return productCommentMapper.updateProductComment(productComment);
    }

    /**
     * 批量删除评论表 用户评论
     *
     * @param comIds 需要删除的评论表 用户评论主键
     * @return 结果
     */
    @Override
    public int deleteProductCommentByComIds(Long[] comIds)
    {
        return productCommentMapper.deleteProductCommentByComIds(comIds);
    }

    /**
     * 删除评论表 用户评论信息
     *
     * @param comId 评论表 用户评论主键
     * @return 结果
     */
    @Override
    public int deleteProductCommentByComId(Long comId)
    {
        return productCommentMapper.deleteProductCommentByComId(comId);
    }

    @Override
    public List<CommentEntity> selectProductCommentByPid(Long id) {
        return productCommentMapper.selectProductCommentByPid(id);
    }

    @Override
    public List<CommentEntity> selectProductCommentByPidHot(Long id) {
        return productCommentMapper.selectProductCommentByPidHot(id);
    }

    @Override
    @Transactional
    public int userUpComment(Long id) {
        //点赞到表里面
        CommentPost commentPost = new CommentPost();
        commentPost.setUserId(SecurityUtils.getUserId());
        commentPost.setComId(id);
        commentPost.setCreateTime(DateUtils.getNowDate());
        commentPostService.insertCommentPost(commentPost);
        return productCommentMapper.userIpComment(id);
    }

    @Transactional
    @Override
    public int UpdateProductCommentByComId(Long id) {

        //删除点赞表里面  com_id =id and user_id = userId;
        commentPostService.deleteCommentUser(id,SecurityUtils.getUserId());
        //减一
        return productCommentMapper.userSubComment(id);
    }

    @Override
    public int selectCommByUIdAndComId(Long userId, Integer commid) {
        return  productCommentMapper.selectCommByUIdAndComId(userId,commid);
    }


}
