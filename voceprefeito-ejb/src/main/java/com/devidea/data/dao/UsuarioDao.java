package com.devidea.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.devidea.data.repository.UsuarioRepository;
import com.devidea.model.Usuario;


@Stateless
public class UsuarioDao extends GenericDao<Usuario> implements UsuarioRepository, Serializable{

	private static final long serialVersionUID = -1708553070868536919L;

	public Usuario find(String email, String password) {
		Criteria criteria = this.session().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("senha", password));
		criteria.add(Restrictions.eq("ativo", true));
		
		return (Usuario) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAllFast(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id AS id, e.nome AS nome, e.sobrenome AS sobrenome, e.email AS email, e.permissao AS permissao ")
		.append("FROM " + getEntity().getName() + " e ")
		.append("WHERE e.ativo = true ")
		.append("ORDER BY e.id ");
		
		Query q = this.session().createQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		List<Usuario> result = (List<Usuario>) q.list();
		return result;
	}

	public Usuario findByEmail(String email) {
		Criteria criteria = this.session().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("ativo", true));
		
		return (Usuario) criteria.uniqueResult();
	}

	public void preCadastro(String classe, String sequence, String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.id AS id, e.nome AS nome ")
		.append("FROM " + classe + " e ")
		.append("WHERE e.ativo = true and e.nome = '" + nome + "'")
		.append("ORDER BY e.nome ");
		
		Query q = this.session().createSQLQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		int count = q.list().size();
		
		if (count == 0) {
			javax.persistence.Query query = getEntityManager().createNativeQuery("INSERT INTO " + classe + " (id, nome, ativo) VALUES (nextval('" + sequence + "'), ?, ?);");
			query.setParameter(1, nome);
			query.setParameter(2, true);
			query.executeUpdate();
		}
	}
	
	public List<Usuario> consultarUsuario(Usuario usuario){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.id AS id, u.ativo AS ativo , u.nome AS nome, u.permissao AS permissao FROM Usuario u WHERE cpf = '"+ usuario.getCpf()+"'");
		
		Query q = this.session().createQuery(sql.toString());
		q.setResultTransformer(Transformers.aliasToBean(getEntity()));
		
		List<Usuario> result = (List<Usuario>) q.list();
		
		ArrayList<Usuario> resultadoFinal = new ArrayList<Usuario>();
		
		for(Usuario user : result){
			if(user.getAtivo() != null &&
					user.getAtivo() == true) {
				resultadoFinal.add(user);
			}
        }
		
		return resultadoFinal;
	}

}
