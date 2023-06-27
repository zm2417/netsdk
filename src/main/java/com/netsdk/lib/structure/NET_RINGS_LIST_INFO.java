package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.structure.NET_BARRIER_LIST_INFO;

/**
 * @author 251823
 * @description 环设备信息
 * @date 2021/09/02
 */
public class NET_RINGS_LIST_INFO extends NetSDKLib.SdkStructure{
	/**
     *  环编号
     */
    public int nRingNo;

    /**
     *  栅栏列表个数
     */
    public int nBarrierNum;

    /**
     *  栅栏列表
     */
    public NET_BARRIER_LIST_INFO[] stuBarrierInfo = (NET_BARRIER_LIST_INFO[]) new NET_BARRIER_LIST_INFO().toArray(4);
    /**
     *  预留字节
     */
    public byte[] szReserved = new byte[32];
}
