package com.fprojects.lifefire;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.annotation.ColorInt;

class DrawBitmap {

    private static Bitmap bitmap;
    static void setBitmap(Bitmap bmap1) {
        bitmap = bmap1;
    }

    private static int[] bitmapPixels;

    static void drawFire (Canvas canvas) {
        final int pixelCount = DrawingFire.getFireWidth() * DrawingFire.getFireHeight();
        if ( bitmapPixels == null || bitmapPixels.length < pixelCount ) {
            bitmapPixels = new int[pixelCount];
        }

        for ( int x = 0; x < DrawingFire.getFireWidth(); x++ ) {
            for ( int y = 0; y < DrawingFire.getFireHeight(); y++ ) {
                int temperature = DrawingFire.getFirePixels(x + y * DrawingFire.getFireWidth());

                if ( temperature < 0 ) { temperature = 0; }

                if ( temperature >= ColorPalette.getFirePaletteLength() ) { temperature = ColorPalette.getFirePaletteLength() - 1; }

                @ColorInt int color = ColorPalette.getColor(temperature);
                bitmapPixels[DrawingFire.getFireWidth() * y + x] = color;
            }
        }
        bitmap.setPixels( bitmapPixels, 0, DrawingFire.getFireWidth(), 0, 0, DrawingFire.getFireWidth(), DrawingFire.getFireHeight() );

        float scale = (float) canvas.getWidth() / DrawingFire.getFireWidth(); // Растягиваем огонь на весь экран
        canvas.scale(scale, scale);
        canvas.drawBitmap( bitmap, 0, 0, DrawingFire.paint );
    }

}
