
【总述】
1. Java库路径： src\main\java\com\netsdk\lib
			    NetSDKLib.java  Java库
				ToolKits.java   封装小工具文件
				Utils.java      判断运行平台，指定加载库的路径文件
				
				SDK所有回调都是子线程，不要在回调里刷新UI以及做一些耗时操作。建议回调函数对象写成全局静态，防止被系统回收。
				
2. 动态库路径：
				libs\win32    win32的动态库
				libs\win64    win64的动态库
				libs\linux32  linux32的动态库
				libs\linux64  linux64的动态库
				libs\mac64    mac64的动态库
				
				lib包需要添加到resource资源中
				
				需要在dynamic-lib-load.xml中添加不同平台下需要加载的C动态库名称。
				
				run_win32.bat  win32运行脚本，运行的jar包，对应jdk 1.6，此脚本指定了jdk
				run_win64.bat  win64运行脚本，运行的jar包，对应jdk 1.8，此脚本指定了jdk
				
				Linux环境下不支持图形化界面;仅支持拉流功能(只能在控制台打印对应的码流信息);	
				
3. 中英文配置文件：res\
			   
4. demo路径： 
	
	主动注册：src\main\java\com\netsdk\demo\frame\AutoRegister	

	人脸开门(人脸闸机)：src\main\java\com\netsdk\demo\frame\Gate

	报警监听：src\main\java\com\netsdk\demo\frame\AlarmListen.java

	抓图：src\main\java\com\netsdk\demo\frame\CapturePicture.java

	设备控制：src\main\java\com\netsdk\demo\frame\DeviceControl.java

	设备搜索和初始化：src\main\java\com\netsdk\demo\frame\DeviceSearchAndInit.java

	下载录像：src\main\java\com\netsdk\demo\frame\DownLoadRecord.java

	云台：src\main\java\com\netsdk\demo\frame\PTZControl.java

	实时预览：src\main\java\com\netsdk\demo\frame\RealPlay.java

	对讲：src\main\java\com\netsdk\demo\frame\Talk.java

	智能交通：src\main\java\com\netsdk\demo\frame\TrafficEvent.java

	热成像：src\main\java\com\netsdk\demo\frame\ThermalCamera.java
		
	VTO:src\main\java\com\netsdk\demo\VTO
	
	点阵屏配置:src\main\java\com\netsdk\demo\frame\FaceRecognition\matrixScreen.java
	
	动环主机：src\main\java\com\netsdk\demo\frame\scada\SCADADemo.java

	以上是功能的界面实现，接口实现路径：src\main\java\com\netsdk\demo\module\
			
5. 运行jar包：target\ 
	
6. 开发工具Ecplise

7. 错误码对应文件：src\main\java\com\netsdk\common\ErrorCode.java
					 
8. package.bat  pom.xml 打包脚本

9. jna.jar 的版本5.4.0


///////////////////////////////////////////////////////////////////
【设备搜索和初始化】
1. 功能概要：
   设备搜索和设备初始化功能。
   
2. Demo中涉及到的NetSDK接口如下：
   1）设备组播和广播搜索
	  开始搜索接口：CLIENT_StartSearchDevices
	  停止搜索接口：CLIENT_StopSearchDevices
   2）设备IP单播搜索
      开始搜索接口：CLIENT_SearchDevicesByIPs   
	  停止搜索接口：CLIENT_StopSearchDevices
	  
3. 注意事项：
   CLIENT_SearchDevicesByIPs 接口每次最多搜索256个
   
4. 相关接口：
   CLIENT_Init      初始化NetSDK
   CLIENT_Cleanup   释放NetSDK缓存
   CLIENT_LogOpen   打开日志
   CLIENT_LogClose  关闭日志
   fSearchDevicesCB 设备搜索回调
   

///////////////////////////////////////////////////////////////////   
【主动注册】
1. 功能概要：
   主动注册主要用于批量登录设备。
   此Demo里集合了 实时预览，对讲、抓图功能。
   
2. Demo中涉及到的NetSDK接口如下：
   1）主动注册功能
	  开启服务接口：CLIENT_ListenServer
	  停止服务接口：CLIENT_StopListenServer
   2）主动注册登录/登出功能
      登录接口：CLIENT_LoginWithHighLevelSecurity   
	  登出接口：CLIENT_Logout
   3）实时预览功能
	  开始预览接口：CLIENT_RealPlayEx
	  停止预览接口：CLIENT_StopRealPlayEx
   4）抓图功能
      设置抓图回调接口：CLIENT_SetSnapRevCallBack
      远程抓图接口：CLIENT_SnapPictureEx
   5）对讲功能
      设置模式接口：CLIENT_SetDeviceMode
	  开始对讲接口：CLIENT_StartTalkEx
	  停止对讲接口：CLIENT_StopTalkEx
	  PC端录音接口：CLIENT_RecordStart
	  结束PC端录音接口：CLIENT_RecordStop
	  将收到的本地PC端检测到的声卡数据发送给设备端接口：CLIENT_TalkSendData
	  将收到的设备端发送过来的语音数据传给SDK解码播放接口：CLIENT_AudioDecEx
   6）配置设备主动注册信息
      [1]IP登录/登出设备功能
	     登录接口：CLIENT_LoginWithHighLevelSecurity   
	     登出接口：CLIENT_Logout
	  [2]注册信息配置功能
	     获取设备的主动注册信息：CLIENT_GetNewDevConfig  CLIENT_ParseData   对应命令：NetSDKLib.CFG_CMD_DVRIP
		 设置设备的主动注册信息：CLIENT_PacketData  CLIENT_SetNewDevConfig  对应命令：NetSDKLib.CFG_CMD_DVRIP
	  
3. 注意事项：
   1）在通过开启服务收到设备上报的设备信息后，需要登录设备。   
      此处的主动注册登录设备跟通用的IP登录设备的区别是：入参需要填设备ID、登录方式tcpSpecCap不同
   2）配置设备的主动注册信息可以通过本Demo设置，也可以通过Web设置。
   
4. 相关接口：
   CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
   CLIENT_Cleanup   		释放NetSDK缓存
   CLIENT_LogOpen   		打开日志
   CLIENT_LogClose  		关闭日志
   CLIENT_SetAutoReconnect  设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
   CLIENT_SetNetworkParam   设置登录网络环境
   fServiceCallBack         启动服务的回调，用于接收设备注册上来的设备信息
   CLIENT_LoginWithHighLevelSecurity		    登录   
   CLIENT_Logout		    登出
   
   
///////////////////////////////////////////////////////////////////
【人脸开门】（主要适配设备为ASI设备）
1. 功能概要：
   主要功能有：门禁事件
			   卡的增、删、改、查、清空
			   人脸的增、删、改、清空(人脸查询到的是MD5，不是图片地址和图片信息，所以人脸查询的实现没意义，不提供)
   
2. Demo中涉及到的NetSDK接口如下：
   1）门禁事件
	  开始订阅接口：CLIENT_RealLoadPictureEx
	  取消订阅接口：CLIENT_StopLoadPic
	  
	  门禁事件：NetSDKLib.EVENT_IVS_ACCESS_CTL
   2）卡和人脸操作
      添加卡信息：CLIENT_ControlDevice   接口里的对应命令：CtrlType.CTRLTYPE_CTRL_RECORDSET_INSERT   入参里的记录集类型：EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
	  修改卡信息：CLIENT_ControlDevice   接口里的对应命令：CtrlType.CTRLTYPE_CTRL_RECORDSET_UPDATE   入参里的记录集类型：EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
	  删除卡信息：CLIENT_ControlDevice   接口里的对应命令：CtrlType.CTRLTYPE_CTRL_RECORDSET_REMOVE   入参里的记录集类型：EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
	  清空卡信息：CLIENT_ControlDevice   接口里的对应命令：CtrlType.CTRLTYPE_CTRL_RECORDSET_CLEAR   入参里的记录集类型：EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
	  查询卡信息：[1]调用CLIENT_FindRecord获取查询句柄  [2]调用CLIENT_FindNextRecord循环查询   [3]查询结束，调用CLIENT_FindRecordClose关闭查询
	  
	  添加人脸：CLIENT_FaceInfoOpreate 接口里的对应命令：EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_ADD
	  修改人脸：CLIENT_FaceInfoOpreate 接口里的对应命令：EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_UPDATE
	  删除人脸：CLIENT_FaceInfoOpreate 接口里的对应命令：EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_REMOVE
	  清空人脸：CLIENT_FaceInfoOpreate 接口里的对应命令：EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_CLEAR
	  
3. 注意事项：
   查询卡信息是根据每次查询的个数循环查询(不像目标识别里，是根据偏移量)
   
4. 相关接口：
   CLIENT_Init      	  初始化NetSDK
   CLIENT_Cleanup   	  释放NetSDK缓存
   CLIENT_LogOpen   	  打开日志
   CLIENT_LogClose  	  关闭日志
   fAnalyzerDataCallBack  订阅回调，接收门禁事件
   CLIENT_LoginWithHighLevelSecurity		  登录   
   CLIENT_Logout		  登出


///////////////////////////////////////////////////////////////////
【双通道预览】
1. 功能概要：
   主要功能有：双通道实时预览
   
2. Demo中涉及到的NetSDK接口如下：
   1）实时预览功能
      开始预览接口：CLIENT_RealPlayEx
      停止预览接口：CLIENT_StopRealPlayEx
	  
3. 注意事项：
   无
   
4. 相关接口：
   CLIENT_Init      	  初始化NetSDK
   CLIENT_Cleanup   	  释放NetSDK缓存
   CLIENT_LogOpen   	  打开日志
   CLIENT_LogClose  	  关闭日志
   CLIENT_LoginWithHighLevelSecurity		  登录   
   CLIENT_Logout		  登出


///////////////////////////////////////////////////////////////////
【云台控制】
1. 功能概要：
   主要功能有：实时预览、远程抓图、八个方向控制、变倍、变焦、光圈
   
2. Demo中涉及到的NetSDK接口如下：
   1）实时预览功能
      开始预览接口：CLIENT_RealPlayEx
      停止预览接口：CLIENT_StopRealPlayEx
   2）远程抓图
      设置抓图回调接口：CLIENT_SetSnapRevCallBack
	  下发抓图命令接口：CLIENT_SnapPictureEx
   3）八个方向控制、变倍、变焦、光圈
      CLIENT_DHPTZControlEx  接口一样，对应的命令不一样，具体参考Demo，Demo里写的很详细
	  
3. 注意事项：
   无
   
4. 相关接口：
   CLIENT_Init      	  初始化NetSDK
   CLIENT_Cleanup   	  释放NetSDK缓存
   CLIENT_LogOpen   	  打开日志
   CLIENT_LogClose  	  关闭日志
   fSnapRev               抓图回调，用于接收图片信息
   CLIENT_LoginWithHighLevelSecurity		  登录   
   CLIENT_Logout		  登出


///////////////////////////////////////////////////////////////////
【下载录像】
1. 功能概要：
   主要功能有：按时间下载录像、按文件下载录像、设置下载录像的码流类型、查询录像		   
   
2. Demo中涉及到的NetSDK接口如下：
   1）设置下载录像的码流类型
      接口：CLIENT_SetDeviceMode  对应命令：EM_USEDEV_MODE.NET_RECORD_STREAM_TYPE
   2）按时间下载
      开始下载接口：CLIENT_DownloadByTimeEx
      停止下载接口：CLIENT_StopDownload
   3）按文件下载
      查询录像接口：CLIENT_QueryRecordFile
	  开始下载接口：CLIENT_DownloadByTimeEx
      停止下载接口：CLIENT_StopDownload
	  
3. 注意事项：
   1）下载录像格式为dav
   2）按文件下载，是先查询录像文件，再调用按时间下载接口下载录像
   
4. 相关接口：
   CLIENT_Init      	     初始化NetSDK
   CLIENT_Cleanup   	     释放NetSDK缓存
   CLIENT_LogOpen   	     打开日志
   CLIENT_LogClose  	     关闭日志
   fTimeDownLoadPosCallBack  下载回调，用于接收下载录像的进度
   CLIENT_LoginWithHighLevelSecurity		     登录   
   CLIENT_Logout		     登出 


///////////////////////////////////////////////////////////////////
【智能交通】
1. 功能概要：
   主要功能有：实时预览、手动抓拍、出入口开闸、智能订阅交通事件
   
2. Demo中涉及到的NetSDK接口如下：
   1）实时预览功能
      开始预览接口：CLIENT_RealPlayEx
      停止预览接口：CLIENT_StopRealPlayEx
   2）手动抓拍
      CLIENT_ControlDeviceEx  对应命令：CtrlType.CTRLTYPE_MANUAL_SNAP
   3）出入口开闸
      CLIENT_ControlDeviceEx  对应命令：CtrlType.CTRLTYPE_CTRL_OPEN_STROBE
   4）订阅交通事件
	  开始订阅接口：CLIENT_RealLoadPictureEx
	  停止订阅接口：CLIENT_StopLoadPic
	  
3. 注意事项：
   调用手动抓拍接口，会触发手动抓拍事件
   
4. 相关接口：
   CLIENT_Init      	  初始化NetSDK
   CLIENT_Cleanup   	  释放NetSDK缓存
   CLIENT_LogOpen   	  打开日志
   CLIENT_LogClose  	  关闭日志
   fAnalyzerDataCallBack  订阅回调，用于接收智能交通的各种事件
   CLIENT_LoginWithHighLevelSecurity		  登录   
   CLIENT_Logout		  登出


///////////////////////////////////////////////////////////////////   
【语音对讲功能】
1.  功能概要：
	主要功能有：直连对讲、转发模式对讲

2.  Demo中涉及到的NetSDK接口如下：
	1） 设置设备对讲模式：CLIENT_SetDeviceMode
			对讲前需要设置若干对讲方式：
				设置语音对讲编码格式对应命令：NetSDKLib.EM_USEDEV_MODE.NET_TALK_ENCODE_TYPE
				设置语音对讲喊话参数对应命令：NetSDKLib.EM_USEDEV_MODE.NET_TALK_SPEAK_PARAM
				设置对讲是否为转发模式对应命令：NetSDKLib.EM_USEDEV_MODE.NET_TALK_TRANSFER_MODE
				转发模式时设置转发通道对应命令：NetSDKLib.EM_USEDEV_MODE.NET_TALK_TALK_CHANNEL
	2） 向设备发送用户的音频数据：CLIENT_TalkSendData
	3） 对设备发来的音频数据解码：CLIENT_AudioDecEx	
	4） 向设备发起语音对讲请求：CLIENT_StartTalkEx
			对讲回调实现pfAudioDataCallBack接口
				回调中byAudioFlag为0调CLIENT_TalkSendData，为1调CLIENT_AudioDecEx处理音频数据
    5） 开始PC端录音：CLIENT_RecordStart
    6） 结束PC端录音：CLIENT_RecordStop
    7） 停止语音对讲：CLIENT_StopTalkEx

3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收

4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出


///////////////////////////////////////////////////////////////////   
【设备控制功能】
1.  功能概要：
	主要功能有：设置重启、获取设备当前时间、设置设备时间

2.  Demo中涉及到的NetSDK接口如下：
    1） 设备控制：CLIENT_ControlDevice
			控制类型（参数emType）为CtrlType.CTRLTYPE_CTRL_REBOOT时下发重启设备命令
    2） 设置设备当前时间：CLIENT_SetupDeviceTime
    3） 查询设备当前时间：CLIENT_QueryDeviceTime
		
3.  注意事项:
	无

4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出


///////////////////////////////////////////////////////////////////  
【抓图功能】
1.  功能概要：
	主要功能有：实时预览、抓图（包含本地抓图、远程抓图和定时抓图）

2.  Demo中涉及到的NetSDK接口如下：
	1） 实时预览功能
			开始预览接口：CLIENT_RealPlayEx
			停止预览接口：CLIENT_StopRealPlayEx
    2） 抓图功能
			本地抓图：CLIENT_CapturePictureEx
			设置抓图回调：CLIENT_SetSnapRevCallBack
				回调实现fSnapRev接口
			远程抓图、定时抓图、停止定时抓图：CLIENT_SnapPictureEx
				抓图模式：-1:表示停止抓图, 0：表示请求一帧（远程抓图）, 1：表示定时发送请求

3.  注意事项:
	本地抓图需要先实时预览
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收

4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出


///////////////////////////////////////////////////////////////////
【报警监听功能】
1.  功能概要：
	主要功能有：外部报警、移动侦测、视频遮挡、视频丢失、硬盘满、硬盘坏的报警上报
2.  Demo中涉及到的NetSDK接口如下：
	1） 设置报警回调：CLIENT_SetDVRMessCallBack
			回调实现fMessCallBack接口
    2） 向设备订阅报警：CLIENT_StartListenEx
	3） 停止订阅报警：CLIENT_StopListen	

3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收

4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出


///////////////////////////////////////////////////////////////////	
【热成像功能】
1.  功能概要：
	主要功能有：测温点参数查询、测温项参数查询、热成像温度查询、温度分布数据获取和保存

2. Demo中涉及到的NetSDK接口如下：
	1)  查询测温点的参数值: CLIENT_QueryDevInfo 
			对应枚举：NetSDKLib.EM_QUERY_DEV_INFO.RADIOMETRY_POINT_TEMPER
	2)  查询测温项的参数值: CLIENT_QueryDevInfo
			对应枚举：NetSDKLib.EM_QUERY_DEV_INFO.RADIOMETRY_TEMPER
	3)  查询热成像温度（枚举值为NetSDKLib.EM_FIND.RADIOMETRY）:
			开始查询：CLIENT_StartFind
			继续查询：CLIENT_DoFind
			结束查询：CLIENT_StopFind
	4)  温度分布数据获取
			订阅：CLIENT_RadiometryAttach
			通知开始获取热图数据：CLIENT_RadiometryFetch
			热图数据解压与转换：CLIENT_RadiometryDataParse
			取消订阅：CLIENT_RadiometryDetach
	
3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收

4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出
///////////////////////////////////////////////////////////////////	
【点阵屏下发功能】
老demo：
1.  功能概要：点阵屏信息下发

2. Demo中涉及到的NetSDK接口如下：
   1）下发点阵屏信息接口：CLIENT_ControlDevice
                对应枚举：CTRLTYPE_CTRL_SET_PARK_INFO
	
3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收
	
4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出	
	
	
新demo:	
1.  功能概要：点阵屏信息下发

2. Demo中涉及到的NetSDK接口如下：
   1）下发点阵屏信息接口：CLIENT_ControlDeviceEx  
                对应枚举：CTRL_SET_PARK_CONTROL_INFO
				
3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收
	
4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出	
///////////////////////////////////////////////////////////////////	
【VTO功能】	
1.  功能概要：
	主要功能有：卡操作，开门关门，语音通话，拉流，指纹采集，事件上报
	
2.  Demo中涉及到的NetSDK接口如下：
     1）查询
        CLIENT_FindRecordClose //结束查询
        CLIENT_FindRecord//开始查询记录
        CLIENT_FindNextRecord//查询记录
        CLIENT_QueryDevState//获取指纹信息
     2)卡操作
        CLIENT_ControlDevice//卡信息操作
        CLIENT_FaceInfoOpreate//人脸操作
     3)开门关门
        CLIENT_ControlDeviceEx//开关门
     4)语音通话
        CLIENT_SetDeviceMode// 设置语音对讲编码格式
        CLIENT_SetDeviceMode// 设置语音对讲喊话参数,设置语音对讲是否为转发模式
        CLIENT_StartTalkEx//开始语音通话
        CLIENT_StopTalkEx//停止通话
     5)拉流
	    CLIENT_RealPlayEx//实时预览
        CLIENT_StopRealPlayEx//停止预览 
     6)采集指纹
        CLIENT_SetDVRMessCallBack//设置回调函数
        CLIENT_StartListenEx//订阅报警信息,通过指纹事件获取指纹数据
     7)事件上报
        CLIENT_StartListenEx//订阅普通报警事件
        CLIENT_RealloadPicEx//订阅智能报警事件
		
3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收
	
4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出	
///////////////////////////////////////////////////////////////////	
【客流量统计功能】	
1.  功能概要：
	主要功能有：客流量统计
	
2. Demo中涉及到的NetSDK接口如下：
   1）客流量统计订阅：CLIENT_AttachVideoStatSummary 
   2）取消客流量统计订阅：CLIENT_DetachVideoStatSummary 
   3）设备控制接口：CLIENT_ControlDevice 
             对应的枚举值：CtrlType.CTRLTYPE_CTRL_CLEAR_SECTION_STAT 
   4) 客流量统计接口回调： fVideoStatSumCallBack
   
3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收
	
4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出
///////////////////////////////////////////////////////////////////	
【动环主机功能】
1.  功能概要：
	主要功能有：获取设备信息，获取设备点位信息，订阅监测点位报警，订阅监测点位信息，订阅普通报警。
	
2. Demo中涉及到的NetSDK接口如下：
   1）获取设备信息：
      CLIENT_QueryDevState	获取当前主机所接入的外部设备ID
   2）获取设备点位信息：
      CLIENT_SCADAGetAttributeInfo	获取设备点位信息
   3）订阅监测点位报警：
      CLIENT_SCADAAlarmAttachInfo	订阅监测点位报警信息
	  CLIENT_SCADAAlarmDetachInfo   取消订阅监测点位报警信息
   4) 订阅监测点位信息： fVideoStatSumCallBack
      CLIENT_SCADAAttachInfo	订阅监测点位信息
      CLIENT_SCADADetachInfo	取消监测点位信息订阅
   5）订阅普通报警
      CLIENT_SetDVRMessCallBack	设置报警回调函数接口
      CLIENT_StartListenEx	订阅报警扩展接口
      CLIENT_StopListen	停止订阅报警

3.  注意事项:
	SDK所有回调都是子线程，JNA是弱引用，建议回调函数对象写成全局静态，防止被系统回收
	
4.  相关接口：
	CLIENT_Init      		初始化NetSDK，并且设置断线回调fDisConnect，当设备断线后，回调里会收到信息
	CLIENT_SetNetworkParam  设置登录网络环境
	CLIENT_SetAutoReconnect 设置重连回调fHaveReConnect，当设备断线后，会向设备发送心跳包，自动连接设备，当重连成功后，回调里会收到信息
	CLIENT_Cleanup   		释放NetSDK缓存
	CLIENT_LogOpen   		打开日志
	CLIENT_LogClose  		关闭日志
	CLIENT_LoginWithHighLevelSecurity			登录   
	CLIENT_Logout			登出