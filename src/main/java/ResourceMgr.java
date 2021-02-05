import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
//单例模式
public class ResourceMgr {
	private static final ResourceMgr INSTANCE = new ResourceMgr();
	private ResourceMgr() {}
	public ResourceMgr getInstance() {
		return INSTANCE;
	}
    public static BufferedImage goodTank1U,goodTank1D,goodTank1L,goodTank1R;
    public static BufferedImage goodTank2U,goodTank2D,goodTank2L,goodTank2R;
    public static BufferedImage badTank1U,badTank1D,badTank1L,badTank1R;
    public static BufferedImage badTank2U,badTank2D,badTank2L,badTank2R;
    public static BufferedImage bulletU,bulletD,bulletL,bulletR;
    public static BufferedImage[] explosion = new BufferedImage[16];
    static {
    	try {
			badTank1U = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/BadTank1.png"));
			badTank1D = ImageUtil.rotateImage(badTank1U, 180);
			badTank1L = ImageUtil.rotateImage(badTank1U, -90);
			badTank1R = ImageUtil.rotateImage(badTank1U, 90);
			
			badTank2U = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/BadTank2.png"));
			badTank2D = ImageUtil.rotateImage(badTank2U, 180);
			badTank2L = ImageUtil.rotateImage(badTank2U, -90);
			badTank2R = ImageUtil.rotateImage(badTank2U, 90);
			
			goodTank1U = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/GoodTank1.png"));
			goodTank1D = ImageUtil.rotateImage(goodTank1U, 180);
			goodTank1L = ImageUtil.rotateImage(goodTank1U, -90);
			goodTank1R = ImageUtil.rotateImage(goodTank1U, 90);
			
			goodTank2U = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/GoodTank2.png"));
			goodTank2D = ImageUtil.rotateImage(goodTank2U, 180);
			goodTank2L = ImageUtil.rotateImage(goodTank2U, -90);
			goodTank2R = ImageUtil.rotateImage(goodTank2U, 90);
			
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/bulletU.png"));
            bulletD = ImageUtil.rotateImage(bulletU, 180); 
            bulletL = ImageUtil.rotateImage(bulletU, -90); 
            bulletR = ImageUtil.rotateImage(bulletU, 90); 
			
			for(int i = 0;i < 16;++i)
				explosion[i] =  ImageIO.read(ResourceMgr.class.getClassLoader().getResource("image/e" + (i+1) + ".gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
