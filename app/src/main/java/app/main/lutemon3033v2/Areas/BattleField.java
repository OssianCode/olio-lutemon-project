package app.main.lutemon3033v2.Areas;

public class BattleField extends Storage {
    private static BattleField battleField = null;

    private BattleField (){
        super("Battle Field");

    }

    public static BattleField getInstance(){
        if(battleField == null){
            battleField = new BattleField();
        }
        return battleField;
    }

    public void fight(){

    }

}

