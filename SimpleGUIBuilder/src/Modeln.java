import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Modeln extends JPanel implements MouseMotionListener, MouseListener {
	private int offX;
	private int offY;
	private String name;
	private boolean sizingX;
	private boolean sizingY;
	private boolean sizingXY;
	private boolean moving;
	private boolean newRec;
	LinkedList<MyRec> MyList;
	ListIterator<MyRec> it;
	MyRec thisRec;
	MainFrame mf;
	int nonameNumber = 0;
	
	class MyRec{
		private int x;
		private int y;
		private int w;
		private int h;
		private String name;
		int getX(){return x;}
		int getY(){return y;}
		int getW(){return w;}
		int getH(){return h;}
		String getName(){return name;}
	}
	public Modeln(){
		MyList = new LinkedList<MyRec>();
		addMouseListener(this);
		addMouseMotionListener(this);		
	}
	public void newMyRec(int x, int y, int w, int h, String name){
		MyRec rec = new MyRec();
		rec.x = x;
		rec.y = y;
		rec.w = w;
		rec.h = h;
		rec.name = name;
		MyList.add(rec);
		thisRec = rec;
		mf.setRecAttribution(thisRec);
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		it = MyList.listIterator();
		while(it.hasNext()){
			MyRec rec = it.next();
			g.drawRect(rec.x, rec.y, rec.w, rec.h);
		}
	}
	public boolean isThere(int x, int y){
		it = MyList.listIterator();
		while(it.hasNext()){
			MyRec rec = it.next();
			if(x >= rec.x-10 && x <= rec.x+rec.w+10 && y >= rec.y-10 && y <= rec.y+rec.h+10){
				thisRec = rec;
				return true;
			}
		}
		return false;
	}
	public void setSize(int newX, int newY, int width, int height, MyRec rec){
		rec.x = newX;
		rec.y = newY;
		rec.w = width;
		rec.h = height;
		mf.setRecAttribution(rec);
		repaint();
	}
 	public void mousePressed(MouseEvent me){
 		if(isThere(me.getX(), me.getY())){
 			mf.setRecAttribution(thisRec);
 		if((me.getX() < thisRec.x+thisRec.w+10 && me.getX() > thisRec.x+thisRec.w-10) && (me.getY() < thisRec.y+thisRec.h+10 && me.getY() > thisRec.y+thisRec.h-10)){
 			sizingXY = true;
 		}
 		else if((me.getX() < thisRec.x+thisRec.w+10 && me.getX() > thisRec.x+thisRec.w-10)){
 			sizingX = true;
 		}
 		else if(me.getY() < thisRec.y+thisRec.h+10 && me.getY() > thisRec.y+thisRec.h-10){
 			sizingY = true;
 		}
 		else if(me.getX() < thisRec.x+thisRec.w-10 && me.getY() < thisRec.y+thisRec.h-10){
 			

 			offX = me.getX() - thisRec.x;
 	 		offY = me.getY() - thisRec.y;
 	 		moving = true;
 		}
 		}
 		else{
 			MyRec rec = new MyRec();
 			rec.x = me.getX();
 			rec.y = me.getY();
 			rec.name = "noname" + nonameNumber++;
 			newRec = true;
 			MyList.add(rec);
 			thisRec = rec;
 			mf.setRecAttribution(thisRec);
 			repaint();
 		}
 	}
	public void mouseReleased(MouseEvent me){
		if(newRec == true)
		{
			thisRec.w = me.getX() - thisRec.x;
			thisRec.h = me.getY() - thisRec.y;
			mf.setRecAttribution(thisRec);
			repaint();
			newRec = false;
		}
		sizingXY = false; sizingX = false; sizingY = false; moving = false; thisRec = null;}
	public void mouseDragged(MouseEvent me){
		if(sizingXY == true){
			thisRec.w = me.getX() - thisRec.x;
			thisRec.h = me.getY() - thisRec.y;
		}
		else if(sizingX == true){
			thisRec.w = me.getX() - thisRec.x;
		}
		else if(sizingY ==true){
			thisRec.h = me.getY() - thisRec.y;
		}
		else if(moving == true)
		{
			thisRec.x = me.getX() - offX;
			thisRec.y = me.getY() - offY;
		}
		else if(newRec == true)
		{
			thisRec.w = me.getX() - thisRec.x;
			thisRec.h = me.getY() - thisRec.y;
		}
		mf.setRecAttribution(thisRec);
		repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
