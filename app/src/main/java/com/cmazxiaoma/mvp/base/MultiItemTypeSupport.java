package com.cmazxiaoma.mvp.base;

/**
 * Description: 沉梦昂志
 * Data：2017/4/10-10:06
 * Author: xiaoma
 */

public interface MultiItemTypeSupport<D> {

   int getLayoutId(int itemType);
   int getItemViewType(int position, D d);
}
