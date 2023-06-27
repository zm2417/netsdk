package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

import static com.netsdk.lib.NetSDKLib.NET_COMMON_STRING_128;

/**
 * 电视墙名称 拆分自{@link NET_IN_MONITORWALL_GET_ENABLE}
 *
 * @author ： 47040
 * @since ： Created in 2020/10/19 11:08
 */
public class NET_MONITORWALL_NAME extends NetSDKLib.SdkStructure {
    /**
     * 电视墙名称
     */
    public byte[] szName = new byte[NET_COMMON_STRING_128];
}
