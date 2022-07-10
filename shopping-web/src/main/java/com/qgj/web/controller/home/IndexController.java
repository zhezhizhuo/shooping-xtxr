package com.qgj.web.controller.home;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.category.service.ICategoryBeanService;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.*;
import com.qgj.common.core.domain.entity.CategoryBean;
import com.qgj.common.core.page.TableDataInfo;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.common.utils.StringUtils;
import com.qgj.home.service.IHomeBannerService;
import com.qgj.product.service.IProductService;
import com.qgj.system.domain.SysNotice;
import com.qgj.system.service.ISysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("home")
@Api(tags = "首页模块")
@ApiSort(1)
public class IndexController extends BaseController {

    public static  final  String CACHE_KEY_HEAD="cache:category:head";

    public static  final  String CACHE_KEY_BANNER="cache:category:banner";
    public static final String CACHE_KEY_GOODS ="cache:category:goods";

    @Resource
    private IProductService productService;
    @Resource
    private ICategoryBeanService categoryBeanService;
    @Resource
    private RedisCache redisCache;

    @Resource
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @GetMapping("/notice/list")
    @ApiOperation(value = "获取通知公告列表")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    @Resource
    IHomeBannerService  bannerService;
            //    获取二级分类下筛选的商品

     @GetMapping("/category/goods/temporary")
    @ApiOperation(value = "获取二级分类下筛选的商品", notes = "分页数据  pageNum 当前记录起始索引(第几页), pageSize 每页显示记录数(每一页多少张)   放回的数据在 rows里")
     @ApiOperationSupport(order = 1)
     public TableDataInfo getCategoryGoodsById( @RequestParam  @ApiParam("分类id") Long id) {
              startPage();
              return  getDataTable(productService.selectProductListByCategoryId(id));
     }



        //首页轮播图
        @GetMapping("/banner")
        @ApiOperation(value = "首页轮播图", notes = "作者: Zhizhuo ")
        public AjaxResult getBanner()
        {
            //redis 查询banner       CACHE_KEY_BANNER
            List<BannerEntity>  cacheData = redisCache.getCacheObject(CACHE_KEY_BANNER);
            if(StringUtils.isNotEmpty(cacheData)){
                return  AjaxResult.success(cacheData);
            }
            List<BannerEntity> banners = bannerService.selectHomeBannerList(null).stream().map(BannerEntity::new).collect(Collectors.toList());
            //把数据存入 redis
            redisCache.setCacheObject(CACHE_KEY_BANNER,banners);
            return AjaxResult.success(banners);
        }


            //首页头部商
    @GetMapping("/category/head")
    @ApiOperation(value = "首页头部商品数据(包括推荐商品)", notes = "作者: Zhizhuo ")
    public AjaxResult getCategoryHead() {
        //先查询 redis
        List<CategoryBean> cacheData = redisCache.getCacheObject(CACHE_KEY_HEAD);
        //有就直接放回
               if(StringUtils.isNotEmpty(cacheData)){
                   return  AjaxResult.success(cacheData);
               }
        //没有就查询数据库
        List<CategoryTreeEntity> categorys = categoryBeanService.buildCategoryHead(categoryBeanService.selectCategoryHead() );
        //把数据存入 redis
         redisCache.setCacheObject(CACHE_KEY_HEAD,categorys,30, TimeUnit.MINUTES);
        return AjaxResult.success(categorys);
    }


    //首页头部商
    @GetMapping("/goods")
    @ApiOperation(value = "首页头部商品数据", notes = "作者: Zhizhuo ")
    public AjaxResult getCategoryGoods() {
        //先查询 redis
        List<CategoryBean> cache = redisCache.getCacheObject(CACHE_KEY_GOODS);
        //有就直接放回
        if(StringUtils.isNotEmpty(cache)){
            return  AjaxResult.success(cache);
        }
        //没有就查询数据库
        List<CategoryTreeEntity> categorys = categoryBeanService.buildCategoryGoods(categoryBeanService.selectCategoryHead() );
        //把数据存入 redis
        redisCache.setCacheObject(CACHE_KEY_GOODS,categorys,30, TimeUnit.MINUTES);
        return AjaxResult.success(categorys);
    }



    //专题数据
    @GetMapping("/special")
    @ApiOperation(value = "专题", notes = "作者: Zhizhuo ")
    public AjaxResult getSpecial() {
//        return  AjaxResult.success(productService.selectProductListNew());
        List<ZhuanTiEntity> zhuanTiEntities = new ArrayList<>();
        ZhuanTiEntity zhuanTiEntity1 = new ZhuanTiEntity();
        zhuanTiEntity1.setId("1");
        zhuanTiEntity1.setCreator("spider");
        zhuanTiEntity1.setTitle("自发热密集焕亮 麦卢卡蜂蜜紧致面膜 60g");
        zhuanTiEntity1.setSummary("和蜂蜜一样的透明膏体，涂上脸就变成白色了，很润，不会像其他涂抹式面膜一样变干，没有味道，敏感皮肤很适合，感觉一年四季都能用，特别是干皮，而且用量很省，一小坨就可以涂整脸了，推荐！");
        zhuanTiEntity1.setLowestPrice(129.0);
        zhuanTiEntity1.setCover("https://yanxuan-item.nosdn.127.net/afd6199278a5b8fd783bc4947960fabc.jpg");
        zhuanTiEntity1.setDetailsUrl("https://yanxuan-item.nosdn.127.net/afd6199278a5b8fd783bc4947960fabc.jpg");
        zhuanTiEntity1.setCollectNum(238);
        zhuanTiEntity1.setViewNum(325);
        zhuanTiEntity1.setReplyNum(313);
        zhuanTiEntities.add(zhuanTiEntity1);
        ZhuanTiEntity zhuanTiEntity2 = new ZhuanTiEntity();
        zhuanTiEntity2.setCreator("spider");
        zhuanTiEntity2.setTitle("【CEO补贴价】桃气白日梦 10支套刷");
        zhuanTiEntity2.setId("1");
        zhuanTiEntity2.setSummary("好");
        zhuanTiEntity2.setLowestPrice(199.0);
        zhuanTiEntity2.setCover("https://yanxuan-item.nosdn.127.net/72e734dd1a4d35ce650afebdaa600b57.png");
        zhuanTiEntity2.setDetailsUrl("https://yanxuan-item.nosdn.127.net/72e734dd1a4d35ce650afebdaa600b57.png   ");
        zhuanTiEntities.add(zhuanTiEntity2);
        zhuanTiEntity1.setCollectNum(19);
        zhuanTiEntity1.setViewNum(39);
        zhuanTiEntity1.setReplyNum(9);
        ZhuanTiEntity zhuanTiEntity3 = new ZhuanTiEntity();
        zhuanTiEntity3.setId("1");
        zhuanTiEntity3.setTitle("地表强温 男式鹅绒毛领经典派克宽松羽绒服");
        zhuanTiEntity3.setCreator("spider");
        zhuanTiEntity3.setSummary("很好，很暖和(｡･ω･｡)！");
        zhuanTiEntity3.setLowestPrice(1299.0);
        zhuanTiEntity3.setCover("https://yanxuan-item.nosdn.127.net/8de8a723b44b36e2d7672387287a3e43.jpg");
        zhuanTiEntity3.setDetailsUrl("https://yanxuan-item.nosdn.127.net/8de8a723b44b36e2d7672387287a3e43.jpg");
        zhuanTiEntity1.setCollectNum(5902);
        zhuanTiEntity1.setViewNum(478);
        zhuanTiEntity1.setReplyNum(982);
        zhuanTiEntities.add(zhuanTiEntity3);

        return AjaxResult.success(zhuanTiEntities);
    }

    //人气推荐
    @GetMapping("/hot")
    @ApiOperation(value = "人气推荐", notes = "作者: Zhizhuo ")
    public AjaxResult getHots() {
        //        return  AjaxResult.success(productService.selectProductListNew());
        List<HotEntity> hots = new ArrayList<>();
        hots.add(new HotEntity(1, "https://yjy-oss-files.oss-cn-zhangjiakou.aliyuncs.com/tuxian/popular_1.jpg", "特惠推荐", "它们最实惠"));
        hots.add(new HotEntity(2, "https://yjy-oss-files.oss-cn-zhangjiakou.aliyuncs.com/tuxian/popular_2.jpg", "爆款推荐", "它们最实惠"));
        hots.add(new HotEntity(3, "https://yjy-oss-files.oss-cn-zhangjiakou.aliyuncs.com/tuxian/popular_3.jpg", "一站买全", "使用场景下精心优选"));
        hots.add(new HotEntity(4, "https://yjy-oss-files.oss-cn-zhangjiakou.aliyuncs.com/tuxian/popular_4.jpg", "热销商品", "热销商品"));
        return AjaxResult.success(hots);
    }

    //新鲜好物
    @GetMapping("/new")
    //    @ApiOperation("新鲜好物")
    @ApiOperation(value = "新鲜好物", notes = "作者: Zhizhuo ")
    public AjaxResult getNews() {
        return AjaxResult.success(productService.selectProductListNew());
    }

}
