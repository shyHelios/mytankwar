import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	public static int WIDTH = ResourceMgr.bulletU.getWidth();
	public static int HEIGHT = ResourceMgr.bulletU.getHeight();
	
	private static final int SPEED = PropertyMgr.getInt("tankSpeed");
	private int x = 300, y = 300;
	private Dir dir;
	private boolean live = true;
	private TankFrame tf = null;
	private Group group = Group.BAD;
    public Rectangle r = new Rectangle();
	
	public Bullet(int x, int y, Dir dir,TankFrame tf,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		r.x = x;
		r.y = y;
		r.width = WIDTH;
		r.height = HEIGHT;
		tf.bullets.add(this);
	}

	public void collideWith(Tank t) {
		// TODO Auto-generated method stub
		if(this.group == t.getGroup())return;
		
		if(this.r.intersects(t.r)) {
			this.die();
			t.die();
			
			int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tf.explodes.add(new Explode(eX, eY, tf));
		}
	}


	private void die() {
		// TODO Auto-generated method stub
		this.live = false;
	}

	public Dir getDir() {
		return dir;
	}
	public Group getGroup() {
		return group;
	}

	public boolean isLive() {
		return live;
	}

	private void move() {
		// TODO Auto-generated method stub
		switch (dir) {
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;

		default:
			break;
		}
		
		r.x = this.x;
		r.y = this.y;
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT)
			this.die();
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		switch (dir) {
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;

		default:
			break;
		}
		move();
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
}
