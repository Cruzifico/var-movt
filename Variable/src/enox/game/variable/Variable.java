package enox.game.variable;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import CrearFrameWork.Framework.Screen;
import CrearFrameWork.Framework.impl.GLGame;

public class Variable extends GLGame{
	boolean firstTimeCreate = true;
	
	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new Main_menu(this);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config){
		super.onSurfaceCreated(gl, config);
		if(firstTimeCreate){
			//Settings.load(getFileIO());
			Assets.load(this);
			firstTimeCreate = false;
		} else {
			Assets.reload();
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		if(Settings.soundEnabled)
			;//Assets.music.pause();
	}

}
