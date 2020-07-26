package com.fprojects.lifefire;

import java.util.Random;

class SpreadFire {

    private static final Random random = new Random();

    static void spreadFire() { //Алгоритм распространения огня - сверху вниз
        for ( int y = 0; y < DrawingFire.getFireHeight() - 1; y++ ) {
            for ( int x = 0; x < DrawingFire.getFireWidth() - 1; x++ ) {
                int rand_x = random.nextInt(3);
                int rand_y = random.nextInt(6);
                int dst_x = Math.min( DrawingFire.getFireWidth() - 1, Math.max( 0, x + rand_x - 1 ) );
                int dst_y = Math.min( DrawingFire.getFireHeight() - 1, y + rand_y );
                int deltaFire = - ( rand_x & 1 );
                DrawingFire.setFirePixels(x + y * DrawingFire.getFireWidth(), Math.max( 0, DrawingFire.getFirePixels(dst_x + dst_y * DrawingFire.getFireWidth()) + deltaFire ));
            }
        }
    }

}
