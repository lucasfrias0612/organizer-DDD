package ar.com.unpaz.organizerddd.application.services;

import java.util.List;

import ar.com.unpaz.organizerddd.domain.repositorycontracts.IRepository;
import ar.com.unpaz.organizerddd.domain.services.IDomainServices;
import ar.com.unpaz.organizerddd.locator.Context;


public abstract class AppServiceImp<E> implements AppServices<E>{
	
	protected IRepository<E> repository;
	private IDomainServices<E> domainservices;
		
	public AppServiceImp(IRepository<E> repository, IDomainServices<E> domainservices){
		this.repository=repository;
		this.domainservices=domainservices;
	}

	@Override
	public void delEntity(E entity) {
		// TODO Auto-generated method stub
		repository.delete(entity);
	}

	@Override
	public boolean addEntity(E pass) {
		// TODO Auto-generated method stub
		if(existEntity(getList(),pass)) {
		Context.APPERRORS="el registro ya existe en la base de datos";
		return false;
		}
		else if(domainservices.validate(pass)){
		Context.APPERRORS="campos invalidos";
		return false;
		}
		else
		repository.insert(pass);
		return true;	

	}

	@Override
	public boolean existEntity(List<E> list,E entity) {
		// TODO Auto-generated method stub
		for(E e : list) {
			if(e.equals(entity))
				return true;
		}
		return false;
	}

}



	


