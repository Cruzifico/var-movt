package enox.game.variable;

import java.util.ArrayList;
import java.util.List;

import CrearFrameWork.Framework.Sound;
import CrearFrameWork.Framework.gl.Texture;
import CrearFrameWork.Framework.gl.TextureRegion;
import CrearFrameWork.Framework.impl.GLGame;

public class Assets {
	public static Texture menu_background;
	public static TextureRegion menu_backgroundRegion;
	public static Texture game_background;
	public static TextureRegion game_backgroundRegion;
	public static Texture font_atlas;
	public static List<TextureRegion> font_atlas_region = new ArrayList<TextureRegion>();
	public static TextureRegion block_region;
	
	public static void load(GLGame game){
		menu_background = new Texture(game, "background.png");
        menu_backgroundRegion = new TextureRegion(menu_background, 0, 0, 960, 640);
        
        game_background = new Texture(game, "Algorithm.png");
        game_backgroundRegion = new TextureRegion(game_background,0,0,960,640);
        
        //Font
        font_atlas = new Texture(game,"element.png");
        	font_atlas_region.add(new TextureRegion(font_atlas,105,0,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,155,0,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,205,0,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,105,50,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,155,50,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,205,50,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,105,100,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,155,100,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,205,100,50,50));
        	font_atlas_region.add(new TextureRegion(font_atlas,105,150,50,50));
        //block
        block_region = new TextureRegion(font_atlas,0,0,105,105);
	}
	
	public static void reload(){
		menu_background.reload();
		game_background.reload();
		font_atlas.reload();
	}
	
	public static void playSound(Sound sound){
		//if(Settings.soundEnabled)
				//sound.play(1);
	}
}
