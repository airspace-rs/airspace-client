// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class9
{

    public void method204(int i, byte byte0, int j)
    {
        int k = anIntArray253[i];
        anIntArray253[i] = anIntArray253[j];
        if(byte0 == 9)
            byte0 = 0;
        else
            anInt229 = -76;
        anIntArray253[j] = k;
        k = anIntArray252[i];
        anIntArray252[i] = anIntArray252[j];
        anIntArray252[j] = k;
    }

    public static void method205(JagexArchive jagexArchive, JagexFont aclass30_sub2_sub1_sub4[], byte byte0, JagexArchive jagexArchive_1)
    {
        aClass12_238 = new Class12(false, 50000);
        Buffer buffer = new Buffer(jagexArchive.getFile("data"));
        int i = -1;
        int j = buffer.get2ByteInt();
        aClass9Array210 = new Class9[j];
        while(buffer.pointer < buffer.data.length)
        {
            int k = buffer.get2ByteInt();
            if(k == 65535)
            {
                i = buffer.get2ByteInt();
                k = buffer.get2ByteInt();
            }
            Class9 class9 = aClass9Array210[k] = new Class9();
            class9.anInt250 = k;
            class9.anInt236 = i;
            class9.anInt262 = buffer.get1ByteAsInt();
            class9.anInt217 = buffer.get1ByteAsInt();
            class9.anInt214 = buffer.get2ByteInt();
            class9.anInt220 = buffer.get2ByteInt();
            class9.anInt267 = buffer.get2ByteInt();
            class9.aByte254 = (byte) buffer.get1ByteAsInt();
            class9.anInt230 = buffer.get1ByteAsInt();
            if(class9.anInt230 != 0)
                class9.anInt230 = (class9.anInt230 - 1 << 8) + buffer.get1ByteAsInt();
            else
                class9.anInt230 = -1;
            int i1 = buffer.get1ByteAsInt();
            if(i1 > 0)
            {
                class9.anIntArray245 = new int[i1];
                class9.anIntArray212 = new int[i1];
                for(int j1 = 0; j1 < i1; j1++)
                {
                    class9.anIntArray245[j1] = buffer.get1ByteAsInt();
                    class9.anIntArray212[j1] = buffer.get2ByteInt();
                }

            }
            int k1 = buffer.get1ByteAsInt();
            if(k1 > 0)
            {
                class9.anIntArrayArray226 = new int[k1][];
                for(int l1 = 0; l1 < k1; l1++)
                {
                    int i3 = buffer.get2ByteInt();
                    class9.anIntArrayArray226[l1] = new int[i3];
                    for(int l4 = 0; l4 < i3; l4++)
                        class9.anIntArrayArray226[l1][l4] = buffer.get2ByteInt();

                }

            }
            if(class9.anInt262 == 0)
            {
                class9.anInt261 = buffer.get2ByteInt();
                class9.aBoolean266 = buffer.get1ByteAsInt() == 1;
                int i2 = buffer.get2ByteInt();
                class9.anIntArray240 = new int[i2];
                class9.anIntArray241 = new int[i2];
                class9.anIntArray272 = new int[i2];
                for(int j3 = 0; j3 < i2; j3++)
                {
                    class9.anIntArray240[j3] = buffer.get2ByteInt();
                    class9.anIntArray241[j3] = buffer.method411();
                    class9.anIntArray272[j3] = buffer.method411();
                }

            }
            if(class9.anInt262 == 1)
            {
                class9.anInt211 = buffer.get2ByteInt();
                class9.aBoolean251 = buffer.get1ByteAsInt() == 1;
            }
            if(class9.anInt262 == 2)
            {
                class9.anIntArray253 = new int[class9.anInt220 * class9.anInt267];
                class9.anIntArray252 = new int[class9.anInt220 * class9.anInt267];
                class9.aBoolean259 = buffer.get1ByteAsInt() == 1;
                class9.aBoolean249 = buffer.get1ByteAsInt() == 1;
                class9.aBoolean242 = buffer.get1ByteAsInt() == 1;
                class9.aBoolean235 = buffer.get1ByteAsInt() == 1;
                class9.anInt231 = buffer.get1ByteAsInt();
                class9.anInt244 = buffer.get1ByteAsInt();
                class9.anIntArray215 = new int[20];
                class9.anIntArray247 = new int[20];
                class9.aClass30_Sub2_Sub1_Sub1Array209 = new Class30_Sub2_Sub1_Sub1[20];
                for(int j2 = 0; j2 < 20; j2++)
                {
                    int k3 = buffer.get1ByteAsInt();
                    if(k3 == 1)
                    {
                        class9.anIntArray215[j2] = buffer.method411();
                        class9.anIntArray247[j2] = buffer.method411();
                        String s1 = buffer.readString();
                        if(jagexArchive_1 != null && s1.length() > 0)
                        {
                            int i5 = s1.lastIndexOf(",");
                            class9.aClass30_Sub2_Sub1_Sub1Array209[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), false, jagexArchive_1, s1.substring(0, i5));
                        }
                    }
                }

                class9.aStringArray225 = new String[5];
                for(int l3 = 0; l3 < 5; l3++)
                {
                    class9.aStringArray225[l3] = buffer.readString();
                    if(class9.aStringArray225[l3].length() == 0)
                        class9.aStringArray225[l3] = null;
                }

            }
            if(class9.anInt262 == 3)
                class9.aBoolean227 = buffer.get1ByteAsInt() == 1;
            if(class9.anInt262 == 4 || class9.anInt262 == 1)
            {
                class9.aBoolean223 = buffer.get1ByteAsInt() == 1;
                int k2 = buffer.get1ByteAsInt();
                if(aclass30_sub2_sub1_sub4 != null)
                    class9.aJagexFont_243 = aclass30_sub2_sub1_sub4[k2];
                class9.aBoolean268 = buffer.get1ByteAsInt() == 1;
            }
            if(class9.anInt262 == 4)
            {
                class9.aString248 = buffer.readString();
                class9.aString228 = buffer.readString();
            }
            if(class9.anInt262 == 1 || class9.anInt262 == 3 || class9.anInt262 == 4)
                class9.anInt232 = buffer.get4ByteInt();
            if(class9.anInt262 == 3 || class9.anInt262 == 4)
            {
                class9.anInt219 = buffer.get4ByteInt();
                class9.anInt216 = buffer.get4ByteInt();
                class9.anInt239 = buffer.get4ByteInt();
            }
            if(class9.anInt262 == 5)
            {
                String s = buffer.readString();
                if(jagexArchive_1 != null && s.length() > 0)
                {
                    int i4 = s.lastIndexOf(",");
                    class9.aClass30_Sub2_Sub1_Sub1_207 = method207(Integer.parseInt(s.substring(i4 + 1)), false, jagexArchive_1, s.substring(0, i4));
                }
                s = buffer.readString();
                if(jagexArchive_1 != null && s.length() > 0)
                {
                    int j4 = s.lastIndexOf(",");
                    class9.aClass30_Sub2_Sub1_Sub1_260 = method207(Integer.parseInt(s.substring(j4 + 1)), false, jagexArchive_1, s.substring(0, j4));
                }
            }
            if(class9.anInt262 == 6)
            {
                int l = buffer.get1ByteAsInt();
                if(l != 0)
                {
                    class9.anInt233 = 1;
                    class9.anInt234 = (l - 1 << 8) + buffer.get1ByteAsInt();
                }
                l = buffer.get1ByteAsInt();
                if(l != 0)
                {
                    class9.anInt255 = 1;
                    class9.anInt256 = (l - 1 << 8) + buffer.get1ByteAsInt();
                }
                l = buffer.get1ByteAsInt();
                if(l != 0)
                    class9.anInt257 = (l - 1 << 8) + buffer.get1ByteAsInt();
                else
                    class9.anInt257 = -1;
                l = buffer.get1ByteAsInt();
                if(l != 0)
                    class9.anInt258 = (l - 1 << 8) + buffer.get1ByteAsInt();
                else
                    class9.anInt258 = -1;
                class9.anInt269 = buffer.get2ByteInt();
                class9.anInt270 = buffer.get2ByteInt();
                class9.anInt271 = buffer.get2ByteInt();
            }
            if(class9.anInt262 == 7)
            {
                class9.anIntArray253 = new int[class9.anInt220 * class9.anInt267];
                class9.anIntArray252 = new int[class9.anInt220 * class9.anInt267];
                class9.aBoolean223 = buffer.get1ByteAsInt() == 1;
                int l2 = buffer.get1ByteAsInt();
                if(aclass30_sub2_sub1_sub4 != null)
                    class9.aJagexFont_243 = aclass30_sub2_sub1_sub4[l2];
                class9.aBoolean268 = buffer.get1ByteAsInt() == 1;
                class9.anInt232 = buffer.get4ByteInt();
                class9.anInt231 = buffer.method411();
                class9.anInt244 = buffer.method411();
                class9.aBoolean249 = buffer.get1ByteAsInt() == 1;
                class9.aStringArray225 = new String[5];
                for(int k4 = 0; k4 < 5; k4++)
                {
                    class9.aStringArray225[k4] = buffer.readString();
                    if(class9.aStringArray225[k4].length() == 0)
                        class9.aStringArray225[k4] = null;
                }

            }
            if(class9.anInt217 == 2 || class9.anInt262 == 2)
            {
                class9.aString222 = buffer.readString();
                class9.aString218 = buffer.readString();
                class9.anInt237 = buffer.get2ByteInt();
            }
            if(class9.anInt217 == 1 || class9.anInt217 == 4 || class9.anInt217 == 5 || class9.anInt217 == 6)
            {
                class9.aString221 = buffer.readString();
                if(class9.aString221.length() == 0)
                {
                    if(class9.anInt217 == 1)
                        class9.aString221 = "Ok";
                    if(class9.anInt217 == 4)
                        class9.aString221 = "Select";
                    if(class9.anInt217 == 5)
                        class9.aString221 = "Select";
                    if(class9.anInt217 == 6)
                        class9.aString221 = "Continue";
                }
            }
        }
        aClass12_238 = null;
        if(byte0 == -84);
    }

    public Model method206(int i, int j)
    {
        Model model = (Model)aClass12_264.get((i << 16) + j);
        if(model != null)
            return model;
        if(i == 1)
            model = Model.getModel(j);
        if(i == 2)
            model = NPC.getForId(j).getHeadModel();
        if(i == 3)
            model = Client.aClass30_Sub2_Sub4_Sub1_Sub2_1126.method453((byte)-41);
        if(i == 4)
            model = Item.method198(j).method202(50, true);
        if(i == 5)
            model = null;
        if(model != null)
            aClass12_264.method223(model, (i << 16) + j, (byte)2);
        return model;
    }

    public static Class30_Sub2_Sub1_Sub1 method207(int i, boolean flag, JagexArchive jagexArchive, String s)
    {
        long l = (Class50.method585((byte)1, s) << 8) + (long)i;
        if(flag)
            throw new NullPointerException();
        Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1 = (Class30_Sub2_Sub1_Sub1)aClass12_238.get(l);
        if(class30_sub2_sub1_sub1 != null)
            return class30_sub2_sub1_sub1;
        try
        {
            class30_sub2_sub1_sub1 = new Class30_Sub2_Sub1_Sub1(jagexArchive, s, i);
            aClass12_238.method223(class30_sub2_sub1_sub1, l, (byte)2);
        }
        catch(Exception _ex)
        {
            return null;
        }
        return class30_sub2_sub1_sub1;
    }

    public static void method208(int i, boolean flag, int j, Model model)
    {
        if(flag)
            return;
        aClass12_264.method224();
        if(model != null && j != 4)
            aClass12_264.method223(model, (j << 16) + i, (byte)2);
    }

    public Model method209(int i, int j, int k, boolean flag)
    {
        Model model;
        if(flag)
            model = method206(anInt255, anInt256);
        else
            model = method206(anInt233, anInt234);
        if(model == null)
            return null;
        if(k == -1 && j == -1 && model.anIntArray1640 == null)
            return model;
        Model model_1 = new Model(9, true, Animation.method532(k, false) & Animation.method532(j, false), false, model);
        if(k != -1 || j != -1)
            model_1.method469((byte)-71);
        if(k != -1)
            model_1.method470(k, 40542);
        if(j != -1)
            model_1.method470(j, 40542);
        model_1.method479(64, 768, -50, -10, -50, true);
        if(i != 0)
            throw new NullPointerException();
        else
            return model_1;
    }

    public Class9()
    {
        anInt213 = 9;
        anInt229 = 891;
    }

    public Class30_Sub2_Sub1_Sub1 aClass30_Sub2_Sub1_Sub1_207;
    public int anInt208;
    public Class30_Sub2_Sub1_Sub1 aClass30_Sub2_Sub1_Sub1Array209[];
    public static Class9 aClass9Array210[];
    public int anInt211;
    public int anIntArray212[];
    public int anInt213;
    public int anInt214;
    public int anIntArray215[];
    public int anInt216;
    public int anInt217;
    public String aString218;
    public int anInt219;
    public int anInt220;
    public String aString221;
    public String aString222;
    public boolean aBoolean223;
    public int anInt224;
    public String aStringArray225[];
    public int anIntArrayArray226[][];
    public boolean aBoolean227;
    public String aString228;
    public int anInt229;
    public int anInt230;
    public int anInt231;
    public int anInt232;
    public int anInt233;
    public int anInt234;
    public boolean aBoolean235;
    public int anInt236;
    public int anInt237;
    public static Class12 aClass12_238;
    public int anInt239;
    public int anIntArray240[];
    public int anIntArray241[];
    public boolean aBoolean242;
    public JagexFont aJagexFont_243;
    public int anInt244;
    public int anIntArray245[];
    public int anInt246;
    public int anIntArray247[];
    public String aString248;
    public boolean aBoolean249;
    public int anInt250;
    public boolean aBoolean251;
    public int anIntArray252[];
    public int anIntArray253[];
    public byte aByte254;
    public int anInt255;
    public int anInt256;
    public int anInt257;
    public int anInt258;
    public boolean aBoolean259;
    public Class30_Sub2_Sub1_Sub1 aClass30_Sub2_Sub1_Sub1_260;
    public int anInt261;
    public int anInt262;
    public int anInt263;
    public static Class12 aClass12_264 = new Class12(false, 30);
    public int anInt265;
    public boolean aBoolean266;
    public int anInt267;
    public boolean aBoolean268;
    public int anInt269;
    public int anInt270;
    public int anInt271;
    public int anIntArray272[];

}
