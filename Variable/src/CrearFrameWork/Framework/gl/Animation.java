package CrearFrameWork.Framework.gl;

import CrearFrameWork.Framework.Math.Vector2;

public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;
    //public static final int ANIMATION_BAKC_AND_FORTH_LOOPING = 2;
    int curFrame=0;
    final TextureRegion[] keyFrames;
    final float frameDuration;
    Vector2 position = new Vector2();
    
    public Animation(float frameDuration, TextureRegion ... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }
    
    public TextureRegion getKeyFrame(float stateTime, int mode) {
        int frameNumber = (int)(stateTime / frameDuration);
    
        if(mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length-1, frameNumber); 
            curFrame = frameNumber;
        } else {
            frameNumber = frameNumber % keyFrames.length;
            curFrame = frameNumber;
        }
        return keyFrames[frameNumber];
    }
    
    public boolean reachedEnd(){
    	System.out.println(curFrame);
    	if(curFrame < keyFrames.length-1)
    		return false;
    	return true;
    }
    
    public void jump(){
    	
    }

    public int getCurFrame(){
        return curFrame;
    }
}
