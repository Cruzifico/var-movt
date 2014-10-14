package enox.game.variable;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import CrearFrameWork.Framework.Game;
import CrearFrameWork.Framework.Input.TouchEvent;
import CrearFrameWork.Framework.Math.Circle;
import CrearFrameWork.Framework.Math.OverlapTester;
import CrearFrameWork.Framework.Math.Vector2;
import CrearFrameWork.Framework.gl.Camera2D;
import CrearFrameWork.Framework.gl.FPSCounter;
import CrearFrameWork.Framework.gl.SpriteBatcher;
import CrearFrameWork.Framework.impl.GLScreen;
import enox.game.variable.World.WorldListener;

public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;    
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;
  
    int state;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;    
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;    
    Circle pauseBounds;
    Circle resumeBounds;
    Circle quitBounds;
    int lastScore;
    String scoreString;    
    FPSCounter fpsCounter;
    
    public GameScreen(Game game) {
        super(game);
        state = GAME_RUNNING;//GAME_PAUSED;//GAME_READY;
        guiCam = new Camera2D(glGraphics, 320, 480);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
       // Assets.blockMusic.play();
        worldListener = new WorldListener() {
            @Override
            public void jump() {            
               //Assets.playSound(Assets.clickSound);// Assets.playSound(Assets.jumpSound);
            }

            @Override
            public void highJump() {
            	
            }

            @Override
            public void hit() {
            	
            }
                     
        };
        world = new World(worldListener);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        pauseBounds = new Circle(455, 25, 25);//Rectangle(320- 50, 480- 50, 50, 50);
        resumeBounds = new Circle(114,97,39);//new Rectangle(160 - 96, 240, 192, 36);
        quitBounds = new Circle(184+284,190,20);
        lastScore = 0;
        scoreString = "score: 0";
        fpsCounter = new FPSCounter();
    }

	@Override
	public void update(float deltaTime) {
	    if(deltaTime > 0.1f)
	        deltaTime = 0.1f;
	    
	    switch(state) {
	    case GAME_READY:
	        updateReady();
	        break;
	    case GAME_RUNNING:
	        updateRunning(deltaTime);
	        break;
	    case GAME_PAUSED:
	        updatePaused();
	        break;
	    case GAME_LEVEL_END:
	        updateLevelEnd();
	        break;
	    case GAME_OVER:
	        updateGameOver();
	        break;
	    }
	}
	
	private void updateReady() {
	    if(game.getInput().getTouchEvents().size() > 0) {
	        state = GAME_RUNNING;
	    }
	}
	
	private void updateRunning(float deltaTime) {
	    float velocity = 2.0f;
	   // int rot = (int) (velocity*deltaTime)%360;
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	        
	        if(OverlapTester.pointInCircle(pauseBounds, touchPoint)){
	            //Assets.clickSound.play(1);
	        	state = GAME_PAUSED;
	            return;
	        }
	    }
	    //draw background
	    world.update(deltaTime, game.getInput().getAccelX(),touchEvents);
	    
	    if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
	        state = GAME_LEVEL_END;        
	    }
	    if(world.state == World.WORLD_STATE_GAME_OVER) {
	        state = GAME_OVER;
	    }
	}
	
	private void updatePaused() {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        
	        touchPoint.set(event.x, event.y);
	        guiCam.touchToWorld(touchPoint);
	        
	        if(OverlapTester.pointInCircle(resumeBounds, touchPoint)) {
	          //Assets.playSound(Assets.clickSound);
	            state = GAME_RUNNING;
	            return;
	        }
	        
	        if(OverlapTester.pointInCircle(quitBounds, touchPoint)) {
	           // Assets.playSound(Assets.clickSound);
	            game.setScreen(new Main_menu(game));
	            return;
	        }
	    }
	}
	
	private void updateLevelEnd() {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {                   
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        world = new World(worldListener);
	        renderer = new WorldRenderer(glGraphics, batcher, world);
	        //world.score = lastScore;
	        state = GAME_READY;
	    }
	}
	
	private void updateGameOver() {
	    List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {                   
	        TouchEvent event = touchEvents.get(i);
	        if(event.type != TouchEvent.TOUCH_UP)
	            continue;
	        game.setScreen(new Main_menu(game));
	    }
	}

	@Override
	public void present(float deltaTime) {
	    GL10 gl = glGraphics.getGL();
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glEnable(GL10.GL_TEXTURE_2D);
	    
	    renderer.render();
	    
	    guiCam.setViewportAndMatrices();
	    gl.glEnable(GL10.GL_BLEND);
	    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

	    switch(state) {
	    case GAME_READY:
	        presentReady();
	        break;
	    case GAME_RUNNING:
	    	presentRunning();
	        break;
	    case GAME_PAUSED:
	        presentPaused();
	        break;
	    case GAME_LEVEL_END:
	        presentLevelEnd();
	        break;
	    case GAME_OVER:
	        presentGameOver();
	        break;
	    }
	    gl.glDisable(GL10.GL_BLEND);
	    fpsCounter.logFrame();
	}
	
	private void presentReady() {
	   // batcher.drawSprite(160, 240, 192, 32, Assets.ready);

	}
	
	private void presentRunning() {
	    //batcher.drawSprite(320 - 32, 480 - 32, 64, 64, Assets.menu_backgroundRegion);
		
	
	    //Assets.font.drawText(batcher, scoreString, 16, 480-20);
	}
	
	private void presentPaused() {        
		
	    //Assets.font.drawText(batcher, scoreString, 16, 480-20);
	}
	
	private void presentLevelEnd() {
	}
	
	private void presentGameOver() {
	}

    @Override
    public void pause() {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    //	Assets.blockMusic.stop();
        //Assets.level1.pause();
    }

    @Override
    public void resume() {    
    //	Assets.blockMusic.play();
    }

    @Override
    public void dispose() {    
    	//Assets.level1.dispose();
    //	Assets.blockMusic.stop();
    }
}
