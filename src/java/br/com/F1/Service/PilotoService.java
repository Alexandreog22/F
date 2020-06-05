/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Service;

import br.com.F1.Dao.PilotoDao;
import br.com.F1.Domain.Gp;
import br.com.F1.Domain.Piloto;
import java.util.List;

public class PilotoService {
    
    PilotoDao pilotoDao = new PilotoDao();
    
    public List<Piloto> Listar(){
        return pilotoDao.listar();
    }
    public Piloto consultar(Integer id){
       return pilotoDao.consultar(id);
    }
    public boolean inserir(Piloto piloto){
        return pilotoDao.inserir(piloto);
    }
    public boolean alterar(Piloto piloto){
        return pilotoDao.alterar(piloto);
    }
    public boolean excluir(Piloto piloto){
        return pilotoDao.excluir(piloto);
    }
    
     public void lancaGp(List<Gp> lista){
        Integer pt = 0;
        Piloto piloto = null;
        for (Gp gp : lista) {
            piloto = pilotoDao.consultar(gp.getPiloto().getId());
            if(piloto != null)
                gp.getPiloto().setPontuacao(gp.getPontuacao() + piloto.getPontuacao());
            
            pilotoDao.alterar(gp.getPiloto());
        }
    }
}
