
【Overview】
1. Java library path:   src\main\java\com\netsdk\lib
                        NetSDKLib.java  Java library
						ToolKits.java   Tools file
						Utils.java      Load different dynamic libraries according to different platforms
						
						All SDK callbacks are child threads. 
						Do not refresh the UI or do some time-consuming operations in the callback. 
						It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system.
						
2. Dynamic library path:
                         libs\win32      win32 dynamic library
                         libs\win64      win64 dynamic library
                         libs\linux32    linux32 dynamic library
                         libs\linux64    linux64 dynamic library
                         libs\mac64      mac64 dynamic library
						 
						 The lib package needs to be added to the resource resource
						 
						 You need to add the name of the C dynamic library that needs to be loaded under different platforms in dynamic-lib-load.xml.
						 
						 run_win32.bat win32 run script, run jar package, corresponding to jdk 1.6, this script specifies jdk
                         run_win64.bat win64 run script, run jar package, corresponding to jdk 1.8, this script specifies jdk
						 
						 The graphical interface is not supported in the Linux environment;
						 only the streaming function is supported (only the corresponding code stream information can be printed on the console);
						 
3. Chinese and English configuration files: res\

4. Demo path:

             Active registration: src\main\java\com\netsdk\demo\frame\AutoRegister

             Face to open the door: src\main\java\com\netsdk\demo\frame\Gate

             Alarm monitoring: src\main\java\com\netsdk\demo\frame\AlarmListen.java

             Capture picture: src\main\java\com\netsdk\demo\frame\CapturePicture.java

             Device control: src\main\java\com\netsdk\demo\frame\DeviceControl.java

             Device search and initialization: src\main\java\com\netsdk\demo\frame\DeviceSearchAndInit.java

             Download the video: src\main\java\com\netsdk\demo\frame\DownLoadRecord.java

             PTZ: src\main\java\com\netsdk\demo\frame\PTZControl.java

             Real-time preview: src\main\java\com\netsdk\demo\frame\RealPlay.java

             Intercom: src\main\java\com\netsdk\demo\frame\Talk.java

             Intelligent transportation: src\main\java\com\netsdk\demo\frame\TrafficEvent.java

             Thermal imaging: src\main\java\com\netsdk\demo\frame\ThermalCamera.java

             VTO:src\main\java\com\netsdk\demo\VTO

             Dot matrix screen configuration: src\main\java\com\netsdk\demo\frame\FaceRecognition\matrixScreen.java
			 
			 Dynamic ring host: src\main\java\com\netsdk\demo\frame\scada\SCADADemo.java

             The above is the interface realization of the function, the interface realization path: src\main\java\com\netsdk\demo\module\
			 
5. Run the jar package: target\

6. Development Tools Ecplise

7. Error code corresponding file: src\main\java\com\netsdk\common\ErrorCode.java

8. package.bat pom.xml packaging script

9. Version 5.4.0 of jna.jar

///////////////////////////////////////////////// /////////////////
[Device search and initialization]
1. Function summary:
    Device search and device initialization functions.
   
2. The NetSDK interfaces involved in the demo are as follows:
    1) Device multicast and broadcast search
          Start search interface: CLIENT_StartSearchDevices
          Stop search interface: CLIENT_StopSearchDevices
    2) Device IP unicast search
          Start search interface: CLIENT_SearchDevicesByIPs
          Stop search interface: CLIENT_StopSearchDevices

3. Matters needing attention:
    CLIENT_SearchDevicesByIPs interface searches at most 256 devices each time
   
4. Related interfaces:
    CLIENT_Init initialize NetSDK
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    fSearchDevicesCB device search callback
	
///////////////////////////////////////////////// /////////////////
【Active Registration】
1. Function summary:
   Active registration is mainly used to log in to devices in batches.
   This demo integrates real-time preview, talkback, and picture capture functions.
   
2. The NetSDK interfaces involved in the demo are as follows:
   1) Active registration function
         Open the service interface: CLIENT_ListenServer
         Stop service interface: CLIENT_StopListenServer
   2) Active registration login/logout function
         Login interface: CLIENT_LoginWithHighLevelSecurity
         Logout interface: CLIENT_Logout
   3) Real-time preview function
         Start preview interface: CLIENT_RealPlayEx
         Stop preview interface: CLIENT_StopRealPlayEx
   4) Snapshot function
         Set the snapshot callback interface: CLIENT_SetSnapRevCallBack
         Remote snapshot interface: CLIENT_SnapPictureEx
   5) Intercom function
         Set mode interface: CLIENT_SetDeviceMode
         Start talk interface: CLIENT_StartTalkEx
         Stop talkback interface: CLIENT_StopTalkEx
         PC recording interface: CLIENT_RecordStart
         End the recording interface of the PC: CLIENT_RecordStop
         Send the received sound card data detected by the local PC to the device interface: CLIENT_TalkSendData
         Send the received voice data from the device to the SDK decoding and playback interface: CLIENT_AudioDecEx
   6) Configure the device to actively register information
       [1]IP login/logout device function
              Login interface: CLIENT_LoginWithHighLevelSecurity
              Logout interface: CLIENT_Logout
       [2] Registration information configuration function
              Get the active registration information of the device: CLIENT_GetNewDevConfig CLIENT_ParseData Corresponding command: NetSDKLib.CFG_CMD_DVRIP
              Set the active registration information of the device: CLIENT_PacketData CLIENT_SetNewDevConfig Corresponding command: NetSDKLib.CFG_CMD_DVRIP

3. Matters needing attention:
    1) After receiving the device information reported by the device through opening the service, you need to log in to the device.
       The difference between the active registration login device and the general IP login device is: the device ID and the login method tcpSpecCap are different when entering the parameters.
    2) The active registration information of the configuration device can be set through this Demo or through the Web.
   
4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_SetAutoReconnect Set the reconnection callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    fServiceCallBack The callback to start the service, used to receive the device information registered by the device
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
	
///////////////////////////////////////////////// /////////////////
[Face opens the door](The main adaptation equipment is ASI equipment)
1. Function summary:
   The main functions are: access control events
   Add, delete, modify, check, and empty cards
   Adding, deleting, modifying, and clearing of faces (the MD5 for face query is MD5, not picture address and picture information, so the realization of face query is meaningless and is not provided)
   
2. The NetSDK interfaces involved in the demo are as follows:
   1) Access control incident
         Start subscription interface: CLIENT_RealLoadPictureEx
         Unsubscribe interface: CLIENT_StopLoadPic

         Access control event: NetSDKLib.EVENT_IVS_ACCESS_CTL
   2) Card and face operation
      Add card information: the corresponding command in the CLIENT_ControlDevice interface: CtrlType.CTRLTYPE_CTRL_RECORDSET_INSERT The record set type entered in the parameter: EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
      Modify card information: The corresponding command in the CLIENT_ControlDevice interface: CtrlType.CTRLTYPE_CTRL_RECORDSET_UPDATE The record set type entered in the parameter: EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
      Delete card information: The corresponding command in the CLIENT_ControlDevice interface: CtrlType.CTRLTYPE_CTRL_RECORDSET_REMOVE The record set type entered in the parameter: EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
      Clear card information: the corresponding command in the CLIENT_ControlDevice interface: CtrlType.CTRLTYPE_CTRL_RECORDSET_CLEAR The record set type entered in the parameter: EM_NET_RECORD_TYPE.NET_RECORD_ACCESSCTLCARD
      Query card information: [1] Call CLIENT_FindRecord to obtain the query handle [2] Call CLIENT_FindNextRecord to cyclically query [3] When the query is over, call CLIENT_FindRecordClose to close the query

      Add face: the corresponding command in the CLIENT_FaceInfoOpreate interface: EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_ADD
      Modify the face: the corresponding command in the CLIENT_FaceInfoOpreate interface: EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_UPDATE
      Delete face: the corresponding command in the CLIENT_FaceInfoOpreate interface: EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_REMOVE
      Clear face: the corresponding command in the CLIENT_FaceInfoOpreate interface: EM_FACEINFO_OPREATE_TYPE.EM_FACEINFO_OPREATE_CLEAR

3. Matters needing attention:
   Query card information is cyclically query based on the number of queries each time (unlike Target Recognition, which is based on offset)
   
4. Related interfaces:
   CLIENT_Init initialize NetSDK
   CLIENT_Cleanup release NetSDK cache
   CLIENT_LogOpen open log
   CLIENT_LogClose close log
   fAnalyzerDataCallBack subscribes to callbacks and receives access control events
   CLIENT_LoginWithHighLevelSecurity login
   CLIENT_Logout logout
   
///////////////////////////////////////////////// /////////////////
【Dual Channel Preview】
1. Function summary:
    The main functions are: dual-channel real-time preview
   
2. The NetSDK interfaces involved in the demo are as follows:
    1) Real-time preview function
       Start preview interface: CLIENT_RealPlayEx
       Stop preview interface: CLIENT_StopRealPlayEx

3. Matters needing attention:
    nothing
   
4. Related interfaces:
    CLIENT_Init initialize NetSDK
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
	
///////////////////////////////////////////////// /////////////////
【PTZ control】
1. Function summary:
    The main functions are: real-time preview, remote capture, eight-direction control, zoom, zoom, iris
   
2. The NetSDK interfaces involved in the demo are as follows:
    1) Real-time preview function
       Start preview interface: CLIENT_RealPlayEx
       Stop preview interface: CLIENT_StopRealPlayEx
    2) Remote capture
       Set the snapshot callback interface: CLIENT_SetSnapRevCallBack
Command interface for issuing snapshots: CLIENT_SnapPictureEx
    3) Eight directions control, zoom, zoom, iris
       The CLIENT_DHPTZControlEx interface is the same, but the corresponding commands are different. Please refer to the Demo for details. The details are written in the Demo.

3. Matters needing attention:
    nothing
   
4. Related interfaces:
    CLIENT_Init initialize NetSDK
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    fSnapRev Snapshot callback, used to receive picture information
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
	
///////////////////////////////////////////////// /////////////////
【Download video】
1. Function summary:
   The main functions are: download video by time, download video by file, set the stream type of download video, and query video
   
2. The NetSDK interfaces involved in the demo are as follows:
   1) Set the stream type of the downloaded video
      Interface: CLIENT_SetDeviceMode Corresponding command: EM_USEDEV_MODE.NET_RECORD_STREAM_TYPE
   2) Download by time
      Start download interface: CLIENT_DownloadByTimeEx
      Stop download interface: CLIENT_StopDownload
   3) Download by file
      Query recording interface: CLIENT_QueryRecordFile
Start download interface: CLIENT_DownloadByTimeEx
      Stop download interface: CLIENT_StopDownload

3. Matters needing attention:
   1) Download the video format as dav
   2) To download by file, first query the video file, and then call the download by time interface to download the video
   
4. Related interfaces:
   CLIENT_Init initialize NetSDK
   CLIENT_Cleanup release NetSDK cache
   CLIENT_LogOpen open log
   CLIENT_LogClose close log
   fTimeDownLoadPosCallBack download callback, used to receive the progress of downloading video
   CLIENT_LoginWithHighLevelSecurity login
   CLIENT_Logout logout
   
///////////////////////////////////////////////// /////////////////
【smart transportation】
1. Function summary:
   The main functions are: real-time preview, manual capture, gate opening, intelligent subscription to traffic events
   
2. The NetSDK interfaces involved in the demo are as follows:
   1) Real-time preview function
        Start preview interface: CLIENT_RealPlayEx
        Stop preview interface: CLIENT_StopRealPlayEx
   2) Manual capture
        CLIENT_ControlDeviceEx corresponding command: CtrlType.CTRLTYPE_MANUAL_SNAP
   3) Opening gates at entrances and exits
        CLIENT_ControlDeviceEx corresponding command: CtrlType.CTRLTYPE_CTRL_OPEN_STROBE
   4) Subscribe to traffic events
        Start subscription interface: CLIENT_RealLoadPictureEx
Stop subscribing interface: CLIENT_StopLoadPic

3. Matters needing attention:
    Calling the manual capture interface will trigger the manual capture event
   
4. Related interfaces:
   CLIENT_Init initialize NetSDK
   CLIENT_Cleanup release NetSDK cache
   CLIENT_LogOpen open log
   CLIENT_LogClose close log
   fAnalyzerDataCallBack subscribes to callbacks to receive various events of intelligent transportation
   CLIENT_LoginWithHighLevelSecurity login
   CLIENT_Logout logout
   
////////////////////////////////////////////////// /////////////////
【Voice Intercom Function】
1. Function summary:
     The main functions are: direct intercom, forward mode intercom

2. The NetSDK interfaces involved in the demo are as follows:
     1) Set device intercom mode: CLIENT_SetDeviceMode
         You need to set up several intercom modes before intercom:
         Set the corresponding command of the voice intercom encoding format: NetSDKLib.EM_USEDEV_MODE.NET_TALK_ENCODE_TYPE
         The corresponding command for setting the voice intercom parameters: NetSDKLib.EM_USEDEV_MODE.NET_TALK_SPEAK_PARAM
         Set whether the intercom is in forwarding mode. Corresponding command: NetSDKLib.EM_USEDEV_MODE.NET_TALK_TRANSFER_MODE
         The corresponding command to set the forwarding channel in forwarding mode: NetSDKLib.EM_USEDEV_MODE.NET_TALK_TALK_CHANNEL
     2) Send the user's audio data to the device: CLIENT_TalkSendData
     3) Decoding the audio data sent by the device: CLIENT_AudioDecEx
     4) Initiate a voice intercom request to the device: CLIENT_StartTalkEx
         Intercom callback implements the pfAudioDataCallBack interface
         In the callback, byAudioFlag is 0 for CLIENT_TalkSendData, and for 1 CLIENT_AudioDecEx to process audio data
     5) Start recording on PC: CLIENT_RecordStart
     6) End the recording on the PC: CLIENT_RecordStop
     7) Stop voice intercom: CLIENT_StopTalkEx

3. Matters needing attention:
       All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
      CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
      CLIENT_SetNetworkParam Set login network environment
      CLIENT_Cleanup release NetSDK cache
      CLIENT_LogOpen open log
      CLIENT_LogClose close log
      CLIENT_LoginWithHighLevelSecurity login
      CLIENT_Logout logout
	  
///////////////////////////////////////////////// /////////////////
【Equipment control function】
1. Function summary:
     The main functions are: set restart, get device current time, set device time

2. The NetSDK interfaces involved in the demo are as follows:
     1) Device control: CLIENT_ControlDevice
When the control type (parameter emType) is CtrlType.CTRLTYPE_CTRL_REBOOT, a device restart command is issued
     2) Set the current time of the device: CLIENT_SetupDeviceTime
     3) Query the current time of the device: CLIENT_QueryDeviceTime

3. Matters needing attention:
     Nothing

4. Related interfaces:
     CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
     CLIENT_SetNetworkParam Set login network environment
     CLIENT_Cleanup release NetSDK cache
     CLIENT_LogOpen open log
     CLIENT_LogClose close log
     CLIENT_LoginWithHighLevelSecurity login
     CLIENT_Logout logout
	 
///////////////////////////////////////////////// /////////////////
【Capture function】
1. Function summary:
     The main functions are: real-time preview, capture (including local capture, remote capture and timing capture)

2. The NetSDK interfaces involved in the demo are as follows:
     1) Real-time preview function
          Start preview interface: CLIENT_RealPlayEx
          Stop preview interface: CLIENT_StopRealPlayEx
     2) Snapshot function
          Local capture: CLIENT_CapturePictureEx
          Set the snapshot callback: CLIENT_SetSnapRevCallBack
          Callback to implement the fSnapRev interface
          Remote snapshot, timing snapshot, stop timing snapshot: CLIENT_SnapPictureEx
          Capture mode: -1: means stop capturing, 0: request one frame (remote capture), 1: means send request regularly

3. Matters needing attention:
     Local capture requires real-time preview first
     All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
     CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
     CLIENT_SetNetworkParam Set login network environment
     CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
     CLIENT_Cleanup release NetSDK cache
     CLIENT_LogOpen open log
     CLIENT_LogClose close log
     CLIENT_LoginWithHighLevelSecurity login
     CLIENT_Logout logout
	 
///////////////////////////////////////////////// /////////////////
【Alarm monitoring function】
1. Function summary:
     The main functions are: external alarm, motion detection, video occlusion, video loss, hard disk full, hard disk failure alarm report
2. The NetSDK interfaces involved in the demo are as follows:
     1) Set alarm callback: CLIENT_SetDVRMessCallBack
        Callback implements the fMessCallBack interface
     2) Subscribe to the alarm to the device: CLIENT_StartListenEx
     3) Stop subscribing to the alarm: CLIENT_StopListen

3. Matters needing attention:
     All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout


///////////////////////////////////////////////// /////////////////
【Thermal imaging function】
1. Function summary:
   The main functions are: measurement point parameter query, measurement item parameter query, thermal imaging temperature query, temperature distribution data acquisition and storage

2. The NetSDK interfaces involved in the demo are as follows:
   1) Query the parameter value of the measurement point: CLIENT_QueryDevInfo
        Corresponding enumeration: NetSDKLib.EM_QUERY_DEV_INFO.RADIOMETRY_POINT_TEMPER
   2) Query the parameter value of the measurement item: CLIENT_QueryDevInfo
        Corresponding enumeration: NetSDKLib.EM_QUERY_DEV_INFO.RADIOMETRY_TEMPER
   3) Query thermal imaging temperature (enumeration value is NetSDKLib.EM_FIND.RADIOMETRY):
        Start query: CLIENT_StartFind
        Continue to query: CLIENT_DoFind
        End query: CLIENT_StopFind
   4) Acquisition of temperature distribution data
        Subscription: CLIENT_RadiometryAttach
        Notification to start getting heat map data: CLIENT_RadiometryFetch
        Heat map data decompression and conversion: CLIENT_RadiometryDataParse
        Unsubscribe: CLIENT_RadiometryDetach

3. Matters needing attention:
      All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
      CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
      CLIENT_SetNetworkParam Set login network environment
      CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
      CLIENT_Cleanup release NetSDK cache
      CLIENT_LogOpen open log
      CLIENT_LogClose close log
      CLIENT_LoginWithHighLevelSecurity login
      CLIENT_Logout logout
	  
///////////////////////////////////////////////// /////////////////
[Dot matrix screen download function]
old demo :
1. Function summary: Dot matrix screen information delivery

2. The NetSDK interfaces involved in the demo are as follows:
    1) Interface for issuing dot matrix screen information: CLIENT_ControlDevice
        Corresponding enumeration: CTRLTYPE_CTRL_SET_PARK_INFO

3. Matters needing attention:
    All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as global static to prevent it from being recycled by the system

4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
	
new demo :
1. Function summary: Dot matrix screen information delivery

2. The NetSDK interfaces involved in the demo are as follows:
    1) Interface for issuing dot matrix screen information: CLIENT_ControlDeviceEx
        Corresponding enumeration: CTRLTYPE_CTRL_SET_PARK_INFO

3. Matters needing attention:
    All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as global static to prevent it from being recycled by the system

4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
///////////////////////////////////////////////// /////////////////
【VTO function】
1. Function summary:
     The main functions are: card operation, door opening and closing, voice call, streaming, fingerprint collection, event reporting

2. The NetSDK interfaces involved in the demo are as follows:
     1) Query
        CLIENT_FindRecordClose //End query
        CLIENT_FindRecord//Start query record
        CLIENT_FindNextRecord//Query record
        CLIENT_QueryDevState//Get fingerprint information
     2) Card operation
        CLIENT_ControlDevice//Card information operation
        CLIENT_FaceInfoOpreate//Face operation
     3) Open and close
        CLIENT_ControlDeviceEx//Open and close the door
     4) Voice call
        CLIENT_SetDeviceMode// Set voice intercom encoding format
        CLIENT_SetDeviceMode// Set voice intercom propaganda parameters, set whether the voice intercom is in forwarding mode
        CLIENT_StartTalkEx//Start voice call
        CLIENT_StopTalkEx//Stop talking
     5) Pull flow
        CLIENT_RealPlayEx//Real-time preview
        CLIENT_StopRealPlayEx//Stop preview
     6) Collect fingerprints
        CLIENT_SetDVRMessCallBack//Set callback function
        CLIENT_StartListenEx//Subscribe to alarm information, obtain fingerprint data through fingerprint events
     7) Incident reporting
        CLIENT_StartListenEx//Subscribe to common alarm events
        CLIENT_RealloadPicEx//Subscribe to smart alarm events

3. Matters needing attention:
     All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
     CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
     CLIENT_SetNetworkParam Set login network environment
     CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
     CLIENT_Cleanup release NetSDK cache
     CLIENT_LogOpen open log
     CLIENT_LogClose close log
     CLIENT_LoginWithHighLevelSecurity login
     CLIENT_Logout logout
	 
///////////////////////////////////////////////// /////////////////
[Passenger flow statistics function]
1. Function summary:
    The main functions are: passenger flow statistics

2. The NetSDK interfaces involved in the demo are as follows:
   1) Customer flow statistics subscription: CLIENT_AttachVideoStatSummary
   2) Cancel customer traffic statistics subscription: CLIENT_DetachVideoStatSummary
   3) Device control interface: CLIENT_ControlDevice
             Corresponding enumeration value: CtrlType.CTRLTYPE_CTRL_CLEAR_SECTION_STAT
   4) Passenger flow statistics interface callback: fVideoStatSumCallBack
   
3. Matters needing attention:
    All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout
///////////////////////////////////////////////// /////////////////
[Dynamic ring host function]
1. Function summary:
The main functions are: obtain equipment information, obtain equipment point information, subscribe to monitoring point alarms, subscribe to monitoring point information, and subscribe to common alarms.

2. The NetSDK interfaces involved in the demo are as follows:
    1) Obtain device information:
       CLIENT_QueryDevState Get the ID of the external device connected to the current host
    2) Obtain equipment point information:
       CLIENT_SCADAGetAttributeInfo Get device point information
    3) Subscribe to the monitoring point alarm:
       CLIENT_SCADAAlarmAttachInfo Subscribe to monitoring point alarm information
       CLIENT_SCADAAlarmDetachInfo Unsubscribe from monitoring point alarm information
    4) Subscribe to monitoring point information: fVideoStatSumCallBack
       CLIENT_SCADAAttachInfo Subscribe to monitoring point information
       CLIENT_SCADADetachInfo cancel monitoring point information subscription
    5) Subscribe to common alarms
       CLIENT_SetDVRMessCallBack Set alarm callback function interface
       CLIENT_StartListenEx subscription alarm extension interface
       CLIENT_StopListen stop subscribing to alarms
   
3. Matters needing attention:
    All SDK callbacks are child threads, and JNA is a weak reference. It is recommended that the callback function object be written as a global static to prevent it from being recycled by the system

4. Related interfaces:
    CLIENT_Init initializes NetSDK and sets the disconnection callback fDisConnect. When the device is disconnected, the callback will receive a message
    CLIENT_SetNetworkParam Set login network environment
    CLIENT_SetAutoReconnect Set the reconnect callback fHaveReConnect. When the device is disconnected, it will send a heartbeat packet to the device and automatically connect to the device. When the reconnection is successful, the callback will receive a message
    CLIENT_Cleanup release NetSDK cache
    CLIENT_LogOpen open log
    CLIENT_LogClose close log
    CLIENT_LoginWithHighLevelSecurity login
    CLIENT_Logout logout