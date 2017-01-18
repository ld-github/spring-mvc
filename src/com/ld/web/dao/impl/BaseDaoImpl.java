package com.ld.web.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.ld.web.been.Page;
import com.ld.web.dao.BaseDao;

/**
 * 
 * <p>Title: BaseDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 * 
 * @param <T>
 *
 * @date 2015-01-08
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private static Logger logger = Logger.getLogger(BaseDaoImpl.class);

    @Resource
    private SessionFactory sf;

    protected Session getCurrentSession() {
        return sf.getCurrentSession();
    }

    private Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String getClassName() {
        return this.getClazz().getSimpleName();
    }

    @Override
    public void save(T t) {
        this.getCurrentSession().save(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public List<T> getList(String where, Map<String, Object> params, LinkedHashMap<String, String> orders) {
        where = where == null ? "" : where;
        String hql = "FROM " + this.getClassName() + " o " + where + this.getOrder(orders);

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.list();
    }

    @Override
    public List<T> getList() {
        return getList(null, null, null);
    }

    @Override
    public T getUniqueResult(String where, Map<String, Object> params) {
        where = where == null ? "" : where;
        String hql = "FROM " + this.getClassName() + " o " + where;

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        try {
            return (T) q.uniqueResult();
        } catch (Exception e) {
            logger.info(String.format("Get unique result error: %s", e.getMessage()), e);
            return null;
        }
    }

    @Override
    public T getUniqueResult(final Long primaryKey) {
        String hql = "FROM " + this.getClassName() + " o WHERE o.id=:id";

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, new HashMap<String, Object>() {
            private static final long serialVersionUID = 8568462066745554547L;
            {
                put("id", primaryKey);
            }
        });
        return (T) q.uniqueResult();
    }

    @Override
    public void update(T t) {
        this.getCurrentSession().update(t);
    }

    @Override
    public int update(String where, Map<String, Object> params) {
        String hql = "UPDATE " + this.getClassName() + " o " + where;
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.executeUpdate();
    }

    @Override
    public void delete(final Long primaryKey) {
        String hql = "DELETE " + this.getClassName() + " o WHERE o.id=:id";

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, new HashMap<String, Object>() {
            private static final long serialVersionUID = -4864915463081917504L;
            {
                put("id", primaryKey);
            }
        });
        q.executeUpdate();
    }

    @Override
    public void delete(T t) {
        this.getCurrentSession().delete(t);
    }

    @Override
    public Page<T> getPage(String where, Map<String, Object> params, LinkedHashMap<String, String> orders, Page<T> page) {
        where = where == null ? "" : where;
        String hql = "FROM " + this.getClassName() + " o " + where + this.getOrder(orders);

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        if (null != page) {
            q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
            q.setMaxResults(page.getPageSize());
        }
        List<T> records = q.list();
        Long total = this.getTotal(where, params);
        page = null == page ? new Page<T>() : page;
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public Long getTotal(String where, Map<String, Object> params) {
        where = where == null ? "" : where;
        String hql = "SELECT COUNT(o) FROM " + this.getClassName() + " o " + where;

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return (Long) q.uniqueResult();
    }

    @Override
    public Long getTotal() {
        return getTotal(null, null);
    }

    /**
     * Convert LinkedHashMap to string like 'order by xx asc, bb desc '
     * 
     * @param orders
     * @return
     */
    private String getOrder(LinkedHashMap<String, String> orders) {
        if (null == orders || orders.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer("ORDER BY");
        for (String key : orders.keySet()) {
            sb.append(" ").append(key).append(" ").append(orders.get(key)).append(",");
        }
        return sb.delete(sb.toString().lastIndexOf(","), sb.toString().length()).toString();
    }

    /**
     * Query setParameter
     * 
     * @param q
     * @param params
     */
    private void setParams(Query q, Map<String, Object> params) {
        if (null == params || params.isEmpty()) {
            return;
        }
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
    }

    @Override
    public Page<T> getPageBySql(String sql, String totalSql, Map<String, Object> params, LinkedHashMap<String, String> orders, Page<T> page) {
        sql = sql + this.getOrder(orders);

        Query q = this.getCurrentSession().createSQLQuery(sql);
        q.setResultTransformer(Transformers.aliasToBean(getClazz()));
        this.setParams(q, params);
        if (null != page) {
            q.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
            q.setMaxResults(page.getPageSize());
        }
        page = null == page ? new Page<T>() : page;
        List<T> records = q.list();
        Long total = this.getTotalBySql(totalSql, params);
        page.setTotal(total);
        page.setRecords(records);
        return page;
    }

    @Override
    public Long getTotalBySql(String sql, Map<String, Object> params) {
        if (null == sql) {
            return null;
        }
        Query q = this.getCurrentSession().createSQLQuery(sql);
        setParams(q, params);
        return Long.parseLong(q.uniqueResult().toString());
    }

    @Override
    public List<T> getListBySql(String sql, Map<String, Object> params, LinkedHashMap<String, String> orders) {
        sql = sql + this.getOrder(orders);

        Query q = this.getCurrentSession().createSQLQuery(sql);
        q.setResultTransformer(Transformers.aliasToBean(getClazz()));
        setParams(q, params);
        return q.list();
    }

    @Override
    public Object getUniqueResultBySql(String sql, Map<String, Object> params) throws Exception {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        setParams(q, params);
        return q.uniqueResult();
    }

    @Override
    public Object getUniqueResultByHql(String hql, Map<String, Object> params) throws Exception {
        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        return q.uniqueResult();
    }

    @Override
    public T getUniqueResultByOrder(String where, Map<String, Object> params, LinkedHashMap<String, String> orders) {
        where = where == null ? "" : where;
        String hql = "FROM " + this.getClassName() + " o " + where + getOrder(orders);

        Query q = this.getCurrentSession().createQuery(hql);
        setParams(q, params);
        q.setFirstResult(0);
        q.setMaxResults(1);
        return (T) q.uniqueResult();
    }

    @Override
    public List<T> getListByHql(String hql, Map<String, Object> params, LinkedHashMap<String, String> orders) {
        hql = hql + this.getOrder(orders);

        Query q = this.getCurrentSession().createQuery(hql);
        q.setResultTransformer(Transformers.aliasToBean(getClazz()));
        setParams(q, params);
        return q.list();
    }

    @Override
    public int executeUpdateSql(String sql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createSQLQuery(sql);
        setParams(q, params);
        return q.executeUpdate();
    }

}
