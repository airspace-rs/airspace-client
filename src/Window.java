import java.awt.*;

public class Window extends Frame
{
    public BrowserApplet browserApplet;

    public Window(BrowserApplet ba, int width, int height)
    {
        browserApplet = ba;
        setTitle("Jagex");
        setResizable(false);
        show();
        toFront();
        resize(width + 8, height + 28);
    }

    public Graphics getGraphics()
    {
        Graphics graphics = super.getGraphics();
        graphics.translate(4, 24);
        return graphics;
    }

    public void update(Graphics graphics)
    {
        browserApplet.update(graphics);
    }

    public void paint(Graphics graphics)
    {
        browserApplet.paint(graphics);
    }
}
