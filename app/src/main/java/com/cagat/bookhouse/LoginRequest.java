package com.cagat.bookhouse;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cagat on 25.12.2016.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://android-project.esy.es/login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
