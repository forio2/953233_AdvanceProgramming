package ProjectMid.controller;


import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;

import java.util.ArrayList;
public class GenItemList {
    public static ArrayList<BasedCharacter> setUpIList() {
        ArrayList<BasedCharacter> itemLists = new ArrayList<BasedCharacter>();
        itemLists.add(new listCharacter("listcharacter", 50,  "ProjectMid/assets/shirt1.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "ProjectMid/assets/armor1.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "ProjectMid/assets/android.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "ProjectMid/assets/youtube.png"));
        //itemLists.add(new listCharacter("listcharacter", 50,  "assets/armor1.png"));
        return itemLists;
    }
}
