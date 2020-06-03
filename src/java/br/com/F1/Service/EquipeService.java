/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Service;

import br.com.F1.Dao.EquipeDao;
import br.com.F1.Domain.Equipe;
import java.util.List;

public class EquipeService {
    
    EquipeDao equipeDao = new EquipeDao();
    
    public List<Equipe> Listar(){
        return equipeDao.listar();
    }
    public Equipe consultar(Integer id){
       return equipeDao.consultar(id);
    }
    public boolean inserir(Equipe equipe){
        return equipeDao.inserir(equipe);
    }
    public boolean alterar(Equipe equipe){
        return equipeDao.alterar(equipe);
    }
    public boolean excluir(Equipe equipe){
        return equipeDao.excluir(equipe);
    }
    
}
