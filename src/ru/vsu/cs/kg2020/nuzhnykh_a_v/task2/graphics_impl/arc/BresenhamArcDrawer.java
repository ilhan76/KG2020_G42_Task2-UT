package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.arc;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.ArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;

import java.awt.*;

public class BresenhamArcDrawer implements ArcDrawer {
    private final PixelDrawer pd;

    public BresenhamArcDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
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
            tempAngle = (double)(xP / a) <= (double)(yP / b) ? getAngle(xP, a, true)
                    : getAngle(yP, b, false);
        }
    }

    private boolean isBetweenAngles(double arg, double minAngle, double maxAngle) {
        return arg >= minAngle && arg <= maxAngle || arg + Math.PI <= maxAngle;
    }

    private double getAngle(int arg, int radius, boolean argIsX) {
        return argIsX ? Math.acos((double)arg / (double)radius) : (Math.asin((double)arg / (double)radius));
    }


}
