/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.F1.Dao;

import br.com.F1.Domain.Piloto;
import br.com.F1.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;


public class PilotoDao {
    
    public List<Piloto> listar(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try{
            List<Piloto> pilotos = session.createQuery("from Piloto order by nome").list();     //traz a lista de categoria 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return pilotos;
         }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public Piloto consultar(Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            Piloto piloto = (Piloto) session.createQuery("from Piloto where id = " + id).uniqueResult();
            session.getTransaction().commit();   
            return piloto;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return null;
        }
    }
    
    public boolean inserir(Piloto piloto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.save(piloto);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }

    public boolean alterar(Piloto piloto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.update(piloto);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
    
    public boolean excluir(Piloto piloto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();             //abrindo 'negociação' c o banco de dados. 
        try{
            session.delete(piloto);
            session.getTransaction().commit();              //se todas as informações estiverem corretas, salva tudo no banco. 
            return true;
        }catch(Exception e){
            e.printStackTrace();                            //Imprimindo o erro no console. 
            session.getTransaction().rollback();            //se der algo errado, desfaz tudo q foi feito no banco 
            return false;
        }
    }
    
}
