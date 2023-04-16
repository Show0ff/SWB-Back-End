package com.khlopin.BearSWB.services;

import com.google.gson.Gson;
import com.khlopin.BearSWB.entity.Access;
import com.khlopin.BearSWB.entity.User;


public class LoginInAccountService {

    private final Gson gson = new Gson();

    public Access getAccess(String dataFromFront) {
        User clientUser = gson.fromJson(dataFromFront, User.class);
        System.out.println(clientUser + " CLIENT USER");
        User userFromDbByLogin = ChatRepository.findUserFromDbByLogin(clientUser.getLogin());
        System.out.println(userFromDbByLogin + " USER FROM DB" );
        Access access;
        int hash = clientUser.getPassword().hashCode();
        if (userFromDbByLogin != null
                && userFromDbByLogin.getLogin().equals(clientUser.getLogin())
                && Integer.parseInt(userFromDbByLogin.getPassword()) == hash) {

            access = Access.TRUE;
        } else {
            access = Access.FALSE;
        }
        return access;
    }
}
