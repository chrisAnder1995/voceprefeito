package com.devidea.data.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import com.devidea.data.repository.GenericRepository;
import com.devidea.model.GenericEntity;

import lombok.Getter;
import lombok.Setter;

@Stateless
public class GenericDao<T> implements GenericRepository<T>{

	private static final long serialVersionUID = -2506443851497497148L;
	
	@Setter
	private Class<T> entity;
	protected int listSize = 5;

	protected Session session() {
		return (Session) getEntityManager().getDelegate(); 
	}
	
	@PersistenceContext(unitName=GenericRepository.PERSISTENCE_UNIT)
	@Getter
	private EntityManager entityManager;
	
	@Override
	public T create(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}
	
	@Override
	public T findById(Integer id) {
		T entity = getEntityManager().find(this.getEntity(), id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
		EntityManager em = this.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery();
		query.select(query.from(getEntity()));
		query.where(
				cb.equal(query.getRoots().iterator().next().get("ativo"), true)
		);
		query.orderBy(cb.asc(query.getRoots().iterator().next().get("id")));

		List<T> result = (List<T>) em.createQuery(query).getResultList();;
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Integer page){
		EntityManager em = this.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery();
		query.select(query.from(getEntity()));
		query.where(
				cb.equal(query.getRoots().iterator().next().get("ativo"), true)
		);
		
		query.orderBy(cb.asc(query.getRoots().iterator().next().get("id")));
		
		TypedQuery<T> typedQuery = em.createQuery(query);
		if (page != null) {
			typedQuery.setMaxResults(listSize);
			typedQuery.setFirstResult(page * listSize);
		}

		List<T> result = (List<T>) typedQuery.getResultList();;
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllFast(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id AS id, e.nome AS nome ")
		.append("FROM " + getEntity().getName() + " e ")
		.append("WHERE e.ativo = true ")
		.append("ORDER BY e.id ");
		
		Query q = this.session().createQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		List<T> result = (List<T>) q.list();
		return result;
	}
	
	@Override
	public T update(T entity) {
		entity = getEntityManager().merge(entity);
		getEntityManager().flush();
		return entity;
	}
	
	public void delete(T entity) {
		getEntityManager().remove(entity);
		getEntityManager().flush();
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntity() {
		if (this.entity == null){
            this.entity = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
		return entity;
	}
	
	protected Object findFieldEntity(Class<? super T> entity, T t, String fieldName) {
        if (entity != Object.class) {
            Field[] campos = entity.getDeclaredFields();
            for (Field field : campos) {
                if (field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    try {
                        return field.get(t);
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                }
            }
        }

        if (entity.getSuperclass() != Object.class)
            return findFieldEntity(entity.getSuperclass(), t, fieldName);
        return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByQuery(T entidade, String queryStr){
		List<Object[]> result = new ArrayList<Object[]>();
		if(queryStr!=null && !queryStr.equals("")){
			StringBuilder queryString = new StringBuilder(queryStr);
	        if (queryString != null && !queryString.toString().trim().isEmpty()){
	            
	            Session session = (Session) getEntityManager().getDelegate();
	        	org.hibernate.Query query = session.createQuery(queryStr);//getEntityManager().createQuery(queryString.toString());
	            Pattern pattern = Pattern.compile(":\\w+");
	            Matcher matcher = pattern.matcher(queryString);
	            while (matcher.find()) {
	                String attribute = matcher.group();
	                query.setParameter(attribute.substring(1), this.findFieldEntity(getEntity(), entidade, attribute.substring(1)));
	            }
	            
	            //query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	            //query.setResultTransformer(Transformers.aliasToBean(this.entidade));
	            result = (ArrayList<Object[]>)query.list();
	            session.clear();
	
	        }
		}
		return result;
	}
	
	// � importante usar o FROM, GROUP BY e ORDER BY no sql em caixa alta - UPPERCASE
	@Override
	public Long getSqlSize(String sql) {
		String[] sqlSplit = sql.split(" FROM ");
		sqlSplit = sqlSplit[1].split(" GROUP BY ");
		
		sqlSplit = sqlSplit[0].split(" ORDER BY ");
		
		String sqlCount = "select count(1) from " + sqlSplit[0];
		
		Query q = this.session().createQuery(sqlCount.toString());
		
		return (Long) q.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T entity, List<Criterion> criterions, Integer... page) {
		try{
			this.getEntity();
			
			Example eg = Example.create(entity).excludeZeroes().ignoreCase().enableLike(MatchMode.ANYWHERE).excludeProperty("serialVersionUID");
	        Session session = (Session) getEntityManager().getDelegate();
	        Criteria criteria = session.createCriteria(this.entity);
	        criteria.add(eg);
	        
	        int size = ((Long) criteria.setProjection(Projections.rowCount()).setResultTransformer(Criteria.ROOT_ENTITY).uniqueResult()).intValue();
	        
	        this.setNullString(entity.getClass(), entity);
	        this.setCriteriaManytoOne(entity.getClass(), entity, criteria, "", "");
	        this.inputCriteriaId(entity.getClass(), entity, criteria);
	        
	        criteria.setProjection(null);
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        
	        if (criterions != null) {
		        for (Criterion criterion : criterions) {
					criteria.add(criterion);
				}
	        } else {
	        	if (((GenericEntity) entity).getListOrder() != null) {
	        		if (((GenericEntity) entity).getOrderAsc() == null || ((GenericEntity) entity).getOrderAsc() == true) {
	        			criteria.addOrder(Order.asc(((GenericEntity) entity).getListOrder()));	
	        		} else {
	        			criteria.addOrder(Order.desc(((GenericEntity) entity).getListOrder()));	
	        		}
	        		
	        	} else {
	        		if (((GenericEntity) entity).getOrderAsc() == null || ((GenericEntity) entity).getOrderAsc() == true) {
	        			criteria.addOrder(Order.asc("id"));	
	        		} else {
	        			criteria.addOrder(Order.desc("id"));	
	        		}
	        		
	        	}
	        }
	        
			if (page != null && page.length > 1 && page[0] != null) {
				Integer pg = page[0];
				if (page[1] != null) {
					criteria.setMaxResults(page[1]);
					criteria.setFirstResult(pg * page[1]);
				} else {
					criteria.setMaxResults(listSize);
					criteria.setFirstResult(pg * listSize);
				}				
			}
	        
			List<T> list = criteria.list();
			
			if (page.length == 3) {
				for (T obj : list) {
					((GenericEntity) obj).setListSize(page[2]);
				}
			}
			if (page.length < 3 && page != null && page.length > 1 && page[0] != null) {
				//int sizeTemp = findByExample(entity, criterions).size();
				//int size = (Integer) criteria.setProjection(Projections.rowCount()).setResultTransformer(Criteria.ROOT_ENTITY).uniqueResult();
				
				for (T obj : list) {
					((GenericEntity) obj).setListSize(size);
				}
			}
			
			return list;
	    }catch (Throwable t) {
	    	t.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public List<T> findByExample(T entity, Integer... page) {
		return findByExample(entity, null, page);
	}
	
	public void setCriteriaManytoOne(Class<?> clazz, Object entidade, Criteria criteria, String property, String nameClassAlias) throws Exception {
		try {
			if(clazz.getSuperclass()!=null){
				setCriteriaManytoOne(clazz.getSuperclass(), entidade, criteria, property, nameClassAlias);
			}
			for (Field field : clazz.getDeclaredFields()) {
				if ((field.getAnnotation(ManyToOne.class)) != null || field.getAnnotation(OneToOne.class)!=null) {
					field.setAccessible(true);
					if (field.get(entidade) != null) {
						String nameProperty = nameClassAlias.concat("".concat(entidade.getClass().getSimpleName().concat("_".concat(field.getName().concat("_1")))));
				        this.setNullString(entidade.getClass(), entidade);
				        Example eg = Example.create(field.get(entidade)).excludeZeroes().ignoreCase().enableLike(MatchMode.ANYWHERE).excludeProperty("serialVersionUID");
						criteria.createCriteria(property.concat(field.getName()), nameProperty, JoinType.LEFT_OUTER_JOIN).add(eg);
						//criteria.createCriteria(property.concat(field.getName())).add(eg);
						nameClassAlias += entidade.getClass().getSimpleName(); 
						
						//criteria.add(Restrictions.eq(field.getName(), field.get(entidade)));
						//Contador -- não permite que passe pelo mesmo objeto mais de uma vez quando houver mais de um atributo ManyToOne dentro do ObjetoPrincipal
						int cont = 1;
						for (Field fld : field.get(entidade).getClass().getDeclaredFields()) {
							fld.setAccessible(true);
							if (fld.get(field.get(entidade)) != null) {
								if(fld.getAnnotation(ManyToOne.class)!=null || fld.getAnnotation(OneToOne.class)!=null){
									if(cont==1){
										setCriteriaManytoOne(field.get(entidade).getClass(), field.get(entidade), criteria, nameProperty.concat("."), nameClassAlias);
									}
									cont++;
								}else if(fld.getAnnotation(ManyToMany.class)==null){
									if(!fld.getName().equals("serialVersionUID") && fld.getAnnotation(Transient.class)==null){
										if(fld.getGenericType().toString().contains("java.lang.String")){
											if(!fld.get(field.get(entidade)).equals("")){
												//criteria.add(Restrictions.ilike(nameProperty.concat("."+fld.getName()), "%"+fld.get(field.get(entidade))+"%"));
											}
										}else{
											if(fld.getAnnotation(Id.class)!=null && !String.valueOf(fld.get(field.get(entidade))).equals("0"))
												criteria.add(Restrictions.eq(nameProperty.concat("."+fld.getName()), fld.get(field.get(entidade))));
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException ia) {
            throw new Exception("Erro ao varrer annotation", ia);
		}
	}
	
	public void setNullString(Class<?> clazz, Object entidade) throws Exception {
		try {
			for (Field field : clazz.getDeclaredFields()) {
				if ((field.getAnnotation(ManyToOne.class)) == null || (field.getAnnotation(OneToMany.class)) == null) {
					field.setAccessible(true);
					if(!field.getName().equals("serialVersionUID") && field.getAnnotation(Transient.class)==null){
						if(field.getGenericType().toString().contains("java.lang.String")){
							if(field.get(entidade) != null && field.get(entidade).equals("")){
								field.set(entidade, null);
							}
						}
						
					}
				}
			}
		} catch (IllegalArgumentException ia) {
            throw new Exception("Erro ao varrer annotation", ia);
		}
	}
	
	private void inputCriteriaId(Class<?> clazz, Object entidade, Criteria criteria) throws Exception {
		for (Field field : clazz.getDeclaredFields()) {
			if ((field.getAnnotation(Id.class)) != null ) {
				field.setAccessible(true);
				if (field.get(entidade) != null) {
					if(!field.get(entidade).equals(0L) && !String.valueOf(field.get(entidade)).equals("0") && !field.get(entidade).equals("")){
						criteria.add(Restrictions.idEq(field.get(entidade)));
					}
				}
			}
		}
		
	}
}
