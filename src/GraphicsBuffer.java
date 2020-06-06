// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;
import java.awt.image.*;

public class GraphicsBuffer implements ImageProducer, ImageObserver
{
    public GraphicsBuffer(int width, int height, Component component)
    {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        aColorModel318 = new DirectColorModel(32, 0xff0000, 65280, 255);
        anImage320 = component.createImage(this);
        method239();
        component.prepareImage(anImage320, this);
        method239();
        component.prepareImage(anImage320, this);
        method239();
        component.prepareImage(anImage320, this);
        method237();
    }

    public void method237()
    {
        Class30_Sub2_Sub1.method331(height, width, pixels);
    }

    public void method238(int i, int j, Graphics g, int k)
    {
        method239();
        if(j != 23680)
            anInt313 = -169;
        g.drawImage(anImage320, k, i, this);
    }

    public synchronized void addConsumer(ImageConsumer imageconsumer)
    {
        anImageConsumer319 = imageconsumer;
        imageconsumer.setDimensions(width, height);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(aColorModel318);
        imageconsumer.setHints(14);
    }

    public synchronized boolean isConsumer(ImageConsumer imageconsumer)
    {
        return anImageConsumer319 == imageconsumer;
    }

    public synchronized void removeConsumer(ImageConsumer imageconsumer)
    {
        if(anImageConsumer319 == imageconsumer)
            anImageConsumer319 = null;
    }

    public void startProduction(ImageConsumer imageconsumer)
    {
        addConsumer(imageconsumer);
    }

    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer)
    {
        System.out.println("TDLR");
    }

    public synchronized void method239()
    {
        if(anImageConsumer319 == null)
        {
            return;
        } else
        {
            anImageConsumer319.setPixels(0, 0, width, height, aColorModel318, pixels, 0, width);
            anImageConsumer319.imageComplete(2);
            return;
        }
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
    {
        return true;
    }

    public int anInt313;
    public int pixels[];
    public int width;
    public int height;
    public ColorModel aColorModel318;
    public ImageConsumer anImageConsumer319;
    public Image anImage320;
}
