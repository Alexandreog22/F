/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Controller;

import br.com.F1.Domain.Gp;
import br.com.F1.Domain.Piloto;
import br.com.F1.Domain.PontuacaoEnum;
import br.com.F1.Service.PilotoService;
import br.com.F1.Util.Mensagens;
import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="pontuacaoMB")
@SessionScoped
public class PontuacaoControler implements Serializable{
    private Integer qtd;
    private Piloto piloto;
    private List<Piloto> listaPiloto;
    private PilotoService pilotoService = new PilotoService();
    private List<Gp> listaGp = new ArrayList<Gp>();
    private Boolean classificacao = Boolean.FALSE;
    private String result;
    private PontuacaoEnum pontuacaoEnum;


    public PontuacaoControler() {
        this.piloto = new Piloto();
        this.listaPiloto = pilotoService.Listar();
        this.classificacao();
    }
    
    public Boolean comparaPilotoPont(){
        for(Gp gp: listaGp){
            if(piloto.getNome().equals(gp.getPiloto().getNome()))
                return true;  
        }
        return false;
    }
    
    public Boolean verificaSeLacouMesmaColocacao(){
        int cont = 0;
        for(Gp gp: listaGp){
            if(gp.getPontuacao().equals(this.qtd)){
                cont++; 
            }
            if(cont == 1)
                return true;
        }
        return false;
    }
    
    public String lancar(){
        if(!comparaPilotoPont() && !verificaSeLacouMesmaColocacao()){
            this.listaPiloto = pilotoService.Listar();
            this.listaGp.add(new Gp(piloto, qtd));
            this.piloto = new Piloto();
            this.qtd = 0;
            return "lancamento.xhtml?faces-redirect=true";
        }
        if(verificaSeLacouMesmaColocacao())
            Mensagens.mensagemErro("Colocação já cadastrado nesse GP!", "Já foi lançado um piloto com está colocação");
        else
            Mensagens.mensagemErro("Piloto já cadastrado nesse GP!", "Piloto já cadastrado nesse GP!");
        
        return "lancamento.xhtml?faces-redirect=true";
    }
    
    public String fecharGp(){
        if(listaGp.size() == this.listaPiloto.size()){
            Mensagens.mensagemSucesso("Sucesso", "Registro salvo com sucesso");
            pilotoService.lancaGp(listaGp);
            classificacao = Boolean.TRUE;
            this.listaGp.removeAll(listaGp);
            this.classificacao();
        }else
            Mensagens.mensagemErro("É obrigatório lançar a colocação de todos os pilotos", "É obrigatório lançar a colocação de todos os pilotos");
        
        return "lancamento.xhtml?faces-redirect=true";
    }
    
    public void classificacao(){
        StringBuilder stringBuilder = new StringBuilder();

        //pega lista de pilotos com a pontuação atual
        this.listaPiloto = pilotoService.Listar();
        
        this.listaPiloto.sort(Comparator.comparing(Piloto::getPontuacao).reversed());
        
        stringBuilder.append("<table id=\"customers\" border=\"1\"><thead><tr><th>Posição</th>");
        int cont = 1;
        for (Piloto piloto1 : listaPiloto) {
            stringBuilder.append("<th>").append(cont).append("</th>");
            cont++;
        }
        cont=1;
        stringBuilder.append("</tr></thead><tbody><tr><th>Pontuação</th>");
         for (Piloto piloto1 : listaPiloto) {
            stringBuilder.append("<td>").append(piloto1.getPontuacao()).append("|").append(piloto1.getNome()).append("</td>");
            cont++;
        }
        stringBuilder.append("</tr></tbody></table>");
        result = stringBuilder.toString();
    }
    
    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public List<Piloto> getListaPiloto() {
        return listaPiloto;
    }

    public void setListaPiloto(List<Piloto> listaPiloto) {
        this.listaPiloto = listaPiloto;
    }

    public List<Gp> getListaGp() {
        return listaGp;
    }

    public void setListaGp(List<Gp> listaGp) {
        this.listaGp = listaGp;
    }

    public Boolean getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Boolean classificacao) {
        this.classificacao = classificacao;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public PontuacaoEnum getPontuacaoEnum() {
        return pontuacaoEnum;
    }

    public void setPontuacaoEnum(PontuacaoEnum pontuacaoEnum) {
        this.pontuacaoEnum = pontuacaoEnum;
    }
   
    public PontuacaoEnum[] getPontuacao(){
        return pontuacaoEnum.values();
    }
}
