package com.netsdk.boat;

import com.netsdk.common.Res;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
import org.apache.log4j.Logger;

import javax.swing.*;

public class BoatDisConnect implements NetSDKLib.fDisConnect {

    private static final Logger log = Logger.getLogger(BoatDisConnect.class);

    private static JFrame frame;

    public BoatDisConnect(JFrame jFrame) {
        frame = jFrame;
    }

    @Override
    public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
        log.info(String.format("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort));
        // 断线提示
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setTitle(BoatFrame.title + " : " + Res.string().getDisConnectReconnecting());
            }
        });
    }
}