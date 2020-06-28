// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Item
{
    public static Item itemCache[];
    public static Class12 imageCache = new Class12(false, 100);
    public static Class12 modelCache = new Class12(false, 50);
    public static boolean aBoolean182 = true;
    public static Buffer itemData;
    public static int anInt180;
    public static int itemCount;
    public static int itemIndices[];
    public static boolean aBoolean187;

    public static void initItems(JagexArchive jagexArchive)
    {
        itemData = new Buffer(jagexArchive.getFile("obj.dat"));
        Buffer buffer = new Buffer(jagexArchive.getFile("obj.idx"));
        itemCount = buffer.get2ByteInt();
        itemIndices = new int[itemCount];

        int i = 2;
        for (int j = 0; j < itemCount; j++) {
            itemIndices[j] = i;
            i += buffer.get2ByteInt();
        }

        itemCache = new Item[10];
        for (int k = 0; k < 10; k++) {
            itemCache[k] = new Item();
        }
    }

    public byte aByte154;
    public int anInt155;
    public int modelRecolourOriginal[];
    public int anInt157;
    public int modelRecolourNew[];
    public boolean aBoolean161;
    public int anInt162;
    public int anInt163;
    public int anInt164;
    public int anInt165;
    public int equippedModelMale2;
    public int anInt167;
    public String aStringArray168[];
    public int anInt169;
    public String aString170;
    public int anInt171;
    public int equippedModelFemale2;
    public int anInt174;
    public int equippedModelMale1;
    public boolean aBoolean176;
    public int anInt177;
    public byte aByteArray178[];
    public int anInt179;
    public int anInt181;
    public int anInt184;
    public int anInt185;
    public boolean aBoolean186;
    public int anInt188;
    public String aStringArray189[];
    public int anInt190;
    public int anInt191;
    public int anInt192;
    public int anIntArray193[];
    public int anInt194;
    public int anInt196;
    public int equippedModelFemale1;
    public int anInt198;
    public int anInt199;
    public int anInt200;
    public int anIntArray201[];
    public int anInt202;
    public int anInt204;
    public byte aByte205;
    public boolean aBoolean206;

    public Item()
    {
        anInt157 = -1;
        anInt171 = 9;
        anInt177 = 9;
        aBoolean186 = false;
        aBoolean206 = false;
    }

    public Model method194(int gender)
    {
        int dialogueModelId = equippedModelMale1;
        int dialogHatModelId = equippedModelMale2;
        if (gender == 1) {
            dialogueModelId = equippedModelFemale1;
            dialogHatModelId = equippedModelFemale2;
        }

        if (dialogueModelId == -1) {
            return null;
        }

        Model dialogueModel = Model.getModel(dialogueModelId);

        if (dialogHatModelId != -1) {
            Model hatModel = Model.getModel(dialogHatModelId);
            Model models[] = {
                dialogueModel,
                hatModel
            };
            dialogueModel = new Model(2, models);
        }

        if (modelRecolourOriginal != null) {
            for (int i = 0; i < modelRecolourOriginal.length; i++) {
                dialogueModel.recolour(modelRecolourOriginal[i], modelRecolourNew[i]);
            }

        }

        return dialogueModel;
    }

    public static void method191()
    {
        modelCache = null;
        imageCache = null;
        itemIndices = null;
        itemCache = null;
        itemData = null;
    }

    public boolean isEquippedModelCached(int gender)
    {
        int primaryModel = equippedModelMale1;
        int secondaryModel = equippedModelMale2;
        if (gender == 1) {
            primaryModel = equippedModelFemale1;
            secondaryModel = equippedModelFemale2;
        }
        if (primaryModel == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.isCached(primaryModel)) {
            flag = false;
        }
        if (secondaryModel != -1 && !Model.isCached(secondaryModel)) {
            flag = false;
        }
        return flag;
    }

    public boolean method195(int i, int j)
    {
        if(i != 40903)
            aBoolean206 = !aBoolean206;
        int k = anInt165;
        int l = anInt188;
        int i1 = anInt185;
        if(j == 1)
        {
            k = anInt200;
            l = anInt164;
            i1 = anInt162;
        }
        if(k == -1)
            return true;
        boolean flag = true;
        if(!Model.isCached(k))
            flag = false;
        if(l != -1 && !Model.isCached(l))
            flag = false;
        if(i1 != -1 && !Model.isCached(i1))
            flag = false;
        return flag;
    }

    public Model method196(int gender)
    {
        int j = anInt165;
        int k = anInt188;
        int l = anInt185;
        if (gender == 1) {
            j = anInt200;
            k = anInt164;
            l = anInt162;
        }
        if (j == -1) {
            return null;
        }
        Model model = Model.getModel(j);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.getModel(k);
                Model model_3 = Model.getModel(l);
                Model models[] = {
                        model, model_1, model_3
                };
                model = new Model(3, models);
            } else {
                Model class30_sub2_sub4_sub6_2 = Model.getModel(k);
                Model aclass30_sub2_sub4_sub6[] = {
                        model, class30_sub2_sub4_sub6_2
                };
                model = new Model(2, aclass30_sub2_sub4_sub6);
            }
        }
        if (gender == 0 && aByte205 != 0) {
            model.method475(0, aByte205, 16384, 0);
        }
        if (gender == 1 && aByte154 != 0) {
            model.method475(0, aByte154, 16384, 0);
        }
        if (modelRecolourOriginal != null) {
            for (int i1 = 0; i1 < modelRecolourOriginal.length; i1++) {
                model.recolour(modelRecolourOriginal[i1], modelRecolourNew[i1]);
            }

        }
        return model;
    }

    public void method197()
    {
        anInt174 = 0;
        aString170 = null;
        aByteArray178 = null;
        modelRecolourOriginal = null;
        modelRecolourNew = null;
        anInt181 = 2000;
        anInt190 = 0;
        anInt198 = 0;
        anInt204 = 0;
        anInt169 = 0;
        anInt194 = 0;
        anInt199 = -1;
        aBoolean176 = false;
        anInt155 = 1;
        aBoolean161 = false;
        aStringArray168 = null;
        aStringArray189 = null;
        anInt165 = -1;
        anInt188 = -1;
        aByte205 = 0;
        anInt200 = -1;
        anInt164 = -1;
        aByte154 = 0;
        anInt185 = -1;
        anInt162 = -1;
        equippedModelMale1 = -1;
        equippedModelMale2 = -1;
        equippedModelFemale1 = -1;
        equippedModelFemale2 = -1;
        anIntArray193 = null;
        anIntArray201 = null;
        anInt179 = -1;
        anInt163 = -1;
        anInt167 = 128;
        anInt192 = 128;
        anInt191 = 128;
        anInt196 = 0;
        anInt184 = 0;
        anInt202 = 0;
    }

    public static Item method198(int i)
    {
        for(int j = 0; j < 10; j++)
            if(itemCache[j].anInt157 == i)
                return itemCache[j];

        anInt180 = (anInt180 + 1) % 10;
        Item item = itemCache[anInt180];
        itemData.pointer = itemIndices[i];
        item.anInt157 = i;
        item.method197();
        item.method203(true, itemData);
        if(item.anInt163 != -1)
            item.method199((byte)61);
        if(!aBoolean182 && item.aBoolean161)
        {
            item.aString170 = "Members Object";
            item.aByteArray178 = "Login to a members' server to use this object.".getBytes();
            item.aStringArray168 = null;
            item.aStringArray189 = null;
            item.anInt202 = 0;
        }
        return item;
    }

    public void method199(byte byte0)
    {
        Item item = method198(anInt163);
        anInt174 = item.anInt174;
        anInt181 = item.anInt181;
        anInt190 = item.anInt190;
        anInt198 = item.anInt198;
        anInt204 = item.anInt204;
        anInt169 = item.anInt169;
        anInt194 = item.anInt194;
        if(byte0 != 61)
            aBoolean186 = !aBoolean186;
        modelRecolourOriginal = item.modelRecolourOriginal;
        modelRecolourNew = item.modelRecolourNew;
        Item item_1 = method198(anInt179);
        aString170 = item_1.aString170;
        aBoolean161 = item_1.aBoolean161;
        anInt155 = item_1.anInt155;
        String s = "a";
        char c = item_1.aString170.charAt(0);
        if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            s = "an";
        aByteArray178 = ("Swap this note at any bank for " + s + " " + item_1.aString170 + ".").getBytes();
        aBoolean176 = true;
    }

    public static Class30_Sub2_Sub1_Sub1 method200(int i, int j, int k, int l)
    {
        if(k == 0)
        {
            Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1 = (Class30_Sub2_Sub1_Sub1) imageCache.get(i);
            if(class30_sub2_sub1_sub1 != null && class30_sub2_sub1_sub1.anInt1445 != j && class30_sub2_sub1_sub1.anInt1445 != -1)
            {
                class30_sub2_sub1_sub1.method329();
                class30_sub2_sub1_sub1 = null;
            }
            if(class30_sub2_sub1_sub1 != null)
                return class30_sub2_sub1_sub1;
        }
        Item item = method198(i);
        if(item.anIntArray193 == null)
            j = -1;
        if(j > 1)
        {
            int i1 = -1;
            for(int j1 = 0; j1 < 10; j1++)
                if(j >= item.anIntArray201[j1] && item.anIntArray201[j1] != 0)
                    i1 = item.anIntArray193[j1];

            if(i1 != -1)
                item = method198(i1);
        }
        Model model = item.method201(1);
        if(model == null)
            return null;
        Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1_2 = null;
        if(item.anInt163 != -1)
        {
            class30_sub2_sub1_sub1_2 = method200(item.anInt179, 10, -1, 9);
            if(class30_sub2_sub1_sub1_2 == null)
                return null;
        }
        Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1_1 = new Class30_Sub2_Sub1_Sub1(32, 32);
        int k1 = Class30_Sub2_Sub1_Sub3.anInt1466;
        int l1 = Class30_Sub2_Sub1_Sub3.anInt1467;
        int ai[] = Class30_Sub2_Sub1_Sub3.anIntArray1472;
        int ai1[] = Class30_Sub2_Sub1.anIntArray1378;
        int i2 = Class30_Sub2_Sub1.anInt1379;
        int j2 = Class30_Sub2_Sub1.anInt1380;
        int k2 = Class30_Sub2_Sub1.anInt1383;
        int l2 = Class30_Sub2_Sub1.anInt1384;
        int i3 = Class30_Sub2_Sub1.anInt1381;
        int j3 = Class30_Sub2_Sub1.anInt1382;
        Class30_Sub2_Sub1_Sub3.aBoolean1464 = false;
        Class30_Sub2_Sub1.method331(32, 32, class30_sub2_sub1_sub1_1.anIntArray1439);
        Class30_Sub2_Sub1.method336(32, 0, 0, 0, 32, 0);
        Class30_Sub2_Sub1_Sub3.method364((byte)6);
        int k3 = item.anInt181;
        if(k == -1)
            k3 = (int)((double)k3 * 1.5D);
        if(k > 0)
            k3 = (int)((double)k3 * 1.04D);
        int l3 = Class30_Sub2_Sub1_Sub3.anIntArray1470[item.anInt190] * k3 >> 16;
        int i4 = Class30_Sub2_Sub1_Sub3.anIntArray1471[item.anInt190] * k3 >> 16;
        model.method482(0, item.anInt198, item.anInt204, item.anInt190, item.anInt169, l3 + ((Class30_Sub2_Sub4) (model)).anInt1426 / 2 + item.anInt194, i4 + item.anInt194);
        for(int i5 = 31; i5 >= 0; i5--)
        {
            for(int j4 = 31; j4 >= 0; j4--)
                if(class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] == 0)
                    if(i5 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(i5 - 1) + j4 * 32] > 1)
                        class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
                    else
                    if(j4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + (j4 - 1) * 32] > 1)
                        class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
                    else
                    if(i5 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + 1 + j4 * 32] > 1)
                        class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
                    else
                    if(j4 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + (j4 + 1) * 32] > 1)
                        class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;

        }

        if(k > 0)
        {
            for(int j5 = 31; j5 >= 0; j5--)
            {
                for(int k4 = 31; k4 >= 0; k4--)
                    if(class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] == 0)
                        if(j5 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(j5 - 1) + k4 * 32] == 1)
                            class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
                        else
                        if(k4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + (k4 - 1) * 32] == 1)
                            class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
                        else
                        if(j5 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + 1 + k4 * 32] == 1)
                            class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
                        else
                        if(k4 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + (k4 + 1) * 32] == 1)
                            class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;

            }

        } else
        if(k == 0)
        {
            for(int k5 = 31; k5 >= 0; k5--)
            {
                for(int l4 = 31; l4 >= 0; l4--)
                    if(class30_sub2_sub1_sub1_1.anIntArray1439[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(k5 - 1) + (l4 - 1) * 32] > 0)
                        class30_sub2_sub1_sub1_1.anIntArray1439[k5 + l4 * 32] = 0x302020;

            }

        }
        if(item.anInt163 != -1)
        {
            int l5 = class30_sub2_sub1_sub1_2.anInt1444;
            int j6 = class30_sub2_sub1_sub1_2.anInt1445;
            class30_sub2_sub1_sub1_2.anInt1444 = 32;
            class30_sub2_sub1_sub1_2.anInt1445 = 32;
            class30_sub2_sub1_sub1_2.method348(0, 16083, 0);
            class30_sub2_sub1_sub1_2.anInt1444 = l5;
            class30_sub2_sub1_sub1_2.anInt1445 = j6;
        }
        if(k == 0)
            imageCache.method223(class30_sub2_sub1_sub1_1, i, (byte)2);
        Class30_Sub2_Sub1.method331(j2, i2, ai1);
        Class30_Sub2_Sub1.method333(j3, k2, false, l2, i3);
        Class30_Sub2_Sub1_Sub3.anInt1466 = k1;
        Class30_Sub2_Sub1_Sub3.anInt1467 = l1;
        Class30_Sub2_Sub1_Sub3.anIntArray1472 = ai;
        Class30_Sub2_Sub1_Sub3.aBoolean1464 = true;
        if(l < 9 || l > 9)
        {
            for(int i6 = 1; i6 > 0; i6++);
        }
        if(item.aBoolean176)
            class30_sub2_sub1_sub1_1.anInt1444 = 33;
        else
            class30_sub2_sub1_sub1_1.anInt1444 = 32;
        class30_sub2_sub1_sub1_1.anInt1445 = j;
        return class30_sub2_sub1_sub1_1;
    }

    public Model method201(int i)
    {
        if(anIntArray193 != null && i > 1)
        {
            int j = -1;
            for(int k = 0; k < 10; k++)
                if(i >= anIntArray201[k] && anIntArray201[k] != 0)
                    j = anIntArray193[k];

            if(j != -1)
                return method198(j).method201(1);
        }
        Model model = (Model) modelCache.get(anInt157);
        if(model != null)
            return model;
        model = Model.getModel(anInt174);
        if(model == null)
            return null;
        if(anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
            model.method478(anInt167, anInt191, anInt177, anInt192);
        if(modelRecolourOriginal != null)
        {
            for(int l = 0; l < modelRecolourOriginal.length; l++)
                model.recolour(modelRecolourOriginal[l], modelRecolourNew[l]);

        }
        model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
        model.aBoolean1659 = true;
        modelCache.method223(model, anInt157, (byte)2);
        return model;
    }

    public Model method202(int i, boolean flag)
    {
        if(anIntArray193 != null && i > 1)
        {
            int j = -1;
            for(int k = 0; k < 10; k++)
                if(i >= anIntArray201[k] && anIntArray201[k] != 0)
                    j = anIntArray193[k];

            if(j != -1)
                return method198(j).method202(1, true);
        }
        Model model = Model.getModel(anInt174);
        if(!flag)
            throw new NullPointerException();
        if(model == null)
            return null;
        if(modelRecolourOriginal != null)
        {
            for(int l = 0; l < modelRecolourOriginal.length; l++)
                model.recolour(modelRecolourOriginal[l], modelRecolourNew[l]);

        }
        return model;
    }

    public void method203(boolean flag, Buffer buffer)
    {
        if(!flag)
            throw new NullPointerException();
        do
        {
            int i = buffer.get1ByteAsInt();
            if(i == 0)
                return;
            if(i == 1)
                anInt174 = buffer.get2ByteInt();
            else
            if(i == 2)
                aString170 = buffer.readString();
            else
            if(i == 3)
                aByteArray178 = buffer.readStringBytes();
            else
            if(i == 4)
                anInt181 = buffer.get2ByteInt();
            else
            if(i == 5)
                anInt190 = buffer.get2ByteInt();
            else
            if(i == 6)
                anInt198 = buffer.get2ByteInt();
            else
            if(i == 7)
            {
                anInt169 = buffer.get2ByteInt();
                if(anInt169 > 32767)
                    anInt169 -= 0x10000;
            } else
            if(i == 8)
            {
                anInt194 = buffer.get2ByteInt();
                if(anInt194 > 32767)
                    anInt194 -= 0x10000;
            } else
            if(i == 10)
                anInt199 = buffer.get2ByteInt();
            else
            if(i == 11)
                aBoolean176 = true;
            else
            if(i == 12)
                anInt155 = buffer.get4ByteInt();
            else
            if(i == 16)
                aBoolean161 = true;
            else
            if(i == 23)
            {
                anInt165 = buffer.get2ByteInt();
                aByte205 = buffer.method409();
            } else
            if(i == 24)
                anInt188 = buffer.get2ByteInt();
            else
            if(i == 25)
            {
                anInt200 = buffer.get2ByteInt();
                aByte154 = buffer.method409();
            } else
            if(i == 26)
                anInt164 = buffer.get2ByteInt();
            else
            if(i >= 30 && i < 35)
            {
                if(aStringArray168 == null)
                    aStringArray168 = new String[5];
                aStringArray168[i - 30] = buffer.readString();
                if(aStringArray168[i - 30].equalsIgnoreCase("hidden"))
                    aStringArray168[i - 30] = null;
            } else
            if(i >= 35 && i < 40)
            {
                if(aStringArray189 == null)
                    aStringArray189 = new String[5];
                aStringArray189[i - 35] = buffer.readString();
            } else
            if(i == 40)
            {
                int j = buffer.get1ByteAsInt();
                modelRecolourOriginal = new int[j];
                modelRecolourNew = new int[j];
                for(int k = 0; k < j; k++)
                {
                    modelRecolourOriginal[k] = buffer.get2ByteInt();
                    modelRecolourNew[k] = buffer.get2ByteInt();
                }

            } else
            if(i == 78)
                anInt185 = buffer.get2ByteInt();
            else
            if(i == 79)
                anInt162 = buffer.get2ByteInt();
            else
            if(i == 90)
                equippedModelMale1 = buffer.get2ByteInt();
            else
            if(i == 91)
                equippedModelFemale1 = buffer.get2ByteInt();
            else
            if(i == 92)
                equippedModelMale2 = buffer.get2ByteInt();
            else
            if(i == 93)
                equippedModelFemale2 = buffer.get2ByteInt();
            else
            if(i == 95)
                anInt204 = buffer.get2ByteInt();
            else
            if(i == 97)
                anInt179 = buffer.get2ByteInt();
            else
            if(i == 98)
                anInt163 = buffer.get2ByteInt();
            else
            if(i >= 100 && i < 110)
            {
                if(anIntArray193 == null)
                {
                    anIntArray193 = new int[10];
                    anIntArray201 = new int[10];
                }
                anIntArray193[i - 100] = buffer.get2ByteInt();
                anIntArray201[i - 100] = buffer.get2ByteInt();
            } else
            if(i == 110)
                anInt167 = buffer.get2ByteInt();
            else
            if(i == 111)
                anInt192 = buffer.get2ByteInt();
            else
            if(i == 112)
                anInt191 = buffer.get2ByteInt();
            else
            if(i == 113)
                anInt196 = buffer.method409();
            else
            if(i == 114)
                anInt184 = buffer.method409() * 5;
            else
            if(i == 115)
                anInt202 = buffer.get1ByteAsInt();
        } while(true);
    }



}
