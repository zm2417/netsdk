package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;

/**
 * @author 251823
 * @description 事件类型EVENT_IVS_PLAY_MOBILEPHONE(玩手机事件)对应的数据块描述信息
 * @date 2021/09/27
 */
public class DEV_EVENT_PLAY_MOBILEPHONE_INFO extends NetSDKLib.SdkStructure{
	/**
     *  通道号
     */
    public int nChannelID;

    /**
     *  1:开始 2:停止
     */
    public int nAction;

    /**
     *  事件名称
     */
    public byte[] szName = new byte[128];

    /**
     *  时间戳(单位是毫秒)
     */
    public double PTS;

    /**
     *  事件发生的时间
     */
    public NET_TIME_EX UTC;

    /**
     *  事件ID
     */
    public int nEventID;

    /**
     *  智能事件规则编号，用于标示哪个规则触发的事件
     */
    public int nRuleID;

    /**
     *  智能事件所属大类,参考枚举 EM_CLASS_TYPE
     */
    public int emClassType;

    /**
     *  事件对应文件信息 
     */
    public NetSDKLib.NET_EVENT_FILE_INFO stuFileInfo;

    /**
     *  事件触发的预置点号,从1开始
     */
    public int nPresetID;

    /**
     *  检测区域顶点数
     */
    public int nDetectRegionNum;

    /**
     *  检测区域,[0,8191]
     */
    public NetSDKLib.NET_POINT[] stuDetectRegion = (NetSDKLib.NET_POINT[]) new NetSDKLib.NET_POINT().toArray(20);
    /**
     *  检测到的物体个数
     */
    public int nObjectNum;

    /**
     *  检测到的物体
     */
    public NetSDKLib.NET_MSG_OBJECT[] stuObjects = (NetSDKLib.NET_MSG_OBJECT[]) new NetSDKLib.NET_MSG_OBJECT().toArray(128);
    /**
     *  stuSceneImage 是否有效
     */
    public int bSceneImage;

    /**
     *  全景广角图
     */
    public NetSDKLib.SCENE_IMAGE_INFO_EX stuSceneImage;

    /**
     *  GPS信息
     */
    public NetSDKLib.NET_GPS_INFO stuGPSInfo = new NetSDKLib.NET_GPS_INFO();

    /**
     *  违章关联视频FTP上传路径
     */
    public byte					szVideoPath[] = new byte[256];
    
	/**
	 * 图片信息数组,结构体NET_IMAGE_INFO_EX2数组指针
	 */
	public Pointer pstuImageInfo;
	/**
	 * 图片信息个数
	 */
	public int nImageInfoNum;
	/**
	 * 违章关联的多个视频信息个数
	 */
	public int nRelatingVideoInfoNum;

	/**
	 * 违章关联的多个视频信息数组，最多支持16个视频
	 */
	public NET_RELATING_VIDEO_INFO[] stuRelatingVideoInfo = new NET_RELATING_VIDEO_INFO[16];
	
    /**
     * 事件公共扩展字段结构体
     */
    public NET_EVENT_INFO_EXTEND   stuEventInfoEx = new NET_EVENT_INFO_EXTEND();

    /**
     *  预留字段
     */
    public byte					byReserved[] = new byte[1012];
    
    public DEV_EVENT_PLAY_MOBILEPHONE_INFO() {
    	for (int i = 0; i < stuRelatingVideoInfo.length; i++) {
    		stuRelatingVideoInfo[i] = new NET_RELATING_VIDEO_INFO();
		}
    }
}
