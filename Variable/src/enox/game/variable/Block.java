package enox.game.variable;

import CrearFrameWork.GameDev2D.DynamicGameObject;
import Font.Font;

public class Block extends DynamicGameObject{
	public Font font;
	public int parent;

	public Block(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		font = new Font("1");
		parent=0;
	}
	
	public Block(Block block) {
		super(block.position.x, block.position.y, block.bounds.width, block.bounds.height);
		// TODO Auto-generated constructor stub
		font = new Font(block.font);
		parent = block.parent;
	}

	public void update(float deltaTime){
		
		//position.sub(VEL_X*deltaTime, VEL_Y*deltaTime);
		//bounds.lowerLeft.sub(VEL_X*deltaTime, VEL_Y*deltaTime);
	}

}
