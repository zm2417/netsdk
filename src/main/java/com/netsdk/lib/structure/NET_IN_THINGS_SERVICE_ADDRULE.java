package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;

/**
 * @author 260611
 * @description 添加联动规则输入参数(对应 EM_THINGS_SERVICE_TYPE_ADDRULE)
 * @date 2022/04/20 10:50:21
 */
public class NET_IN_THINGS_SERVICE_ADDRULE extends NetSDKLib.SdkStructure {
    /**
     * 结构体大小, 调用者必须初始化该字段
     */
    public int dwSize;
    /**
     * 产品ID，全网唯一
     */
    public byte[] szProductID = new byte[64];
    /**
     * 设备ID
     */
    public byte[] szDeviceID = new byte[64];
    /**
     * 类型名称“timer”、“alarm”
     */
    public byte[] szClassName = new byte[256];
    /**
     * 联动规则内容
     */
    public NET_THINGS_SERVICE_RULECONTENT stuRuleContent = new NET_THINGS_SERVICE_RULECONTENT();

    public NET_IN_THINGS_SERVICE_ADDRULE() {
        this.dwSize = this.size();
    }
}