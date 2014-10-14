package enox.game.variable;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import CrearFrameWork.Framework.Game;
import CrearFrameWork.Framework.Input.TouchEvent;
import CrearFrameWork.Framework.Math.OverlapTester;
import CrearFrameWork.Framework.Math.Rectangle;
import CrearFrameWork.Framework.Math.Vector2;
import CrearFrameWork.Framework.gl.Camera2D;
import CrearFrameWork.Framework.gl.SpriteBatcher;
import CrearFrameWork.Framework.impl.GLScreen;

public class Main_menu extends GLScreen {
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle playBounds;
	Vector2 touchPoint;
	
	public Main_menu(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		guiCam = new Camera2D(glGraphics, 480,320);
		batcher = new SpriteBatcher(glGraphics, 100);
		//soundBounds = new Rectangle(0,0,64,64);
		playBounds = new Rectangle(143,25,242,232);
		touchPoint = new Vector2();
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();

		//Assets.music.play();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(OverlapTester.pointInRectangle(playBounds, touchPoint)) { 
				//	Assets.clickSound.play(1);
				//	return;
					System.out.println("Play button has been clicked");
					return;
				}
			}
			
			if(event.type == TouchEvent.TOUCH_DRAGGED){

			}
			
			if(event.type == TouchEvent.TOUCH_UP){
				if(OverlapTester.pointInRectangle(playBounds, touchPoint)) { 
					game.setScreen(new GameScreen(game)); 
					return;
				}
			}
		 }
	}
	
	
	float rot =0;
	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		//draw something
		batcher.beginBatch(Assets.menu_background);
		batcher.drawSprite(240, 160, 480, 320, Assets.menu_backgroundRegion);
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
		//System.out.println(SIZE);
		//System.out.println(VEL);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		//Assets.music.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		//Assets.music.play();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}	
}
