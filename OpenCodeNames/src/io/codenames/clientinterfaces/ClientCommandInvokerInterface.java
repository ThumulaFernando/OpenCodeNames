package io.codenames.clientinterfaces;

import io.codenames.serverdata.Game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCommandInvokerInterface extends Remote {
    boolean startGame() throws RemoteException;
    boolean reciveMessageBroadCast(String Message,String playerName,int team) throws RemoteException;
    boolean cardOpened(String code, boolean turnChange) throws RemoteException;
    boolean turnPassed() throws RemoteException;
    boolean reciveGameMessage(String message) throws RemoteException;
    boolean reciveHint(String hint,String playerName,int team) throws RemoteException;
    boolean playerDropped()throws RemoteException;
    void gameWinLossMessageReceive(int lastTurnBy, boolean wonByDeathCard) throws RemoteException;
}
