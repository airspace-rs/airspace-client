// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class23
{

    public static void method264(int i, JagexArchive jagexArchive)
    {
        Buffer buffer = new Buffer(jagexArchive.getFile("spotanim.dat"));
        if(i != 0)
            aBoolean401 = !aBoolean401;
        anInt402 = buffer.get2ByteInt();
        if(aClass23Array403 == null)
            aClass23Array403 = new Class23[anInt402];
        for(int j = 0; j < anInt402; j++)
        {
            if(aClass23Array403[j] == null)
                aClass23Array403[j] = new Class23();
            aClass23Array403[j].anInt404 = j;
            aClass23Array403[j].method265(true, buffer);
        }

    }

    public void method265(boolean flag, Buffer buffer)
    {
        if(!flag)
            throw new NullPointerException();
        do
        {
            int i = buffer.get1ByteAsInt();
            if(i == 0)
                return;
            if(i == 1)
                anInt405 = buffer.get2ByteInt();
            else
            if(i == 2)
            {
                anInt406 = buffer.get2ByteInt();
                if(Class20.aClass20Array351 != null)
                    aClass20_407 = Class20.aClass20Array351[anInt406];
            } else
            if(i == 4)
                anInt410 = buffer.get2ByteInt();
            else
            if(i == 5)
                anInt411 = buffer.get2ByteInt();
            else
            if(i == 6)
                anInt412 = buffer.get2ByteInt();
            else
            if(i == 7)
                anInt413 = buffer.get1ByteAsInt();
            else
            if(i == 8)
                anInt414 = buffer.get1ByteAsInt();
            else
            if(i >= 40 && i < 50)
                anIntArray408[i - 40] = buffer.get2ByteInt();
            else
            if(i >= 50 && i < 60)
                anIntArray409[i - 50] = buffer.get2ByteInt();
            else
                System.out.println("Error unrecognised spotanim config code: " + i);
        } while(true);
    }

    public Model method266()
    {
        Model model = (Model)aClass12_415.get(anInt404);
        if(model != null)
            return model;
        model = Model.getModel(anInt405);
        if(model == null)
            return null;
        for(int i = 0; i < 6; i++)
            if(anIntArray408[0] != 0)
                model.recolour(anIntArray408[i], anIntArray409[i]);

        aClass12_415.method223(model, anInt404, (byte)2);
        return model;
    }

    public Class23()
    {
        anInt400 = 9;
        anInt406 = -1;
        anIntArray408 = new int[6];
        anIntArray409 = new int[6];
        anInt410 = 128;
        anInt411 = 128;
    }

    public int anInt400;
    public static boolean aBoolean401 = true;
    public static int anInt402;
    public static Class23 aClass23Array403[];
    public int anInt404;
    public int anInt405;
    public int anInt406;
    public Class20 aClass20_407;
    public int anIntArray408[];
    public int anIntArray409[];
    public int anInt410;
    public int anInt411;
    public int anInt412;
    public int anInt413;
    public int anInt414;
    public static Class12 aClass12_415 = new Class12(false, 30);

}
