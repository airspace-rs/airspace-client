// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class41
{

    public static void method546(int i, JagexArchive jagexArchive)
    {
        Buffer buffer = new Buffer(jagexArchive.getFile("varp.dat", null));
        anInt702 = 0;
        anInt700 = buffer.method410();
        if(aClass41Array701 == null)
            aClass41Array701 = new Class41[anInt700];
        if(anIntArray703 == null)
            anIntArray703 = new int[anInt700];
        for(int j = 0; j < anInt700; j++)
        {
            if(aClass41Array701[j] == null)
                aClass41Array701[j] = new Class41();
            aClass41Array701[j].method547(buffer, false, j);
        }

        if(i != 0)
            aBoolean698 = !aBoolean698;
        if(buffer.pointer != buffer.data.length)
            System.out.println("varptype load mismatch");
    }

    public void method547(Buffer buffer, boolean flag, int i)
    {
        if(flag)
            anInt699 = -91;
        do
        {
            int j = buffer.method408();
            if(j == 0)
                return;
            if(j == 1)
                anInt705 = buffer.method408();
            else
            if(j == 2)
                anInt706 = buffer.method408();
            else
            if(j == 3)
            {
                aBoolean707 = true;
                anIntArray703[anInt702++] = i;
            } else
            if(j == 4)
                aBoolean708 = false;
            else
            if(j == 5)
                anInt709 = buffer.method410();
            else
            if(j == 6)
                aBoolean710 = true;
            else
            if(j == 7)
                anInt711 = buffer.method413();
            else
            if(j == 8)
            {
                anInt712 = 1;
                aBoolean713 = true;
            } else
            if(j == 10)
                aString704 = buffer.method415();
            else
            if(j == 11)
                aBoolean713 = true;
            else
            if(j == 12)
                anInt714 = buffer.method413();
            else
            if(j == 13)
                anInt712 = 2;
            else
                System.out.println("Error unrecognised config code: " + j);
        } while(true);
    }

    public Class41()
    {
        anInt699 = -32357;
        aBoolean707 = false;
        aBoolean708 = true;
        aBoolean710 = false;
        aBoolean713 = false;
        anInt714 = -1;
    }

    public static boolean aBoolean698 = true;
    public int anInt699;
    public static int anInt700;
    public static Class41 aClass41Array701[];
    public static int anInt702;
    public static int anIntArray703[];
    public String aString704;
    public int anInt705;
    public int anInt706;
    public boolean aBoolean707;
    public boolean aBoolean708;
    public int anInt709;
    public boolean aBoolean710;
    public int anInt711;
    public int anInt712;
    public boolean aBoolean713;
    public int anInt714;

}
