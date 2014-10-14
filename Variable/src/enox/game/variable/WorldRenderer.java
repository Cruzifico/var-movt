package enox.game.variable;

import javax.microedition.khronos.opengles.GL10;

import CrearFrameWork.Framework.gl.Camera2D;
import CrearFrameWork.Framework.gl.SpriteBatcher;
import CrearFrameWork.Framework.impl.GLGraphics;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = 480;
    static final float FRUSTUM_HEIGHT = 320;    
    GLGraphics glGraphics;
    World world;
    Camera2D cam;
    SpriteBatcher batcher;
    //double s=0;
    
    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.batcher = batcher;
    }
    
    public void render() {
        //if(world.ball.position.y > cam.position.y )
        //    cam.position.y = world.ball.position.y;
        cam.setViewportAndMatrices();
        renderBackground();
        renderVariables();
        //renderObjects();
    }
    
    public void renderVariables(){
    		GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    		batcher.beginBatch(Assets.font_atlas);
    		int len = world.block.size();
    		int l=0;
    		double s = world.s;
    		if(len>0)
    			l=(int) (world.block.get(0).bounds.width);
    		for(int i =0,dx=(int) s+l/2; i < len;i++,dx+=l+s){
    			float y = world.block.get(i).position.y;
    			dx=(int) world.block.get(i).position.x;//this right here
    			batcher.drawSprite(dx, y, 105, 105, Assets.block_region);//dx
    			int le = world.block.get(i).font.size();
    			int ws = 50; 
    			int ds =(l-le*ws)/(le+1);
    			for(int j=0,ddx=(int) (ds+ws/2);j<le;j++,ddx+=ws+ds){
    				batcher.drawSprite(dx+ddx-ws, y, 50, 50, world.block.get(i).font.getRegion(j));
    			}
    		}
    		batcher.endBatch();
    		gl.glDisable(GL10.GL_BLEND);
    }
    
    public void renderBackground() {
    		batcher.beginBatch(Assets.game_background);
    		batcher.drawSprite(240, 160, 480, 320, Assets.game_backgroundRegion);
    		batcher.endBatch();
    }
    
    float theita = 0;
    float dx=160,dy=60;
    float x = 160, y = 60,vx=0,vy=0;
    
    public void renderObjects() {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        //draw something
        gl.glDisable(GL10.GL_BLEND);
    }
    
}
