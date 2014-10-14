package CrearFrameWork.Framework.Math;


public class Circle {
	public final Vector2 center = new Vector2();
	public float radius;
	
	public Circle(float x, float y, float radius){
		this.center.set(x,y);
		this.radius = radius;
	}

	public void setCenter(Vector2 position, int i) {
		// TODO Auto-generated method stub
		this.center.set(position.x,position.y);
		this.radius = i;
	}
}
