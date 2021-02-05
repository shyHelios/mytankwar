import java.awt.Graphics;

public class Explode {
     private int x,y;
     private TankFrame tf = null;
     private boolean live = true;
     private int step = 0;
     public static int WIDTH = ResourceMgr.explosion[0].getWidth();
 	 public static int HEIGHT = ResourceMgr.explosion[0].getHeight();

	public Explode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	 }

	public boolean isLive() {
		return live;
	}
     
	 public void paint(Graphics g) {
		 g.drawImage(ResourceMgr.explosion[step++], this.x, this.y, null);
		 
		 if(step >= ResourceMgr.explosion.length)
			 live = false;
	 }
     
	 public void setLive(boolean live) {
		this.live = live;
	}
}
