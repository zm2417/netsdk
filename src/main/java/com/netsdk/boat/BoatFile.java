package com.netsdk.boat;

import org.apache.log4j.Logger;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoatFile {

    private static final Logger log = Logger.getLogger(BoatFile.class);

    private static final String FILE_PATH = "C:\\船只\\";
    private static final String HAVE_BOAT = FILE_PATH + "船只存在.txt";
    private static final String NO_BOAT = FILE_PATH + "船只离开.txt";

    public static LocalDateTime lastBoatWarn = null;
    /**
     * 3分15秒
     */
    private static final Integer millis = 1000 * (3 * 60 + 15);

    public static synchronized void haveBoat() {
        // 晚上7点到11点是工作时间，才创建有船只文件
        LocalDateTime now = LocalDateTime.now();
        if (!checkWorkTime(now)) {
            return;
        }
        // 船只出现
        File file = new File(HAVE_BOAT);
        File noBoat = new File(NO_BOAT);
        try {
            if (!file.exists()) {
                lastBoatWarn = LocalDateTime.now();
                file.createNewFile();
                log.info("创建" + HAVE_BOAT + "成功");
            }
            if (noBoat.exists()) {
                noBoat.delete();
            }
        } catch (Exception e) {
            log.error("创建" + HAVE_BOAT + "失败", e);
        }
    }

    private static boolean checkWorkTime(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        if (hour < 19 || hour >= 23) {
            // 非工作时间
            return false;
        }
        return true;
    }

    public static synchronized void noBoat() {
//        lastBoatWarn = null;
        // 船只消失
        File file = new File(NO_BOAT);
        File haveBoat = new File(HAVE_BOAT);
        try {
            if (!file.exists()) {
                file.createNewFile();
                log.info("创建" + NO_BOAT + "成功");
                Thread.sleep(5 * 1000);
            }
            if (haveBoat.exists()) {
                haveBoat.delete();
            }
        } catch (Exception e) {
            log.error("创建" + NO_BOAT + "失败", e);
        }
    }

    public static synchronized void timer() {
        if (lastBoatWarn == null) {
//            System.out.println(1);
            noBoat();
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastBoatWarn, now);
        if (Math.abs(duration.toMillis()) > millis) {
//            System.out.println(2);
            // 超过指定时间没有出现有船只报警，即为无船只
            noBoat();
        }
    }

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse("2023-07-08 18:57:56", fmt);
        LocalDateTime date2 = LocalDateTime.parse("2023-07-08 19:57:56", fmt);
        LocalDateTime date3 = LocalDateTime.parse("2023-07-08 22:57:56", fmt);
        LocalDateTime date4 = LocalDateTime.parse("2023-07-08 23:57:56", fmt);
        System.out.println(checkWorkTime(date1));
        System.out.println(checkWorkTime(date2));
        System.out.println(checkWorkTime(date3));
        System.out.println(checkWorkTime(date4));
    }

}
