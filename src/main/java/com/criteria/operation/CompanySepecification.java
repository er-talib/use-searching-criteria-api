package com.criteria.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.criteria.entities.Company;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompanySepecification implements Specification<Company> {

	private static final long serialVersionUID = 1L;
	private List<SearchingCriteria> list ;
	
	public CompanySepecification() {
		this.list = new ArrayList<>();
	}
	
	public void add(SearchingCriteria criteria) {
		list.add(criteria);
	}
	
	@Override
	public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		List<Predicate> pridicate = new ArrayList<>();
		
		for(SearchingCriteria criteria : list) {
			if(criteria.getOperation().equals(SearchingOperation.GREATER_THAN)) {
				pridicate.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.LESS_THAN)) {
				pridicate.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.GREATER_THAN_EQUAL)) {
				pridicate.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.LESS_THAN_EQUAL)) {
				pridicate.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.EQUAL)) {
				pridicate.add(builder.equal(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.NOT_EQUAL)) {
				pridicate.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue().toString()));
			}else if(criteria.getOperation().equals(SearchingOperation.IN)) {
				pridicate.add(builder.in(root.get(criteria.getKey())).value(criteria.getValues()));
			}else if(criteria.getOperation().equals(SearchingOperation.NOT_IN)) {
				pridicate.add(builder.not(root.get(criteria.getKey())).in(criteria.getValues()));
			}else if(criteria.getOperation().equals(SearchingOperation.BETWEEN)) {
				pridicate.add(builder.between(root.<Date>get(criteria.getKey()), (Date)criteria.getValues().get(0), (Date)criteria.getValues().get(1)));
			}else if (criteria.getOperation().equals(SearchingOperation.MATCH)) {
				pridicate.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%" ));
			}else if(criteria.getOperation().equals(SearchingOperation.MATCH_END)) {
				pridicate.add(builder.like(builder.lower(root.get(criteria.getKey())),criteria.getValue().toString().toLowerCase() + "%"));
			}else if(criteria.getOperation().equals(SearchingOperation.MATCH_START)) {
				pridicate.add(builder.like(builder.lower(root.get(criteria.getKey())),  "%" +  criteria.getValue()));
			}
		}
		
		return builder.and(pridicate.toArray(new Predicate[0]));
	}

}
