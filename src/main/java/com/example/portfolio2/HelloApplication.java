package com.example.portfolio2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

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

        // Combobox til vores bachelor program
        ComboBox<String> comboBoxProgram = new ComboBox<>();
        comboBoxProgram.getItems().addAll(model.baseProgram());

        //Combobox til kurser
        ComboBox<String> comboBoxCourses = new ComboBox<>();
        TextArea textAreaCourses = new TextArea();
        textAreaCourses.setEditable(false);

        // Håndtering af programvalg
        comboBoxProgram.setOnAction(event -> {
            String selectedProgram = comboBoxProgram.getValue();
            comboBoxCourses.getItems().clear();  // Ryd tidligere valg
            textAreaCourses.clear();

            List<String> courses = model.baseCourse(selectedProgram);
            if (courses != null) {
                comboBoxCourses.getItems().addAll(courses);
            }
        });

        // Når kursus vælges, tilføj det til tekstområdet
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

        // Når Subject 1 vælges → Opdater kurser
        comboBoxSubject1.setOnAction(event -> {
            String selectedSubject = comboBoxSubject1.getValue();
            comboBoxSubjectCourses1.getItems().clear(); // Ryd tidligere valg
            textAreaSubject1.clear(); // Ryd tekstfeltet

            List<String> subjectCourses = model.subjectCourse(selectedSubject);
            if (selectedSubject != null) {
                comboBoxSubjectCourses1.getItems().addAll(subjectCourses);
            }
        });

        // Når Subject 2 vælges → Opdater kurser
        comboBoxSubject2.setOnAction(event -> {
            String selectedSubject = comboBoxSubject2.getValue();
            comboBoxSubjectCourses2.getItems().clear();
            if (selectedSubject != null) {
                comboBoxSubjectCourses2.getItems().addAll(model.subjectCourse(selectedSubject));
            }
        });



        // **ELECTIVES**
        ComboBox<String> comboBoxElectives = new ComboBox<>();
        comboBoxElectives.getItems().addAll("Functional Programming", "Scientific Computing");

        // **GRIDPANE LAYOUT**
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);

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
        root.add(comboBoxElectives, 3, 1);

        // **SCENE SETUP**
        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Bachelor Program Selector");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    }


