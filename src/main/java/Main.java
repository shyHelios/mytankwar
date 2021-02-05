public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		TankFrame tf = new TankFrame();
		int enemyTankCount = PropertyMgr.getInt("enemyTankCount");
		for (int i = 0; i < enemyTankCount; ++i) {
			tf.enemies.add(new Tank(40 + i * 40, 60, Dir.DOWN, tf, Group.BAD));
		}
		new Thread(() -> new Audio("audio/war1.wav").loop()).start();

		while (true) {
			Thread.sleep(25);
			tf.repaint();
		}
	}

}
