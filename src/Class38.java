// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38
{

    public static void method535(int i, JagexArchive jagexArchive)
    {
        Buffer buffer = new Buffer(jagexArchive.getFile("idk.dat"));
        anInt655 = buffer.get2ByteInt();
        if(aClass38Array656 == null)
            aClass38Array656 = new Class38[anInt655];
        for(int j = 0; j < anInt655; j++)
        {
            if(aClass38Array656[j] == null)
                aClass38Array656[j] = new Class38();
            aClass38Array656[j].method536(true, buffer);
        }

        if(i == 0);
    }

    public void method536(boolean flag, Buffer buffer)
    {
        if(!flag)
            throw new NullPointerException();
        do
        {
            int i = buffer.get1ByteAsInt();
            if(i == 0)
                return;
            if(i == 1)
                anInt657 = buffer.get1ByteAsInt();
            else
            if(i == 2)
            {
                int j = buffer.get1ByteAsInt();
                anIntArray658 = new int[j];
                for(int k = 0; k < j; k++)
                    anIntArray658[k] = buffer.get2ByteInt();

            } else
            if(i == 3)
                aBoolean662 = true;
            else
            if(i >= 40 && i < 50)
                anIntArray659[i - 40] = buffer.get2ByteInt();
            else
            if(i >= 50 && i < 60)
                anIntArray660[i - 50] = buffer.get2ByteInt();
            else
            if(i >= 60 && i < 70)
                anIntArray661[i - 60] = buffer.get2ByteInt();
            else
                System.out.println("Error unrecognised config code: " + i);
        } while(true);
    }

    public boolean method537(byte byte0)
    {
        if(anIntArray658 == null)
            return true;
        boolean flag = true;
        if(byte0 == 2)
        {
            byte0 = 0;
        } else
        {
            for(int i = 1; i > 0; i++);
        }
        for(int j = 0; j < anIntArray658.length; j++)
            if(!Model.isCached(anIntArray658[j]))
                flag = false;

        return flag;
    }

    public Model method538(boolean flag)
    {
        if(flag)
            throw new NullPointerException();
        if(anIntArray658 == null)
            return null;
        Model aclass30_sub2_sub4_sub6[] = new Model[anIntArray658.length];
        for(int i = 0; i < anIntArray658.length; i++)
            aclass30_sub2_sub4_sub6[i] = Model.getModel(anIntArray658[i]);

        Model model;
        if(aclass30_sub2_sub4_sub6.length == 1)
            model = aclass30_sub2_sub4_sub6[0];
        else
            model = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6);
        for(int j = 0; j < 6; j++)
        {
            if(anIntArray659[j] == 0)
                break;
            model.recolour(anIntArray659[j], anIntArray660[j]);
        }

        return model;
    }

    public boolean method539(boolean flag)
    {
        if(flag)
            throw new NullPointerException();
        boolean flag1 = true;
        for(int i = 0; i < 5; i++)
            if(anIntArray661[i] != -1 && !Model.isCached(anIntArray661[i]))
                flag1 = false;

        return flag1;
    }

    public Model method540(int i)
    {
        if(i != 0)
            throw new NullPointerException();
        Model aclass30_sub2_sub4_sub6[] = new Model[5];
        int j = 0;
        for(int k = 0; k < 5; k++)
            if(anIntArray661[k] != -1)
                aclass30_sub2_sub4_sub6[j++] = Model.getModel(anIntArray661[k]);

        Model model = new Model(j, aclass30_sub2_sub4_sub6);
        for(int l = 0; l < 6; l++)
        {
            if(anIntArray659[l] == 0)
                break;
            model.recolour(anIntArray659[l], anIntArray660[l]);
        }

        return model;
    }

    public Class38()
    {
        anInt654 = 9;
        anInt657 = -1;
        anIntArray659 = new int[6];
        anIntArray660 = new int[6];
        aBoolean662 = false;
    }

    public int anInt654;
    public static int anInt655;
    public static Class38 aClass38Array656[];
    public int anInt657;
    public int anIntArray658[];
    public int anIntArray659[];
    public int anIntArray660[];
    public int anIntArray661[] = {
        -1, -1, -1, -1, -1
    };
    public boolean aBoolean662;
}
