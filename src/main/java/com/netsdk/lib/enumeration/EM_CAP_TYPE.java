package com.netsdk.lib.enumeration;

import com.netsdk.lib.NetSDKLib;


/**
 * 帽子类型
 * 
 * @author ： 260611
 * @since ： Created in 2021/10/19 20:21
 */
public class EM_CAP_TYPE extends NetSDKLib.SdkStructure {

    /**
     *  未知
     */
    public static final int					EM_CAP_UNKNOWN = 0;
    /**
     *  普通帽子
     */
    public static final int					EM_CAP_ORDINARY = 1;
    /**
     *  头盔
     */
    public static final int					EM_CAP_HELMET = 2;
    /**
     *  安全帽
     */
    public static final int					EM_CAP_SAFE = 3;
}