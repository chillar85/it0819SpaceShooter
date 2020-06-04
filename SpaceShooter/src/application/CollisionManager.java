package application;


public class CollisionManager {

	public CollisionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkBoundsProjectile(Projectile p, Enemy e) {
		return p.getBoundsInParent().intersects(e.getBoundsInParent());
	}
	
	public boolean checkBoundsPlayer(Player p, Enemy e) {
		return p.getBoundsInParent().intersects(e.getBoundsInParent());
	}

}