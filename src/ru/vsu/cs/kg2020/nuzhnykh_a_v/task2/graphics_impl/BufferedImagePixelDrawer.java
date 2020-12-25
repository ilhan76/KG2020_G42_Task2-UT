package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BufferedImagePixelDrawer {
    private final BufferedImage bi;

    public BufferedImagePixelDrawer(BufferedImage bi) {
        this.bi = bi;
    }

    public void setPixel(int x, int y, Color c) {
        if (x >= 0 && y >= 0 && x < this.bi.getWidth() && y < this.bi.getHeight()) {
            Color bg = new Color(this.bi.getRGB(x, y));
            if (c.getAlpha() != 255) {
                c = new Color((int)((double)(bg.getRed() * (255 - c.getAlpha())) / 255.0D + (double)(c.getRed() * c.getAlpha()) / 255.0D), (int)((double)(bg.getGreen() * (255 - c.getAlpha())) / 255.0D + (double)(c.getGreen() * c.getAlpha()) / 255.0D), (int)((double)(bg.getBlue() * (255 - c.getAlpha())) / 255.0D + (double)(c.getBlue() * c.getAlpha()) / 255.0D));
            }

            this.bi.setRGB(x, y, c.getRGB());
        }
    }
}
