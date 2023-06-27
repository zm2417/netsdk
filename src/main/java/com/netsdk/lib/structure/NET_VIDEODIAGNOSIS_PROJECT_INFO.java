package com.netsdk.lib.structure;

import com.netsdk.lib.NetSDKLib.SdkStructure;
import com.sun.jna.Pointer;

public class NET_VIDEODIAGNOSIS_PROJECT_INFO  extends SdkStructure{
	public int					dwSize;	
	public byte[]					szProjectName= new byte[260];		// 计划名称
	public int						nTotalTaskNum;					// 调用者分配任务列表个数, 根据能力集获取
	public int						nReturnTaskNum;					// 返回实际任务列表个数
	public Pointer		pstProjectTasks;				// 任务列表 , 调用者分配内存nTotalTaskNum个
	
	public NET_VIDEODIAGNOSIS_PROJECT_INFO() {
		 this.dwSize = this.size();
	}
}
