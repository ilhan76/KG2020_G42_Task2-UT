package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.pie;


import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;

import java.awt.*;

public class BresenhamPieDrawer implements PieDrawer {
    private final PixelDrawer pd;

    public BresenhamPieDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        int a = width/2, b = height/2,
                x0 = x + a, y0 = y + b,
                xP = 0, yP = b;
        while(arcAngle > Math.PI*2)
            arcAngle -= Math.PI*2;
        double radStartAngle = startAngle,
                radAngle = arcAngle;

        int functionValue = 0;
        double tempAngle = Math.PI / 2;
        while (xP <= a && yP <= b){
            if (isBetweenAngles(tempAngle, radStartAngle, radStartAngle + radAngle))
                pd.setPixel(x0 + xP, y0 - yP, c);

            if (isBetweenAngles(Math.PI - tempAngle, radStartAngle, radStartAngle + radAngle))
                pd.setPixel(x0 - xP, y0 - yP, c);

            if (isBetweenAngles(Math.PI + tempAngle, radStartAngle, radStartAngle + radAngle))
                pd.setPixel(x0 - xP, y0 + yP, c);

            if (isBetweenAngles(Math.PI * 2 - tempAngle, radStartAngle, radStartAngle + radAngle))
                pd.setPixel(x0 + xP, y0 + yP, c);

            if (functionValue <= 0){
                functionValue += 2 * b * b * xP + b * b;
                xP++;
            }
            if(functionValue >= 0){
                functionValue -= 2 * a * a * yP - a * a;
                yP--;
            }
            tempAngle = (double)xP / a <= (double)yP / b ? getAngle(xP, a, true)
                    : getAngle(yP, b, false);
        }

        int x1 = x + a + (int)(a * Math.cos(radStartAngle));
        int y1 = y + b - (int)(b * Math.sin(radStartAngle));
        drawLine(x + a, y + b, x1, y1, c);
        x1 = x + width / 2 + (int)(a * Math.cos(radStartAngle + radAngle));
        y1 = y + height / 2 - (int)(b * Math.sin(radStartAngle + radAngle));
        drawLine(x + a, y + b, x1, y1, c);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1, dy = y2 - y1;
        if(Math.abs(dx) >= Math.abs(dy)){
            if(x1 > x2){
                int b = x1;
                x1 = x2; x2 = b;
                b = y1; y1 = y2; y2 = b;
                dx *= -1; dy *= -1;
            }
            int d = 2 * Math.abs(dy) -  dx;
            int yP = y1;
            for(int i = x1; i <= x2; i++){
                pd.setPixel(i, yP, c);
                if(d > 0){
                    yP += Math.signum(dy);
                    d -= 2 * (dx - Math.abs(dy));
                }
                else
                    d -= - 2 * Math.abs(dy);
            }
        }else {
            if(y1 > y2){
                int b = x1;
                x1 = x2; x2 = b;
                b = y1; y1 = y2; y2 = b;
                dx *= -1; dy *= -1;
            }
            int d = 2 * Math.abs(dx) -  dy;
            int xP = x1;
            for(int i = y1; i <= y2; i++){
                pd.setPixel(xP, i, c);
                if(d > 0){
                    xP += Math.signum(dx);
                    d -= 2 * (dy - Math.abs(dx));
                }
                else
                    d -= - 2 * Math.abs(dx);
            }
        }
    }

    private boolean isBetweenAngles(double arg, double minAngle, double maxAngle) {
        return arg >= minAngle && arg <= maxAngle || arg + 360 <= maxAngle;
    }

    private double getAngle(int arg, int radius, boolean argIsX) {
        return argIsX ? Math.acos((double)arg / (double)radius) : (Math.asin((double)arg / (double)radius));
    }
}
