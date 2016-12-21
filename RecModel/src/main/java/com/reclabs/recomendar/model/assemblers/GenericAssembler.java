/**
 * Project: RecModel
 * Created by: raulanatol at 13/02/2013 15:56:37
 * Licensed to Recomendar Labs SL
 * Recomendar Labs SL © 2012
 *
 * You may obtain a copy of the license at
 * http://recomendarlabs.com/licenses/License
 */
package com.reclabs.recomendar.model.assemblers;

import com.reclabs.recomendar.common.helpers.types.GenericHelper;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Assembler genérico.
 * @param <T> Fuente principal del assembler.
 * @param <S> Conversión principal del assembler.
 * @author raulanatol
 */
@SuppressWarnings("FieldCanBeLocal")
public abstract class GenericAssembler<T, S> {
    private final int T = 0;
    private final int S = 1;
    /**
     * Campos ignorados por defecto del assembler.
     * Deberán ser modificados por cada una de las implementaciones.
     */
    @SuppressWarnings({"WeakerAccess", "CanBeFinal"})
    protected String[] ignoreProperties = {};
    /**
     * Listado de las clases creadas.
     */
    private final Object[] clazz = {null, null};

    /**
     * Obtenemos (mediante Java Reflection) la clase concreta
     * parametrizada T.
     * @param generic
     * @return La clase T
     */
    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public <V> Class<V> getGenericClass(int generic) {
        if (clazz[generic] == null) {
            clazz[generic] = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[generic];
        }
        return (Class<V>) clazz[generic];
    }

    /**
     * @param source
     * @return La copia de <S> sobre <T>
     */
    @SuppressWarnings({"WeakerAccess", "unchecked"})
    public T toRight(S source) {
        T result = (T) GenericHelper.getInstance(getGenericClass(T));
        BeanUtils.copyProperties(source, result, ignoreProperties);
        return internalToRight(source, result);
    }

    /**
     * @param sources
     * @return Copias de listado <S> sobre listado <T>
     */
    public List<T> toRight(List<S> sources) {
        List<T> result = null;
        if (sources != null) {
            result = new ArrayList<>();
            for (S source : sources) {
                result.add(toRight(source));
            }
        }
        return result;
    }

    /**
     * @param source
     * @param result
     * @param ignoreFields
     * @return La copia de <T> sobre <S>
     */
    public S toLeft(T source, S result, List<String> ignoreFields) {
        List<String> allIgnore = new ArrayList<>();
        allIgnore.addAll(ignoreFields);
        allIgnore.addAll(Arrays.asList(ignoreProperties));
        return toLeft(source, result, allIgnore.toArray(new String[allIgnore.size()]));
    }

    /**
     * @param source
     * @return La copia de <T> sobre <S>
     */
    @SuppressWarnings("unchecked")
    public S toLeft(T source) {
        S result = (S) GenericHelper.getInstance(getGenericClass(S));
        return toLeft(source, result, ignoreProperties);
    }

    /**
     * @param source
     * @param result
     * @param ignore
     * @return
     */
    private S toLeft(T source, S result, String[] ignore) {
        BeanUtils.copyProperties(source, result, ignore);
        return internalToLeft(source, result);
    }

    /**
     * Resto de copias que no pueda hacer el copyProperties.
     * @param source
     * @param result
     * @return El resultado de S sobre T
     */
    public abstract T internalToRight(S source, T result);

    /**
     * Resto de copias que no pueda hacer el copyProperties.
     * @param source
     * @param result
     * @return El resultado de T sobre S
     */
    public abstract S internalToLeft(T source, S result);
}
