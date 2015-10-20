package ch.heigvd.amt.gamification.dao;


import ch.heigvd.amt.gamification.model.AbstractDomainModelEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 * last edition 21.10.2015 by Parfait Noubissi
 * @param <T>
 * @param <PK>
 * But : ici on implémente les méthodes qui sont propres à toutes les entités.
 */

@Local
public interface IGenericDAO<T extends AbstractDomainModelEntity, PK> {

  public PK create(T t);

  public T createAndReturnManagedEntity(T t);

  public void update(T t) throws BusinessDomainEntityNotFoundException ;

  public void delete(T t) throws BusinessDomainEntityNotFoundException ;

  public long count();
  
  public T findById(PK id) throws BusinessDomainEntityNotFoundException ;

  public List<T> findAll();
  
  public List<T> findAllByPage(int pageSize, int pageIndex);

}
