package enox.game.variable;

import java.util.ArrayList;
import java.util.List;

import android.util.FloatMath;

import CrearFrameWork.Framework.Input.TouchEvent;
import CrearFrameWork.Framework.Math.Vector2;

public class World {
	public interface WorldListener{
		public void jump();
		public void highJump();
		public void hit();
	}
	
	public static final float WORLD_WIDTH = 480;//10;
	public static final float WORLD_HEIGHT = 320;//15*20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final Vector2 gravity = new Vector2(0,-12);
	public List<Block> block;
	public List<Integer> instr;
	
	public final WorldListener listener;
	public int state;
	public float heightSoFar;
	Vector2 touchPoint = new Vector2(),touchDownPoint = new Vector2();
	boolean touchDown = false;
	float stateTime=1;
	boolean touchDragged = false;
	Block temp;
	double s=0;
	public int num_parents=0, num_children=0;
	private int instr_counter=0;
	
	public World(WorldListener listener){
		this.listener= listener;

		this.heightSoFar = 0;
		this.state = WORLD_STATE_RUNNING;
		instr = new ArrayList<Integer>();
		instr.add(new Integer(0));
		instr.add(new Integer(0));
		
		block = new ArrayList<Block>();
		block.add(new Block(200-105,130,105,105));
		block.add(new Block(200,130,105,105));
		block.add(new Block(200+105,130,105,105));
		block.add(new Block(200+105,130,105,105));
		block.get(0).font.setText("3");
		block.get(1).font.setText("1");
		block.get(2).font.setText("2");
		block.get(3).font.setText("0");
		block.get(0).parent =1;
		block.get(1).parent =1;
		block.get(2).parent =0;
		block.get(3).parent =1;
		temp = new Block(0,130,105,105);
		float w = WORLD_WIDTH;
        float numB = block.size();
        float l =  block.get(0).bounds.width;
        s =(w-numB*l)/(numB+1);
        int len = block.size();
        ///////////////////
        for(int i =0,dx= (int) ((s+l)/2); i < len;i++,dx+=l+s){
			block.get(i).position.x = dx;
		}
        ////////////////
	}
	
	int indexTouch=-1;
	public void update(float deltaTime, float accelX, List<TouchEvent> touchEvents){
		int len = touchEvents.size();
	    for(int i = 0; i < len; i++) {
	    	TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            
            if(event.type == TouchEvent.TOUCH_DOWN){
	    			stateTime=0;
	    			touchDown = true;
	    			touchDownPoint.set(touchPoint);
            }
            
            if(event.type == TouchEvent.TOUCH_DRAGGED){
	    			touchDragged = true;
	    		}

            if(event.type == TouchEvent.TOUCH_UP){
	    			touchDown = false;
	    			touchDragged = false;
            }
	    	
            stateTime+=deltaTime;
	   }
	    
	    //Sort list of font numbers
//	    int size = block.size();
//	    
//	    for(int i =0; i < size-1;i++){
//	    		for(int j = i+1; j < size;j++){
//	    			if(block.get(i).font.getValue() > block.get(j).font.getValue()){
//	    				float tempIx = block.get(i).position.x, tempJx = block.get(j).position.x;
//	    				block.set(j, block.set(i, block.get(j)));
//	    				block.get(i).position.x = tempIx; block.get(j).position.x = tempJx;
//	    			}
//	    		}
//	    }
	    
//	    int size_ins = instr.size();
//	    if(instr_counter < size_ins){
//	    		switch(instr.get(instr_counter)){
//	    		case 0:	jump(0,1,deltaTime);
//	    			break;
//	    		default:
//	    			break;
//	    		}
//	    }
	}

	private void checkCollisions(){
		checkPlatformCollisions();
		checkItemCollisions();
	}
	
	private void checkItemCollisions() {
		// TODO Auto-generated method stub
		
	}

	private void checkPlatformCollisions(){
		//Check coll
	}
	
	 private void checkGameOver() {
	 }
	 
	 private float theita=0,posx=0,posy=0,posx2=0,posy2=0,r=0;
	 private boolean set=false;
	 
	 private void jump(int index, int jindex, float deltaTime){
		 int l=(int) (block.get(0).bounds.width);
		 
		 if(!set){
			 r= Math.abs((block.get(index).position.x-block.get(jindex).position.x)/2);
			 posx=(float) (block.get(index).position.x+r);
			 posx2=(float) (block.get(jindex).position.x-r);
			 posy=block.get(index).position.y;
			 posy2=block.get(jindex).position.y;
			 set=true;
		 }
		 if(theita< Math.PI)
			 theita+=Math.PI*deltaTime;

		 block.get(index).position.x = posx - (float) (r)* FloatMath.cos(theita);
		 block.get(index).position.y = posy + (float) (r) * FloatMath.sin(theita);
		 block.get(jindex).position.x = posx2 + (float) (r)* FloatMath.cos(2*3.14f-theita);
		 block.get(jindex).position.y = posy2 + (float) (r) * FloatMath.sin(2*3.14f-theita);
		 if(theita>=Math.PI){ block.get(index).position.y=block.get(jindex).position.y=130; 
		 		//Block tempB = new Block(block.get(index));
		 		temp = block.get(index);
		 		block.set(index, block.get(jindex));
		 		block.set(jindex, temp);
		 		set=false;
		 		theita=0;instr_counter++;
		 	}
	 }
}
