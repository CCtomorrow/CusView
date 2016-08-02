package yong.qing.com.qimingview.uniqueid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.util.UUID;


/**
 * <b>Project:</b> oms.mmc.util <br>
 * <b>Create Date:</b> 2016/7/11 <br>
 * <b>Author:</b> Devin <br>
 * <b>Address:</b> qingyongai@linghit.com <br>
 * <b>Description:</b> 把获取设备号的方法独立出来 <br>
 */
public class DeviceIDUtil {

    /**
     * 基本目录名
     */
    public static final String WORK_BASE_DIR_NAME = "Common";

    /**
     * DEVICE_ID存放目录名
     */
    public static final String WORK_DEVICE_ID_DIR_NAME = "DeviceId";

    /**
     * 默认在SD卡的存放目录
     */
    public static final String DEFAULT_SDCARD_WORK_DIR = android.os.Environment.
            getExternalStorageDirectory().getAbsolutePath()
            + File.separator + WORK_BASE_DIR_NAME;

    /**
     * 默认的DEVICE_ID存放目录
     */
    public static final String DEFAULT_SDCARD_DEVICEID_DIR = DEFAULT_SDCARD_WORK_DIR
            + File.separator + WORK_DEVICE_ID_DIR_NAME;

    /**
     * 判断是否是无效ID
     *
     * @param id 是否以0开头以及结尾，以及为空
     * @return true无效
     */
    public static boolean invalidId(String id) {
        return TextUtils.isEmpty(id) || (id.startsWith("00000") && id.endsWith("00000"));
    }

    /**
     * 获得独一无二的Psuedo ID
     *
     * @return ID
     */
    public static String getUniquePsuedoID() {
        String serial;
        String m_szDevIDShort = "25" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 位
        try {
            //API>=9 使用serial号
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial_mmc"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        String uid = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        return MD5Util.getMD5Str(uid);
    }

    /**
     * 获取手机的IMEI值
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei;
        try {
            imei = tm.getDeviceId();
        } catch (Exception e) {
            imei = null;
        }
        return imei;
    }

    /**
     * 获取设备号的唯一码，订单系统使用
     */
    public static String getUniqueId(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        final String deviceKey = "device_id";
        String deviceId = sp.getString(deviceKey, null);
        if (invalidId(deviceId)) {
            deviceId = getIDFromSD();
            if (invalidId(deviceId)) {
                deviceId = getDeviceId(context);
                if (invalidId(deviceId)) {
                    try {
                        deviceId = Settings.Secure.getString(context.getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                    } catch (Exception e) {
                        deviceId = null;
                    }
                }
                if (invalidId(deviceId)) {
                    deviceId = getUniquePsuedoID();
                }
                if (invalidId(deviceId)) {
                    deviceId = UUID.randomUUID().toString();
                }
            }
        }
        sp.edit().putString(deviceKey, deviceId).apply();
        saveIDToSD(deviceId);
        return deviceId;
    }

    /**
     * 获取ID
     */
    public static String getIDFromSD() {
        try {
            File dir = new File(DEFAULT_SDCARD_DEVICEID_DIR);
            if (dir.exists()) {
                File[] files = dir.listFiles();
                if (files.length > 1) return null;
                File f = files[0];
                return f.getName();
            }
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    /**
     * 存储ID
     */
    public static boolean saveIDToSD(String key) {
        try {
            File dir = new File(DEFAULT_SDCARD_DEVICEID_DIR);
            boolean can = false;
            if (!dir.exists()) {
                can = dir.mkdirs();
            } else {
                File[] files = dir.listFiles();
                for (File f : files) {
                    can = f.delete();
                    if (!can) {
                        break;
                    }
                }
            }
            if (can) {
                File file = new File(dir, key);
                return file.createNewFile();
            }
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

}
