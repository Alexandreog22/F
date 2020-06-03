/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Dao;

import br.com.F1.Domain.Equipe;
import br.com.F1.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class EquipeDao {
    
    public List<Equipe> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            List<Equipe> equipes = session.createQuery("from Equipe order by nome").list();     //traz a lista de categoria 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return equipes;
         }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public Equipe consultar(Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            Equipe equipe = (Equipe) session.createQuery("from Equipe where id = " + id).uniqueResult();
            session.getTransaction().commit();   
            return equipe;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public boolean inserir(Equipe equipe) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.save(equipe);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }

    public boolean alterar(Equipe equipe) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.update(equipe);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
    
    public boolean excluir(Equipe equipe) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.delete(equipe);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
}
