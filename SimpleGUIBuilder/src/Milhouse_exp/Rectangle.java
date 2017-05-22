public class Rectangle
{
	private int x,y,width,height;
	
	public Rectangle(int x,int y,int width,int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		if (this.width<0)
			this.width = 0;
		if (this.height<0)
			this.height = 0;
	}
	
	public Rectangle(Rectangle a)
	{
		this.x = a.x;
		this.y = a.y;
		this.width = a.width;
		this.height = a.height;
	}
	
	@Override
	public String toString()
	{
		return "Start: ("+x+","+y+"), Width: "+width+", Height: "+height+"\n";
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int area()
	{
		return width*height;
	}
	
	public boolean overlaps(Rectangle a)
	{
		if ((x>a.x+a.width) || (a.x>x+width) || (y>a.y+a.height) || (a.y>y+height))
		{
			return false;
		}
		return true;
	}
}
