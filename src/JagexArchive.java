// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class JagexArchive
{
    public JagexArchive(byte[] dataIn)
    {
        Buffer buffer = new Buffer(dataIn);

        int i = buffer.get3ByteInt();
        int j = buffer.get3ByteInt();

        if (j != i) {
            byte out[] = new byte[i];
            BZip2.decompress(out, i, dataIn, j, 6);
            outputData = out;
            buffer = new Buffer(outputData);
            isDecompressed = true;
        } else {
            outputData = dataIn;
            isDecompressed = false;
        }
        fileCount = buffer.get2ByteInt();

        names = new int[fileCount];
        fileSizes = new int[fileCount];
        decompressedFileSizes = new int[fileCount];
        offsets = new int[fileCount];

        int k = buffer.pointer + fileCount * 10;
        for (int l = 0; l < fileCount; l++) {
            names[l] = buffer.get4ByteInt();
            fileSizes[l] = buffer.get3ByteInt();
            decompressedFileSizes[l] = buffer.get3ByteInt();
            offsets[l] = k;
            k += decompressedFileSizes[l];
        }
    }

    public byte[] getFile(String fileName)
    {
        int i = 0;

        fileName = fileName.toUpperCase();
        for (int j = 0; j < fileName.length(); j++) {
            i = (i * 61 + fileName.charAt(j)) - 32;
        }

        byte data[];

        for (int k = 0; k < fileCount; k++) {
            if (names[k] == i) {
                data = new byte[fileSizes[k]];
                if (!isDecompressed) {
                    BZip2.decompress(data, fileSizes[k], outputData, decompressedFileSizes[k], offsets[k]);
                } else {
                    for (int l = 0; l < fileSizes[k]; l++) {
                        data[l] = outputData[offsets[k] + l];
                    }

                }
                return data;
            }
        }

        return null;
    }

    public byte outputData[];
    public int fileCount;
    public int names[];
    public int fileSizes[];
    public int decompressedFileSizes[];
    public int offsets[];
    public boolean isDecompressed;
}
