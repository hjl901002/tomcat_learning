import java.applet.*;
import java.awt.*;

public class TestApplet extends Applet {
	String strFont;
	public void init(){
		strFont = getParameter("font");
	}
	
	public void paint(Graphics g) {
		Font f = new Font(strFont, Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.blue);
		g.drawString("这是使用<jsp:plugin>动作元素的例子", 0, 30);
	}
}