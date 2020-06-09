/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Controller;

import br.com.F1.Domain.Equipe;
import br.com.F1.Domain.Piloto;
import br.com.F1.Service.EquipeService;
import br.com.F1.Service.PilotoService;
import br.com.F1.Util.Mensagens;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="pilotoMB")
@SessionScoped
public class PilotoController implements Serializable{
    
    private List<Piloto> listaPiloto;
    private Piloto piloto;
    private PilotoService pilotoService = new PilotoService();
    private EquipeService equipeService = new EquipeService();
    private List<Equipe> equipes;

    public PilotoController() {
        listar();
        this.piloto = new Piloto();
    }
    
    public void listar(){
        listaPiloto = pilotoService.Listar();//vai ser prenchida pelo service ele pede o dao e o dao buca no banco e manda para o controller.
    }

    public String novo(){
        this.equipes = equipeService.Listar();
        this.piloto = new Piloto();
        return "new.xhtml?faces-redirect=true";
    }
    
    public Boolean comparaPiloto(){
        int cont = 0;
        for(Piloto p: listaPiloto){
            if(piloto.getNome().equals(p.getNome())){
                return true;
            }
            /*if(piloto.getEquipe().getId().equals(p.getEquipe().getId())){
                cont++;
            }*/
        }
        /*if(cont == 2){
            return true;
        }*/
        return false;
    }
    
    public Boolean comparaEquipe(){
        int cont = 0;
        for(Piloto p: listaPiloto){
            if(piloto.getEquipe().getId().equals(p.getEquipe().getId())){
                cont++;
            }
        }
        if(cont == 2){
            return true;
        }
        return false;
    }
    
    public String salva(){
        if(!comparaPiloto()){
            if(!comparaEquipe()){
                if(piloto.getPontuacao() == null)
                    piloto.setPontuacao(0);
                if(pilotoService.inserir(piloto)){
                    Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
                    listar();
                    return "list.xhtml?faces-redirect=true";
                }
                Mensagens.mensagemErro("Erro", "Ocorreu um erro ao salvar o registro.");
                return null ;
            }
            Mensagens.mensagemErro("Equipe com 2 pilotos", "Piloto já cadastrado!");
            return null ;
        }
        Mensagens.mensagemErro("Piloto já cadastrado!", "Piloto já cadastrado!");
        return null ;
    }
    
    public String editar(Piloto piloto){
        this.piloto = piloto;
        this.equipes = equipeService.Listar();
        return "alter.xhtml?faces-redirect=true";
    }
    
    public String excluir( Piloto piloto){
        if(pilotoService.excluir(piloto)){
            Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
            this.piloto = new Piloto();
            listar();
            return voltar();
        }
        Mensagens.mensagemErro("Erro", "Ocorreu um erro ao excluir o registro.");
        return null ;
    }
    
    public String alterar(){
        if(comparaPiloto()){
            Mensagens.mensagemErro("Piloto já existe", "Ocorreu um erro ao excluir o registro.");
            return "list.xhtml?faces-redirect=true";
        }else{
            pilotoService.alterar(piloto);
            listar();
            return voltar();
        }
    }
    
    public String voltar(){
        return "list.xhtml?faces-redirect=true";
    }
    
    public String cancelar(){
        return "list.xhtml?faces-redirect=true";
    }

    public List<Piloto> getListaPiloto() {
        return listaPiloto;
    }

    public void setListaPiloto(List<Piloto> listaPiloto) {
        this.listaPiloto = listaPiloto;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

        
    
}
