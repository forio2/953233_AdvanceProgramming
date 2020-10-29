package ProjectMid.Item;

public class listCharacter extends BasedCharacter  {
    
    private int speed;

    public listCharacter(String name, int speed, String imgpath) {
        this.name = name;
        this.imgpath = imgpath;
        this.speed = speed;
    }

    public String getImagepath() {
        return imgpath;
    }

    @Override
    public String toString() {
        return name;
    }
}

