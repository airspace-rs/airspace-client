// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class30_Sub2_Sub4_Sub1_Sub2 extends Class30_Sub2_Sub4_Sub1
{

    public Model method444(int i)
    {
        if(i != 4016)
            aBoolean1716 = !aBoolean1716;
        if(!aBoolean1710)
            return null;
        Model model = method452(0);
        if(model == null)
            return null;
        super.anInt1507 = ((Class30_Sub2_Sub4) (model)).anInt1426;
        model.aBoolean1659 = true;
        if(aBoolean1699)
            return model;
        if(super.anInt1520 != -1 && super.anInt1521 != -1)
        {
            Class23 class23 = Class23.aClass23Array403[super.anInt1520];
            Model class30_sub2_sub4_sub6_2 = class23.method266();
            if(class30_sub2_sub4_sub6_2 != null)
            {
                Model model_3 = new Model(9, true, Animation.method532(super.anInt1521, false), false, class30_sub2_sub4_sub6_2);
                model_3.method475(0, -super.anInt1524, 16384, 0);
                model_3.method469((byte)-71);
                model_3.method470(class23.aClass20_407.anIntArray353[super.anInt1521], 40542);
                model_3.anIntArrayArray1658 = null;
                model_3.anIntArrayArray1657 = null;
                if(class23.anInt410 != 128 || class23.anInt411 != 128)
                    model_3.method478(class23.anInt410, class23.anInt410, anInt1715, class23.anInt411);
                model_3.method479(64 + class23.anInt413, 850 + class23.anInt414, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6_1[] = {
                        model, model_3
                };
                model = new Model(2, -819, true, aclass30_sub2_sub4_sub6_1);
            }
        }
        if(aModel_1714 != null)
        {
            if(Client.anInt1161 >= anInt1708)
                aModel_1714 = null;
            if(Client.anInt1161 >= anInt1707 && Client.anInt1161 < anInt1708)
            {
                Model model_1 = aModel_1714;
                model_1.method475(anInt1711 - super.anInt1550, anInt1712 - anInt1709, 16384, anInt1713 - super.anInt1551);
                if(super.anInt1510 == 512)
                {
                    model_1.method473(360);
                    model_1.method473(360);
                    model_1.method473(360);
                } else
                if(super.anInt1510 == 1024)
                {
                    model_1.method473(360);
                    model_1.method473(360);
                } else
                if(super.anInt1510 == 1536)
                    model_1.method473(360);
                Model aclass30_sub2_sub4_sub6[] = {
                        model, model_1
                };
                model = new Model(2, -819, true, aclass30_sub2_sub4_sub6);
                if(super.anInt1510 == 512)
                    model_1.method473(360);
                else
                if(super.anInt1510 == 1024)
                {
                    model_1.method473(360);
                    model_1.method473(360);
                } else
                if(super.anInt1510 == 1536)
                {
                    model_1.method473(360);
                    model_1.method473(360);
                    model_1.method473(360);
                }
                model_1.method475(super.anInt1550 - anInt1711, anInt1709 - anInt1712, 16384, super.anInt1551 - anInt1713);
            }
        }
        model.aBoolean1659 = true;
        return model;
    }

    public void method451(int i, Buffer buffer)
    {
        buffer.pointer = 0;
        gender = buffer.get1ByteAsInt();
        anInt1706 = buffer.get1ByteAsInt();
        if(i != 0)
            return;
        aNPC_Def_1698 = null;
        anInt1701 = 0;
        for(int j = 0; j < 12; j++)
        {
            int k = buffer.get1ByteAsInt();
            if(k == 0)
            {
                anIntArray1717[j] = 0;
                continue;
            }
            int i1 = buffer.get1ByteAsInt();
            anIntArray1717[j] = (k << 8) + i1;
            if(j == 0 && anIntArray1717[0] == 65535)
            {
                aNPC_Def_1698 = NPC.getForId(buffer.get2ByteInt());
                break;
            }
            if(anIntArray1717[j] >= 512 && anIntArray1717[j] - 512 < Item.itemCount)
            {
                int l1 = Item.method198(anIntArray1717[j] - 512).anInt202;
                if(l1 != 0)
                    anInt1701 = l1;
            }
        }

        for(int l = 0; l < 5; l++)
        {
            int j1 = buffer.get1ByteAsInt();
            if(j1 < 0 || j1 >= Client.anIntArrayArray1003[l].length)
                j1 = 0;
            anIntArray1700[l] = j1;
        }

        super.anInt1511 = buffer.get2ByteInt();
        if(super.anInt1511 == 65535)
            super.anInt1511 = -1;
        super.anInt1512 = buffer.get2ByteInt();
        if(super.anInt1512 == 65535)
            super.anInt1512 = -1;
        super.anInt1554 = buffer.get2ByteInt();
        if(super.anInt1554 == 65535)
            super.anInt1554 = -1;
        super.anInt1555 = buffer.get2ByteInt();
        if(super.anInt1555 == 65535)
            super.anInt1555 = -1;
        super.anInt1556 = buffer.get2ByteInt();
        if(super.anInt1556 == 65535)
            super.anInt1556 = -1;
        super.anInt1557 = buffer.get2ByteInt();
        if(super.anInt1557 == 65535)
            super.anInt1557 = -1;
        super.anInt1505 = buffer.get2ByteInt();
        if(super.anInt1505 == 65535)
            super.anInt1505 = -1;
        aString1703 = Class50.method587(-45804, Class50.method584(buffer.method414(-35089), (byte)-99));
        anInt1705 = buffer.get1ByteAsInt();
        anInt1723 = buffer.get2ByteInt();
        aBoolean1710 = true;
        aLong1718 = 0L;
        for(int k1 = 0; k1 < 12; k1++)
        {
            aLong1718 <<= 4;
            if(anIntArray1717[k1] >= 256)
                aLong1718 += anIntArray1717[k1] - 256;
        }

        if(anIntArray1717[0] >= 256)
            aLong1718 += anIntArray1717[0] - 256 >> 4;
        if(anIntArray1717[1] >= 256)
            aLong1718 += anIntArray1717[1] - 256 >> 8;
        for(int i2 = 0; i2 < 5; i2++)
        {
            aLong1718 <<= 3;
            aLong1718 += anIntArray1700[i2];
        }

        aLong1718 <<= 1;
        aLong1718 += gender;
    }

    public Model method452(int i)
    {
        if(aNPC_Def_1698 != null)
        {
            int j = -1;
            if(super.anInt1526 >= 0 && super.anInt1529 == 0)
                j = Class20.aClass20Array351[super.anInt1526].anIntArray353[super.anInt1527];
            else
            if(super.anInt1517 >= 0)
                j = Class20.aClass20Array351[super.anInt1517].anIntArray353[super.anInt1518];
            Model model = aNPC_Def_1698.method164(j, -1, null);
            return model;
        }
        long l = aLong1718;
        int k = -1;
        int i1 = -1;
        int j1 = -1;
        int k1 = -1;
        if(super.anInt1526 >= 0 && super.anInt1529 == 0)
        {
            Class20 class20 = Class20.aClass20Array351[super.anInt1526];
            k = class20.anIntArray353[super.anInt1527];
            if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511)
                i1 = Class20.aClass20Array351[super.anInt1517].anIntArray353[super.anInt1518];
            if(class20.anInt360 >= 0)
            {
                j1 = class20.anInt360;
                l += j1 - anIntArray1717[5] << 40;
            }
            if(class20.anInt361 >= 0)
            {
                k1 = class20.anInt361;
                l += k1 - anIntArray1717[3] << 48;
            }
        } else
        if(super.anInt1517 >= 0)
            k = Class20.aClass20Array351[super.anInt1517].anIntArray353[super.anInt1518];
        Model model_1 = (Model)aClass12_1704.get(l);
        if(i != 0)
        {
            for(int l1 = 1; l1 > 0; l1++);
        }
        if(model_1 == null)
        {
            boolean flag = false;
            for(int i2 = 0; i2 < 12; i2++)
            {
                int k2 = anIntArray1717[i2];
                if(k1 >= 0 && i2 == 3)
                    k2 = k1;
                if(j1 >= 0 && i2 == 5)
                    k2 = j1;
                if(k2 >= 256 && k2 < 512 && !Class38.aClass38Array656[k2 - 256].method537((byte)2))
                    flag = true;
                if(k2 >= 512 && !Item.method198(k2 - 512).method195(40903, gender))
                    flag = true;
            }

            if(flag)
            {
                if(aLong1697 != -1L)
                    model_1 = (Model)aClass12_1704.get(aLong1697);
                if(model_1 == null)
                    return null;
            }
        }
        if(model_1 == null)
        {
            Model aclass30_sub2_sub4_sub6[] = new Model[12];
            int j2 = 0;
            for(int l2 = 0; l2 < 12; l2++)
            {
                int i3 = anIntArray1717[l2];
                if(k1 >= 0 && l2 == 3)
                    i3 = k1;
                if(j1 >= 0 && l2 == 5)
                    i3 = j1;
                if(i3 >= 256 && i3 < 512)
                {
                    Model model_3 = Class38.aClass38Array656[i3 - 256].method538(false);
                    if(model_3 != null)
                        aclass30_sub2_sub4_sub6[j2++] = model_3;
                }
                if(i3 >= 512)
                {
                    Model class30_sub2_sub4_sub6_4 = Item.method198(i3 - 512).method196(gender);
                    if(class30_sub2_sub4_sub6_4 != null)
                        aclass30_sub2_sub4_sub6[j2++] = class30_sub2_sub4_sub6_4;
                }
            }

            model_1 = new Model(j2, aclass30_sub2_sub4_sub6);
            for(int j3 = 0; j3 < 5; j3++)
                if(anIntArray1700[j3] != 0)
                {
                    model_1.recolour(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][anIntArray1700[j3]]);
                    if(j3 == 1)
                        model_1.recolour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j3]]);
                }

            model_1.method469((byte)-71);
            model_1.method479(64, 850, -30, -50, -30, true);
            aClass12_1704.method223(model_1, l, (byte)2);
            aLong1697 = l;
        }
        if(aBoolean1699)
            return model_1;
        Model class30_sub2_sub4_sub6_2 = Model.aModel_1621;
        class30_sub2_sub4_sub6_2.method464(7, model_1, Animation.method532(k, false) & Animation.method532(i1, false));
        if(k != -1 && i1 != -1)
            class30_sub2_sub4_sub6_2.method471(-20491, Class20.aClass20Array351[super.anInt1526].anIntArray357, i1, k);
        else
        if(k != -1)
            class30_sub2_sub4_sub6_2.method470(k, 40542);
        class30_sub2_sub4_sub6_2.method466(false);
        class30_sub2_sub4_sub6_2.anIntArrayArray1658 = null;
        class30_sub2_sub4_sub6_2.anIntArrayArray1657 = null;
        return class30_sub2_sub4_sub6_2;
    }

    public boolean method449(boolean flag)
    {
        if(!flag)
            throw new NullPointerException();
        return aBoolean1710;
    }

    public Model method453(byte byte0)
    {
        if(byte0 != -41)
            anInt1715 = 132;
        if(!aBoolean1710)
            return null;
        if(aNPC_Def_1698 != null)
            return aNPC_Def_1698.getHeadModel();
        boolean flag = false;
        for(int i = 0; i < 12; i++)
        {
            int j = anIntArray1717[i];
            if(j >= 256 && j < 512 && !Class38.aClass38Array656[j - 256].method539(false))
                flag = true;
            if(j >= 512 && !Item.method198(j - 512).isEquippedModelCached(gender))
                flag = true;
        }

        if(flag)
            return null;
        Model aclass30_sub2_sub4_sub6[] = new Model[12];
        int k = 0;
        for(int l = 0; l < 12; l++)
        {
            int i1 = anIntArray1717[l];
            if(i1 >= 256 && i1 < 512)
            {
                Model model_1 = Class38.aClass38Array656[i1 - 256].method540(0);
                if(model_1 != null)
                    aclass30_sub2_sub4_sub6[k++] = model_1;
            }
            if(i1 >= 512)
            {
                Model class30_sub2_sub4_sub6_2 = Item.method198(i1 - 512).method194(gender);
                if(class30_sub2_sub4_sub6_2 != null)
                    aclass30_sub2_sub4_sub6[k++] = class30_sub2_sub4_sub6_2;
            }
        }

        Model model = new Model(k, aclass30_sub2_sub4_sub6);
        for(int j1 = 0; j1 < 5; j1++)
            if(anIntArray1700[j1] != 0)
            {
                model.recolour(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][anIntArray1700[j1]]);
                if(j1 == 1)
                    model.recolour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j1]]);
            }

        return model;
    }

    public Class30_Sub2_Sub4_Sub1_Sub2()
    {
        aLong1697 = -1L;
        aBoolean1699 = false;
        anIntArray1700 = new int[5];
        aBoolean1710 = false;
        anInt1715 = 9;
        aBoolean1716 = true;
        anIntArray1717 = new int[12];
    }

    public long aLong1697;
    public NPC aNPC_Def_1698;
    public boolean aBoolean1699;
    public int anIntArray1700[];
    public int anInt1701;
    public int gender;
    public String aString1703;
    public static Class12 aClass12_1704 = new Class12(false, 260);
    public int anInt1705;
    public int anInt1706;
    public int anInt1707;
    public int anInt1708;
    public int anInt1709;
    public boolean aBoolean1710;
    public int anInt1711;
    public int anInt1712;
    public int anInt1713;
    public Model aModel_1714;
    public int anInt1715;
    public boolean aBoolean1716;
    public int anIntArray1717[];
    public long aLong1718;
    public int anInt1719;
    public int anInt1720;
    public int anInt1721;
    public int anInt1722;
    public int anInt1723;

}
