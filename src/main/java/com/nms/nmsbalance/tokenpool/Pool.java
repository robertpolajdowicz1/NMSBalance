package com.nms.nmsbalance.tokenpool;

import com.nms.nmsbalance.alien.*;

import java.util.HashMap;

public class Pool {
    private int numberOfTokens = 0;
    private int idAliensCounter = 0;
    private HashMap<Integer, Alien> tokenPool = new HashMap<>();

    public void addToken(int type){
        if (type == 1){
            tokenPool.put(idAliensCounter,new Alien("Larwa",1));
        } else if (type == 2) {
            tokenPool.put(idAliensCounter, new Alien("Pełzacz",2));
        } else if (type == 3) {
            tokenPool.put(idAliensCounter, new Alien("Dorosły",3));
        } else if (type == 4) {
            tokenPool.put(idAliensCounter, new Alien("Reproduktor",4));
        } else if (type == 5){
            tokenPool.put(idAliensCounter,new Alien("Królowa",5));
        }
        addIdAliensCounter();
        addNumberOfTokens();
    }
    public void removeToken(int id){
        if (tokenPool.containsKey(id)){
            tokenPool.remove(id);
            substractNumberOfTokens();
        }
    }

    public HashMap<Integer, Alien> getTokenPool() {
        return tokenPool;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public void setTokenPool(int numberOfPlayers){
        tokenPool.put(idAliensCounter,new Alien("pusty",0));
        addIdAliensCounter();
        addNumberOfTokens();
        for (int i =0; i <4;i++){
            tokenPool.put(idAliensCounter, new Alien("Larwa",1));
            addIdAliensCounter();
            addNumberOfTokens();
        }
        tokenPool.put(idAliensCounter, new Alien("Pełzacz",2));
        addIdAliensCounter();
        addNumberOfTokens();
        for (int i = 0; i < 3 + numberOfPlayers; i++){
            tokenPool.put(idAliensCounter, new Alien("Dorosły",3));
            addIdAliensCounter();
            addNumberOfTokens();
        }
       tokenPool.put(idAliensCounter, new Alien("Królowa",5));
        addIdAliensCounter();
        addNumberOfTokens();
    }
    public int countToken(int type){
        int counter = 0;
        for (Alien a : tokenPool.values())
        {
            if(a.getIntType() == type){
                counter++;
            }
        }
        return counter;
    }
    private void addNumberOfTokens(){
        numberOfTokens++;
    }
    private void addIdAliensCounter(){
        idAliensCounter++;
    }
    private void substractNumberOfTokens(){
        numberOfTokens--;
    }
}
