package com.cwttech.alert.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public  class SmtpAuth extends Authenticator {
    private String user, password;

    public SmtpAuth(String getuser, String getpassword) {
        user = getuser;
        password = getpassword;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
}
