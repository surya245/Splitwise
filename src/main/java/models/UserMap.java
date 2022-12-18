package models;

import java.util.HashMap;
import java.util.Map;

public class UserMap {
    private static UserMap userMap = null;
    private Map<String ,User> map;

    private UserMap() {
        this.map = new HashMap<>();
    }
    public static UserMap getInstance(){
        if(userMap == null){
            userMap = new UserMap();
        }
        return  userMap;
    }

    public Map<String,User> getMap(){
        return this.map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }
}
