// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class44
{

    public Class44(int i, byte abyte0[])
    {
        aByte723 = 0;
        anInt724 = 9;
        anInt725 = -29508;
        if(i != 44820)
        {
            throw new NullPointerException();
        } else
        {
            method570(abyte0, aByte723);
            return;
        }
    }

    public void method570(byte abyte0[], byte byte0)
    {
        Buffer buffer = new Buffer(abyte0, 891);
        int i = buffer.method412();
        int j = buffer.method412();
        if(j != i)
        {
            byte abyte1[] = new byte[i];
            Class13.method225(abyte1, i, abyte0, j, 6);
            aByteArray726 = abyte1;
            buffer = new Buffer(aByteArray726, 891);
            aBoolean732 = true;
        } else
        {
            aByteArray726 = abyte0;
            aBoolean732 = false;
        }
        anInt727 = buffer.method410();
        if(byte0 != 0)
            return;
        anIntArray728 = new int[anInt727];
        anIntArray729 = new int[anInt727];
        anIntArray730 = new int[anInt727];
        anIntArray731 = new int[anInt727];
        int k = buffer.pointer + anInt727 * 10;
        for(int l = 0; l < anInt727; l++)
        {
            anIntArray728[l] = buffer.method413();
            anIntArray729[l] = buffer.method412();
            anIntArray730[l] = buffer.method412();
            anIntArray731[l] = k;
            k += anIntArray730[l];
        }

    }

    public byte[] method571(String s, byte abyte0[])
    {
        int i = 0;
        s = s.toUpperCase();
        for(int j = 0; j < s.length(); j++)
            i = (i * 61 + s.charAt(j)) - 32;

        for(int k = 0; k < anInt727; k++)
            if(anIntArray728[k] == i)
            {
                if(abyte0 == null)
                    abyte0 = new byte[anIntArray729[k]];
                if(!aBoolean732)
                {
                    Class13.method225(abyte0, anIntArray729[k], aByteArray726, anIntArray730[k], anIntArray731[k]);
                } else
                {
                    for(int l = 0; l < anIntArray729[k]; l++)
                        abyte0[l] = aByteArray726[anIntArray731[k] + l];

                }
                return abyte0;
            }

        return null;
    }

    public byte aByte723;
    public int anInt724;
    public int anInt725;
    public byte aByteArray726[];
    public int anInt727;
    public int anIntArray728[];
    public int anIntArray729[];
    public int anIntArray730[];
    public int anIntArray731[];
    public boolean aBoolean732;
}
