// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Censor
{
    public static void loadConfig(JagexArchive jagexArchive)
    {
        Buffer characters = new Buffer(jagexArchive.getFile("fragmentsenc.txt"));
        Buffer censoredWords = new Buffer(jagexArchive.getFile("badenc.txt"));
        Buffer censoredDomains = new Buffer(jagexArchive.getFile("domainenc.txt"));
        Buffer topLevelDomains = new Buffer(jagexArchive.getFile("tldlist.txt"));
        readValues(characters, censoredWords, censoredDomains, topLevelDomains);
    }

    public static void readValues(Buffer characters, Buffer censoredWords, Buffer censoredDomains, Buffer topLevelDomains)
    {
        readCensoredWords(censoredWords);
        readCensoredDomains(censoredDomains);
        readCharacters(characters);
        readTLDs(topLevelDomains);
    }

    public static void readTLDs(Buffer topLevelDomains)
    {
        int tldLength = topLevelDomains.get4ByteInt();

        tldList = new char[tldLength][];
        tldListArray = new int[tldLength];

        for (int j = 0; j < tldLength; j++) {
            tldListArray[j] = topLevelDomains.get1ByteAsInt();
            char tld[] = new char[topLevelDomains.get1ByteAsInt()];
            for (int k = 0; k < tld.length; k++) {
                tld[k] = (char) topLevelDomains.get1ByteAsInt();
            }

            tldList[j] = tld;
        }
    }

    public static void readCensoredWords(Buffer buffer)
    {
        int censoredWordsLength = buffer.get4ByteInt();
        censoredWords = new char[censoredWordsLength][];
        censoredWordsBytes = new byte[censoredWordsLength][][];
        initCensoredWords(buffer, censoredWords, censoredWordsBytes);
    }

    public static void readCensoredDomains(Buffer censoredDomains)
    {
        int censoredDomainsLength = censoredDomains.get4ByteInt();
        censoredDomainsWords = new char[censoredDomainsLength][];
        initCensoredDomains(censoredDomainsWords, censoredDomains);
    }

    public static void readCharacters(Buffer characters)
    {
        Censor.characters = new int[characters.get4ByteInt()];
        for (int i = 0; i < Censor.characters.length; i++) {
            Censor.characters[i] = characters.get2ByteInt();
        }
    }

    public static void initCensoredWords(Buffer buffer, char[][] censoredWords, byte[][][] censoredWordsComplex)
    {
        for (int j = 0; j < censoredWords.length; j++) {
            char character[] = new char[buffer.get1ByteAsInt()];
            for (int k = 0; k < character.length; k++) {
                character[k] = (char) buffer.get1ByteAsInt();
            }

            censoredWords[j] = character;
            byte characterByte[][] = new byte[buffer.get1ByteAsInt()][2];
            for (int l = 0; l < characterByte.length; l++) {
                characterByte[l][0] = (byte) buffer.get1ByteAsInt();
                characterByte[l][1] = (byte) buffer.get1ByteAsInt();
            }

            if (characterByte.length > 0) {
                censoredWordsComplex[j] = characterByte;
            }
        }
    }

    public static void initCensoredDomains(char ac[][], Buffer buffer)
    {
        for(int j = 0; j < ac.length; j++) {
            char ac1[] = new char[buffer.get1ByteAsInt()];
            for(int k = 0; k < ac1.length; k++)
                ac1[k] = (char) buffer.get1ByteAsInt();

            ac[j] = ac1;
        }
    }

    public static void stripIllegalCharacters(char[] characters)
    {
        int writePos = 0;
        for (int readPos = 0; readPos < characters.length; readPos++) {
            if (characterIsLegal(characters[readPos])) {
                characters[writePos] = characters[readPos];
            } else {
                characters[writePos] = ' ';
            }
            if (writePos == 0 || characters[writePos] != ' ' || characters[writePos - 1] != ' ') {
                writePos++;
            }
        }

        for (int k = writePos; k < characters.length; k++) {
            characters[k] = ' ';
        }
    }

    public static boolean characterIsLegal(char c)
    {
        return c >= ' ' && c <= '\177' || c == ' ' || c == '\n' || c == '\t' || c == '\243' || c == '\u20AC';
    }

    public static String censorString(String string)
    {
        char characters[] = string.toCharArray();

        stripIllegalCharacters(characters);

        String trimmedString = (new String(characters)).trim();
        characters = trimmedString.toLowerCase().toCharArray();
        String lowercaseString = trimmedString.toLowerCase();

        removeTLDs(characters);
        removeNoNoWords(characters);
        removeDomains(characters);
        removeLongNumbers(characters);

        for (int j = 0; j < exceptions.length; j++) {
            for (int k = -1; (k = lowercaseString.indexOf(exceptions[j], k + 1)) != -1; ) {
                char ac1[] = exceptions[j].toCharArray();
                for (int i1 = 0; i1 < ac1.length; i1++) {
                    characters[i1 + k] = ac1[i1];
                }
            }
        }

        restoreCasing(trimmedString.toCharArray(), characters);
        fixCases(characters);

        return (new String(characters)).trim();
    }

    public static void restoreCasing(char originalString[], char newString[])
    {
        for (int j = 0; j < originalString.length; j++) {
            if (newString[j] != '*' && isUppercaseLetter(originalString[j])) {
                newString[j] = originalString[j];
            }
        }
    }

    public static void fixCases(char[] characters)
    {
        boolean flag = true;
        for (int j = 0; j < characters.length; j++) {
            char c = characters[j];
            if (isLetter(c)) {
                if (flag) {
                    if (isLowercaseLetter(c)) {
                        flag = false;
                    }
                } else if (isUppercaseLetter(c)) {
                    characters[j] = (char) ((c + 97) - 65);
                }
            } else {
                flag = true;
            }
        }
    }

    public static void removeNoNoWords(char characters[])
    {
        for (int i = 0; i < 2; i++) {
            for (int j = censoredWords.length - 1; j >= 0; j--) {
                censorSubstring(characters, censoredWords[j]);
            }
        }
    }

    public static void removeDomains(char[] ac)
    {
        char ac1[] = (char[])ac.clone();
        char ac2[] = {
            '(', 'a', ')'
        };
        censorSubstring(ac1, ac2);
        char ac3[] = (char[])ac.clone();
        char ac4[] = {
            'd', 'o', 't'
        };
        censorSubstring(ac3, ac4);
        for (int i = censoredDomainsWords.length - 1; i >= 0; i--) {
            removeEmail(ac, censoredDomainsWords[i], ac3, ac1);
        }
    }

    public static void removeEmail(char[] text, char[] domain, char[] dotRemovedText, char[] atSignRemovedText)
    {
        if (domain.length > text.length) {
            return;
        }
        int incrementAmount;
        for (int i = 0; i <= text.length - domain.length; i += incrementAmount) {
            int l = i;
            int charsFound = 0;
            incrementAmount = 1;
            while (l < text.length) {
                int j1 = 0;
                char c = text[l];
                char c1 = '\0';
                if (l + 1 < text.length) {
                    c1 = text[l + 1];
                }
                if (charsFound < domain.length && (j1 = method511(43, c, domain[charsFound], c1)) > 0) {
                    l += j1;
                    charsFound++;
                    continue;
                }
                if (charsFound == 0) {
                    break;
                }
                if ((j1 = method511(43, c, domain[charsFound - 1], c1)) > 0) {
                    l += j1;
                    if (charsFound == 1) {
                        incrementAmount++;
                    }
                    continue;
                }
                if (charsFound >= domain.length || !isNonAlphaNumeric(c)) {
                    break;
                }
                l++;
            }
            if (charsFound >= domain.length) {
                boolean isNoNoWord = false;
                int atBeforeDomain = isAtSignBeforeDomain(text, atSignRemovedText, i);
                int dotAfterDomain = isDotAfterDomain(dotRemovedText, text, l - 1);
                if (atBeforeDomain > 2 || dotAfterDomain > 2) {
                    isNoNoWord = true;
                }
                if (isNoNoWord) {
                    for (int i2 = i; i2 < l; i2++) {
                        text[i2] = '*';
                    }
                }
            }
        }
    }

    public static int isAtSignBeforeDomain(char[] text, char[] fakeAtRemovedText, int domainStartPosition)
    {
        if (domainStartPosition == 0) {
            return 2;
        }

        for (int k = domainStartPosition - 1; k >= 0; k--) {
            if (!isNonAlphaNumeric(text[k])) {
                break;
            }
            if (text[k] == '@') {
                return 3;
            }
        }

        int censoredCharactersCount = 0;
        for (int i1 = domainStartPosition - 1; i1 >= 0; i1--) {
            if (!isNonAlphaNumeric(fakeAtRemovedText[i1])) {
                break;
            }
            if (fakeAtRemovedText[i1] == '*') {
                censoredCharactersCount++;
            }
        }

        if (censoredCharactersCount >= 3) {
            return 4;
        }
        return !isNonAlphaNumeric(text[domainStartPosition - 1]) ? 0 : 1;
    }

    public static int isDotAfterDomain(char[] fateAtRemovedText, char[] text, int domainEnd)
    {
        if (domainEnd + 1 == text.length) {
            return 2;
        }
        for (int j = domainEnd + 1; j < text.length; j++) {
            if (!isNonAlphaNumeric(text[j])) {
                break;
            }
            if (text[j] == '.' || text[j] == ',') {
                return 3;
            }
        }

        int k = 0;
        for (int l = domainEnd + 1; l < text.length; l++) {
            if (!isNonAlphaNumeric(fateAtRemovedText[l])) {
                break;
            }
            if (fateAtRemovedText[l] == '*') {
                k++;
            }
        }

        if (k >= 3) {
            return 4;
        }
        return !isNonAlphaNumeric(text[domainEnd + 1]) ? 0 : 1;
    }

    public static void removeTLDs(char characters[])
    {
        char temp[] = (char[])characters.clone();
        char dotString[] = {
            'd', 'o', 't'
        };
        censorSubstring(temp, dotString);

        char temp2[] = (char[])characters.clone();
        char slashString[] = {
            's', 'l', 'a', 's', 'h'
        };
        censorSubstring(temp2, slashString);

        for (int i = 0; i < tldList.length; i++) {
            method506(temp2, tldList[i], tldListArray[i], (byte) 51, temp, characters);
        }
    }

    public static void method506(char ac[], char ac1[], int i, byte byte0, char ac2[], char ac3[])
    {
        if(ac1.length > ac3.length)
            return;
        boolean flag = true;
        int j;
        for(int k = 0; k <= ac3.length - ac1.length; k += j)
        {
            int l = k;
            int i1 = 0;
            j = 1;
            while(l < ac3.length) 
            {
                int j1 = 0;
                char c = ac3[l];
                char c1 = '\0';
                if(l + 1 < ac3.length)
                    c1 = ac3[l + 1];
                if(i1 < ac1.length && (j1 = method511(43, c, ac1[i1], c1)) > 0)
                {
                    l += j1;
                    i1++;
                    continue;
                }
                if(i1 == 0)
                    break;
                if((j1 = method511(43, c, ac1[i1 - 1], c1)) > 0)
                {
                    l += j1;
                    if(i1 == 1)
                        j++;
                    continue;
                }
                if(i1 >= ac1.length || !isNonAlphaNumeric(c))
                    break;
                l++;
            }
            if(i1 >= ac1.length)
            {
                boolean flag1 = false;
                int k1 = method507(36209, ac3, k, ac2);
                int l1 = method508(false, ac3, ac, l - 1);
                if(i == 1 && k1 > 0 && l1 > 0)
                    flag1 = true;
                if(i == 2 && (k1 > 2 && l1 > 0 || k1 > 0 && l1 > 2))
                    flag1 = true;
                if(i == 3 && k1 > 0 && l1 > 2)
                    flag1 = true;
                boolean _tmp = i == 3 && k1 > 2 && l1 > 0;
                if(flag1)
                {
                    int i2 = k;
                    int j2 = l - 1;
                    if(k1 > 2)
                    {
                        if(k1 == 4)
                        {
                            boolean flag2 = false;
                            for(int l2 = i2 - 1; l2 >= 0; l2--)
                                if(flag2)
                                {
                                    if(ac2[l2] != '*')
                                        break;
                                    i2 = l2;
                                } else
                                if(ac2[l2] == '*')
                                {
                                    i2 = l2;
                                    flag2 = true;
                                }

                        }
                        boolean flag3 = false;
                        for(int i3 = i2 - 1; i3 >= 0; i3--)
                            if(flag3)
                            {
                                if(isNonAlphaNumeric(ac3[i3]))
                                    break;
                                i2 = i3;
                            } else
                            if(!isNonAlphaNumeric(ac3[i3]))
                            {
                                flag3 = true;
                                i2 = i3;
                            }

                    }
                    if(l1 > 2)
                    {
                        if(l1 == 4)
                        {
                            boolean flag4 = false;
                            for(int j3 = j2 + 1; j3 < ac3.length; j3++)
                                if(flag4)
                                {
                                    if(ac[j3] != '*')
                                        break;
                                    j2 = j3;
                                } else
                                if(ac[j3] == '*')
                                {
                                    j2 = j3;
                                    flag4 = true;
                                }

                        }
                        boolean flag5 = false;
                        for(int k3 = j2 + 1; k3 < ac3.length; k3++)
                            if(flag5)
                            {
                                if(isNonAlphaNumeric(ac3[k3]))
                                    break;
                                j2 = k3;
                            } else
                            if(!isNonAlphaNumeric(ac3[k3]))
                            {
                                flag5 = true;
                                j2 = k3;
                            }

                    }
                    for(int k2 = i2; k2 <= j2; k2++)
                        ac3[k2] = '*';

                }
            }
        }

        if(byte0 != 51)
            aBoolean619 = !aBoolean619;
    }

    public static int method507(int i, char ac[], int j, char ac1[])
    {
        if(j == 0)
            return 2;
        for(int k = j - 1; k >= 0; k--)
        {
            if(!isNonAlphaNumeric(ac[k]))
                break;
            if(ac[k] == ',' || ac[k] == '.')
                return 3;
        }

        int l = 0;
        for(int i1 = j - 1; i1 >= 0; i1--)
        {
            if(!isNonAlphaNumeric(ac1[i1]))
                break;
            if(ac1[i1] == '*')
                l++;
        }

        if(i != 36209)
            aBoolean619 = !aBoolean619;
        if(l >= 3)
            return 4;
        return !isNonAlphaNumeric(ac[j - 1]) ? 0 : 1;
    }

    public static int method508(boolean flag, char ac[], char ac1[], int i)
    {
        if(flag)
            anInt608 = 391;
        if(i + 1 == ac.length)
            return 2;
        for(int j = i + 1; j < ac.length; j++)
        {
            if(!isNonAlphaNumeric(ac[j]))
                break;
            if(ac[j] == '\\' || ac[j] == '/')
                return 3;
        }

        int k = 0;
        for(int l = i + 1; l < ac.length; l++)
        {
            if(!isNonAlphaNumeric(ac1[l]))
                break;
            if(ac1[l] == '*')
                k++;
        }

        if(k >= 5)
            return 4;
        return !isNonAlphaNumeric(ac[i + 1]) ? 0 : 1;
    }

    public static void censorSubstring(char[] haystack, char[] needle)
    {
        if (needle.length > haystack.length) {
            return;
        }

        boolean flag = true;

        int j;

        for (int k = 0; k <= haystack.length - needle.length; k += j) {
            int l = k;
            int i1 = 0;
            int j1 = 0;
            j = 1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            while (l < haystack.length && (!flag2 || !flag3)) {
                int k1 = 0;
                char c = haystack[l];
                char c2 = '\0';
                if (l + 1 < haystack.length) {
                    c2 = haystack[l + 1];
                }
                if (i1 < needle.length && (k1 = method512(c2, c, aBoolean614, needle[i1])) > 0) {
                    if (k1 == 1 && isNumber(c)) {
                        flag2 = true;
                    }
                    if (k1 == 2 && (isNumber(c) || isNumber(c2))) {
                        flag2 = true;
                    }
                    l += k1;
                    i1++;
                    continue;
                }
                if (i1 == 0) {
                    break;
                }
                if ((k1 = method512(c2, c, aBoolean614, needle[i1 - 1])) > 0) {
                    l += k1;
                    if (i1 == 1) {
                        j++;
                    }
                    continue;
                }
                if (i1 >= needle.length || !characterThatsSpecialSomehow(c)) {
                    break;
                }
                if (isNonAlphaNumeric(c) && c != '\'') {
                    flag1 = true;
                }
                if (isNumber(c)) {
                    flag3 = true;
                }
                l++;
                if ((++j1 * 100) / (l - k) > 90) {
                    break;
                }
            }
            if (i1 >= needle.length && (!flag2 || !flag3)) {
                boolean flag4 = true;
                if (!flag1) {
                    char c1 = ' ';
                    if (k - 1 >= 0) {
                        c1 = haystack[k - 1];
                    }
                    char c3 = ' ';
                    if (l < haystack.length) {
                        c3 = haystack[l];
                    }
                    byte byte0 = method513(c1, anInt615);
                    byte byte1 = method513(c3, anInt615);
                } else {
                    boolean flag5 = false;
                    boolean flag6 = false;
                    if (k - 1 < 0 || isNonAlphaNumeric(haystack[k - 1]) && haystack[k - 1] != '\'') {
                        flag5 = true;
                    }
                    if (l >= haystack.length || isNonAlphaNumeric(haystack[l]) && haystack[l] != '\'') {
                        flag6 = true;
                    }
                    if (!flag5 || !flag6) {
                        boolean flag7 = false;
                        int k2 = k - 2;
                        if (flag5) {
                            k2 = k;
                        }
                        for (; !flag7 && k2 < l; k2++) {
                            if (k2 >= 0 && (!isNonAlphaNumeric(haystack[k2]) || haystack[k2] == '\'')) {
                                char ac2[] = new char[3];
                                int j3;
                                for (j3 = 0; j3 < 3; j3++) {
                                    if (k2 + j3 >= haystack.length || isNonAlphaNumeric(haystack[k2 + j3]) && haystack[k2 + j3] != '\'') {
                                        break;
                                    }
                                    ac2[j3] = haystack[k2 + j3];
                                }

                                boolean flag8 = true;
                                if (j3 == 0) {
                                    flag8 = false;
                                }
                                if (j3 < 3 && k2 - 1 >= 0 && (!isNonAlphaNumeric(haystack[k2 - 1]) || haystack[k2 - 1] == '\'')) {
                                    flag8 = false;
                                }
                                if (flag8 && !method523(ac2, (byte) 4)) {
                                    flag7 = true;
                                }
                            }
                        }

                        if (!flag7) {
                            flag4 = false;
                        }
                    }
                }
                if (flag4) {
                    int l1 = 0;
                    int i2 = 0;
                    int j2 = -1;
                    for (int l2 = k; l2 < l; l2++) {
                        if (isNumber(haystack[l2])) {
                            l1++;
                        } else if (isLetter(haystack[l2])) {
                            i2++;
                            j2 = l2;
                        }
                    }

                    if (j2 > -1) {
                        l1 -= l - 1 - j2;
                    }
                    if (l1 <= i2) {
                        for (int i3 = k; i3 < l; i3++) {
                            haystack[i3] = '*';
                        }

                    } else {
                        j = 1;
                    }
                }
            }
        }
    }

    public static boolean method510(byte byte0, byte byte1, byte abyte0[][], byte byte2)
    {
        int i = 0;
        if(byte1 != 8)
            anInt613 = 308;
        if(abyte0[i][0] == byte0 && abyte0[i][1] == byte2)
            return true;
        int j = abyte0.length - 1;
        if(abyte0[j][0] == byte0 && abyte0[j][1] == byte2)
            return true;
        do
        {
            int k = (i + j) / 2;
            if(abyte0[k][0] == byte0 && abyte0[k][1] == byte2)
                return true;
            if(byte0 < abyte0[k][0] || byte0 == abyte0[k][0] && byte2 < abyte0[k][1])
                j = k;
            else
                i = k;
        } while(i != j && i + 1 != j);
        return false;
    }

    public static int method511(int i, char c, char c1, char c2)
    {
        if(i <= 0)
            return anInt608;
        if(c1 == c)
            return 1;
        if(c1 == 'o' && c == '0')
            return 1;
        if(c1 == 'o' && c == '(' && c2 == ')')
            return 2;
        if(c1 == 'c' && (c == '(' || c == '<' || c == '['))
            return 1;
        if(c1 == 'e' && c == '\u20AC')
            return 1;
        if(c1 == 's' && c == '$')
            return 1;
        return c1 != 'l' || c != 'i' ? 0 : 1;
    }

    public static int method512(char c, char c1, boolean flag, char c2)
    {
        if(!flag)
            anInt613 = -260;
        if(c2 == c1)
            return 1;
        if(c2 >= 'a' && c2 <= 'm')
        {
            if(c2 == 'a')
            {
                if(c1 == '4' || c1 == '@' || c1 == '^')
                    return 1;
                return c1 != '/' || c != '\\' ? 0 : 2;
            }
            if(c2 == 'b')
            {
                if(c1 == '6' || c1 == '8')
                    return 1;
                return (c1 != '1' || c != '3') && (c1 != 'i' || c != '3') ? 0 : 2;
            }
            if(c2 == 'c')
                return c1 != '(' && c1 != '<' && c1 != '{' && c1 != '[' ? 0 : 1;
            if(c2 == 'd')
                return (c1 != '[' || c != ')') && (c1 != 'i' || c != ')') ? 0 : 2;
            if(c2 == 'e')
                return c1 != '3' && c1 != '\u20AC' ? 0 : 1;
            if(c2 == 'f')
            {
                if(c1 == 'p' && c == 'h')
                    return 2;
                return c1 != '\243' ? 0 : 1;
            }
            if(c2 == 'g')
                return c1 != '9' && c1 != '6' && c1 != 'q' ? 0 : 1;
            if(c2 == 'h')
                return c1 != '#' ? 0 : 1;
            if(c2 == 'i')
                return c1 != 'y' && c1 != 'l' && c1 != 'j' && c1 != '1' && c1 != '!' && c1 != ':' && c1 != ';' && c1 != '|' ? 0 : 1;
            if(c2 == 'j')
                return 0;
            if(c2 == 'k')
                return 0;
            if(c2 == 'l')
                return c1 != '1' && c1 != '|' && c1 != 'i' ? 0 : 1;
            if(c2 == 'm')
                return 0;
        }
        if(c2 >= 'n' && c2 <= 'z')
        {
            if(c2 == 'n')
                return 0;
            if(c2 == 'o')
            {
                if(c1 == '0' || c1 == '*')
                    return 1;
                return (c1 != '(' || c != ')') && (c1 != '[' || c != ']') && (c1 != '{' || c != '}') && (c1 != '<' || c != '>') ? 0 : 2;
            }
            if(c2 == 'p')
                return 0;
            if(c2 == 'q')
                return 0;
            if(c2 == 'r')
                return 0;
            if(c2 == 's')
                return c1 != '5' && c1 != 'z' && c1 != '$' && c1 != '2' ? 0 : 1;
            if(c2 == 't')
                return c1 != '7' && c1 != '+' ? 0 : 1;
            if(c2 == 'u')
            {
                if(c1 == 'v')
                    return 1;
                return (c1 != '\\' || c != '/') && (c1 != '\\' || c != '|') && (c1 != '|' || c != '/') ? 0 : 2;
            }
            if(c2 == 'v')
                return (c1 != '\\' || c != '/') && (c1 != '\\' || c != '|') && (c1 != '|' || c != '/') ? 0 : 2;
            if(c2 == 'w')
                return c1 != 'v' || c != 'v' ? 0 : 2;
            if(c2 == 'x')
                return (c1 != ')' || c != '(') && (c1 != '}' || c != '{') && (c1 != ']' || c != '[') && (c1 != '>' || c != '<') ? 0 : 2;
            if(c2 == 'y')
                return 0;
            if(c2 == 'z')
                return 0;
        }
        if(c2 >= '0' && c2 <= '9')
        {
            if(c2 == '0')
            {
                if(c1 == 'o' || c1 == 'O')
                    return 1;
                return (c1 != '(' || c != ')') && (c1 != '{' || c != '}') && (c1 != '[' || c != ']') ? 0 : 2;
            }
            if(c2 == '1')
                return c1 != 'l' ? 0 : 1;
            else
                return 0;
        }
        if(c2 == ',')
            return c1 != '.' ? 0 : 1;
        if(c2 == '.')
            return c1 != ',' ? 0 : 1;
        if(c2 == '!')
            return c1 != 'i' ? 0 : 1;
        else
            return 0;
    }

    public static byte method513(char c, int i)
    {
        while(i >= 0) 
            anInt606 = -93;
        if(c >= 'a' && c <= 'z')
            return (byte)((c - 97) + 1);
        if(c == '\'')
            return 28;
        if(c >= '0' && c <= '9')
            return (byte)((c - 48) + 29);
        else
            return 27;
    }

    public static void removeLongNumbers(char[] characters)
    {
        int j = 0;
        int k = 0;
        int l = 0;
        int i1 = 0;
        while ((j = getPositionOfFirstNumber(characters, k)) != -1) {
            boolean flag = false;
            for (int j1 = k; j1 >= 0 && j1 < j && !flag; j1++) {
                if (!isNonAlphaNumeric(characters[j1]) && !characterThatsSpecialSomehow(characters[j1])) {
                    flag = true;
                }
            }

            if (flag) {
                l = 0;
            }
            if (l == 0) {
                i1 = j;
            }
            k = getPositionOfFirstNonNumber(characters, j);
            int k1 = 0;
            for (int l1 = j; l1 < k; l1++) {
                k1 = (k1 * 10 + characters[l1]) - 48;
            }

            if (k1 > 255 || k - j > 8) {
                l = 0;
            } else {
                l++;
            }
            if (l == 4) {
                for (int i2 = i1; i2 < k; i2++) {
                    characters[i2] = '*';
                }

                l = 0;
            }
        }
    }

    public static int getPositionOfFirstNumber(char[] characters, int startPos)
    {
        for (int i = startPos; i < characters.length && i >= 0; i++) {
            if (characters[i] >= '0' && characters[i] <= '9') {
                return i;
            }
        }

        return -1;
    }

    public static int getPositionOfFirstNonNumber(char[] text, int startPos)
    {
        for (int i = startPos; i < text.length && i >= 0; i++) {
            if (text[i] < '0' || text[i] > '9') {
                return i;
            }
        }

        return text.length;
    }

    public static boolean isNonAlphaNumeric(char character)
    {
        return !isLetter(character) && !isNumber(character);
    }

    public static boolean characterThatsSpecialSomehow(char c)
    {
        if (c < 'a' || c > 'z') {
            return true;
        }
        return c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
    }

    public static boolean isLetter(char c)
    {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public static boolean isNumber(char c)
    {
        return c >= '0' && c <= '9';
    }

    public static boolean isLowercaseLetter(char c)
    {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUppercaseLetter(char character)
    {
        return character >= 'A' && character <= 'Z';
    }

    public static boolean method523(char ac[], byte byte0)
    {
        if(byte0 == aByte617)
            byte0 = 0;
        else
            throw new NullPointerException();
        boolean flag = true;
        for(int i = 0; i < ac.length; i++)
            if(!isNumber(ac[i]) && ac[i] != 0)
                flag = false;

        if(flag)
            return true;
        int j = method524(ac, 8801);
        int k = 0;
        int l = characters.length - 1;
        if(j == characters[k] || j == characters[l])
            return true;
        do
        {
            int i1 = (k + l) / 2;
            if(j == characters[i1])
                return true;
            if(j < characters[i1])
                l = i1;
            else
                k = i1;
        } while(k != l && k + 1 != l);
        return false;
    }

    public static int method524(char ac[], int i)
    {
        if(i != anInt618)
        {
            for(int j = 1; j > 0; j++);
        }
        if(ac.length > 6)
            return 0;
        int k = 0;
        for(int l = 0; l < ac.length; l++)
        {
            char c = ac[ac.length - l - 1];
            if(c >= 'a' && c <= 'z')
                k = k * 38 + ((c - 97) + 1);
            else
            if(c == '\'')
                k = k * 38 + 27;
            else
            if(c >= '0' && c <= '9')
                k = k * 38 + ((c - 48) + 28);
            else
            if(c != 0)
                return 0;
        }

        return k;
    }

    public static int anInt606 = 9;
    public static boolean aBoolean607;
    public static int anInt608 = 748;
    public static int anInt609 = 201;
    public static boolean aBoolean610 = true;
    public static int anInt611;
    public static byte aByte612 = -117;
    public static int anInt613 = -575;
    public static boolean aBoolean614 = true;
    public static int anInt615 = -720;
    public static int anInt616 = -511;
    public static byte aByte617 = 4;
    public static int anInt618 = 8801;
    public static boolean aBoolean619 = true;
    public static int characters[];
    public static char censoredWords[][];
    public static byte censoredWordsBytes[][][];
    public static char censoredDomainsWords[][];
    public static char tldList[][];
    public static int tldListArray[];
    public static final String exceptions[] = {
        "cook", "cook's", "cooks", "seeks", "sheet", "woop", "woops", "faq", "noob", "noobs"
    };
    public static boolean aBoolean627;

}
