package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;

/**
 * @author 251823
 * @description 事件类型 EVENT_IVS_VEHICLE_DISTANCE_NEAR (安全驾驶车距过近报警事件) 对应的数据块描述信息
 * @date 2021/09/27
 */
public class DEV_EVENT_VEHICLE_DISTANCE_NEAR_INFO extends NetSDKLib.SdkStructure{
	/**
     *  事件动作, 0表示脉冲事件, 1表示持续性事件开始, 2表示持续性事件结束;
     */
    public int nAction;

    /**
     *  事件发生的时间
     */
    public NET_TIME_EX UTC;

    /**
     *  GPS信息
     */
    public NetSDKLib.NET_GPS_STATUS_INFO stuGPSStatusInfo;

    /**
     *  司机ID
     */
    public byte[] szDriverID = new byte[32];

    /**
     *  违章关联视频FTP上传路径
     */
    public byte[] szVideoPath = new byte[256];
    
	/**
	 * 违章关联的多个视频信息个数
	 */
	public int nRelatingVideoInfoNum;

	/**
	 * 违章关联的多个视频信息数组，最多支持16个视频
	 */
	public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16];

    /**
     *  预留字节
     */
    public byte[] bReserved = new byte[736];
    
    public DEV_EVENT_VEHICLE_DISTANCE_NEAR_INFO() {
    	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
    		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
		}
    }

}
