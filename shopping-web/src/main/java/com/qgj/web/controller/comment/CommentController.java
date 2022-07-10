package com.qgj.web.controller.comment;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.constant.Constants;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.CommentEntity;
import com.qgj.common.core.domain.entity.CommentPost;
import com.qgj.common.core.domain.entity.ProductComment;
import com.qgj.common.core.domain.model.LoginBody;
import com.qgj.common.utils.StringUtils;
import com.qgj.product.service.ICommentPostService;
import com.qgj.product.service.IProductCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/evaluate")
@Api(tags = "评价模块")
@ApiSort(4)
public class CommentController extends BaseController {

    @Resource
    private IProductCommentService commentServicet;

    @Resource
    private ICommentPostService commentPostService;

    @PostMapping()
    @ApiOperationSupport(includeParameters = {
            "content",
            "pId",
            "parentId",
            "praiseCount",
            "score",
            "status",
            "tabs"
    })
    @ApiOperation(value = "发布评价 ", notes = "需要传入的 p_id 商品id,  tabs 标签 ,content 内容 ,score 分数")
    public AjaxResult addComment(@RequestBody ProductComment commentEntity) {
        commentEntity.setuId(getUserId());
        commentEntity.setStatus(0);
        commentEntity.setParentId(0L);
            commentServicet.insertProductComment(commentEntity);
        AjaxResult ajax = AjaxResult.success("评论成功,待审核");
        return ajax;
    }

    // 用户点赞  点赞
    @GetMapping("/up")
    @ApiOperation("用户点赞 ")
    public AjaxResult userLike(Long id) {
        AjaxResult ajax = AjaxResult.success();
        //查看用户是否已经点赞;
        CommentPost commentPost = new CommentPost();
        commentPost.setComId(id);
        commentPost.setUserId(getUserId());
        List<CommentPost> comments = commentPostService.selectCommentPostList(commentPost);
        if (comments == null || comments.size() == 0) {
            commentServicet.userUpComment(id);
            return AjaxResult.success("点赞成功");
        } else {
            return AjaxResult.success("已经点赞不可重复点赞");

        }
    }

    @PostMapping("/down")
    @ApiOperation("取消点赞 ")
    public AjaxResult userDownLike(@RequestParam @ApiParam("评论id") Long id) {
        return AjaxResult.success(commentServicet.UpdateProductCommentByComId(id));
    }
}
