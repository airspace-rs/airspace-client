// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.*;

public class JagexFileStore
{

    public JagexFileStore(int i, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2, int j)
    {
        // Not checked yet
        anInt306 = 923;
        aBoolean307 = true;
        anInt311 = j;

        // Definitely good
        anInt312 = i;
        this.randomAccessFile = randomAccessFile;
        this.randomAccessFile2 = randomAccessFile2;
    }

    public synchronized byte[] decompress(int i)
    {
        try
        {
            method236(randomAccessFile2, -660, i * 6);
            int l;
            for(int j = 0; j < 6; j += l)
            {
                l = randomAccessFile2.read(aByteArray308, j, 6 - j);
                if(l == -1)
                    return null;
            }

            int i1 = ((aByteArray308[0] & 0xff) << 16) + ((aByteArray308[1] & 0xff) << 8) + (aByteArray308[2] & 0xff);
            int j1 = ((aByteArray308[3] & 0xff) << 16) + ((aByteArray308[4] & 0xff) << 8) + (aByteArray308[5] & 0xff);
            if(i1 < 0 || i1 > anInt312)
                return null;
            if(j1 <= 0 || (long)j1 > randomAccessFile.length() / 520L)
                return null;
            byte abyte0[] = new byte[i1];
            int k1 = 0;
            for(int l1 = 0; k1 < i1; l1++)
            {
                if(j1 == 0)
                    return null;
                method236(randomAccessFile, -660, j1 * 520);
                int k = 0;
                int i2 = i1 - k1;
                if(i2 > 512)
                    i2 = 512;
                int j2;
                for(; k < i2 + 8; k += j2)
                {
                    j2 = randomAccessFile.read(aByteArray308, k, (i2 + 8) - k);
                    if(j2 == -1)
                        return null;
                }

                int k2 = ((aByteArray308[0] & 0xff) << 8) + (aByteArray308[1] & 0xff);
                int l2 = ((aByteArray308[2] & 0xff) << 8) + (aByteArray308[3] & 0xff);
                int i3 = ((aByteArray308[4] & 0xff) << 16) + ((aByteArray308[5] & 0xff) << 8) + (aByteArray308[6] & 0xff);
                int j3 = aByteArray308[7] & 0xff;
                if(k2 != i || l2 != l1 || j3 != anInt311)
                    return null;
                if(i3 < 0 || (long)i3 > randomAccessFile.length() / 520L)
                    return null;
                for(int k3 = 0; k3 < i2; k3++)
                    abyte0[k1++] = aByteArray308[k3 + 8];

                j1 = i3;
            }

            return abyte0;
        }
        catch(IOException _ex)
        {
            return null;
        }
    }

    public synchronized boolean method234(int i, byte abyte0[], byte byte0, int j)
    {
        boolean flag = method235(true, 923, j, i, abyte0);
        if(byte0 == 2)
            byte0 = 0;
        else
            aBoolean307 = !aBoolean307;
        if(!flag)
            flag = method235(false, 923, j, i, abyte0);
        return flag;
    }

    public synchronized boolean method235(boolean flag, int i, int j, int k, byte abyte0[])
    {
        i = 27 / i;
        try
        {
            int l;
            if(flag)
            {
                method236(randomAccessFile2, -660, j * 6);
                int k1;
                for(int i1 = 0; i1 < 6; i1 += k1)
                {
                    k1 = randomAccessFile2.read(aByteArray308, i1, 6 - i1);
                    if(k1 == -1)
                        return false;
                }

                l = ((aByteArray308[3] & 0xff) << 16) + ((aByteArray308[4] & 0xff) << 8) + (aByteArray308[5] & 0xff);
                if(l <= 0 || (long)l > randomAccessFile.length() / 520L)
                    return false;
            } else
            {
                l = (int)((randomAccessFile.length() + 519L) / 520L);
                if(l == 0)
                    l = 1;
            }
            aByteArray308[0] = (byte)(k >> 16);
            aByteArray308[1] = (byte)(k >> 8);
            aByteArray308[2] = (byte)k;
            aByteArray308[3] = (byte)(l >> 16);
            aByteArray308[4] = (byte)(l >> 8);
            aByteArray308[5] = (byte)l;
            method236(randomAccessFile2, -660, j * 6);
            randomAccessFile2.write(aByteArray308, 0, 6);
            int j1 = 0;
            for(int l1 = 0; j1 < k; l1++)
            {
                int i2 = 0;
                if(flag)
                {
                    method236(randomAccessFile, -660, l * 520);
                    int j2;
                    int l2;
                    for(j2 = 0; j2 < 8; j2 += l2)
                    {
                        l2 = randomAccessFile.read(aByteArray308, j2, 8 - j2);
                        if(l2 == -1)
                            break;
                    }

                    if(j2 == 8)
                    {
                        int i3 = ((aByteArray308[0] & 0xff) << 8) + (aByteArray308[1] & 0xff);
                        int j3 = ((aByteArray308[2] & 0xff) << 8) + (aByteArray308[3] & 0xff);
                        i2 = ((aByteArray308[4] & 0xff) << 16) + ((aByteArray308[5] & 0xff) << 8) + (aByteArray308[6] & 0xff);
                        int k3 = aByteArray308[7] & 0xff;
                        if(i3 != j || j3 != l1 || k3 != anInt311)
                            return false;
                        if(i2 < 0 || (long)i2 > randomAccessFile.length() / 520L)
                            return false;
                    }
                }
                if(i2 == 0)
                {
                    flag = false;
                    i2 = (int)((randomAccessFile.length() + 519L) / 520L);
                    if(i2 == 0)
                        i2++;
                    if(i2 == l)
                        i2++;
                }
                if(k - j1 <= 512)
                    i2 = 0;
                aByteArray308[0] = (byte)(j >> 8);
                aByteArray308[1] = (byte)j;
                aByteArray308[2] = (byte)(l1 >> 8);
                aByteArray308[3] = (byte)l1;
                aByteArray308[4] = (byte)(i2 >> 16);
                aByteArray308[5] = (byte)(i2 >> 8);
                aByteArray308[6] = (byte)i2;
                aByteArray308[7] = (byte)anInt311;
                method236(randomAccessFile, -660, l * 520);
                randomAccessFile.write(aByteArray308, 0, 8);
                int k2 = k - j1;
                if(k2 > 512)
                    k2 = 512;
                randomAccessFile.write(abyte0, j1, k2);
                j1 += k2;
                l = i2;
            }

            return true;
        }
        catch(IOException _ex)
        {
            return false;
        }
    }

    public synchronized void method236(RandomAccessFile randomaccessfile, int i, int j)
        throws IOException
    {
        while(i >= 0) 
            return;
        if(j < 0 || j > 0x3c00000)
        {
            System.out.println("Badseek - pos:" + j + " len:" + randomaccessfile.length());
            j = 0x3c00000;
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception _ex) { }
        }
        randomaccessfile.seek(j);
    }

    public int anInt306;
    public boolean aBoolean307;
    public static byte aByteArray308[] = new byte[520];
    public RandomAccessFile randomAccessFile;
    public RandomAccessFile randomAccessFile2;
    public int anInt311;
    public int anInt312;

}