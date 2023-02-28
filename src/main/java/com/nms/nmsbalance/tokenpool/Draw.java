package com.nms.nmsbalance.tokenpool;

import java.util.List;
import java.util.Random;

public class Draw {
    private final Pool pool = new Pool();

    public void setPool(int numberOfPlayers){
        pool.setTokenPool(numberOfPlayers);
    }

    public int drawToken(){
        int size = pool.getNumberOfTokens();
        Random random = new Random();
        int randomNumber = random.nextInt(size);
        List<Integer> drawList = pool.getTokenPool().keySet().stream().toList();
        return drawList.get(randomNumber);
    }

    public double calculateBar(int type){
        double alienCounter = pool.countToken(type);
        return alienCounter/ pool.getNumberOfTokens();
    }
}
