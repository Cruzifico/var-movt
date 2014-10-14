package Font;

import java.util.ArrayList;
import java.util.List;

import CrearFrameWork.Framework.gl.TextureRegion;
import enox.game.variable.Assets;

public class Font {
	String buffer;
	List<TextureRegion> bufferList;
	int value;
	
	public Font(String txt){
		buffer = txt;
		bufferList = new ArrayList<TextureRegion>();
		createTextureRegionList();
	}
	
	public Font(Font font) {
		// TODO Auto-generated constructor stub
		buffer = font.buffer;
		bufferList = new ArrayList<TextureRegion>();
		bufferList = font.bufferList;
		createTextureRegionList();
	}

	public int size(){
		return bufferList.size();
	}
	
	public int getValue(){
		return value;
	}
	
	public void setText(String txt){
		bufferList.clear();
		buffer = txt;
		createTextureRegionList();
	}
	
	public TextureRegion getRegion(int index){
		return bufferList.get(index);
	}
	
	void createTextureRegionList(){
		int len = buffer.length();
		value=0;
		for(int ind =0; ind < len; ind++){
			int ascii = (int)buffer.charAt(ind)-48;
			value+= ascii*Math.pow(10,ind );
			if(ascii>=Assets.font_atlas_region.size()){ascii=0;}
			int tmp = ascii;
			TextureRegion temp=new TextureRegion(Assets.font_atlas_region.get(tmp));
			bufferList.add(temp);
		}
	}
}
