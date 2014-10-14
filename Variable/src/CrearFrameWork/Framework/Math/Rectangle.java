package CrearFrameWork.Framework.Math;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height,theita;
    
    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
        theita=0;
    }

    public Rectangle(float x, float y, float width, float height, float theita) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
        this.theita = theita;
    }

    public Vector2 getCenter(){
        return new Vector2(lowerLeft.x + width/2,lowerLeft.y + height/2);
    }
    
    public void setCenter(Vector2 cen){
    		lowerLeft.x = cen.x - width/2;
    		lowerLeft.y = cen.y - height/2;
    }
    
    //will return the corners of rectangle
    public List<Vector2> getCorners(){
        float cx = lowerLeft.x + width/2; float cy = lowerLeft.y + height/2;
        float rDiag = android.util.FloatMath.sqrt((height*height/4) + (width*width/4));
        float x1 = cx + rDiag*android.util.FloatMath.cos((theita + 45) * 3.14f/180f);
        float y1 = cy + rDiag*android.util.FloatMath.sin((theita + 45) * 3.14f/180f);

        float x2 = cx + rDiag*android.util.FloatMath.cos((theita - 45) * 3.14f / 180f);
        float y2 = cy + rDiag*android.util.FloatMath.sin((theita - 45) * 3.14f/180f);

        float x3 = cx + rDiag*android.util.FloatMath.cos((theita - 135) * 3.14f/180f);
        float y3 = cy + rDiag*android.util.FloatMath.sin((theita - 135) * 3.14f/180f);

        float x4 = cx + rDiag*android.util.FloatMath.cos((theita + 135) * 3.14f/180f);
        float y4 = cy + rDiag*android.util.FloatMath.sin((theita + 135) * 3.14f/180f);

        List<Vector2> res = new ArrayList<Vector2>();
        res.add(new Vector2(x1,y1)); res.add(new Vector2(x2,y2));
        res.add(new Vector2(x3,y3)); res.add(new Vector2(x4,y4));

        return res;
    }

    public List<Vector2> getEdges(List<Vector2> rectCorners){
        List<Vector2> edges = new ArrayList<Vector2>();
        edges.add(new Vector2(rectCorners.get(1).x - rectCorners.get(0).x,rectCorners.get(1).y - rectCorners.get(0).y));
        edges.add(new Vector2(rectCorners.get(2).x - rectCorners.get(1).x,rectCorners.get(2).y - rectCorners.get(1).y));
        edges.add(new Vector2(rectCorners.get(3).x - rectCorners.get(2).x,rectCorners.get(3).y - rectCorners.get(2).y));
        edges.add(new Vector2(rectCorners.get(0).x - rectCorners.get(3).x,rectCorners.get(0).y - rectCorners.get(3).y));
        return edges;
    }

    public List<Vector2> getEdgeNormal(List<Vector2> rectEdges){
        List<Vector2> rectNormalEdges = new ArrayList<Vector2>();
        for(int i = 0; i < rectEdges.size(); i++){
            rectNormalEdges.add(rectEdges.get(i).getNormal(1));
        }
        return  rectNormalEdges;
    }

    public List<Float> getRectCornerProjection(List<Vector2> rectCorners, Vector2 normalAxis){
        List<Float> res = new ArrayList<Float>();
        for(int i = 0; i < rectCorners.size(); i++){
            res.add(rectCorners.get(i).dot(normalAxis));
        }
        return res;
    }

    public Vector2 getMinMax(List<Float> projections){
        float min = 99999, max = -99999;
        for(int i = 0; i < projections.size(); i++){
            if(projections.get(i) < min) min = projections.get(i);
            if(projections.get(i) > max) max = projections.get(i);
        }
        Vector2 res = new Vector2(min,max);
        return res;
    }
}