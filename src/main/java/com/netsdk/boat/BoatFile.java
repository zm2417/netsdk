package com.netsdk.boat;

import org.apache.log4j.Logger;

import java.io.File;
import java.time.LocalDateTime;

public class BoatFile {

    private static final Logger log = Logger.getLogger(BoatFile.class);

    private static final String FILE_PATH = "C:\\船只\\";
    private static final String HAVE_BOAT = FILE_PATH + "船只存在.txt";
    private static final String NO_BOAT = FILE_PATH + "船只离开.TXT";

    public static LocalDateTime lastBoatWarn = null;

    public static synchronized void haveBoat() {
        lastBoatWarn = LocalDateTime.now();
        // 船只出现
        File file = new File(HAVE_BOAT);
        File noBoat = new File(NO_BOAT);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (noBoat.exists()) {
                noBoat.delete();
            }
            log.info("创建" + HAVE_BOAT + "成功");
        } catch (Exception e) {
            log.error("创建" + HAVE_BOAT + "失败", e);
        }
    }

    public static synchronized void noBoat() {
        // 船只消失
        File file = new File(NO_BOAT);
        File haveBoat = new File(HAVE_BOAT);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (haveBoat.exists()) {
                haveBoat.delete();
            }
            log.info("创建" + NO_BOAT + "成功");
        } catch (Exception e) {
            log.error("创建" + NO_BOAT + "失败", e);
        }
    }

}
