// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class37
{

    public static void method533(int i, JagexArchive jagexArchive)
    {
        if(i != 0)
            anInt644 = 91;
        Buffer buffer = new Buffer(jagexArchive.getFile("varbit.dat"));
        anInt645 = buffer.get2ByteInt();
        if(aClass37Array646 == null)
            aClass37Array646 = new Class37[anInt645];
        for(int j = 0; j < anInt645; j++)
        {
            if(aClass37Array646[j] == null)
                aClass37Array646[j] = new Class37();
            aClass37Array646[j].method534(buffer, false, j);
            if(aClass37Array646[j].aBoolean651)
                Class41.aClass41Array701[aClass37Array646[j].anInt648].aBoolean713 = true;
        }

        if(buffer.pointer != buffer.data.length)
            System.out.println("varbit load mismatch");
    }

    public void method534(Buffer buffer, boolean flag, int i)
    {
        if(flag)
            return;
        do
        {
            int j = buffer.get1ByteAsInt();
            if(j == 0)
                return;
            if(j == 1)
            {
                anInt648 = buffer.get2ByteInt();
                anInt649 = buffer.get1ByteAsInt();
                anInt650 = buffer.get1ByteAsInt();
            } else
            if(j == 10)
                aString647 = buffer.readString();
            else
            if(j == 2)
                aBoolean651 = true;
            else
            if(j == 3)
                anInt652 = buffer.get4ByteInt();
            else
            if(j == 4)
                anInt653 = buffer.get4ByteInt();
            else
                System.out.println("Error unrecognised config code: " + j);
        } while(true);
    }

    public Class37()
    {
        aBoolean651 = false;
        anInt652 = -1;
    }

    public static int anInt644;
    public static int anInt645;
    public static Class37 aClass37Array646[];
    public String aString647;
    public int anInt648;
    public int anInt649;
    public int anInt650;
    public boolean aBoolean651;
    public int anInt652;
    public int anInt653;
}
