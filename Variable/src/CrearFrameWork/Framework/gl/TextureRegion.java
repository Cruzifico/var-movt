package CrearFrameWork.Framework.gl;

public class TextureRegion {    
    public final float u1, v1;
    public final float u2, v2;
    public float un1, vn1;
    public float un2, vn2;
    public final Texture texture;
    
    public TextureRegion(Texture texture, float x, float y, float width, float height) {
        this.u1 = x / texture.width;
        this.v1 = y / texture.height;
        this.u2 = this.u1 + width / texture.width;
        this.v2 = this.v1 + height / texture.height;        
        this.texture = texture;
    }
    
    public TextureRegion(TextureRegion textureRegion) {
		// TODO Auto-generated constructor stub
    		this.u1 = textureRegion.u1;
    		this.v1 = textureRegion.v1;
    		this.u2 = textureRegion.u2;
    		this.v2 = textureRegion.v2;
    		this.texture = textureRegion.texture;
	}

	void setTextureRegion(float x, float y, float w, float h){
    		this.un1 = x/texture.width;
    		this.vn1 = y/texture.height;
    		this.un2 = this.un1 + w / texture.width;
    		this.vn2 = this.vn1 + h / texture.height;
    }
}
