import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
																																													
import javax.swing.JPanel;

/**
 * Custom JPanel. Mouse Listeners for JPanel are here.
 * Allow overrided paintComponent methods to draw rectangles.
 *
 */
class MyPanel extends JPanel 
{
	Rectangle rec;
	Rectangle recDraw;

	ArrayList<Rectangle> myRecs = new ArrayList<Rectangle>();	//나중에 저장할 때 사용할 예정 (파일 IO)

	//boolean allowClear = false;	 혹시나 사각형 지우는 기능 필요할 때 사용

	JPanel myPanel = new JPanel();
	public MyPanel()
	{
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				int x = e.getX();
				int y = e.getY();
				rec = new Rectangle(x, y, 0, 0);
				updateDrawableRect(getWidth(), getHeight());
				MainFrame.xField.setText(String.valueOf(x));
				MainFrame.yField.setText(String.valueOf(y));
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				updateSize(e);
				myRecs.add(recDraw);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				updateSize(e);
			}
		});
	}
	
	void updateSize(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		rec.setWidth(x - rec.getX());
		rec.setHeight(y - rec.getY());
		updateDrawableRect(getWidth(), getHeight());
		MainFrame.wField.setText(String.valueOf(Math.abs(x - rec.getX())));
		MainFrame.hField.setText(String.valueOf(Math.abs(y - rec.getY())));
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (rec != null)
		{
			g.setColor(Color.BLACK);
			g.drawRect(recDraw.getX(), recDraw.getY(), recDraw.getWidth(), recDraw.getHeight());
			g.setColor(Color.WHITE);
			g.fillRect(recDraw.getX() + 1, recDraw.getY() + 1, recDraw.getWidth() - 1, recDraw.getHeight() - 1);
			for(int i = 0; i < myRecs.size(); i ++)
			{
				g.setColor(Color.BLACK);
				g.drawRect(myRecs.get(i).getX(), myRecs.get(i).getY(), myRecs.get(i).getWidth(), myRecs.get(i).getHeight());
				g.setColor(Color.PINK);
				g.fillRect(myRecs.get(i).getX() + 1, myRecs.get(i).getY() + 1, myRecs.get(i).getWidth() - 1, myRecs.get(i).getHeight() - 1);
			}
		}
		/*
		if(allowClear)
		{
			for(int i = 0; i < myRecs.size(); i++)
			{
			g.clearRect(myRecs.get(i).getX(), myRecs.get(i).getY(), myRecs.get(i).getWidth(), myRecs.get(i).getHeight());
			}

		}
		*/
	}
	
	private void updateDrawableRect(int compWidth, int compHeight)
	{
		int x = rec.getX();
		int y = rec.getY();
		int width = rec.getWidth();
		int height = rec.getHeight();
		
		if (width < 0)
		{
			width = 0 - width;
			x = x - width + 1;
			if(x < 0)
			{
				width += x;
				x = 0;
			}
		}
		if (height < 0)
		{
			height = 0 - height;
			y = y - height;
			if (y < 0)
			{
				height += y;
				y = 0;
			}
		}
		
		if ((x + width) > compWidth)
		{
			width = compWidth - x;
		}
		if ((y + height) > compHeight)
		{
			height = compHeight - y;
		}
		
		recDraw = new Rectangle(x, y, width, height);
	}
	/*
	public void clearPanel()
	{
		allowClear = true;
		rec = null;
		repaint();
		recDraw = null;
		myRecs.clear();
		allowClear = false;
	}
	*/
}