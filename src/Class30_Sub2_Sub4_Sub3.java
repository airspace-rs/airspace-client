// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class30_Sub2_Sub4_Sub3 extends Class30_Sub2_Sub4
{

    public Class30_Sub2_Sub4_Sub3(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        aBoolean1565 = true;
        anInt1566 = 9;
        aBoolean1567 = false;
        aClass23_1568 = Class23.aClass23Array403[i1];
        anInt1560 = i;
        anInt1561 = l1;
        anInt1562 = k1;
        anInt1563 = j1;
        anInt1564 = j + l;
        if(k != 6)
        {
            throw new NullPointerException();
        } else
        {
            aBoolean1567 = false;
            return;
        }
    }

    public Model method444(int i)
    {
        if(i != 4016)
            throw new NullPointerException();
        Model model = aClass23_1568.method266();
        if(model == null)
            return null;
        int j = aClass23_1568.aClass20_407.anIntArray353[anInt1569];
        Model model_1 = new Model(9, true, Animation.method532(j, false), false, model);
        if(!aBoolean1567)
        {
            model_1.method469((byte)-71);
            model_1.method470(j, 40542);
            model_1.anIntArrayArray1658 = null;
            model_1.anIntArrayArray1657 = null;
        }
        if(aClass23_1568.anInt410 != 128 || aClass23_1568.anInt411 != 128)
            model_1.method478(aClass23_1568.anInt410, aClass23_1568.anInt410, anInt1566, aClass23_1568.anInt411);
        if(aClass23_1568.anInt412 != 0)
        {
            if(aClass23_1568.anInt412 == 90)
                model_1.method473(360);
            if(aClass23_1568.anInt412 == 180)
            {
                model_1.method473(360);
                model_1.method473(360);
            }
            if(aClass23_1568.anInt412 == 270)
            {
                model_1.method473(360);
                model_1.method473(360);
                model_1.method473(360);
            }
        }
        model_1.method479(64 + aClass23_1568.anInt413, 850 + aClass23_1568.anInt414, -30, -50, -30, true);
        return model_1;
    }

    public void method454(int i, boolean flag)
    {
        if(!flag)
        {
            for(int j = 1; j > 0; j++);
        }
        for(anInt1570 += i; anInt1570 > aClass23_1568.aClass20_407.method258(anInt1569, (byte)-39);)
        {
            anInt1570 -= aClass23_1568.aClass20_407.method258(anInt1569, (byte)-39) + 1;
            anInt1569++;
            if(anInt1569 >= aClass23_1568.aClass20_407.anInt352 && (anInt1569 < 0 || anInt1569 >= aClass23_1568.aClass20_407.anInt352))
            {
                anInt1569 = 0;
                aBoolean1567 = true;
            }
        }

    }

    public int anInt1560;
    public int anInt1561;
    public int anInt1562;
    public int anInt1563;
    public int anInt1564;
    public boolean aBoolean1565;
    public int anInt1566;
    public boolean aBoolean1567;
    public Class23 aClass23_1568;
    public int anInt1569;
    public int anInt1570;
}
