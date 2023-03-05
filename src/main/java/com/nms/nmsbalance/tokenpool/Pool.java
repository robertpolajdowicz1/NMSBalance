package com.nms.nmsbalance.tokenpool;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Pool {
    private int idAliensCounter = 0;
    private HashMap<Integer,Token> tokenPool = new HashMap<>();
    public int getNumberOfTokens() {
        return tokenPool.size();
    }

    public void setTokenPool(int numberOfPlayers){
        //tokenPool.put(idAliensCounter,new Token("pusty",0));
       // addIdAliensCounter();

        for (int i = 0; i < 4; i++){
            tokenPool.put(idAliensCounter, new Token("Larwa",1));
            addIdAliensCounter();

        }
        tokenPool.put(idAliensCounter, new Token("Pelzacz",2));
        addIdAliensCounter();

        for (int i = 0; i < 3 + numberOfPlayers; i++){
            tokenPool.put(idAliensCounter, new Token("Dorosly",3));
            addIdAliensCounter();

        }
       tokenPool.put(idAliensCounter, new Token("Krolowa",5));
        addIdAliensCounter();

    }
    public int countToken(int type){
        int counter = 0;
        for (Token t : tokenPool.values())
        {
            if(t.getIntType() == type){
                counter++;
            }
        }
        return counter;
    }
    private void addIdAliensCounter(){
        idAliensCounter++;
    }
    public double calculateBar(int type){
        double alienCounter = countToken(type);
        return alienCounter/ getNumberOfTokens();
    }

    public Token drawToken(){
        List<Integer> tokenKeys = tokenPool.keySet().stream().toList();
        Random random = new Random();
        int choosenTokenID = random.nextInt(tokenKeys.size());
        Token alienToken = tokenPool.get(tokenKeys.get(choosenTokenID));
        tokenPool.remove(tokenKeys.get(choosenTokenID));
        return alienToken;
    }


}
