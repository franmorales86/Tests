/**
 * Project: RecCommon
 * Created by: fjmorales at 04/12/2012 20:33:58
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 * 
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.common.types;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Representa a un hash de elementos ubicados en un set único. La estructura de ordenación es la
 * siguiente: [key1] [Key1.2][key1.3][key1.4] [key2] [Key2.1][key2.2][key2.3]
 * 
 * Siendo siempre único los Key principales (key1 y key2) y únicos dentro de un determinado Key los
 * secundarios (key1.2, key1.3, key1.4) pero NO se garantiza que Key1.2 no pueda ser igual que
 * Key2.1
 * @author raulanatol
 * @param <KEY1>
 * @param <KEY2>
 */
public class SingleHashSet<KEY1, KEY2> {
	private final Map<KEY1, HashSet<KEY2>> values;

	/**
	 * 
	 */
	public SingleHashSet() {
		super();
		this.values = new HashMap<>();
	}

	/**
	 * Añadimos un nuevo elemento
	 * @param key1
	 * @param key2
	 */
	public void addItem(final KEY1 key1, final KEY2 key2) {
		HashSet<KEY2> set = this.values.get(key1);
		if (set == null) {
			set = new HashSet<>();
			this.values.put(key1, set);
		}
		set.add(key2);
	}

	/**
	 * Obtenemos el listado de KEY1 de los elementos del mapa
	 * @return Listado de KEY1
	 */
	public Set<KEY1> getIteratorKey1() {
		return this.values.keySet();
	}

	/**
	 * Dado un KEY1 obtenemos todos los valores que contiene el mismo para ese KEY1
	 * @param key1
	 * @return Conjunto de valores
	 */
	public HashSet<KEY2> getKey2ListByKey1(final KEY1 key1) {
		return this.values.get(key1);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SingleHashSet<?, ?> other = (SingleHashSet<?, ?>) obj;
		if (this.values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!this.values.equals(other.values)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SingleHashSet [values=" + this.values + "]";
	}
}
