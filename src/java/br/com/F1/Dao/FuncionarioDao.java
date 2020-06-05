/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Dao;

import br.com.F1.Domain.Funcionario;
import br.com.F1.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


public class FuncionarioDao {
    
    public List<Funcionario> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            List<Funcionario> funcionarios = session.createQuery("from Funcionario order by nome").list();     //traz a lista de categoria 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return funcionarios;
         }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public Funcionario consultar(Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            Funcionario funcionario = (Funcionario) session.createQuery("from Funcionario where id = " + id).uniqueResult();
            session.getTransaction().commit();   
            return funcionario;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public boolean inserir(Funcionario funcionario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.save(funcionario);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }

    public boolean alterar(Funcionario funcionario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.update(funcionario);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
    
    public boolean excluir(Funcionario funcionario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.delete(funcionario);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
}
