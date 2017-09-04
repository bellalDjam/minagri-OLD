package dz.minagri.stat.util;

import java.io.Serializable;

import javax.persistence.Transient;


public abstract class Identifiable implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1286389044982906979L;
	// To remember the result of the first hashcode call.
			// because it's based on id. If id was not assigned, then is assigned by hibernate
			// after the first hashcode call.
			// The second hashcode call (based on an assigned different id) would return a 
			// different value (but hashcode should always return the same value, else HashMap and
			// HashSet loose the entity).
			@Transient
			private Integer hashCodeCache = null;
			
			public abstract Long getId();
			
			/** 
			 * Needed to bypass the Hibernate proxies hassle. 
			 * @return the concrete class of the implementing entity 
			 */
			public abstract Class<?> getConcreteClass();

			@Override
			synchronized public int hashCode() {
				if (hashCodeCache == null) { // See comment on the hashCodeCache definition.
					final int prime = 31;
					hashCodeCache = 1;
					hashCodeCache = prime * hashCodeCache + 
					                  ((getId() == null) ? 0 : getId().hashCode());
				}
				return hashCodeCache;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if(! (obj instanceof Identifiable)){
					return false;
				}
				Identifiable identifiable = (Identifiable) obj;
				if (!(getConcreteClass().isAssignableFrom(identifiable.getConcreteClass())))
					return false;
				if (getId() == null) {  // TODO PARKING : Shouln't we say false in any case of one entity has a null id ?  (suppresse the nested if) 
					if (identifiable.getId() != null)
						return false;
				} else if (!getId().equals(identifiable.getId()))
					return false;
				return true;
			}
			
			public String toString() {
				return "Id="+ (getId() == null ? "null" : getId().toString()) + " ";
			}
		}
