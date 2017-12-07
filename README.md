###ViewPager一屏显示多个页面
[CSDN博客地址具体讲解](http://blog.csdn.net/Liu_yunzhao/article/details/78198745)
[http://blog.csdn.net/Liu_yunzhao/article/details/78198745](http://blog.csdn.net/Liu_yunzhao/article/details/78198745)

使用ViewPager一般我们只需要一屏只显示一个页面，例如引导页、轮播图等。但它如何能一屏显示多个页面呢？其实很简单就是在控件的父布局或根布局添加clipChildren=false属性即可

android:clipChildren属性：是否限制子控件在其范围内显示，也就是说当子控件超出父控件规定大小时，超出部分是否显示，clipChildren默认值为true。 也就是不允许超出绘制

###效果图

![](https://github.com/liuyunzhao/ViewPageGallery/blob/master/git/vp.png)

![](https://github.com/liuyunzhao/ViewPageGallery/blob/master/git/v_all.gif)
		
![](https://github.com/liuyunzhao/ViewPageGallery/blob/master/git/v_all_fr.gif)
			
![](https://github.com/liuyunzhao/ViewPageGallery/blob/master/git/v_all_mid.gif)

##切换动画
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    public static final float MIN_SCALE = 0.8f;//原图片缩小0.8倍
    private static final float MIN_ALPHA = 0.6f;//透明度

    public void transformPage(View page, float position) {
        if (position < -1) {//[-Infinity,-1)左边显示出半个的page
            page.setAlpha(MIN_ALPHA);//设置page的透明度
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {
            if (position < 0) {//(0,-1] 第一页向左移动
                if (position < -0.2f)//最大缩小到0.8倍
                    position = -0.2f;
                page.setAlpha(1f + position*2);
                page.setScaleY(1f + position);
                page.setScaleX(1f + position);
            } else {//[1,0] 第二页向左移动 成currentItem
                if (position > 0.2)
                    position = 0.2f;
                page.setAlpha(1f -position*2);
                page.setScaleY(1f - position);
                page.setScaleX(1f - position);
            }
        } else {//(1,+Infinity]右边显示出半个的page
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}

postion主要分为

1. [-Infinity,-1)
2. [-1,1]
3. (1,+Infinity]

第一个对应左边显示一点的page，第二个对应中间显示的page，第三个对应右边显示一点的page。
第一个和第三个只需要设置最小值就可以。
第二个还需要细分为(0,-1]和[1,0]

由第一页滑动到第二页来说：
页1的position变化为：从0到-1
页2的position变化为：从1到0

**提示**

不设置setPageMargin大小两个page页也是有间距的
间距大小=page原大小*（1-缩放倍数）/2

**总结**

其实效果实现起来并不难，只要理解了clipChildren属性的作用，再自定义一个PageTransformer过渡动画，ViewPager的各种效果也就不难实现了。

当然了，如果你不想用clipChildren属性，或者说使用继承PagerAdapter的方式定义布局不方便，则可以使用Viewpager添加Fragment方式来实现。效果图和上面一样就不再放了，所有实现方式再源码中都有，感兴趣可以戳源码下载。

## **[Demo下载](https://github.com/liuyunzhao/ViewPageGallery/blob/master/git/app.apk)** ##

