package de.rissi.LogBlock.Database;

import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.eclipse.persistence.exceptions.DatabaseException;

public class LogBlock_DBManager
{
	
	Player player;
	Block block;
	Time time;
	private final String LogBlockPU = "hwipu";
	
	/**
	 * the standard constructor for DBManager.
	 */
	public LogBlock_DBManager()
	{
	}
	
	/**
	 * creates an entry in the database.
	 *
	 * @param <T>
	 *            the class of the created object
	 * @param entity
	 *            the object that gets an entry
	 */
	public <T> void createEntity(T entity)
	{
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(LogBlockPU);
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (DatabaseException | RollbackException ex)
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			throw ex;
		} finally
		{
			em.close();
			emf.close();
		}
	}
	
	/**
	 * updates an entry in the database.
	 *
	 * @param <T>
	 *            the class of the created object
	 * @param entity
	 *            the object that gets updated
	 */
	public <T> void updateEntity(T entity)
	{
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(LogBlockPU);
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (RollbackException | DatabaseException ex)
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			throw ex;
		} finally
		{
			em.close();
			emf.close();
		}
	}
	
	/**
	 * searches the rooms in the database for the specified keyword.
	 *
	 * @param keyword
	 *            the keyword which it searches
	 * @return the list of the found rooms
	 */
	public List<?> readPlayer(String keyword)
	{
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(LogBlockPU);
		final EntityManager em = emf.createEntityManager();
		try
		{
			return em.createQuery("select t from " + Player.class.getName() + " t where t. like '%" + keyword + "%'")
					.getResultList();
			
		} finally
		{
			em.close();
			emf.close();
		}
	}
	
	/**
	 * deletes the given entry from the database.
	 *
	 * @param <T>
	 *            the class of the entry
	 * @param entity
	 *            the object of the entry
	 */
	public <T> void deleteEntity(T entity)
	{
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(LogBlockPU);
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();
		try
		{
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		} catch (DatabaseException | RollbackException ex)
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			throw ex;
		} finally
		{
			em.close();
			emf.close();
		}
	}
	
	/**
	 * finds a single entry in the database.
	 *
	 * @param <T>
	 *            the class of the wanted entry
	 * @param clss
	 *            the name of the class
	 * @param id
	 *            the id of the entry
	 * @return the found entry
	 */
	public <T> T findSingleEntity(Class<T> clss, Object id)
	{
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory(LogBlockPU);
		final EntityManager em = emf.createEntityManager();
		try
		{
			return em.find(clss, id);
		} finally
		{
			em.close();
			emf.close();
		}
	}
}
