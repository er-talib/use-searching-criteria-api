package com.criteria.operation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchingCriteria {
	
	private String key ;
	private Object value ;
	private SearchingOperation operation ;
	private List<Object> values ;

}
