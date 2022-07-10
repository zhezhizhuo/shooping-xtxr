package com.qgj.web.controller.product;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qgj.common.annotation.Log;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.enums.BusinessType;
import com.qgj.common.core.domain.entity.ProductComment;
import com.qgj.product.service.IProductCommentService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 评论表 用户评论Controller
 *
 * @author qgj
 * @date 2022-04-21
 */
@RestController

@Api(tags = "评论表 用户评论 接口")
@RequestMapping("/product/comment")

public class ProductCommentController extends BaseController {
    @Resource
    private IProductCommentService productCommentService;

    /**
     * 查询评论表 用户评论列表
     */
    @ApiOperation(value = "查询评论表 用户评论列表")
    @PreAuthorize("@ss.hasPermi('product:comment:list')")
   
    @GetMapping("/list")
    public TableDataInfo list(ProductComment productComment) {
        startPage();
        List<ProductComment> list = productCommentService.selectProductCommentList(productComment);
        return getDataTable(list);
    }

    /**
     * 导出评论表 用户评论列表
     */
    @ApiOperation(value = "导出评论表 用户评论列表")

    @PreAuthorize("@ss.hasPermi('product:comment:export')")
    @Log(title = "评论表 用户评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductComment productComment) {
        List<ProductComment> list = productCommentService.selectProductCommentList(productComment);
        ExcelUtil<ProductComment> util = new ExcelUtil<ProductComment>(ProductComment.class);
        util.exportExcel(response, list, "评论表 用户评论数据");
    }

    /**
     * 获取评论表 用户评论详细信息
     */
    @ApiOperation(value = "获取评论表 用户评论详细信息")
    @PreAuthorize("@ss.hasPermi('product:comment:query')")
    @GetMapping(value = "/{comId}")
    public AjaxResult getInfo(@PathVariable("comId") Long comId) {
        return AjaxResult.success(productCommentService.selectProductCommentByComId(comId));
    }

    /**
     * 新增评论表 用户评论
     */
    @ApiOperation(value = "新增评论表 用户评论")
    @ApiOperationSupport(includeParameters = {"ud", "createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('product:comment:add')")
    @Log(title = "评论表 用户评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductComment productComment) {
        return toAjax(productCommentService.insertProductComment(productComment));
    }

    /**
     * 修改评论表 用户评论
     */
    @ApiOperation(value = "修改评论表 用户评论")
    @ApiOperationSupport(includeParameters = {"createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('product:comment:edit')")
    @Log(title = "评论表 用户评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductComment productComment) {
        return toAjax(productCommentService.updateProductComment(productComment));
    }

    /**
     * 删除评论表 用户评论
     */
    @ApiOperation(value = "删除评论表 用户评论")
    @PreAuthorize("@ss.hasPermi('product:comment:remove')")
    @Log(title = "评论表 用户评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{comIds}")
    public AjaxResult remove(@PathVariable Long[] comIds) {
        return toAjax(productCommentService.deleteProductCommentByComIds(comIds));
    }
}
