package com.example.detlef.fall2what.gamelogic;

import android.content.SharedPreferences;

/**
 * Created by Altair07 on 11/01/2016.
 */
public class GameManager {

    private static GameManager instance;

    SharedPreferences preferences;

    private void GameManager()
    {

    }

    public static GameManager getInstance()
    {
        if(instance == null)
            instance = new GameManager();

        return instance;
    }

}
