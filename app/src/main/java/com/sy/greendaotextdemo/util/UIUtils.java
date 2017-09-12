package com.sy.greendaotextdemo.util;

import android.content.Context;

import com.sy.greendaotextdemo.app.MyApplication;

/**
 * 创建时间： 2017/9/11 0011.
 * 编写人：xutailian
 * 功能描述：
 */

public class UIUtils {
    /**
     * @return	全局的上下文环境
     */
    public static Context getContext(){
        return MyApplication.getContext();
    }
}
