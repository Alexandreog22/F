/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Domain;

/**
 *
 * @author leona
 */


public enum PontuacaoEnum {    
    PRIMEIRO(25,"Primeiro lugar"), SEGUNDO(18,"Segundo lugar"), TERCEIRO(15,"Terceiro lugar"), QUARTO(12,"Quarto lugar"),
    QUINTO(10,"Quinto lugar") , SEXTO(8,"Sexto lugar") , SETIMO (6,"Sétimo lugar") , OITAVO(4,"Oitavo lugar"), NONO(2,"Nono lugar"), DECIMO(1,"Décimo lugar");
     
    private final int valor;
    private final String descricao;
    PontuacaoEnum(int valorOpcao,String descricao){
        valor = valorOpcao;
        this.descricao = descricao;
    }
    public int getValor(){
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
