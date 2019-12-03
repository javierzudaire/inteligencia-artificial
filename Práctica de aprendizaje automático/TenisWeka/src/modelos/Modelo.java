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
            // create Random Forest
            //Classifier cls = new J48();
            Classifier cls = new RandomForest();

            // train
            Instances inst = new Instances(
                    new BufferedReader(
                            new FileReader("./training_data/iris.arff")));
            inst.setClassIndex(inst.numAttributes() - 1);
            cls.buildClassifier(inst);

            // serialize model
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("./models/objetoJ48Iris.model"));
            oos.writeObject(cls);
            oos.flush();
            oos.close();

        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //5.8,2.7,3.9,1.2,?
    public String aplicarModelo() {
        try {
            String[] valoresAtributos = {"Iris-setosa", "Iris-versicolor", "Iris-virginica"};
            Classifier clasificador = (Classifier) weka.core.SerializationHelper.read("./models/objetoJ48Iris.model");
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("./test_data/test.arff");
            Instances data = source.getDataSet();
            data.setClassIndex(4);
            return valoresAtributos[(int) clasificador.classifyInstance(data.instance(0))];
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void introducirDatos() throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("-- CLASSIFIER RANDOM FOREST --");
        System.out.println("");
        System.out.print("Introduce sepallenght: ");
        String sepallenght = scan.next();
        System.out.print("Introduce sepalwidth: ");
        String sepalwidth = scan.next();
        System.out.print("Introduce petallength: ");
        String petallength = scan.next();
        System.out.print("Introduce petalwidth: ");
        String petalwidth = scan.next();
        scan.close();

        String data = sepallenght + "," + sepalwidth + "," + petallength + "," + petalwidth + ",?";

        Path test = Paths.get("test_data/test.arff");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(test, StandardCharsets.UTF_8));

        fileContent.set(9, data);

        Files.write(test, fileContent, StandardCharsets.UTF_8);

    }

}
