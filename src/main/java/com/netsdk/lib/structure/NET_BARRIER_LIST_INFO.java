package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.structure.NET_PHASES_INFO;

/**
 * @author 251823
 * @description 栅栏列表信息
 * @date 2021/09/02
 */
public class NET_BARRIER_LIST_INFO extends NetSDKLib.SdkStructure{
	/**
     *  栅栏编号
     */
    public int nBarrierNo;

    /**
     *  相位列表个数
     */
    public int nPhaseNum;

    /**
     *  相位列表
     */
    public NET_PHASES_INFO[] stuPhaseInfo = (NET_PHASES_INFO[]) new NET_PHASES_INFO().toArray(16);
    /**
     *  预留字节
     */
    public byte[] szReserved = new byte[32];
}
