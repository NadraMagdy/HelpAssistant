package com.example.helpassistant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Email;
    private String Phone;
    private String UserID;

    public String getFirstName(){
        return  this.FirstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public String getUserName(){
        return this.UserName;
    }

    public String getEmail(){
        return this.Email;
    }

    public String getPhone(){
        return this.Phone;
    }

    public String getUserID(){
        return  this.UserID;
    }

    // Return an object from JSON
    public static UserModel fromJson(JSONObject jsonObject){
        UserModel user = new UserModel();
        try{
            user.Email = jsonObject.getString("Email");
            user.FirstName = jsonObject.getString("FirstName");
            user.LastName = jsonObject.getString("LastName");
            user.Phone = jsonObject.getString("PhoneNumber");
            user.UserName = jsonObject.getString("UserName");
            user.UserID = jsonObject.getString("UserID");
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
