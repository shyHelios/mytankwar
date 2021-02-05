import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tank {
    public static int WIDTH = ResourceMgr.badTank1U.getWidth();
    public static int HEIGHT = ResourceMgr.badTank1U.getHeight();

    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    private int x = 200, y = 200;

    Dir dir = Dir.RIGHT;
    private int step = 0;

    private boolean moving = true;

    private boolean live = true;

    TankFrame tf = null;

    private Random random = new Random();
    Group group = Group.BAD;
    public Rectangle r = new Rectangle();

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
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
    }

    public void die() {
        // TODO Auto-generated method stub
        live = false;
    }

    public void fire(FireStrategy fireStrategy) {
        // TODO Auto-generated method stub
        fireStrategy.fire(this);
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return live;
    }

    public boolean isMoving() {
        return moving;
    }

    private void move() {
        // TODO Auto-generated method stub
        if (!moving) return;
        ++step;
        if (step == 100) step = 0;
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

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire(DefaultFireStrategy.getInstance());
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();
        boundsCheck();

        r.x = this.x;
        r.y = this.y;
    }

    private void boundsCheck() {
        // TODO Auto-generated method stub
        if (x < 2) x = 2;
        if (y < 28) y = 28;
        if (x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        // TODO Auto-generated method stub
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void paint(Graphics g) {
        // TODO Auto-generated method stub

        switch (dir) {
            case UP:
                g.drawImage(this.group == Group.GOOD ? ((step & 1) == 0 ? ResourceMgr.goodTank1U : ResourceMgr.goodTank2U) : ((step & 1) == 0 ? ResourceMgr.badTank1U : ResourceMgr.badTank2U), x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ((step & 1) == 0 ? ResourceMgr.goodTank1D : ResourceMgr.goodTank2D) : ((step & 1) == 0 ? ResourceMgr.badTank1D : ResourceMgr.badTank2D), x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ((step & 1) == 0 ? ResourceMgr.goodTank1L : ResourceMgr.goodTank2L) : ((step & 1) == 0 ? ResourceMgr.badTank1L : ResourceMgr.badTank2L), x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ((step & 1) == 0 ? ResourceMgr.goodTank1R : ResourceMgr.goodTank2R) : ((step & 1) == 0 ? ResourceMgr.badTank1R : ResourceMgr.badTank2R), x, y, null);
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

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
