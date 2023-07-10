package com.nms.nmsbalance.tokenpool;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Pool {
    private int idTokenCounter = 0;
    private HashMap<Integer, Token> tokenPool = new HashMap<>();

    public int getNumberOfTokens() {
        return tokenPool.size();
    }

    public void setTokenPool(int numberOfPlayers) {

        for (int i = 0; i < 4; i++) {
            tokenPool.put(idTokenCounter, new Token("Larwa", 1));
            addTokenIDCounter();
        }
        tokenPool.put(idTokenCounter, new Token("Pelzacz", 2));
        addTokenIDCounter();
        tokenPool.put(idTokenCounter, new Token("Pusty", 0));
        addTokenIDCounter();
        for (int i = 0; i < 3 + numberOfPlayers; i++) {
            tokenPool.put(idTokenCounter, new Token("Dorosly", 3));
            addTokenIDCounter();
        }
        tokenPool.put(idTokenCounter, new Token("Krolowa", 5));
        addTokenIDCounter();
    }

    public int countToken(int type) {
        int counter = 0;
        for (Token t : tokenPool.values()) {
            if (t.getIntType() == type) {
                counter++;
            }
        }
        return counter;
    }

    private void addTokenIDCounter() {
        idTokenCounter++;
    }

    public double calculateBar(int type) {
        double alienCounter = countToken(type);
        return alienCounter / getNumberOfTokens();
    }

    public Token drawToken() {
        List<Integer> tokenKeys = tokenPool.keySet().stream().toList();
        Random random = new Random();
        int choosenTokenID = random.nextInt(tokenKeys.size());
        Token alienToken = tokenPool.get(tokenKeys.get(choosenTokenID));

        if (alienToken.getIntType() != 0) {
            tokenPool.remove(tokenKeys.get(choosenTokenID));
        }
        return alienToken;
    }


    public int pickTokenWhenNestFounded(boolean nestFounded, boolean playerInNest, boolean alienInNest) {
        List<Integer> tokenKeys = tokenPool.keySet().stream().toList();
        Random random = new Random();
        int choosenTokenID = random.nextInt(tokenKeys.size());
        Token alienToken = tokenPool.get(tokenKeys.get(choosenTokenID));
        int tierID = alienToken.getIntType();

        if (tierID == 0) {
            addToken(3);
            return 0;
        }
        if (tierID == 1 || tierID == 2) {
            if (tierID == 1) {
                addToken(3);
                tokenPool.remove(tokenKeys.get(choosenTokenID));
                return 1;
            } else {
                addToken(4);
                tokenPool.remove(tokenKeys.get(choosenTokenID));
                return 2;
            }
        }
        if (tierID == 3) {
            return 3;
        }
        if (tierID == 4) {
            return 4;
        }
        if (tierID == 5) {
            if (nestFounded) {
                if (alienInNest || playerInNest) {
                    tokenPool.remove(tokenKeys.get(choosenTokenID));
                    return 5;
                } else {
                    return 6;
                }
            } else {
                return 6;
            }
        }
        return tierID;
    }

    public int pickToken() {
        List<Integer> tokenKeys = tokenPool.keySet().stream().toList();
        Random random = new Random();
        int choosenTokenID = random.nextInt(tokenKeys.size());
        Token alienToken = tokenPool.get(tokenKeys.get(choosenTokenID));
        int tierID = alienToken.getIntType();

        if (tierID == 0) {
            addToken(3);
            return 0;
        }
        if (tierID == 1 || tierID == 2) {
            if (tierID == 1) {
                addToken(3);
                tokenPool.remove(tokenKeys.get(choosenTokenID));
                return 1;
            } else {
                addToken(4);
                tokenPool.remove(tokenKeys.get(choosenTokenID));
                return 2;
            }
        }
        if (tierID == 3) {
            return 3;
        }
        if (tierID == 4) {
            return 4;
        }
        if (tierID == 5) {
            return 6;
        }
        return tierID;
    }

    public void addToken(int tierID) {
        String stringType = "";
        if (tierID == 1) {
            stringType = "Larwa";
        }
        if (tierID == 2) {
            stringType = "Pelzacz";
        }
        if (tierID == 3) {
            stringType = "Dorosly";
        }
        if (tierID == 4) {
            stringType = "Reproduktor";
        }
        if (tierID == 5) {
            stringType = "Krolowa";
        }
        tokenPool.put(idTokenCounter, new Token(stringType, tierID));
        addTokenIDCounter();
    }
}
