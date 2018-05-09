package com.cmazxiaoma.mvp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.cmazxiaoma.mvp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-10:47
 * Author: xiaoma
 */

public class MyPicasso {
    public static void loadImageHolder(Context mcontext,String url,int width,int height,ImageView imageView){
        Picasso.with(mcontext).load(url).placeholder(R.drawable.load).error(R.drawable.error).resize(width,height).into(imageView);
    }

    public static void loadDefaultImg(Context mcontext, String url, ImageView imageView, Callback callback){
       Picasso.with(mcontext).load(url).placeholder(R.drawable.load).error(R.drawable.error).into(imageView,callback);
    }

    public static void loadDefaultImg(Context mcontext, String url, ImageView imageView){
        Picasso.with(mcontext).load(url).placeholder(R.drawable.load).error(R.drawable.error).into(imageView);
    }

    public static void loadCropSquareImg(Context mcontext,String url,ImageView imageView){
        Picasso.with(mcontext).load(url).placeholder(R.drawable.load).error(R.drawable.error).transform(new CropSquareTransformation()).into(imageView);
    }



    public  static class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }
}
