package app.main.lutemon3033v2.Areas;

public class TrainingArena extends Storage {
    private static TrainingArena trainingArea = null;

    private TrainingArena(){
        super("Training area");

    }

    public static TrainingArena getInstance(){
        if(trainingArea == null){
            trainingArea = new TrainingArena();
        }
        return trainingArea;
    }

    public void train(){

    }

}
