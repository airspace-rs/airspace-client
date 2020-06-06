// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable
{
    public Connection(BrowserApplet browserApplet_, Socket socket) throws IOException
    {
        anInt416 = -53;
        aBoolean417 = true;
        anInt418 = 519;
        isDisconnected = false;
        aBoolean427 = false;
        aBoolean428 = false;
        browserApplet = browserApplet_;
        this.socket = socket;
        this.socket.setSoTimeout(30000);
        this.socket.setTcpNoDelay(true);
        inputStream = this.socket.getInputStream();
        outputStream = this.socket.getOutputStream();
    }

    public void close()
    {
        isDisconnected = true;
        try {
            if(inputStream != null) {
                inputStream.close();
            }
            if(outputStream != null) {
                outputStream.close();
            }
            if(socket != null) {
                socket.close();
            }
        } catch(IOException _ex) {
            System.out.println("Error closing stream");
        }
        aBoolean427 = false;
        synchronized(this)
        {
            notify();
        }
        aByteArray424 = null;
    }

    public int readByte() throws IOException {
        if (isDisconnected) {
            return 0;
        } else {
            return inputStream.read();
        }
    }

    public int method269() throws IOException {
        if (isDisconnected) {
            return 0;
        } else {
            return inputStream.available();
        }
    }

    public void method270(byte abyte0[], int i, int j) throws IOException
    {
        if (isDisconnected) {
            return;
        }

        int k;

        for(; j > 0; j -= k)
        {
            k = inputStream.read(abyte0, i, j);
            if(k <= 0)
                throw new IOException("EOF");
            i += k;
        }

    }

    public void write(int i, int j, byte abyte0[], int k) throws IOException
    {
        if(isDisconnected) {
            return;
        }

        if(aBoolean428)
        {
            aBoolean428 = false;
            throw new IOException("Error in writer thread");
        }
        if(aByteArray424 == null)
            aByteArray424 = new byte[5000];
        synchronized(this)
        {
            for(int l = 0; l < i; l++)
            {
                aByteArray424[anInt426] = abyte0[l + k];
                anInt426 = (anInt426 + 1) % 5000;
                if(anInt426 == (anInt425 + 4900) % 5000)
                    throw new IOException("buffer overflow");
            }

            if(!aBoolean427)
            {
                aBoolean427 = true;
                browserApplet.newThread(this, 3);
            }
            notify();
        }
        if(j != 0)
            anInt418 = 255;
    }

    public void run()
    {
        while(aBoolean427) 
        {
            int i;
            int j;
            synchronized(this)
            {
                if(anInt426 == anInt425)
                    try
                    {
                        wait();
                    }
                    catch(InterruptedException _ex) { }
                if(!aBoolean427)
                    return;
                j = anInt425;
                if(anInt426 >= anInt425)
                    i = anInt426 - anInt425;
                else
                    i = 5000 - anInt425;
            }
            if(i > 0)
            {
                try
                {
                    outputStream.write(aByteArray424, j, i);
                }
                catch(IOException _ex)
                {
                    aBoolean428 = true;
                }
                anInt425 = (anInt425 + i) % 5000;
                try
                {
                    if(anInt426 == anInt425)
                        outputStream.flush();
                }
                catch(IOException _ex)
                {
                    aBoolean428 = true;
                }
            }
        }
    }

    public void method272(byte byte0)
    {
        if(byte0 != 1)
            anInt416 = 457;
        System.out.println("dummy:" + isDisconnected);
        System.out.println("tcycl:" + anInt425);
        System.out.println("tnum:" + anInt426);
        System.out.println("writer:" + aBoolean427);
        System.out.println("ioerror:" + aBoolean428);
        try
        {
            System.out.println("available:" + method269());
            return;
        }
        catch(IOException _ex)
        {
            return;
        }
    }

    public int anInt416;
    public boolean aBoolean417;
    public int anInt418;
    public InputStream inputStream;
    public OutputStream outputStream;
    public Socket socket;
    public boolean isDisconnected;
    public BrowserApplet browserApplet;
    public byte aByteArray424[];
    public int anInt425;
    public int anInt426;
    public boolean aBoolean427;
    public boolean aBoolean428;
}
