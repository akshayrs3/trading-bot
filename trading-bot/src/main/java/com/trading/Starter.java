package com.trading;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

import java.io.IOException;

public class Starter {
    public static void main(String args[]) throws KiteException, IOException {
        KiteConnect kiteSdk = new KiteConnect("your_apiKey");

        // Set userId.
        kiteSdk.setUserId("your_userId");

/* First you should get request_token, public_token using kitconnect login and then use request_token, public_token, api_secret to make any kiteconnect api call.
Get login url. Use this url in webview to login user, after authenticating user you will get requestToken. Use the same to get accessToken. */
        String url = kiteSdk.getLoginURL();

// Get accessToken as follows,
        User user =  kiteSdk.generateSession("request_token", "your_apiSecret");

// Set request token and public token which are obtained from login process.
        kiteSdk.setAccessToken(user.accessToken);
        kiteSdk.setPublicToken(user.publicToken);

// Set session expiry callback.
        kiteSdk.setSessionExpiryHook(new SessionExpiryHook() {
            @Override
            public void sessionExpired() {
                System.out.println("session expired");
            }
        });
        System.out.println("Hello World!");
    }
}
