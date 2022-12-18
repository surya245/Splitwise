package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {

    private String userId;
    private String name;
    private String email;
    private String mobile;
    private static List<User> userList;

    public static List<User> getUserList() {
        return userList;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public User(String userId, String name, String email, String mobile) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        UserMap userMap = UserMap.getInstance();

        Map<String ,User> userToId = userMap.getMap();
        userToId.put(userId,this);
        userMap.setMap(userToId);
        if(userList == null){
            userList = new ArrayList<>();
        }
        userList.add(this);
    }
}
