package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib.DH_POINT;
import com.netsdk.lib.NetSDKLib.SdkStructure;
/**
 * @description 标定区域
 * @author 119178
 * @date 2021/3/16
 */
public class NET_ANALYSE_TASK_GLOBAL_CALIBRATEAREA extends SdkStructure{
	/**
	 * 标尺线个数
	 */
	public int							nStaffs;														
	/**
	 * 标尺线  
	 */
	public	NET_STAFF_INFO[]		    stuStaffs= (NET_STAFF_INFO[])new NET_STAFF_INFO().toArray(32);	
	/**
	 * 标定多边形区域
	 */
	public	DH_POINT[]					stuCalibratePloygonArea=(DH_POINT[])new DH_POINT().toArray(20);
	/**
	 * 标定多边形区域顶点个数
	 */
	public	int							nCalibratePloygonAreaNum;  
	/**
	 * 保留字节
	 */
	public	byte[]                        byReserved = new byte[1024];												
}
