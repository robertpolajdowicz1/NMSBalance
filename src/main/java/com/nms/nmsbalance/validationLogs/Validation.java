package com.nms.nmsbalance.validationLogs;

public class Validation {
    public static boolean validationToken(String tierID) {
        if (isNumeric(tierID)) {
            if (Integer.parseInt(tierID) > 0 && Integer.parseInt(tierID) < 6) {
                return true;
            }
        }
        return false;
    }

    public static boolean validationRoom(String roomID) {
        if(isNumeric(roomID))
        {
            return Integer.parseInt(roomID) > 0 && Integer.parseInt(roomID) < 22;
        }else{
            return false;
        }
    }

    public static boolean validationAlien(String alienID) {
        if (isNumeric(alienID)) {
            return Integer.parseInt(alienID) < 100 && Integer.parseInt(alienID) > 0;
        } else {
            return false;
        }
    }


    public static boolean validationPlayer(String playerID, int numberOfPlayers) {
        if(isNumeric(playerID)){
            return Integer.parseInt(playerID) > 0 && Integer.parseInt(playerID) < numberOfPlayers + 1;
        }
        else{
            return false;
        }
    }

    private static boolean isNumeric(String ID) {
        try {
            Integer.parseInt(ID);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
