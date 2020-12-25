package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.line;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private final PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int d;
        int yP;
        int i;
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (x1 > x2) {
                d = x1;
                x1 = x2;
                x2 = d;
                y1 = y2;
                dx *= -1;
                dy *= -1;
            }

            d = 2 * Math.abs(dy) - dx;
            yP = y1;

            for(i = x1; i <= x2; ++i) {
                this.pd.setPixel(i, yP, color);
                if (d > 0) {
                    yP = (int)((float)yP + Math.signum((float)dy));
                    d -= 2 * (dx - Math.abs(dy));
                } else {
                    d -= -2 * Math.abs(dy);
                }
            }
        } else {
            if (y1 > y2) {
                x1 = x2;
                d = y1;
                y1 = y2;
                y2 = d;
                dx *= -1;
                dy *= -1;
            }

            d = 2 * Math.abs(dx) - dy;
            yP = x1;

            for(i = y1; i <= y2; ++i) {
                this.pd.setPixel(yP, i, color);
                if (d > 0) {
                    yP = (int)((float)yP + Math.signum((float)dx));
                    d -= 2 * (dy - Math.abs(dx));
                } else {
                    d -= -2 * Math.abs(dx);
                }
            }
        }

    }
}
