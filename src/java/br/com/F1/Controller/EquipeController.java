/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Controller;

import br.com.F1.Domain.Equipe;
import br.com.F1.Domain.Piloto;
import br.com.F1.Service.EquipeService;
import br.com.F1.Util.Mensagens;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="equipeMB")
@SessionScoped
public class EquipeController implements Serializable{
    private List<Equipe> listaEquipe;
    private Equipe equipe;
    private EquipeService equipeService = new EquipeService();

    public EquipeController() {
        listar();
    }
    
    public void listar(){
        listaEquipe = equipeService.Listar();//vai ser prenchida pelo service ele pede o dao e o dao buca no banco e manda para o controller.
    }

    public String novo(){
        this.equipe = new Equipe();
        return "new.xhtml?faces-redirect=true";
    }
    
    public Boolean comparaEquipe(){
        for(Equipe e: listaEquipe){
            if(equipe.getNome().equals(e.getNome())){
                return true;
            }
        }
        return false;
    }
    
    public String salva(){
        if(!comparaEquipe()){
            if(equipeService.inserir(equipe)){
                Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
                listar();
                return "list.xhtml?faces-redirect=true";
            }
            Mensagens.mensagemErro("Erro", "Ocorreu um erro ao salvar o registro.");
            return null ;
            
        }
        Mensagens.mensagemErro("Equipe já cadastrada", "Ocorreu um erro ao salvar o registro.");
        return null ;  
        
    }
    
    public String editar(Equipe equipe){
        this.equipe = equipe;
        return "alter.xhtml?faces-redirect=true";
    }
    
    public String excluir(Equipe equipe){
        if(equipeService.excluir(equipe)){
            Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
            this.equipe = new Equipe();
            listar();
            return voltar();
        }
        Mensagens.mensagemErro("Erro", "Ocorreu um erro ao excluir o registro.");
        return null ;
    }
    
    public String alterar(){
        equipeService.alterar(equipe);
        listar();
        return voltar();
    }
    
    public String voltar(){
        return "list.xhtml?faces-redirect=true";
    }
    
    public String cancelar(){
        return "list.xhtml?faces-redirect=true";
    }
    
    
    //get set
    
    public List<Equipe> getListaEquipe() {
        return listaEquipe;
    }

    public void setListaEquipe(List<Equipe> listaEquipe) {
        this.listaEquipe = listaEquipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public EquipeService getEquipeService() {
        return equipeService;
    }

    public void setEquipeService(EquipeService equipeService) {
        this.equipeService = equipeService;
    }
    
    
    
    
}
