package CrearFrameWork.Framework.Math;

import java.util.ArrayList;
import java.util.List;

public class OverlapTester {
    float theita=0,theita2=0;
    List<Vector2> normals;
    List<Vector2> normals2;

    public OverlapTester(){
        normals = new ArrayList<Vector2>();
        normals.add(new Vector2());
        normals.add(new Vector2());
        normals.add(new Vector2());
        normals.add(new Vector2());

        normals2 = new ArrayList<Vector2>();
        normals2.add(new Vector2());
        normals2.add(new Vector2());
        normals2.add(new Vector2());
        normals2.add(new Vector2());
    }

	public static boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distSquared(c2.center);
        float radiusSum = c1.radius + c2.radius;

        return distance <= radiusSum * radiusSum;
    }
    
    public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
        if(r1.lowerLeft.x < r2.lowerLeft.x + r2.width &&
           r1.lowerLeft.x + r1.width > r2.lowerLeft.x &&
           r1.lowerLeft.y < r2.lowerLeft.y + r2.height &&
           r1.lowerLeft.y + r1.height > r2.lowerLeft.y)
            return true;
        else
            return false;
    }
    
    public static boolean overlapPointRectangle(Vector2 vec, Rectangle r){
    	if(vec.x >= r.lowerLeft.x && 
    	   vec.x <= r.lowerLeft.x + r.width && 
    	   vec.y >= r.lowerLeft.y && 
    	   vec.y <= r.lowerLeft.y + r.height)
   			return true;
    	else
    		return false;
    }
    
    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float closestX = c.center.x;
        float closestY = c.center.y;
        
        if(c.center.x < r.lowerLeft.x) {
            closestX = r.lowerLeft.x; 
        } 
        else if(c.center.x > r.lowerLeft.x + r.width) {
            closestX = r.lowerLeft.x + r.width;
        }
          
        if(c.center.y < r.lowerLeft.y) {
            closestY = r.lowerLeft.y;
        } 
        else if(c.center.y > r.lowerLeft.y + r.height) {
            closestY = r.lowerLeft.y + r.height;
        }
        
        return c.center.distSquared(closestX, closestY) < c.radius * c.radius;           
    }
    
    public static boolean pointInCircle(Circle c, Vector2 p) {
        return c.center.distSquared(p) < c.radius * c.radius;
    }
    
    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distSquared(x, y) < c.radius * c.radius;
    }
    
    public static boolean pointInRectangle(Rectangle r, Vector2 p) {
        return r.lowerLeft.x <= p.x && r.lowerLeft.x + r.width >= p.x &&
               r.lowerLeft.y <= p.y && r.lowerLeft.y + r.height >= p.y;
    }
    
    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.lowerLeft.x <= x && r.lowerLeft.x + r.width >= x &&
               r.lowerLeft.y <= y && r.lowerLeft.y + r.height >= y;
    }

    /**
    *Calculates if collision has occurred between two 
    *rectangular objects using Single Axis Theorem.
    *@param Rectangle this method accepts two parameters of type Rectangle
    *@return an object of type boolean 
    */
    public static boolean overlapRectanglesSAT(Rectangle shape1, Rectangle shape2){

        Vector2 minTransVector = new Vector2();
        Vector2 center1 = new Vector2(shape1.lowerLeft.x+shape1.width/2,shape1.lowerLeft.y + shape1.height/2);
        Vector2 center2 = new Vector2(shape2.lowerLeft.x+shape2.width/2,shape2.lowerLeft.y + shape2.height/2);

        List<Vector2> shape1corners = shape1.getCorners();
        List<Vector2> shape2corners = shape2.getCorners();

        List<Vector2> shape1Edges = shape1.getEdges(shape1corners);
        List<Vector2> shape2Edges = shape2.getEdges(shape2corners);

        List<Vector2> shape1EdgesNormals = shape1.getEdgeNormal(shape1Edges);
        List<Vector2> shape2EdgesNormals = shape2.getEdgeNormal(shape2Edges);

        for(int i = 0; i < shape1corners.size();i++){
            List<Float> shape1CornerProjection = shape1.getRectCornerProjection(shape1corners,shape1EdgesNormals.get(i));
            List<Float> shape2CornerProjection = shape2.getRectCornerProjection(shape2corners,shape1EdgesNormals.get(i));

            Vector2 minMax1 = shape1.getMinMax(shape1CornerProjection);
            Vector2 minMax2 = shape2.getMinMax(shape2CornerProjection);

            boolean over = minMax1.overlap(minMax2);
            if(!over) return false;
        }

        for(int i = 0; i < shape2corners.size();i++){
            List<Float> shape1CornerProjection = shape1.getRectCornerProjection(shape1corners,shape2EdgesNormals.get(i));
            List<Float> shape2CornerProjection = shape2.getRectCornerProjection(shape2corners,shape2EdgesNormals.get(i));

            Vector2 minMax1 = shape1.getMinMax(shape1CornerProjection);
            Vector2 minMax2 = shape2.getMinMax(shape2CornerProjection);

            boolean over = minMax1.overlap(minMax2);
            if(!over) return false;
        }

        return true;
    }
}