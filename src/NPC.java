// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class NPC
{
    public static int cacheIndex;
    public static Buffer npcData;
    public static int npcCount;
    public static int npcIndices[];
    public static NPC npcs[];
    public static Client aClient82;
    public static Class12 modelCache = new Class12(false, 30);

    public static void clearCache()
    {
        modelCache = null;
        npcIndices = null;
        npcs = null;
        npcData = null;
    }

    public static NPC getForId(int npcId)
    {
        for (int j = 0; j < 20; j++) {
            if (npcs[j].type == (long) npcId) {
                return npcs[j];
            }
        }

        cacheIndex = (cacheIndex + 1) % 20;
        NPC NPC = npcs[cacheIndex] = new NPC();
        npcData.pointer = npcIndices[npcId];
        NPC.type = npcId;
        NPC.loadNPC(npcData);
        return NPC;
    }

    public static void initNPCs(JagexArchive jagexArchive)
    {
        npcData = new Buffer(jagexArchive.getFile("npc.dat"));
        Buffer buffer = new Buffer(jagexArchive.getFile("npc.idx"));
        npcCount = buffer.get2ByteInt();
        npcIndices = new int[npcCount];

        int i = 2;
        for (int j = 0; j < npcCount; j++) {
            npcIndices[j] = i;
            i += buffer.get2ByteInt();
        }

        npcs = new NPC[20];

        for (int k = 0; k < 20; k++) {
            npcs[k] = new NPC();
        }
    }

    public int turn90CCAnimation;
    public int varBitId;
    public int turn180Animation;
    public int sessionSettingId;
    public int level;
    public int anInt63;
    public int anInt64;
    public String name;
    public String actions[];
    public int walkAnimation;
    public byte boundDim;
    public int anInt69;
    public int recolourTarget[];
    public int anInt71;
    public int additionalModels[];
    public int headIcon;
    public int recolourOriginal[];
    public int idleAnimation;
    public long type;
    public int degreesToTurn;
    public boolean aBoolean81;
    public int turn90CAnimation;
    public boolean isClickable;
    public int lightModifier;
    public int scaleY;
    public boolean hasMinimapDot;
    public int childIds[];
    public byte description[];
    public int anInt90;
    public int scaleXZ;
    public int shadowModifier;
    public boolean isInvisible;
    public int npcModels[];
    public int anInt96;

    public NPC()
    {
        turn90CCAnimation = -1;
        varBitId = -1;
        turn180Animation = -1;
        sessionSettingId = -1;
        level = -1;
        anInt63 = 9;
        anInt64 = 1834;
        walkAnimation = -1;
        boundDim = 1;
        anInt69 = 9;
        anInt71 = -1;
        headIcon = -1;
        idleAnimation = -1;
        type = -1L;
        degreesToTurn = 32;
        aBoolean81 = false;
        turn90CAnimation = -1;
        isClickable = true;
        scaleY = 128;
        hasMinimapDot = true;
        anInt90 = -1;
        scaleXZ = 128;
        isInvisible = false;
        anInt96 = -1;
    }

    public Model getHeadModel()
    {
        if (childIds != null) {
            NPC npc = method161();
            if (npc == null) {
                return null;
            } else {
                return npc.getHeadModel();
            }
        }

        if (additionalModels == null) {
            return null;
        }

        boolean modelIsMissing = false;

        for (int i = 0; i < additionalModels.length; i++) {
            if (!Model.isCached(additionalModels[i])) {
                modelIsMissing = true;
            }
        }

        if (modelIsMissing) {
            return null;
        }

        Model additionalModels[] = new Model[this.additionalModels.length];
        for (int j = 0; j < this.additionalModels.length; j++) {
            additionalModels[j] = Model.getModel(this.additionalModels[j]);
        }

        Model headModel;
        if (additionalModels.length == 1) {
            headModel = additionalModels[0];
        } else {
            headModel = new Model(additionalModels.length, additionalModels);
        }
        if (recolourOriginal != null) {
            for (int k = 0; k < recolourOriginal.length; k++) {
                headModel.recolour(recolourOriginal[k], recolourTarget[k]);
            }
        }
        return headModel;
    }

    public NPC method161()
    {
        int j = -1;
        if (varBitId != -1) {
            Class37 class37 = Class37.aClass37Array646[varBitId];
            int k = class37.anInt648;
            int l = class37.anInt649;
            int i1 = class37.anInt650;
            int j1 = Client.anIntArray1232[i1 - l];
            j = aClient82.anIntArray971[k] >> l & j1;
        } else if (sessionSettingId != -1) {
            j = aClient82.anIntArray971[sessionSettingId];
        }
        if (j < 0 || j >= childIds.length || childIds[j] == -1) {
            return null;
        } else {
            return getForId(childIds[j]);
        }
    }

    public Model method164(int frameId, int frameId2, int[] ai)
    {
        if (childIds != null) {
            NPC npc = method161();
            if (npc == null) {
                return null;
            } else {
                return npc.method164(frameId, frameId2, ai);
            }
        }

        Model model = (Model)modelCache.get(type);

        if (model == null) {
            boolean flag = false;
            for (int i1 = 0; i1 < npcModels.length; i1++) {
                if (!Model.isCached(npcModels[i1])) {
                    flag = true;
                }
            }

            if (flag) {
                return null;
            }
            Model aclass30_sub2_sub4_sub6[] = new Model[npcModels.length];
            for (int j1 = 0; j1 < npcModels.length; j1++) {
                aclass30_sub2_sub4_sub6[j1] = Model.getModel(npcModels[j1]);
            }

            if (aclass30_sub2_sub4_sub6.length == 1) {
                model = aclass30_sub2_sub4_sub6[0];
            } else {
                model = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6);
            }
            if (recolourOriginal != null) {
                for (int k1 = 0; k1 < recolourOriginal.length; k1++) {
                    model.recolour(recolourOriginal[k1], recolourTarget[k1]);
                }

            }
            model.method469((byte) -71);
            model.method479(64 + lightModifier, 850 + shadowModifier, -30, -50, -30, true);
            modelCache.method223(model, type, (byte) 2);
        }
        Model model_1 = Model.aModel_1621;
        model_1.method464(7, model, Animation.method532(frameId, false) & Animation.method532(frameId2, false));
        if (frameId != -1 && frameId2 != -1) {
            model_1.method471(-20491, ai, frameId2, frameId);
        } else if (frameId != -1) {
            model_1.method470(frameId, 40542);
        }
        if (scaleXZ != 128 || scaleY != 128) {
            model_1.method478(scaleXZ, scaleXZ, anInt63, scaleY);
        }
        model_1.method466(false);
        model_1.anIntArrayArray1658 = null;
        model_1.anIntArrayArray1657 = null;
        if (boundDim == 1) {
            model_1.aBoolean1659 = true;
        }
        return model_1;
    }

    public void loadNPC(Buffer buffer)
    {
        do {
            int i = buffer.get1ByteAsInt();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                int j = buffer.get1ByteAsInt();
                npcModels = new int[j];
                for (int k = 0; k < j; k++) {
                    npcModels[k] = buffer.get2ByteInt();
                }
            } else if (i == 2) {
                name = buffer.readString();
            } else if (i == 3) {
                description = buffer.readStringBytes();
            } else if (i == 12) {
                boundDim = buffer.method409();
            } else if (i == 13) {
                idleAnimation = buffer.get2ByteInt();
            } else if (i == 14) {
                walkAnimation = buffer.get2ByteInt();
            } else if (i == 17) {
                walkAnimation = buffer.get2ByteInt();
                turn180Animation = buffer.get2ByteInt();
                turn90CAnimation = buffer.get2ByteInt();
                turn90CCAnimation = buffer.get2ByteInt();
            } else if (i >= 30 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 30] = buffer.readString();
                if (actions[i - 30].equalsIgnoreCase("hidden")) {
                    actions[i - 30] = null;
                }
            } else if (i == 40) {
                int colours = buffer.get1ByteAsInt();
                recolourOriginal = new int[colours];
                recolourTarget = new int[colours];
                for (int j = 0; j < colours; j++) {
                    recolourOriginal[j] = buffer.get2ByteInt();
                    recolourTarget[j] = buffer.get2ByteInt();
                }

            } else if (i == 60) {
                int additionalModelLength = buffer.get1ByteAsInt();
                additionalModels = new int[additionalModelLength];
                for (int j = 0; j < additionalModelLength; j++) {
                    additionalModels[j] = buffer.get2ByteInt();
                }

            } else if (i == 90) {
                anInt96 = buffer.get2ByteInt();
            } else if (i == 91) {
                anInt71 = buffer.get2ByteInt();
            } else if (i == 92) {
                anInt90 = buffer.get2ByteInt();
            } else if (i == 93) {
                hasMinimapDot = false;
            } else if (i == 95) {
                level = buffer.get2ByteInt();
            } else if (i == 97) {
                scaleXZ = buffer.get2ByteInt();
            } else if (i == 98) {
                scaleY = buffer.get2ByteInt();
            } else if (i == 99) {
                isInvisible = true;
            } else if (i == 100) {
                lightModifier = buffer.method409();
            } else if (i == 101) {
                shadowModifier = buffer.method409() * 5;
            } else if (i == 102) {
                headIcon = buffer.get2ByteInt();
            } else if (i == 103) {
                degreesToTurn = buffer.get2ByteInt();
            } else if (i == 106) {
                varBitId = buffer.get2ByteInt();
                if (varBitId == 65535) {
                    varBitId = -1;
                }
                sessionSettingId = buffer.get2ByteInt();
                if (sessionSettingId == 65535) {
                    sessionSettingId = -1;
                }
                int j = buffer.get1ByteAsInt();
                childIds = new int[j + 1];
                for (int k = 0; k <= j; k++) {
                    childIds[k] = buffer.get2ByteInt();
                    if (childIds[k] == 65535) {
                        childIds[k] = -1;
                    }
                }

            } else if (i == 107) {
                isClickable = false;
            }
        } while (true);
    }
}
