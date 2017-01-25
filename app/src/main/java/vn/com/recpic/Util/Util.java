package vn.com.recpic.Util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Tan Le on 2017-01-18.
 */
public class Util {
    private static long m_lExitTime = 0;
    private Context context;
    private static Resources res;
    private static String w_Subject;

    public Util(Context context){
        this.context = context;
    }
    public static boolean exitApplication(Context context) {
        long current = System.currentTimeMillis();
        long diff = current - m_lExitTime;
        if (diff > 3800) {
            Toast.makeText(context,"Please click twice to quit", Toast.LENGTH_LONG).show();
            m_lExitTime = current;
            return false;
        } else if (diff < 500) {
            return true;
        }
        m_lExitTime = current;
        return false;
    }

    public static String getDeviceId(Context p_context) {
        TelephonyManager w_tm = (TelephonyManager) p_context.getSystemService(Context.TELEPHONY_SERVICE);
        if (w_tm.getDeviceId() != null)
            return w_tm.getDeviceId();
        else
            return Settings.Secure.getString(p_context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getPhoneNumber(Context p_context) {
        String w_strPhoneNumber = "none";
        TelephonyManager w_tm = (TelephonyManager) p_context.getSystemService(Context.TELEPHONY_SERVICE);
        if (w_tm.getDeviceId() != null && w_tm.getLine1Number() != null){
            w_strPhoneNumber = w_tm.getLine1Number();
            w_strPhoneNumber = w_strPhoneNumber.replace("+84", "0");
        }
        if (w_strPhoneNumber.length() == 0) w_strPhoneNumber = "none";
        w_strPhoneNumber = "01000000001";
        return w_strPhoneNumber;
    }

    public static String getAppVersion(Context p_context){
        String w_strCurVersion = "0.0";
        try {
            w_strCurVersion = p_context.getPackageManager().getPackageInfo(p_context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return w_strCurVersion;
    }

    public static String getTimeExpression(Context p_context, long p_time) {
        long timeGap = System.currentTimeMillis() - p_time;
        timeGap = timeGap/1000;

        long w_Days = timeGap / (24 * 3600);
        long w_Hours = timeGap / 3600;
        long w_Mins = timeGap / 60;
        long w_Seconds = timeGap;

        String w_strExpression = "";
//        if (w_Days > 99) {
//            w_strExpression = String.format("%d " + p_context.getResources().getString(R.string.txt_utils_day), w_Days);
//        } else if (w_Days > 0) {
//            w_strExpression = String.format("%d " + p_context.getResources().getString(R.string.txt_utils_day), w_Days);
//        } else if (w_Hours > 0) {
//            w_strExpression = String.format("%d " + p_context.getResources().getString(R.string.txt_utils_hour), w_Hours);
//        } else if (w_Mins > 0) {
//            w_strExpression = String.format("%d " + p_context.getResources().getString(R.string.txt_utils_min), w_Mins);
//        } else if (w_Seconds >= 0) {
//            w_strExpression = String.format("%d " + p_context.getResources().getString(R.string.txt_utils_second), w_Seconds);
//        }

        return w_strExpression;
    }


//    public static float getDistanceFromMe(UserInfo p_me, UserInfo p_peer) {
//        return (float) gps2m(p_me.Latitude, p_me.Longitude, p_peer.Latitude, p_peer.Longitude) / 1000.0f;
//    }

    //
    // http://www.androidsnippets.com/calculate-distance-between-two-gps-coordinates
    // Distance between (lat_a, lng_a) and (lat_b, lng_b) in meters.
    //
    private static double gps2m(float lat_a, float lng_a, float lat_b, float lng_b) {
        double pk = (double) (180 / 3.14169);
        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }


//    public static String[] getSubject(Context context){
//        w_Subject = ""  + context.getResources().getString(R.string.txt_utils_topic1)
//                + context.getResources().getString(R.string.txt_utils_topic2)
//                + context.getResources().getString(R.string.txt_utils_topic3)
//                + context.getResources().getString(R.string.txt_utils_topic4)
//                + context.getResources().getString(R.string.txt_utils_topic5)
//                + context.getResources().getString(R.string.txt_utils_topic6)
//                + context.getResources().getString(R.string.txt_utils_topic7)
//                + context.getResources().getString(R.string.txt_utils_topic8)
//                + context.getResources().getString(R.string.txt_utils_topic9)
//                + context.getResources().getString(R.string.txt_utils_topic10)
//                + context.getResources().getString(R.string.txt_utils_topic11)
//                + context.getResources().getString(R.string.txt_utils_topic12)
//                + context.getResources().getString(R.string.txt_utils_topic13)
//                + context.getResources().getString(R.string.txt_utils_topic14)
//                + context.getResources().getString(R.string.txt_utils_topic15)
//                + context.getResources().getString(R.string.txt_utils_topic16)
//                + context.getResources().getString(R.string.txt_utils_topic17)
//                + context.getResources().getString(R.string.txt_utils_topic18)
//                + context.getResources().getString(R.string.txt_utils_topic19)
//                + context.getResources().getString(R.string.txt_utils_topic20)
//                + context.getResources().getString(R.string.txt_utils_topic21);
//
//        String[] w_arrSubject = w_Subject.split("#");
//        return w_arrSubject;
//    }

    public static String getTempFolderPath() {
        String w_strTempFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TalkBang/";
        File w_fTempFolder = new File(w_strTempFolderPath);
        if (w_fTempFolder.exists() && w_fTempFolder.isDirectory()) {
            return w_strTempFolderPath;
        } else {
            w_fTempFolder.mkdir();
            return w_strTempFolderPath;
        }
    }


    public static String getPath(Context p_context, Uri uri) {
        return ImageFilePathUtil.getPath(p_context, uri);
    }

    /**
     * options가 나타내는 이미지를 reqWidth, reqHeight에 맞추기 위한 다운샘플링율을 계산.
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;    //Default subsampling size
        // See if image raw height and width is bigger than that of required view
        if (options.outHeight > reqHeight || options.outWidth > reqWidth) {
            //bigger
            final int halfHeight = options.outHeight / 2;
            final int halfWidth = options.outWidth / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    /**
     *
     * @param bitmap
     * @param degrees
     * @return
     */
    public static Bitmap getRotatedBitmap(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != b2) {
                    bitmap.recycle();
                    bitmap = b2;
                }
            } catch (OutOfMemoryError ex) {}
        }

        return bitmap;
    }

    /**
     *
     * @return
     */
    public static Bitmap loadOrientationAdjustedBitmap(String p_strFileName, int p_nMaxWidth, int p_nMaxHeight) {
                BitmapFactory.Options w_opt = new BitmapFactory.Options();
        w_opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(p_strFileName, w_opt);
        w_opt.inSampleSize = calculateInSampleSize(w_opt, p_nMaxWidth, p_nMaxHeight);
        w_opt.inJustDecodeBounds = false;
        Bitmap w_bmpCaptured = BitmapFactory.decodeFile(p_strFileName, w_opt);

        ExifInterface w_exif = null;
        try {
            w_exif = new ExifInterface(p_strFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return w_bmpCaptured;
        }
        int w_nOrientation = w_exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        int w_nRotateAngle = 0;
        switch (w_nOrientation) {
            case ExifInterface.ORIENTATION_UNDEFINED:
            case ExifInterface.ORIENTATION_NORMAL:
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                w_nRotateAngle = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                w_nRotateAngle = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                w_nRotateAngle = 270;
                break;
        }
        w_bmpCaptured = getRotatedBitmap(w_bmpCaptured, w_nRotateAngle);
        return w_bmpCaptured;
    }

    /**
     * Do image size limit to max width or height.
     * If the image is bigger than the threshold(500 * 500), it resizes to fit the threshold and save it to the temp folder and return the temp file path.
     * If the image is smaller than the threshold, it returns nothing(null).
     *
     * @param p_bitmap
     * @return
     */
    public static String getSizeLimitedImageFilePath(Bitmap p_bitmap) {
        int w_nMaxWidth = 500;

        int w_nBmpWidth = p_bitmap.getWidth();
        int w_nBmpHeight = p_bitmap.getHeight();

        if (w_nBmpWidth > w_nMaxWidth || w_nBmpHeight > w_nBmpHeight) {
            //
            // Resize.
            //
            float rate = 0.0f;
            if (w_nBmpWidth > w_nBmpHeight) {
                rate = w_nMaxWidth / (float) w_nBmpWidth;
                w_nBmpHeight = (int) (w_nBmpHeight * rate);
                w_nBmpWidth = w_nMaxWidth;
            } else {
                rate = w_nMaxWidth / (float) w_nBmpHeight;
                w_nBmpWidth = (int) (w_nBmpWidth * rate);
                w_nBmpHeight = w_nMaxWidth;
            }
            Bitmap w_bmpSizeLimited = Bitmap.createScaledBitmap(p_bitmap, w_nBmpWidth, w_nBmpHeight, true);

            //
            // Save to the temp folder
            //
            String w_strFilePath = Util.getTempFolderPath() + String.format("%d.jpg", System.currentTimeMillis());
            try {
                File w_file = new File(w_strFilePath);
                w_file.createNewFile();

                FileOutputStream w_out = new FileOutputStream(w_file);
                w_bmpSizeLimited.compress(Bitmap.CompressFormat.JPEG, 100, w_out);
                w_out.flush();
                w_out.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return w_strFilePath;
        }

        return null;
    }

    public static String getImageFilePath(Bitmap p_bitmap) {
        String w_strFilePath = Util.getTempFolderPath() + String.format("%d.jpg", System.currentTimeMillis());
        try {
            File w_file = new File(w_strFilePath);
            w_file.createNewFile();

            FileOutputStream w_out = new FileOutputStream(w_file);
            p_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, w_out);
            w_out.flush();
            w_out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return w_strFilePath;
    }

    public static String getSizeLimitedImageFilePath(String p_strBmpFilePath) {
        Bitmap w_bmpSource = decodeFile(p_strBmpFilePath);
        return Util.getSizeLimitedImageFilePath(w_bmpSource);
    }

    private static Bitmap decodeFile(String path) {
        Bitmap b = null;
        File f = new File(path);
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();

            int IMAGE_MAX_SIZE = 1000; // maximum dimension limit
            int scale = 1;
            if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
                scale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }
//------------------------------------------------------------------------------------------------->

    public static boolean isApplicationRunningBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(am.getRunningAppProcesses().size());
        for (ActivityManager.RunningTaskInfo runningTaskInfo : tasks) {
            if (runningTaskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
