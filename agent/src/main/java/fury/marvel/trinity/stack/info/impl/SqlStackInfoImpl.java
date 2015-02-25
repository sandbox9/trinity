package fury.marvel.trinity.stack.info.impl;


import fury.marvel.trinity.stack.info.ResultSetStackInfo;
import fury.marvel.trinity.stack.info.SqlStackInfo;
import fury.marvel.trinity.stack.info.marshall.JacksonMarshaller;
import fury.marvel.trinity.stack.info.marshall.MarshallerFactory;
import fury.marvel.trinity.stack.info.marshall.StringObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poets11 on 15. 2. 4..
 */
public class SqlStackInfoImpl extends AbstractStackInfo implements SqlStackInfo {
    private String sql;
    private List<StringObject> params;
    private StringObject result;
    private ResultSetStackInfoImpl resultSet;

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public List<StringObject> getParams() {
        return params;
    }

    @Override
    public StringObject getResult() {
        return result;
    }

    @Override
    public ResultSetStackInfo getResultSetStackInfo() {
        return resultSet;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void addParam(Object object) {
        if (params == null) params = new ArrayList<StringObject>();

        params.add(MarshallerFactory.getMarshaller().marshall(object));
    }

    public void setResult(Object result) {
        if (result != null && result instanceof java.sql.ResultSet) {
            this.result = MarshallerFactory.getMarshaller().marshall(result, true);
            initResultSet((java.sql.ResultSet) result);
        } else {
            this.result = MarshallerFactory.getMarshaller().marshall(result);
        }
    }

    public void initResultSet(java.sql.ResultSet rs) {
        resultSet = new ResultSetStackInfoImpl();
        resultSet.initColumnNames(rs);
    }

    public void setResultSet(ResultSetStackInfoImpl resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public String toString() {
        return "SqlStackInfoImpl{" +
                "sql='" + sql + '\'' +
                ", params=" + params +
                ", result=" + result +
                ", resultSet=" + resultSet +
                "} " + super.toString();
    }
}
