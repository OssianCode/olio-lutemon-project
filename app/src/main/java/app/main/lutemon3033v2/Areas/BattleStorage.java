package app.main.lutemon3033v2.Areas;

import app.main.lutemon3033v2.Lutemons.Lutemon;

public class BattleStorage {


    private static BattleStorage battleStorage = null;


    private Lutemon lutemonA = null;
    private Lutemon lutemonB = null;
    private Lutemon winner = null;
    private Lutemon loser = null;

    private BattleStorage (){


    }

    public static BattleStorage getInstance(){
        if(battleStorage == null){
            battleStorage = new BattleStorage();
        }
        return battleStorage;
    }

    public void setWinner(Lutemon winner) {
        this.winner = winner;
    }

    public void setLoser(Lutemon loser) {
        this.loser = loser;
    }

    public void setLutemonA(Lutemon lutemonA) {
        this.lutemonA = lutemonA;
    }

    public void setLutemonB(Lutemon lutemonB) {
        this.lutemonB = lutemonB;
    }

    public Lutemon getWinner() {
        return winner;
    }

    public Lutemon getLoser() {
        return loser;
    }

    public Lutemon getLutemonA() {
        return lutemonA;
    }

    public Lutemon getLutemonB() {
        return lutemonB;
    }
}
