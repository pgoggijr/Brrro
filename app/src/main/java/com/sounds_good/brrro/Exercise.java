package com.sounds_good.brrro;

/**
 * Created by pgoggijr on 4/22/15.
 */
public interface Exercise {
    public int[][] getSets();
    public int getType();
    public void updateSet(int set, int rep);
}
