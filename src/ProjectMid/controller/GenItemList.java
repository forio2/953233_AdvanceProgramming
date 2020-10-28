package ProjectMid.controller;


import ProjectMid.model.Item.BasedCharacter;
import ProjectMid.model.Item.listCharacter;

import java.util.ArrayList;
public class GenItemList {
    public static ArrayList<BasedCharacter> setUpIList() {
        ArrayList<BasedCharacter> itemLists = new ArrayList<BasedCharacter>();
        itemLists.add(new listCharacter("listcharacter", 50,  "assets/shirt1.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "assets/armor1.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "assets/android.png"));
        itemLists.add(new listCharacter("listcharacter", 50,  "assets/youtube.png"));
        //itemLists.add(new listCharacter("listcharacter", 50,  "assets/armor1.png"));
        return itemLists;
    }
}
