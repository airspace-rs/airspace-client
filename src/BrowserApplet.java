// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class BrowserApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener
{
    public boolean aBoolean1;
    public int anInt2;
    public int anInt3;
    public int anInt4;
    public int anInt5;
    public int anInt6;
    public long aLongArray7[];
    public int anInt8;
    public boolean aBoolean9;
    public int aWidth;
    public int aHeight;
    public Graphics graphics;
    public GraphicsBuffer graphicsBuffer;
    public Class30_Sub2_Sub1_Sub1 aClass30_Sub2_Sub1_Sub1Array14[];
    public Window window;
    public boolean aBoolean16;
    public boolean aBoolean17;
    public int anInt18;
    public int anInt19;
    public int anInt20;
    public int anInt21;
    public int anInt22;
    public int anInt23;
    public int anInt24;
    public long aLong25;
    public int anInt26;
    public int anInt27;
    public int anInt28;
    public long aLong29;
    public int anIntArray30[];
    public int anIntArray31[];
    public int anInt32;
    public int anInt33;
    public static int anInt34;

    public void initialiseWithWindow(int width, int height)
    {
        aWidth = width;
        aHeight = height;
        window = new Window(this, aWidth, aHeight);
        graphics = getWindow().getGraphics();
        graphicsBuffer = new GraphicsBuffer(aWidth, aHeight, getWindow());

        newThread(this, 1);
    }

    public void intialise(int width, int height)
    {
        aWidth = width;
        aHeight = height;
        graphics = getWindow().getGraphics();
        graphicsBuffer = new GraphicsBuffer(aWidth, aHeight, getWindow());

        newThread(this, 1);
    }

    public void run()
    {
        getWindow().addMouseListener(this);
        getWindow().addMouseMotionListener(this);
        getWindow().addKeyListener(this);
        getWindow().addFocusListener(this);
        if (window != null) {
            window.addWindowListener(this);
        }
        method13(0, (byte)4, "Loading...");
        method6();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        int j1 = 0;
        for(int k1 = 0; k1 < 10; k1++)
            aLongArray7[k1] = System.currentTimeMillis();

        long l = System.currentTimeMillis();
        while(anInt4 >= 0) 
        {
            if(anInt4 > 0)
            {
                anInt4--;
                if(anInt4 == 0)
                {
                    method3(4747);
                    return;
                }
            }
            int i2 = j;
            int j2 = k;
            j = 300;
            k = 1;
            long l1 = System.currentTimeMillis();
            if(aLongArray7[i] == 0L)
            {
                j = i2;
                k = j2;
            } else
            if(l1 > aLongArray7[i])
                j = (int)((long)(2560 * anInt5) / (l1 - aLongArray7[i]));
            if(j < 25)
                j = 25;
            if(j > 256)
            {
                j = 256;
                k = (int)((long)anInt5 - (l1 - aLongArray7[i]) / 10L);
            }
            if(k > anInt5)
                k = anInt5;
            aLongArray7[i] = l1;
            i = (i + 1) % 10;
            if(k > 1)
            {
                for(int k2 = 0; k2 < 10; k2++)
                    if(aLongArray7[k2] != 0L)
                        aLongArray7[k2] += k;

            }
            if(k < anInt6)
                k = anInt6;
            try
            {
                Thread.sleep(k);
            }
            catch(InterruptedException _ex)
            {
                j1++;
            }
            for(; i1 < 256; i1 += j)
            {
                anInt26 = anInt22;
                anInt27 = anInt23;
                anInt28 = anInt24;
                aLong29 = aLong25;
                anInt22 = 0;
                method7(24869);
                anInt32 = anInt33;
            }

            i1 &= 0xff;
            if(anInt5 > 0)
                anInt8 = (1000 * j) / (anInt5 * 256);
            method9(0);
            if(aBoolean9)
            {
                System.out.println("ntime:" + l1);
                for(int l2 = 0; l2 < 10; l2++)
                {
                    int i3 = ((i - l2 - 1) + 20) % 10;
                    System.out.println("otim" + i3 + ":" + aLongArray7[i3]);
                }

                System.out.println("fps:" + anInt8 + " ratio:" + j + " count:" + i1);
                System.out.println("del:" + k + " deltime:" + anInt5 + " mindel:" + anInt6);
                System.out.println("intex:" + j1 + " opos:" + i);
                aBoolean9 = false;
                j1 = 0;
            }
        }
        if(anInt4 == -1)
            method3(4747);
    }

    public void method3(int i)
    {
        anInt4 = -2;
        method8(493);
        if(i != 4747)
            return;
        if(window != null)
        {
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception _ex) { }
            try
            {
                System.exit(0);
                return;
            }
            catch(Throwable _ex) { }
        }
    }

    public void method4(boolean flag, int i)
    {
        if(flag)
        {
            return;
        } else
        {
            anInt5 = 1000 / i;
            return;
        }
    }

    public void start()
    {
        if(anInt4 >= 0)
            anInt4 = 0;
    }

    public void stop()
    {
        if(anInt4 >= 0)
            anInt4 = 4000 / anInt5;
    }

    public void destroy()
    {
        anInt4 = -1;
        try
        {
            Thread.sleep(5000L);
        }
        catch(Exception _ex) { }
        if(anInt4 == -1)
            method3(4747);
    }

    public void update(Graphics g)
    {
        if(graphics == null)
            graphics = g;
        aBoolean16 = true;
        method10((byte)1);
    }

    public void paint(Graphics g)
    {
        if(graphics == null)
            graphics = g;
        aBoolean16 = true;
        method10((byte)1);
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(window != null)
        {
            i -= 4;
            j -= 22;
        }
        anInt18 = 0;
        anInt23 = i;
        anInt24 = j;
        aLong25 = System.currentTimeMillis();
        if(mouseevent.isMetaDown())
        {
            anInt22 = 2;
            anInt19 = 2;
            return;
        } else
        {
            anInt22 = 1;
            anInt19 = 1;
            return;
        }
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        anInt18 = 0;
        anInt19 = 0;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        anInt18 = 0;
        anInt20 = -1;
        anInt21 = -1;
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(window != null)
        {
            i -= 4;
            j -= 22;
        }
        anInt18 = 0;
        anInt20 = i;
        anInt21 = j;
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(window != null)
        {
            i -= 4;
            j -= 22;
        }
        anInt18 = 0;
        anInt20 = i;
        anInt21 = j;
    }

    public void keyPressed(KeyEvent keyevent)
    {
        anInt18 = 0;
        int i = keyevent.getKeyCode();
        int j = keyevent.getKeyChar();
        if(j < 30)
            j = 0;
        if(i == 37)
            j = 1;
        if(i == 39)
            j = 2;
        if(i == 38)
            j = 3;
        if(i == 40)
            j = 4;
        if(i == 17)
            j = 5;
        if(i == 8)
            j = 8;
        if(i == 127)
            j = 8;
        if(i == 9)
            j = 9;
        if(i == 10)
            j = 10;
        if(i >= 112 && i <= 123)
            j = (1008 + i) - 112;
        if(i == 36)
            j = 1000;
        if(i == 35)
            j = 1001;
        if(i == 33)
            j = 1002;
        if(i == 34)
            j = 1003;
        if(j > 0 && j < 128)
            anIntArray30[j] = 1;
        if(j > 4)
        {
            anIntArray31[anInt33] = j;
            anInt33 = anInt33 + 1 & 0x7f;
        }
    }

    public void keyReleased(KeyEvent keyevent)
    {
        anInt18 = 0;
        int i = keyevent.getKeyCode();
        char c = keyevent.getKeyChar();
        if(c < '\036')
            c = '\0';
        if(i == 37)
            c = '\001';
        if(i == 39)
            c = '\002';
        if(i == 38)
            c = '\003';
        if(i == 40)
            c = '\004';
        if(i == 17)
            c = '\005';
        if(i == 8)
            c = '\b';
        if(i == 127)
            c = '\b';
        if(i == 9)
            c = '\t';
        if(i == 10)
            c = '\n';
        if(c > 0 && c < '\200')
            anIntArray30[c] = 0;
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public int method5(int i)
    {
        while(i >= 0) 
        {
            for(int j = 1; j > 0; j++);
        }
        int k = -1;
        if(anInt33 != anInt32)
        {
            k = anIntArray31[anInt32];
            anInt32 = anInt32 + 1 & 0x7f;
        }
        return k;
    }

    public void focusGained(FocusEvent focusevent)
    {
        aBoolean17 = true;
        aBoolean16 = true;
        method10((byte)1);
    }

    public void focusLost(FocusEvent focusevent)
    {
        aBoolean17 = false;
        for(int i = 0; i < 128; i++)
            anIntArray30[i] = 0;

    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowClosing(WindowEvent windowevent)
    {
        destroy();
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    public void method6()
    {
    }

    public void method7(int i)
    {
        if(i == anInt2);
    }

    public void method8(int i)
    {
        i = 91 / i;
    }

    public void method9(int i)
    {
        if(i != 0)
        {
            for(int j = 1; j > 0; j++);
        }
    }

    public void method10(byte byte0)
    {
        if(byte0 == 1)
            byte0 = 0;
    }

    public Component getWindow()
    {
        if (window != null) {
            return window;
        } else {
            return this;
        }
    }

    public void newThread(Runnable runnable, int priority)
    {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }

    public void method13(int i, byte byte0, String s)
    {
        while(graphics == null)
        {
            graphics = getWindow().getGraphics();
            try
            {
                getWindow().repaint();
            }
            catch(Exception _ex) { }
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception _ex) { }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = getWindow().getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        getWindow().getFontMetrics(font1);
        if(aBoolean16)
        {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, aWidth, aHeight);
            aBoolean16 = false;
        }
        Color color = new Color(140, 17, 17);
        int j = aHeight / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(aWidth / 2 - 152, j, 304, 34);
        graphics.fillRect(aWidth / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        if(byte0 != 4)
        {
            return;
        } else
        {
            graphics.fillRect((aWidth / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString(s, (aWidth - fontmetrics.stringWidth(s)) / 2, j + 22);
            return;
        }
    }

    public BrowserApplet()
    {
        aBoolean1 = true;
        anInt2 = 24869;
        anInt3 = 748;
        anInt5 = 20;
        anInt6 = 1;
        aLongArray7 = new long[10];
        aBoolean9 = false;
        aClass30_Sub2_Sub1_Sub1Array14 = new Class30_Sub2_Sub1_Sub1[6];
        aBoolean16 = true;
        aBoolean17 = true;
        anIntArray30 = new int[128];
        anIntArray31 = new int[128];
    }
}