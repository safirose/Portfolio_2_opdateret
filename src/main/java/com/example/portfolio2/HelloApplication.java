package com.example.portfolio2;
//Importerer følgende javafx klasser
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//Importerer Java klasser * = Mange
import java.util.*;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        // Opretter labels/Overskrifter til vores GUI
        Label label1 = new Label("Program");
        Label label2 = new Label("Subject 1");
        Label label3 = new Label("Subject 2");
        Label label4 = new Label("Electives");

        // Opretter model-instans for at hente data (fra model programmet?)
        Model model = new Model();

        // Combobox til valg af bachelorprogram
        ComboBox<String> comboBoxProgram = new ComboBox<>();
        comboBoxProgram.getItems().addAll(model.baseProgram());

        //Combobox til kurser
        ComboBox<String> comboBoxCourses = new ComboBox<>();
        //Opretter et textarea :)
        TextArea textAreaCourses = new TextArea();
        TextArea textAreaElectives = new TextArea();
        //Bruger skal ikke kunne ændre i vores textarea
        textAreaCourses.setEditable(false);

        // Håndtering af programvalg
        comboBoxProgram.setOnAction(event -> {
        //Henter det valgte program
            String selectedProgram = comboBoxProgram.getValue();
        //Rydder "fravalgte" kurser fra combobox
            comboBoxCourses.getItems().clear();
        //Rydder "fravalgte" kurser fra textarea
            textAreaCourses.clear();
        //Henter listen af kurser, der hører til det valgte program - fra model-klassen
            List<String> courses = model.baseCourse(selectedProgram);
            if (courses != null) {
                comboBoxCourses.getItems().addAll(courses);
            }
        });

        // Når kursus vælges, tilføj til textarea
        comboBoxCourses.setOnAction(event -> {
            String selectedCourse = comboBoxCourses.getValue();
            if (selectedCourse != null && !textAreaCourses.getText().contains(selectedCourse)) {
                textAreaCourses.appendText(selectedCourse + "\n");
            }
        });

        // Combobox til fagmodul 1 & 2
        ComboBox<String> comboBoxSubject1 = new ComboBox<>();
        TextArea textAreaSubject1 = new TextArea();
        textAreaSubject1.setEditable(false);


        ComboBox<String> comboBoxSubject2 = new ComboBox<>();
        TextArea textAreaSubject2 = new TextArea();
        textAreaSubject2.setEditable(false);

        comboBoxSubject1.getItems().addAll(model.subjectModule());
        comboBoxSubject2.getItems().addAll(model.subjectModule());

        // Kurser af fagmoduler (Afhænger af valgt subject module)
        ComboBox<String> comboBoxSubjectCourses1 = new ComboBox<>();
        ComboBox<String> comboBoxSubjectCourses2 = new ComboBox<>();

        comboBoxSubject1.setOnAction(event -> {
            String selectedSubject = comboBoxSubject1.getValue();
            comboBoxSubjectCourses1.getItems().clear();
            textAreaSubject1.clear();

            if (selectedSubject != null) {
                List<String> subjectCourses = model.subjectCourse(selectedSubject);
                comboBoxSubjectCourses1.getItems().addAll(subjectCourses);
            }
        });


        // **Når Subject 2 vælges → Opdater kurser, men ikke textArea endnu**
        comboBoxSubject2.setOnAction(event -> {
            String selectedSubject = comboBoxSubject2.getValue();
            comboBoxSubjectCourses2.getItems().clear();
            textAreaSubject2.clear();

            if (selectedSubject != null) {
                List<String> subjectCourses = model.subjectCourse(selectedSubject);
                comboBoxSubjectCourses2.getItems().addAll(subjectCourses);
            }
        });
        // **Når et kursus vælges i Subject 1 → Tilføj til textAreaSubject1**
        comboBoxSubjectCourses1.setOnAction(event -> {
            String selectedCourse = comboBoxSubjectCourses1.getValue();
            if (selectedCourse != null && !textAreaSubject1.getText().contains(selectedCourse)) {
                textAreaSubject1.appendText(selectedCourse + "\n");
            }
        });
        // **Når et kursus vælges i Subject 2 → Tilføj til textAreaSubject2**
        comboBoxSubjectCourses2.setOnAction(event -> {
            String selectedCourse = comboBoxSubjectCourses2.getValue();
            if (selectedCourse != null && !textAreaSubject2.getText().contains(selectedCourse)) {
                textAreaSubject2.appendText(selectedCourse + "\n");
            }
        });
        //Combobox til layout, så det ser lidt pænere ud
        ComboBox comboboxLayout = new ComboBox();


        // Combobox til valgfag
        ComboBox<String> comboBoxElectives = new ComboBox<>();
        comboBoxElectives.getItems().addAll("Functional Programming", "Scientific Computing");

        // Vores gridpane layout, så vi kan styre placering af vores elementer
        GridPane root = new GridPane();
        //Hvad gør disse to?
        //root.setHgap(10);
        //root.setVgap(10);

        root.add(label1, 0, 0);
        root.add(comboBoxProgram, 0, 1);
        root.add(comboBoxCourses, 0, 2);
        root.add(textAreaCourses, 0, 3);

        root.add(label2, 1, 0);
        root.add(comboBoxSubject1, 1, 1);
        root.add(comboBoxSubjectCourses1, 1, 2);
        root.add(textAreaSubject1, 1, 3);

        root.add(label3, 2, 0);
        root.add(comboBoxSubject2, 2, 1);
        root.add(comboBoxSubjectCourses2, 2, 2);
        root.add(textAreaSubject2, 2, 3);

        root.add(label4, 3, 0);
        root.add(comboboxLayout,3,1);
        root.add(comboBoxElectives, 3, 2);
        root.add(textAreaElectives, 3, 3);

        // Vores "scene"
        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Bachelor Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    }

    //NOTE:Hvis tid, lav programmet metode orienteret.
    //NOTE: Hvis tid, lav layout pænere ved at ændre størrelsen på combobox, textarea, labels osv.



