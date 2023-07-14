package app.main.lutemon3033v2.Areas;

public class Graveyard extends Storage {
        private static Graveyard graveyard = null;

        private Graveyard (){
            super("Graveyard");

        }

        public static Graveyard getInstance(){
            if(graveyard == null){
                graveyard = new Graveyard();
            }
            return graveyard;
        }

        public void bury(){

        }

    }
