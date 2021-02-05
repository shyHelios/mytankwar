import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class TankFrame extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"),GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
	Tank myTank = new Tank(200, 200, Dir.RIGHT,this,Group.GOOD);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> enemies = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();
	
	public TankFrame() {
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setResizable(false);
		this.setTitle("tank war");
		this.setVisible(true);
		this.addKeyListener(new MyKeyListener());
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});

	}
    //双缓冲解决闪烁问题
	Image OffScreenImage = null;
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if(OffScreenImage == null) {
			OffScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen = OffScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.black);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(OffScreenImage,0,0,null);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(Color.white);
		g.drawString("子弹的数量" + bullets.size(), 10, 40);
		g.drawString("敌方坦克数量" + enemies.size(), 10, 60);
		g.drawString("爆炸数量" + explodes.size(), 10, 80);
		g.setColor(c);
		myTank.paint(g);
        for(Iterator<Bullet> it = bullets.iterator();it.hasNext();) {
        	Bullet b = it.next();
        	if(!b.isLive())
        		it.remove();
        	else b.paint(g);
        }
        
        for(Iterator<Tank> it = enemies.iterator();it.hasNext();) {
        	Tank t = it.next();
        	if(!t.isLive())
        		it.remove();
        	else t.paint(g);
        }
        
        for(Iterator<Bullet> it1 = bullets.iterator();it1.hasNext();) {
        	Bullet b = it1.next();
        	//没加对自己的碰撞检测
        	for(Iterator<Tank> it2 = enemies.iterator();it2.hasNext();) {
        		Tank t = it2.next();
        		b.collideWith(t);
        	}
        }
        
        for(Iterator<Explode> it = explodes.iterator();it.hasNext();) {
              Explode e = it.next();
              if(!e.isLive())
          		it.remove();
          	else e.paint(g);
        }
	}

	class MyKeyListener extends KeyAdapter {

		boolean bU = false;
		boolean bD = false;
		boolean bL = false;
		boolean bR = false;

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_W:
				bU = true;
				break;
			case KeyEvent.VK_S:
				bD = true;
				break;
			case KeyEvent.VK_A:
				bL = true;
				break;
			case KeyEvent.VK_D:
				bR = true;
				break;
			default:
				break;
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_W:
				bU = false;
				break;
			case KeyEvent.VK_S:
				bD = false;
				break;
			case KeyEvent.VK_A:
				bL = false;
				break;
			case KeyEvent.VK_D:
				bR = false;
				break;
			case KeyEvent.VK_SPACE:
				myTank.fire(FourDirFireStrategy.getInstance());
				break;
			default:
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if(!bU&&!bD&&!bL&&!bR)
				myTank.setMoving(false);
			else {
				myTank.setMoving(true);
				if (bU)
					myTank.setDir(Dir.UP);
				if (bD)
					myTank.setDir(Dir.DOWN);
				if (bL)
					myTank.setDir(Dir.LEFT);
				if (bR)
					myTank.setDir(Dir.RIGHT);
			}
		}
	}
}
