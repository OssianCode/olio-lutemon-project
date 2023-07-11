package app.main.lutemon3033;

public class Home extends Storage {

    private static Home home = null;

    private Home (){
        super("Home");

    }

    public static Home getInstance(){
        if(home == null){
            home = new Home();
        }
        return home;
    }

    public void createLutemon(Lutemon lutemon){


    }
}
