package com.hh.bamboobase.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.fastjson.JSON;
import com.hh.bamboobase.utils.entity.ExtInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片处理工具
 * Created by guchenkai on 2015/12/8.
 */
public final class ImageUtils {

    public static Bitmap textToBitmap(Context context, int size, String text, int userId) {
        int[] colors = {Color.parseColor("#ed5564"), Color.parseColor("#ff9b2d"),
                Color.parseColor("#ffd505"), Color.parseColor("#88d152"),
                Color.parseColor("#48cfae"), Color.parseColor("#50c1e9")};
        if (text.length() < 1) {
            text = "";
        } else {
            text = text.substring(text.length() - 1, text.length());
        }
        size = DensityUtils.dp2px(context, size);
        Log.e("Bitmap", "size = " + size);
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);//初始化画布绘制的图像到icon上
        canvas.drawColor(Color.TRANSPARENT);//图层的背景色
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//创建画笔
        paint.setTextSize(DensityUtils.sp2px(context, 16));//设置文字的大小
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        paint.setTypeface(Typeface.DEFAULT_BOLD);//文字的样式(加粗)
        paint.setColor(Color.parseColor("#313131"));//文字的颜色
        Paint roundPaint = new Paint();
        roundPaint.setColor(colors[userId % 5]);
        RectF r2 = new RectF();
        r2.left = 0;
        r2.right = size;
        r2.top = 0;
        r2.bottom = size;
        canvas.drawRoundRect(r2, 10, 10, roundPaint);
        Log.e("Bitmap", "rect width = " + rect.width());
        Log.e("Bitmap", "rect height = " + rect.height());
        int startX = size / 2 - rect.width() / 2;
        int startY = size / 2 + rect.height() / 2;
        Log.e("Bitmap", "startX = " + startX);
        Log.e("Bitmap", "startY = " + startY);
        canvas.drawText(text, startX, startY, paint);
        canvas.save();//保存所有图层
        canvas.restore();
        if (bitmap == null) {
            Log.e("Bitmap", "bitmap is null");
        } else {
            Log.e("Bitmap", bitmap.toString());
        }
        return bitmap;
    }

    public static String getExtInfo(String message) {
        String json = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(message, opts);
            opts.inSampleSize = 1;
            opts.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(message, opts);
            int width = opts.outWidth;
            int height = opts.outHeight;
            ExtInfo.PicExtInfo extInfo = new ExtInfo.PicExtInfo();
            extInfo.setWidth(width);
            extInfo.setHeight(height);
            json = JSON.toJSONString(extInfo);
            if (bitmap != null && bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 将小图片x轴循环填充进imageView中
     *
     * @param imageView imageView
     * @param bitmap    目标图片
     */
    public static void fillXInImageView(Context context, ImageView imageView, Bitmap bitmap) {
        int screenWidth = DensityUtils.getScreenWidth(context);//屏幕宽度
        imageView.setImageBitmap(createRepeater(screenWidth, bitmap));
    }


    /**
     * 将图片在imageView中x轴循环填充需要的bitmap
     *
     * @param width 填充宽度
     * @param src   图片源
     * @return 新图片源
     */
    private static Bitmap createRepeater(int width, Bitmap src) {
        int count = (width + src.getWidth() - 1) / src.getWidth(); //计算出平铺填满所给width（宽度）最少需要的重复次数
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth() * count, src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        for (int idx = 0; idx < count; ++idx) {
            canvas.drawBitmap(src, idx * src.getWidth(), 0, null);
        }
        return bitmap;
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     *
     * @param filePath 图片路径
     * @param width    压缩后的宽
     * @param height   压缩后的高
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int width, int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width, height);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 将bitmap输出到sdCard中
     *
     * @param outPath 输出路径
     */
    public static void bitmapOutSdCard(Bitmap bitmap, String outPath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 截取滚动屏画面保存到图片
     *
     * @param view     view源
     * @param savePath 保存路径
     */
    public static void cutOutScrollViewToImage(ScrollView view, String savePath, OnImageSaveCallback callback) {
        int mSrollViewHeight = 0;
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            if (child instanceof LinearLayout || child instanceof RelativeLayout) {
                child.setBackgroundColor(Color.WHITE);
                mSrollViewHeight += child.getHeight();
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), mSrollViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        File file = new File(savePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            if (callback != null) callback.onImageSave(isSuccess);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截取滚动屏画面保存到图片
     *
     * @param view     view源
     * @param savePath 保存路径
     */
    public static void cutOutScrollViewToImage(ScrollView view, String savePath) {
        cutOutScrollViewToImage(view, savePath, null);
    }

    /**
     * 截取画面保存到图片
     *
     * @param view     view源
     * @param savePath 保存路径
     */
    public static void cutOutViewToImage(View view, String savePath, OnImageSaveCallback callback) {
//        int mSrollViewHeight = 0;
//        for (int i = 0; i < view.getChildCount(); i++) {
//            View child = view.getChildAt(i);
//            if (child instanceof LinearLayout || child instanceof RelativeLayout) {
//                child.setBackgroundResource(R.color.white);
//                mSrollViewHeight += child.getHeight();
//            }
//        }
        view.setBackgroundColor(Color.WHITE);
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        File file = new File(savePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            if (callback != null) callback.onImageSave(isSuccess);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 截取画面保存到图片
     *
     * @param view     view源
     * @param savePath 保存路径
     */
    public static void cutOutViewToImage(View view, String savePath) {
        cutOutViewToImage(view, savePath, null);
    }

    /**
     * 截取view画面保存至bitmap
     *
     * @param view view源
     */
    public static Bitmap cutOutViewToSmallBitmap(Context context, View view) {
//        return Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        Bitmap bitmap = Bitmap.createBitmap(DensityUtils.dp2px(context, 30), DensityUtils.dp2px(context, 30), Bitmap.Config.ARGB_8888);
        if (null != view) {
            view.setBackgroundColor(Color.WHITE);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
        }
        return bitmap;
        /*Matrix matrix = new Matrix();
        matrix.postScale(0.1f, 0.1f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;*/
    }

//

    /**
     * create a new image file
     *
     * @return
     * @throws IOException
     */
    public static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    /**
     *
     */
    public interface OnImageSaveCallback {

        void onImageSave(boolean isSuccess);
    }


    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context
     * @param imageUri
     * @date 2014-10-12
     */
    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isImage(String url) {
        if (TextUtils.isEmpty(url)) return false;
//        String upperUrl = new String();
        url = url.toUpperCase();
        if (url.contains(".JPG") || url.contains(".PNG") || url.contains(".GIF") || url.contains(".JPEG") || url.contains(".BMP"))
            return true;
        else return false;
    }

    public static String getImageSizeUrl(String url, ImageSizeURL type) {
        if (!isImage(url)) return "";
        else {
            int i = url.lastIndexOf(".");
            String font = url.substring(0, i);
            switch (type) {
                case SIZE__1200X400:
                    font += "_1200x400";
                    break;
                case SIZE_1200x750:
                    font += "_1200x750";
                    break;
                case SIZE_1200x600:
                    font += "_1200x600";
                    break;
                case SIZE_240x240:
                    font += "_240x240";
                    break;
                case SIZE_360x360:
                    font += "_360x360";
                    break;
                case SIZE_180x180:
                    font += "_180x180";
                    break;
                case SIZE_1200x1200:
                    font += "_1200x1200";
                    break;
                case SIZE_120x120:
                    font += "_120x120";
                    break;
                case SIZE_150x150:
                    font += "_150x150";
                    break;
                case SIZE_192x192:
                    font += "_192x192";
                    break;
                case SIZE_96x96:
                    font += "_96x96";
                    break;
                case SIZE_250x0:
                    font += "_250x0";
                    break;
            }
            return font + url.substring(i, url.length());
        }
    }

    public enum ImageSizeURL {
        SIZE__1200X400, SIZE_1200x750, SIZE_1200x600, SIZE_240x240, SIZE_360x360, SIZE_180x180, SIZE_1200x1200, SIZE_120x120, SIZE_150x150, SIZE_192x192, SIZE_96x96, SIZE_250x0
    }

}
