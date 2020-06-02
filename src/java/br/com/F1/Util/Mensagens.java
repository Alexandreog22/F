/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author leona
 */
public class Mensagens {
    
    
    public static void mensagemSucesso(String titulo,String mensagem){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,mensagem)); 
        context.getExternalContext().getFlash().setKeepMessages(true); //manter mensagem após a navegação
    }

    public static void mensagemErro(String titulo, String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,mensagem)); 
        context.getExternalContext().getFlash().setKeepMessages(true); //manter mensagem após a navegação   
    }
}
