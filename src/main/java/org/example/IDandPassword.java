package org.example;

import java.util.HashMap;

public class IDandPassword {
    HashMap<String,String> loginInfo = new HashMap<>();

    IDandPassword()
    {
        loginInfo.put("Mikolaj","password");
        loginInfo.put("Dawid","password");
        loginInfo.put("Adrian","password");
    }

    protected HashMap getLoginInfo(){
        return loginInfo;
    }
}
