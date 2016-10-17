package com.yujinlong.note.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {

    public static void switchViewVisibility(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE
                        : View.VISIBLE);
            }
        }
    }

    public static void setViewShow(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void setViewHide(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }

    public static void setViewEnabled(boolean enable, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setEnabled(enable);
            }
        }
    }

    public static void setViewVisibility(boolean show, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    public static void setBackgroundResource(int resid, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setBackgroundResource(resid);
            }
        }
    }

    public static void setBackgroundDrawable(Drawable drawable,
                                             View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                Drawable clone = drawable.getConstantState().newDrawable();
                v.setBackgroundDrawable(clone);
            }
        }
    }



    public static Bitmap scaleDrawableBitmap(Bitmap bm, float scaleRatio,
                                             int width, int height) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleRatio, scaleRatio);
        bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(),
                matrix, false);
        Matrix matrix1 = new Matrix();
        bm = Bitmap.createBitmap(bm, checkVilidity((bm.getWidth() - width) / 2),
                checkVilidity((bm.getHeight() - height) / 2),
                getVilidity(bm.getWidth(), width),
                getVilidity(bm.getHeight(), height), matrix1, false);
        return bm;
    }

    private static int checkVilidity(float f) {
        if (f < 0) {
            return 0;
        }
        return (int) f;
    }

    private static int getVilidity(int actual, int expect) {
        if (actual < expect) {
            return actual;
        }
        return expect;
    }

    private static Bitmap drawableBitmapDrawableToBitmapWithMask(
            Drawable draSrc, Bitmap bmpMask, int nDesWidth, int nDesHeight) {
        Bitmap bmpResult = Bitmap.createBitmap(nDesWidth, nDesHeight,
                Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpResult);
        drawBitmapDrawableToCanvas(canvas, draSrc, nDesWidth, nDesHeight);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bmpMask, 0, 0, paint);
        paint.setXfermode(null);

        return bmpResult;
    }

    private static void drawBitmapDrawableToCanvas(Canvas canvas,
                                                   Drawable draSrc, int nDesWidth, int nDesHeight) {
        Bitmap bmpSrc = ((BitmapDrawable) draSrc).getBitmap();
        if (bmpSrc == null)
            return;

        Matrix matrix = new Matrix();
        int nSrcW = bmpSrc.getWidth();
        int nSrcH = bmpSrc.getHeight();
        float fScaleW = nDesWidth / (float) nSrcW;
        float fScaleH = nDesHeight / (float) nSrcH;
        matrix.postScale(fScaleW, fScaleH);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(bmpSrc, 0, 0, paint);
    }

    public static void setTextColor(int color, TextView... targetViews) {
        for (TextView v : targetViews) {
            if (v != null) {
                v.setTextColor(color);
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, String dpValue) {
        try {
            if (dpValue.endsWith("dp")) {
                float dp = Integer
                        .parseInt(dpValue.substring(0, dpValue.indexOf("dp")));
                return dip2px(context, dp);
            } else if (dpValue.endsWith("px")) {
                return Integer
                        .parseInt(dpValue.substring(0, dpValue.indexOf("px")));
            }
        } catch (Exception e) {
            Log.e("ViewUtils","dip to px error!");
        }
        return 0;

    }

    public static int sp2px(Context context, int value) {
        float v = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }

    /**
     * @param v
     * @return px
     */
    public static int getDesiredWidth(TextView v) {
        TextPaint paint = v.getPaint();
        return (int) Layout.getDesiredWidth(v.getText().toString(), 0,
                v.getText().length(), paint);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            // pre-condition
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }

    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }


    public static String StringCut(String input, int index, int count) {
        if (input.isEmpty()) {
            return "";
        }
        count = (count > input.length() - index + 1) ? input.length() - index + 1 :
                count;
        return input.substring(index - 1, index + count - 1);
    }
}
