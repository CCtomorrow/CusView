package yong.qing.com.qimingview.uniqueid;

import android.os.Build;
import android.util.Log;

import java.util.UUID;

/**
 * <b>Project:</b> yong.qing.com.qimingview.util <br>
 * <b>Create Date:</b> 2016/4/8 <br>
 * <b>Author:</b> Devin <br>
 * <b>Description:</b>  <br>
 */
public class GetUniqueId {

    //获得独一无二的Psuedo ID
    public static String getUniquePsuedoID() {
        String serial;
        String m_szDevIDShort = "35" +
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
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        String id = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        Log.e("TAG", id);
        return id;
    }
}
