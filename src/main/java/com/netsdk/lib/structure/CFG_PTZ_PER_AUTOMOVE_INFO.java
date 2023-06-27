package com.netsdk.lib.structure;


import com.netsdk.lib.NetSDKLib;


/**
 * @创建人 291189
 * @创建时间 2021/5/28
 * @描述
 */
public class CFG_PTZ_PER_AUTOMOVE_INFO extends NetSDKLib.SdkStructure {

    /**
     *  定时动作开关标志 TRUE 开，FALSE 关
     */
    public int bEnable;

    /**
     * 时间表类型
     */
    public NetSDKLib.CFG_TIME_SCHEDULE stTimeSchedule=new NetSDKLib.CFG_TIME_SCHEDULE();


    /**
     * 定时功能 (link CFG_PTZ_FUNC_TYPE)
     */
    public int emFuncType;

    /**
     * 	巡航编号,		范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wTourMin和wTourMax
     */
    public int nTourId;

    /**
     * 自动巡迹编号,	范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wPatternMin和wPatternMax
     */
    public int	nPatternId;

    /**
     * 预置点编号,		范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wPresetMin和wPresetMax
     */

    public int	nPresetId;

    /**
     * 自动线扫编号,	范围参照CFG_PTZ_PROTOCOL_CAPS_INFO的wAutoScanMin和wAutoScanMax
     */
    public int nScanId;

    /**
     *自动归位,一段时间不操作云台，自动归位到该时间段内的动作
     */
   public  CFG_AUTO_HOMING stAutoHoming=new CFG_AUTO_HOMING();

    /**
     * 预置点快照使能,只有当emFuncType为FUNC_TYPE_PRE时才有效
     */
   public int bSnapshotEnable;

    /**
     * 预置点抓拍延时时间,单位秒,只有当emFuncType为FUNC_TYPE_PRE时才有效
     */
   public int	nSnapshortDelayTime;


}
