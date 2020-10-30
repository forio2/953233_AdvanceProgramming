package ProjectMid.controller;


import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GenItemList {
    public static ArrayList<BasedCharacter> setUpIList() {
        ArrayList<BasedCharacter> itemLists = new ArrayList<BasedCharacter>();
        itemLists.add(new listCharacter("Day", "/ProjectMid/assets/Background.png",  "ProjectMid/assets/shirt1.png"));
        itemLists.add(new listCharacter("Night", "/ProjectMid/assets/bg.jpg",  "ProjectMid/assets/armor1.png"));
        return itemLists;
    }

}
