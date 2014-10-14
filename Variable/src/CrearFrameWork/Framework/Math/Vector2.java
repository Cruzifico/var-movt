package CrearFrameWork.Framework.Math;

import android.util.FloatMath;

public class Vector2 {
	public static Vector2 unit = new Vector2(1,-1);
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	public float x, y;

	public Vector2() {
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	public Vector2 cpy() {
		return new Vector2(x, y);
	}

	public Vector2 set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2 set(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
		return this;
	}

	public Vector2 add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2 add(Vector2 other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2 tween(Vector2 other, float deltaT){
		if(deltaT>0.9f) deltaT = 1f;
		this.x = other.x*deltaT + this.x*(1-deltaT);
		this.y = other.y*deltaT + this.y*(1-deltaT);
		return this;
	}
	
	public Vector2 tween_2nd(Vector2 other, float deltaT){
		if(deltaT>0.9f) deltaT = 1f;
		this.x = other.x*deltaT + this.x*((1-deltaT)*(1-deltaT));
		this.y = other.y*deltaT + this.y*((1-deltaT)*(1-deltaT));
		return this;
	}

	public Vector2 sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2 sub(Vector2 other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2 mul(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

    public Vector2 div(float scalar){
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

	public float len() {
		return FloatMath.sqrt(x * x + y * y);
	}

	public Vector2 nor() {
		float len = len();
		if (len != 0) {
			this.x /= len;
			this.y /= len;
		}
		return this;
	}

	public float angle() {
		float angle = (float) Math.atan2(y, x) * TO_DEGREES;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public Vector2 rotate(float angle) {
		float rad = angle * TO_RADIANS;
		float cos = FloatMath.cos(rad);
		float sin = FloatMath.sin(rad);

		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin + this.y * cos;

		this.x = newX;
		this.y = newY;

		return this;
	}

	public float dist(Vector2 other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return FloatMath.sqrt(distX * distX + distY * distY);
	}

	public float dist(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return FloatMath.sqrt(distX * distX + distY * distY);
	}

	public float distSquared(Vector2 other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return distX * distX + distY * distY;
	}

	public float distSquared(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return distX * distX + distY * distY;
	}
	
	public float dot(Vector2 other){
		return this.x*other.x+this.y*other.y;
	}
	
	public Vector2 projectOnto(Vector2 other){
		float dp = this.dot(other);
		return new Vector2((dp/(other.x*other.x + other.y*other.y))*other.x,
				(dp/(other.x*other.x + other.y*other.y))*other.y);
	}
	
	public Vector2 getNormal(int left_right){
		Vector2 normal = new Vector2(-this.y,this.x);
		if(left_right == 1)//turn to right normal otherwise its left normal
			normal.mul(-1);
		normal.nor();//normalize the vector
		return normal;
	}

    public boolean overlap(Vector2 q){
        if(q.x <= this.x && this.x <= q.y){
            return true;
        }
        if(q.x <= this.y && this.y <= q.y){
            return true;
        }

        if(this.x <= q.x && q.x <= this.y){
            return true;
        }
        if(this.x <= q.y && q.y <= this.y){
            return true;
        }
        return false;
    }
}