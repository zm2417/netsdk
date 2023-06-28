package com.netsdk.boat;

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import com.sun.jna.Pointer;
import org.apache.log4j.Logger;

/**
 * 船只事件回调
 */
public class BoatAnalyzerDataCB implements NetSDKLib.fAnalyzerDataCallBack {

    private static final Logger log = Logger.getLogger(BoatAnalyzerDataCB.class);

    @Override
    public int invoke(NetSDKLib.LLong lAnalyzerHandle, int dwAlarmType,
                      Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize,
                      Pointer dwUser, int nSequence, Pointer reserved) {
        if (lAnalyzerHandle.longValue() == 0 || pAlarmInfo == null) {
            return -1;
        }

        if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSLINEDETECTION) {
            log.info("船只入侵");
            NetSDKLib.DEV_EVENT_CROSSLINE_INFO msg = new NetSDKLib.DEV_EVENT_CROSSLINE_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);

        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_CROSSREGIONDETECTION) {
            log.info("船只停留");
            NetSDKLib.DEV_EVENT_CROSSREGION_INFO msg = new NetSDKLib.DEV_EVENT_CROSSREGION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);
            if (msg.stuIntelliCommInfo.emClassType == NetSDKLib.EM_CLASS_TYPE.EM_CLASS_BOATDETECTION) {
                // 船只检测
                if (msg.bDirection == '0' || msg.bDirection == '2') {
                    // 船只出现
                    BoatFile.haveBoat();
                } else if (msg.bDirection == '1' || msg.bDirection == '3') {
                    // 船只消失
                    BoatFile.noBoat();
                }
            }
        } else if (dwAlarmType == NetSDKLib.EVENT_IVS_PARKINGDETECTION) {
            log.info("非法停车事件");
            NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO msg = new NetSDKLib.DEV_EVENT_PARKINGDETECTION_INFO();
            ToolKits.GetPointerData(pAlarmInfo, msg);

        }

        return 0;
    }


}
