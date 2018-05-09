package com.cmazxiaoma.mvp.Utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.cmazxiaoma.mvp.Config;
import com.cmazxiaoma.mvp.application.BaseApplication;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description: 沉梦昂志
 * Data：2017/5/16-20:11
 * Author: xiaoma
 */

public class Common{

    public static int getMax(int[] arr){
        int maxValue=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>maxValue){
                maxValue=arr[i];
            }
        }
        return maxValue;
    }

    public static void Toast(String url){
        Toast.makeText(BaseApplication.getContext(),url, Toast.LENGTH_SHORT).show();
    }

    public static boolean saveImg2Local(Bitmap bitmap){
        if (bitmap == null) {
            return false;
        }

        File appdir=new File(Config.FILE_PATH);
        if(!appdir.exists()){
            appdir.mkdirs();
        }
        String fileName=System.currentTimeMillis()+".png";
        File file=new File(appdir,fileName);
        FileOutputStream os=null;
        try {
            os=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
            UtilsLog.i("save image succeed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  false;
        }finally {
            try {
                if(os!=null){
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //把图片插入到图库
        try {
            MediaStore.Images.Media.insertImage(BaseApplication.getContext().getContentResolver(),file.getAbsolutePath(),fileName,"cmazxiaoma");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //通知图库更新
        BaseApplication.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+file.getPath())));
        return  true;
    }

    public static String bitmap2String(Bitmap bitmap){
        if(bitmap==null){
            throw  new IllegalArgumentException("bitmap不能为空");
        }
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] data=os.toByteArray();
        String str=new String(Base64.encodeToString(data,Base64.DEFAULT));
        return str;
    }

    public static Bitmap string2Bitmap(String str){
        byte[] data=Base64.decode(str,Base64.DEFAULT);
        ByteArrayInputStream is=null;
        is=new ByteArrayInputStream(data);
        Bitmap bitmap= BitmapFactory.decodeStream(is);
        return bitmap;
    }
}
