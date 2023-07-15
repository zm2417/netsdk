package com.netsdk.boat;

import com.netsdk.common.*;
import com.netsdk.demo.frame.Gate.CardManegerDialog;
import com.netsdk.demo.module.GateModule;
import com.netsdk.demo.module.LoginModule;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * 船只检测
 */
public class BoatFrame extends JFrame {

    private static final Logger log = Logger.getLogger(BoatFrame.class);

    private static final long serialVersionUID = 1L;

    // 获取界面窗口
    private static JFrame frame = new JFrame();

    // 设备断线通知回调
    private static BoatDisConnect disConnect = new BoatDisConnect(frame);

    // 网络连接恢复
    private static BoatHaveReConnect haveReConnect = new BoatHaveReConnect(frame);

    // 订阅句柄
    public static NetSDKLib.LLong m_hAttachHandle = new NetSDKLib.LLong(0);

    private Vector<String> chnList = new Vector<String>();

    private BoatAnalyzerDataCB analyzerCallback = new BoatAnalyzerDataCB();

    private java.awt.Component target = this;

    private boolean isAttach = false;

    public static final String title = "船只检测";

    /*
     * 登录控件
     */
    private LoginPanel loginPanel;

    private JComboBox chnComboBox;
    private JButton attachBtn;
    private JButton detachBtn;

    public BoatFrame() {

        setTitle(title);
        setLayout(new BorderLayout());
        pack();
        setSize(800, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        LoginModule.init(disConnect, haveReConnect);   // 打开工程，初始化

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        loginPanel = new LoginPanel();
        GatePanel gatePanel = new GatePanel();


        add(loginPanel, BorderLayout.NORTH);
        add(gatePanel, BorderLayout.CENTER);


        loginPanel.addLoginBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginPanel.checkLoginText()) {
                    if (login()) {
                        frame = ToolKits.getFrame(e);
                        frame.setTitle(Res.string().getGate() + " : " + Res.string().getOnline());
                    }
                }
            }
        });

        loginPanel.addLogoutBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle(Res.string().getGate());
                logout();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GateModule.stopRealLoadPic(m_hAttachHandle);
                LoginModule.logout();
                LoginModule.cleanup();   // 关闭工程，释放资源

                dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FunctionList demo = new FunctionList();
                        demo.setVisible(true);
                    }
                });
            }
        });
    }

    public void startBoatFrame() {
        loginPanel.loginBtn.doClick();
        attachBtn.doClick();
    }

    // 登录
    public boolean login() {
        int loginNumber = 0;
        while (true) {
            loginNumber++;
            if (LoginModule.login(loginPanel.ipTextArea.getText(),
                    Integer.parseInt(loginPanel.portTextArea.getText()),
                    loginPanel.nameTextArea.getText(),
                    new String(loginPanel.passwordTextArea.getPassword()))) {

                for (int i = 1; i < LoginModule.m_stDeviceInfo.byChanNum + 1; i++) {
                    chnList.add(Res.string().getChannel() + " " + String.valueOf(i));
                }

                // 登陆成功，将通道添加到控件
                chnComboBox.setModel(new DefaultComboBoxModel(chnList));

                loginPanel.setButtonEnable(true);
                setEnable(true);

                return true;
            } else {
                log.info("连接失败");
                setTitle(title + "连接失败,等待5秒再次尝试连接第" + loginNumber + "次");
                try {
                    Thread.sleep(5 * 1000);
                } catch (Exception e) {
                    log.error("thread sleep error", e);
                }
                log.info("尝试连接第" + loginNumber + "次");
//                JOptionPane.showMessageDialog(null, Res.string().getLoginFailed() + ", " + ToolKits.getErrorCodeShow() + "尝试第" + loginNumber + "次连接", Res.string().getErrorMessage(), JOptionPane.ERROR_MESSAGE);
//                return false;
            }
        }
//        if (LoginModule.login(loginPanel.ipTextArea.getText(),
//                Integer.parseInt(loginPanel.portTextArea.getText()),
//                loginPanel.nameTextArea.getText(),
//                new String(loginPanel.passwordTextArea.getPassword()))) {
//
//            for (int i = 1; i < LoginModule.m_stDeviceInfo.byChanNum + 1; i++) {
//                chnList.add(Res.string().getChannel() + " " + String.valueOf(i));
//            }
//
//            // 登陆成功，将通道添加到控件
//            chnComboBox.setModel(new DefaultComboBoxModel(chnList));
//
//            loginPanel.setButtonEnable(true);
//            setEnable(true);
//
//        } else {
//            JOptionPane.showMessageDialog(null, Res.string().getLoginFailed() + ", " + ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(), JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
    }

    // 登出
    public void logout() {
        GateModule.stopRealLoadPic(m_hAttachHandle);
        LoginModule.logout();

        loginPanel.setButtonEnable(false);

        for (int i = 0; i < LoginModule.m_stDeviceInfo.byChanNum; i++) {
            chnList.clear();
        }

        chnComboBox.setModel(new DefaultComboBoxModel());
        setEnable(false);
        detachBtn.setEnabled(false);

        isAttach = false;

        clearPanel();
    }

    /**
     * 闸机界面面板
     */
    private class GatePanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public GatePanel() {
            BorderEx.set(this, "", 4);
            setLayout(new BorderLayout());

            JPanel gateOperatePanel = new JPanel();
            JPanel gateShowPanel = new JPanel();

            add(gateOperatePanel, BorderLayout.WEST);
            add(gateShowPanel, BorderLayout.CENTER);

            /**
             *  闸机操作面板
             */
            gateOperatePanel.setLayout(new BorderLayout());
            gateOperatePanel.setPreferredSize(new Dimension(250, 70));

            JPanel channelPanel = new JPanel();
            JPanel operatePanel = new JPanel();
            gateOperatePanel.add(channelPanel, BorderLayout.NORTH);
            gateOperatePanel.add(operatePanel, BorderLayout.CENTER);

            // 通道面板
            channelPanel.setBorder(BorderFactory.createTitledBorder(""));
            channelPanel.setPreferredSize(new Dimension(220, 70));
            channelPanel.setLayout(new FlowLayout());

            JLabel channelLabel = new JLabel(Res.string().getChannel());
            chnComboBox = new JComboBox();

            chnComboBox.setPreferredSize(new Dimension(100, 20));

            channelPanel.add(channelLabel);
            channelPanel.add(chnComboBox);

            // 按钮面板
            operatePanel.setBorder(BorderFactory.createTitledBorder(Res.string().getOperate()));
            operatePanel.setLayout(new FlowLayout());

            attachBtn = new JButton(Res.string().getAttach());
            detachBtn = new JButton(Res.string().getDetach());
            JLabel nullJLabel = new JLabel("");

            nullJLabel.setPreferredSize(new Dimension(205, 40));
            attachBtn.setPreferredSize(new Dimension(100, 20));
            detachBtn.setPreferredSize(new Dimension(100, 20));

            operatePanel.add(attachBtn);
            operatePanel.add(detachBtn);
            operatePanel.add(nullJLabel);

            setEnable(false);
            detachBtn.setEnabled(false);

            /**
             * 闸机订阅展示面板
             */
            gateShowPanel.setBorder(BorderFactory.createTitledBorder(""));
            gateShowPanel.setLayout(new BorderLayout());

            JPanel cardInfoPanel = new JPanel();

            gateShowPanel.add(cardInfoPanel, BorderLayout.CENTER);

            //
            cardInfoPanel.setLayout(new FlowLayout());


            Dimension dimension = new Dimension();
            dimension.width = 150;
            dimension.height = 20;

            setOnClickListener();
        }
    }

    // 监听
    private void setOnClickListener() {
        // 订阅
        attachBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
//                m_hAttachHandle = GateModule.realLoadPic(chnComboBox.getSelectedIndex(), analyzerCallback);
//                GateModule.realLoadPic(1, analyzerCallback);
                // channelId = -1：订阅所有通道
                m_hAttachHandle = GateModule.realLoadPic(-1, analyzerCallback);
                if (m_hAttachHandle.longValue() != 0) {
                    isAttach = true;
                    attachBtn.setEnabled(false);
                    detachBtn.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, ToolKits.getErrorCodeShow(), Res.string().getErrorMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 取消订阅
        detachBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                GateModule.stopRealLoadPic(m_hAttachHandle);
                synchronized (this) {
                    isAttach = false;
                }
                attachBtn.setEnabled(true);
                detachBtn.setEnabled(false);

                clearPanel();
            }
        });

    }

    private void setEnable(boolean bln) {
        chnComboBox.setEnabled(bln);
        attachBtn.setEnabled(bln);
    }

    private void clearPanel() {
    }

    class AccessEvent extends AWTEvent {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 1;

        private BufferedImage gateBufferedImage = null;
        private NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO msg = null;

        public AccessEvent(Object target,
                           BufferedImage gateBufferedImage,
                           NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO msg) {
            super(target, EVENT_ID);
            this.gateBufferedImage = gateBufferedImage;
            this.msg = msg;
        }

        public BufferedImage getGateBufferedImage() {
            return gateBufferedImage;
        }

        public NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO getAccessInfo() {
            return msg;
        }
    }

    @Override
    protected void processEvent(AWTEvent event) {
        if (event instanceof AccessEvent) {  // 门禁事件处理
            AccessEvent ev = (AccessEvent) event;

            BufferedImage gateBufferedImage = ev.getGateBufferedImage();
            NetSDKLib.DEV_EVENT_ACCESS_CTL_INFO msg = ev.getAccessInfo();

            if (!isAttach) {
                return;
            }

        } else {
            super.processEvent(event);
        }
    }

}
