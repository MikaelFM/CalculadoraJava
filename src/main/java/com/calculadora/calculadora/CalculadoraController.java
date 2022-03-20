package com.calculadora.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CalculadoraController {

    @FXML
    protected Label tela;
    @FXML
    protected Label subTela;

    protected ArrayList<String> number = new ArrayList<>();
    protected ArrayList<String> numbers = new ArrayList<>();

    @FXML
    protected void onNumber(ActionEvent event){
        Button b = (Button) event.getSource();
        String s = b.getText();
        number.add(s);
        tela.setText(arrayToString());
    }
    @FXML
    protected void clear(){
        number.clear();
        tela.setText("0");
    }
    @FXML
    protected void reset(){
        number.clear();
        numbers.clear();
        arrayToSubTela();
        tela.setText("0");
    }
    @FXML
    protected void backspace(){
        number.remove(number.size() - 1);
        tela.setText(arrayToString());
    }
    @FXML
    protected void onOperation(ActionEvent event){
        Button b = (Button) event.getSource();
        String s = b.getText();
        numbers.add(tela.getText());
        if(numbers.size() != 3){
            try {
                numbers.set(1, s);
            } catch (Exception e){
                numbers.add(s);
            }
            clear();
            arrayToSubTela();
        } else if (numbers.size() == 3){
            numbers.set(0, formataFloat(String.valueOf(fazOperacao())));
            arrayToSubTela();
            onOperation(event);
        }

    }
    @FXML
    protected void trocaSinal(ActionEvent event){
        Button b = (Button) event.getSource();
        String s = b.getText();
        try {
            if (!number.get(0).equals("-")) {
                number.add(0, "-");
            } else {
                number.remove(0);
            }
        } catch (Exception e){
            number.add("-");
        }
        tela.setText(arrayToString());
    }
    @FXML
    protected void result(){
        numbers.add(tela.getText());
        number.clear();
        String resultadoFinal = formataFloat(String.valueOf(fazOperacao()));
        stringToArray(resultadoFinal, number);
        tela.setText(resultadoFinal);
        subTela.setText("");
        numbers.clear();
    }
    @FXML
    protected void virgula(ActionEvent event){
        Button b = (Button) event.getSource();
        String s = b.getText();
        if(!number.contains(",")){
            number.add(s);
        }
        tela.setText(arrayToString());
    }
    @FXML
    protected String arrayToString(){
        String resultado = "";
        for(String s : number){
            resultado += s;
        }
        return resultado;
    }
    protected void arrayToSubTela(){
        String resultado = "";
        for(String s: numbers){
            resultado += s + " ";
        }
        subTela.setText(resultado);
    }
    protected void stringToArray(String s, ArrayList a){
        a.clear();
        String[] caracteres = s.split("");
        for(String str: caracteres){
            a.add(str);
        };
    }
    protected float fazOperacao(){
        float result;
        float n1 = Float.parseFloat(numbers.get(0).replace(",", "."));
        float n2 = Float.parseFloat(numbers.get(2).replace(",", "."));
        switch (numbers.get(1)){
            case " - ":
                result = n1 - n2;
                break;
            case " * ":
                result = n1 * n2;
                break;
            case " / ":
                result = n1 / n2;
                break;
            default:
                result = n1 + n2;
                break;
        }
        numbers.remove(2);
        numbers.remove(1);
        return result;
    }
    protected String formataFloat(String result){
        String resultado = "";
        String[] s = result.split("\\.");
        if(s[1].equals("0")){
           resultado = s[0];
        } else {
            resultado = result.replace(".", ",");
        }
        return resultado;
    }

    public void closeApp(ActionEvent event) {
        CalculadoraApplication.stage1.close();
    }
}