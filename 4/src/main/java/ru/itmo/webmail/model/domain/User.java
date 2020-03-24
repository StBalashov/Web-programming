package ru.itmo.webmail.model.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String passwordSha1;
    private long mId;
    private String mEmail;

    public String getLogin() {
        return login;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordSha1() {
        return passwordSha1;
    }

    public void setPasswordSha1(String passwordSha1) {
        this.passwordSha1 = passwordSha1;
    }
}
