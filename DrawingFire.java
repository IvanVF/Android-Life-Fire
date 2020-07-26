package com.fprojects.lifefire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class DrawingFire extends View {

    public DrawingFire(Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
    }

    private static int[] firePixels;
    public static int getFirePixels(int pixelNumber) {
        return firePixels[pixelNumber];
    }
    public static void setFirePixels(int pixelNumber, int pixelColor) {
        firePixels[pixelNumber] = pixelColor;
    }

    private static int fireWidth;
    public static int getFireWidth() {
        return fireWidth;
    }

    private static int fireHeight;
    public static int getFireHeight() {
        return fireHeight;
    }

    @Override
    protected void onSizeChanged ( int w, int h, int oldw, int oldh ) {
        float aspectRatio = (float) w / h;
        fireWidth = 200; // Установка "ширины" огня - чем больше, тем качественней и требовательней к ресурсам
        fireHeight = (int) (fireWidth / aspectRatio);
        firePixels = new int[fireWidth * fireHeight];

        for  ( int x = 0; x < fireWidth; x++ ) {
            firePixels[x + (fireHeight - 1) * fireWidth] = ColorPalette.getFirePaletteLength();
        }

        DrawBitmap.setBitmap ( Bitmap.createBitmap(fireWidth, fireHeight, Bitmap.Config.RGB_565) );
    }

    static final Paint paint = new Paint();

    @Override
    protected void onDraw (Canvas canvas){
        SpreadFire.spreadFire();
        DrawBitmap.drawFire(canvas);
        invalidate();
    }

}
