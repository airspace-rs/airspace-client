public class Floor
{
    public static int floorCount;
    public static Floor floors[];

    public static void initFloors(JagexArchive jagexArchive)
    {
        Buffer buffer = new Buffer(jagexArchive.getFile("flo.dat"));
        floorCount = buffer.get2ByteInt();

        if (floors == null) {
            floors = new Floor[floorCount];
        }

        for (int j = 0; j < floorCount; j++) {
            if (floors[j] == null) {
                floors[j] = new Floor();
            }
            floors[j].loadFloors(buffer);
        }
    }

    public int weightedHue;
    public int colour;
    public boolean hasShadowing = true;
    public int hue;
    public int luminance;
    public String name;
    public int rgb;
    public int saturation;
    public int textureId = -1;
    public int chroma;

    public Floor()
    {

    }

    public void loadFloors(Buffer buffer)
    {
        do {
            int configCode = buffer.get1ByteAsInt();
            if (configCode == 0) {
                return;
            }
            if (configCode == 1) {
                rgb = buffer.get3ByteInt();
                method262(rgb);
            } else if (configCode == 2) {
                textureId = buffer.get1ByteAsInt();
            } else if (configCode == 3) {
                //
            } else if (configCode == 5) {
                hasShadowing = false;
            } else if (configCode == 6) {
                name = buffer.readString();
            } else if (configCode == 7) {
                int tempHue = hue;
                int tempSat = saturation;
                int tempLum = luminance;
                int tempChr = weightedHue;
                int rgb = buffer.get3ByteInt();
                method262(rgb);
                hue = tempHue;
                saturation = tempSat;
                luminance = tempLum;
                weightedHue = tempChr;
                chroma = tempChr;
            } else {
                System.out.println("Error unrecognised config code: " + configCode);
            }
        } while (true);
    }

    public void method262(int rgb)
    {
        double red = (double) (rgb >> 16 & 0xff) / 256D;
        double green = (double) (rgb >> 8 & 0xff) / 256D;
        double blue = (double) (rgb & 0xff) / 256D;

        // Figure out which is darkest
        double darkest = red;
        if (green < darkest) {
            darkest = green;
        }
        if (blue < darkest) {
            darkest = blue;
        }

        // Figure out which is brightest
        double brightest = red;
        if (green > brightest) {
            brightest = green;
        }
        if (blue > brightest) {
            brightest = blue;
        }

        double hue = 0;
        double saturation = 0;
        double lumination = (darkest + brightest) / 2;

        if (darkest != brightest) {
            if (lumination < 0.5) {
                saturation = (brightest - darkest) / (brightest + darkest);
            }
            if (lumination >= 0.5) {
                saturation = (brightest - darkest) / (2 - brightest - darkest);
            }

            if (red == brightest) {
                hue = (green - blue) / (brightest - darkest);
            } else if (green == brightest) {
                hue = 2 + (blue - red) / (brightest - darkest);
            } else if (blue == brightest) {
                hue = 4 + (red - green) / (brightest - darkest);
            }
        }

        hue /= 6;

        this.hue = (int)(hue * 256);
        this.saturation = (int)(saturation * 256);
        luminance = (int)(lumination * 256D);

        if (this.saturation < 0) {
            this.saturation = 0;
        } else if (this.saturation > 255) {
            this.saturation = 255;
        }

        if (luminance < 0) {
            luminance = 0;
        } else if (luminance > 255) {
            luminance = 255;
        }

        if (lumination > 0.5) {
            chroma = (int) ((1.0D - lumination) * saturation * 512D);
        } else {
            chroma = (int) (lumination * saturation * 512D);
        }
        if (chroma < 1) {
            chroma = 1;
        }

        weightedHue = (int) (hue * chroma);

        int weightedHue = (this.hue + (int) (Math.random() * 16D)) - 8;
        if (weightedHue < 0) {
            weightedHue = 0;
        } else if (weightedHue > 255) {
            weightedHue = 255;
        }

        int blendedSaturation = (this.saturation + (int) (Math.random() * 48D)) - 24;
        if (blendedSaturation < 0) {
            blendedSaturation = 0;
        } else if (blendedSaturation > 255) {
            blendedSaturation = 255;
        }

        int blendedLuminance = (luminance + (int) (Math.random() * 48D)) - 24;
        if (blendedLuminance < 0) {
            blendedLuminance = 0;
        } else if (blendedLuminance > 255) {
            blendedLuminance = 255;
        }

        colour = encode(weightedHue, blendedSaturation, blendedLuminance);
    }

    public int encode(int hue, int saturation, int luminance)
    {
        if (luminance > 179) {
            saturation /= 2;
        }
        if (luminance > 192) {
            saturation /= 2;
        }
        if (luminance > 217) {
            saturation /= 2;
        }
        if (luminance > 243) {
            saturation /= 2;
        }
        return (hue / 4 << 10) + (saturation / 32 << 7) + luminance / 2;
    }
}
