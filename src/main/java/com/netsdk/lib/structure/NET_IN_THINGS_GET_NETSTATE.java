package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description CLIENT_GetThingsNetState接口输入参数
 * @date 2022/04/20 10:16:56
 */
public class NET_IN_THINGS_GET_NETSTATE extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小, 调用者必须初始化该字段
     */
    public int dwSize;

    public NET_IN_THINGS_GET_NETSTATE() {
        this.dwSize = this.size();
    }
}