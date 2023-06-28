package com.netsdk.boat;

import com.netsdk.common.Res;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
import org.apache.log4j.Logger;

import javax.swing.*;

public class BoatHaveReConnect implements NetSDKLib.fHaveReConnect {

    private static final Logger log = Logger.getLogger(BoatHaveReConnect.class);

    private static JFrame frame;

    public BoatHaveReConnect(JFrame jFrame) {
        frame = jFrame;
    }

    @Override
    public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) {
        log.info(String.format("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort));

        // 重连提示
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setTitle(BoatFrame.title + " : " + Res.string().getOnline());
            }
        });
    }
}
