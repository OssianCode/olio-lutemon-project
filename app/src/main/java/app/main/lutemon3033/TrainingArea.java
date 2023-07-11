package app.main.lutemon3033;

public class TrainingArea extends Storage {
    private static TrainingArea trainingArea = null;

    private TrainingArea (){
        super("Training area");

    }

    public static TrainingArea getInstance(){
        if(trainingArea == null){
            trainingArea = new TrainingArea();
        }
        return trainingArea;
    }

    public void train(){

    }

}
