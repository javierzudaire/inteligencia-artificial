/*
 *  Javier Zudaire
 */
package modelos;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author javierzudaire
 */
public class Modelo {

    public void aprenderModelo() {
        try {
            // Create Random Forest
            Classifier cls = new RandomForest();

            // train
            Instances inst = new Instances(
                    new BufferedReader(
                            new FileReader("./training_data/atp_matches_2018.arff")));
            inst.setClassIndex(inst.numAttributes() - 1);
            cls.buildClassifier(inst);

            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("./models/objetoRandomForest.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();

        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String aplicarModelo() {
        try {
            String[] valoresAtributos = {"Player1", "Player2"};
            Classifier clasificador = (Classifier) weka.core.SerializationHelper.read("./models/objeto2RandomForest.model");
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("./test_data/test_tennis.arff");
            Instances data = source.getDataSet();
            data.setClassIndex(1);
            return valoresAtributos[(int) clasificador.classifyInstance(data.instance(0))];
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void introducirDatos() throws IOException {

        //Clay,?,R,FRA,24.75,R,CRO,30.15,10,15,7,15,32,7
        Scanner scan = new Scanner(System.in);
        System.out.println("-- CLASSIFIER RANDOM FOREST --");
        System.out.println("");
        System.out.print("Introduce surface (Hard,Clay,Grass): ");
        String surface = scan.next();
        System.out.print("Introduce player1_hand (R,L,U): ");
        String winner_hand = scan.next();
        System.out.print("Introduce player1_ioc (country): ");
        String winner_ioc = scan.next();
        System.out.print("Introduce player1_age: ");
        String winner_age = scan.next();
        System.out.print("Introduce player2_hand (R,L,U): ");
        String loser_hand = scan.next();
        System.out.print("Introduce player2_ioc (country): ");
        String loser_ioc = scan.next();
        System.out.print("Introduce player2_age: ");
        String loser_age = scan.next();
        System.out.print("Introduce player1_ace (numeric): ");
        String w_ace = scan.next();
        System.out.print("Introduce player1_SvGms (numeric): ");
        String w_SvGms = scan.next();
        System.out.print("Introduce player2_ace (numeric): ");
        String l_ace = scan.next();
        System.out.print("Introduce player2_SvGms (numeric): ");
        String l_SvGms = scan.next();
        System.out.print("Introduce player1_rank (numeric): ");
        String winner_rank = scan.next();
        System.out.print("Introduce player2_rank (numeric): ");
        String loser_rank = scan.next();
        scan.close();
        System.out.println("");

        String data = surface + ",?," + winner_hand + "," + winner_ioc + "," + winner_age + "," + loser_hand + "," + loser_ioc + "," + loser_age + "," + w_ace + "," + w_SvGms + "," + l_ace + "," + l_SvGms + "," + winner_rank + "," + loser_rank;

        Path test = Paths.get("test_data/test_tennis.arff");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(test, StandardCharsets.UTF_8));

        fileContent.set(18, data);

        Files.write(test, fileContent, StandardCharsets.UTF_8);

    }

}
