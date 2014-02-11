package com.example.models;


import com.j256.ormlite.field.DatabaseField;

/**
 * Created by wben on 14-2-10.
 */

public class Hello {
    @DatabaseField(generatedId =  true)
    int id;
    @DatabaseField
    String word;

    public Hello()
    {}
    public Hello(String string)
    {
        word = string;
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(",word=").append(word);
        return sb.toString();
    }


}
