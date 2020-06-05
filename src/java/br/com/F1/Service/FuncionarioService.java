/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Service;

import br.com.F1.Dao.FuncionarioDao;
import br.com.F1.Domain.Funcionario;
import java.util.List;


public class FuncionarioService {
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    
    public List<Funcionario> Listar(){
        return funcionarioDao.listar();
    }
    public Funcionario consultar(Integer id){
       return funcionarioDao.consultar(id);
    }
    public boolean inserir(Funcionario funcionario){
        return funcionarioDao.inserir(funcionario);
    }
    public boolean alterar(Funcionario funcionario){
        return funcionarioDao.alterar(funcionario);
    }
    public boolean excluir(Funcionario funcionario){
        return funcionarioDao.excluir(funcionario);
    }
    
}
