package ProjectMid.controller;


import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;

import java.util.ArrayList;
public class GenItemList {
    public static ArrayList<BasedCharacter> setUpIList() {
        ArrayList<BasedCharacter> itemLists = new ArrayList<BasedCharacter>();
        itemLists.add(new listCharacter("day", "/ProjectMid/assets/Background.png",  "ProjectMid/assets/shirt1.png"));
        itemLists.add(new listCharacter("night", "/ProjectMid/assets/bg.jpg",  "ProjectMid/assets/armor1.png"));
        return itemLists;
    }
}
