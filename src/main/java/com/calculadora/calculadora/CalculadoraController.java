package com.calculadora.calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraController {

    @FXML
    protected Label tela;
    @FXML
    protected Label subTela;
    protected ArrayList<String> number = new ArrayList<>();
    protected ArrayList<String> numbers = new ArrayList<>();


    @FXML
    protected void onNumber(String s){
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
    protected void onOperation(String s){
        numbers.add(tela.getText());
        if(numbers.size() != 3){
            try {
                numbers.set(1, s);
            } catch (Exception e){
                numbers.add(s);
            }
            clear();
            arrayToSubTela();
        } else {
            numbers.set(0, formataFloat(String.valueOf(fazOperacao())));
            arrayToSubTela();
            onOperation(s);
        }

    }
    @FXML
    protected void trocaSinal(){
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
    protected void virgula(){
        if(!number.contains(",")){
            number.add(",");
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
    public void closeApp() {
        CalculadoraApplication.stage1.close();
    }
    public void key(KeyEvent k){
            String s = k.getText();
            String code = String.valueOf(k.getCode());
            System.out.println(code);
            ArrayList<String> algarismos = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
            ArrayList<String> operacoes = new ArrayList<>(List.of("+", "-", "/", "*"));
            if (algarismos.contains(s)) {
                onNumber(s);
            } else if (code.equals("BACK_SPACE")) {
                backspace();
            } else if (s.equals("=") || code.equals("ENTER")) {
                result();
            } else if (s.equals(",") || s.equals(".")) {
                virgula();
            } else if (operacoes.contains(s)) {
                onOperation(" " + s + " ");
            }   else if (code.equals("ESCAPE")) {
                closeApp();
            }
    }
    @FXML
    protected void pegaOperacao(ActionEvent event){
        Button b = (Button) event.getSource();
        onOperation(b.getText());
    }
    @FXML
    protected void pegaNumero(ActionEvent event){
        Button b = (Button) event.getSource();
        onNumber(b.getText());
    }

}