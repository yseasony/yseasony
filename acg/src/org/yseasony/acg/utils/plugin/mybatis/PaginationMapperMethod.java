package org.yseasony.acg.utils.plugin.mybatis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.yseasony.acg.utils.Page;

public class PaginationMapperMethod {  
	  
    private SqlSession sqlSession;  
    private Configuration config;  
  
    private SqlCommandType type;  
    private String commandName;  
    private String commandCountName;  
  
    private Class<?> declaringInterface;  
    private Method method;  
  
    private Integer rowBoundsIndex;  
    private Integer paginationIndex;  
    private List<String> paramNames;  
    private List<Integer> paramPositions;  
  
    private boolean hasNamedParameters;  
  
    public PaginationMapperMethod(Class<?> declaringInterface, Method method,  
            SqlSession sqlSession) {  
        paramNames = new ArrayList<String>();  
        paramPositions = new ArrayList<Integer>();  
        this.sqlSession = sqlSession;  
        this.method = method;  
        this.config = sqlSession.getConfiguration();  
        this.hasNamedParameters = false;  
        this.declaringInterface = declaringInterface;  
        setupFields();  
        setupMethodSignature();  
        setupCommandType();  
        validateStatement();  
    }  
      
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public Object execute(Object[] args) {  
        final Object param = getParam(args);  
        Page<Object> page ;  
        RowBounds rowBounds ;  
        if (paginationIndex != null) {  
            page = (Page) args[paginationIndex];  
            rowBounds = page.newRowBounds();  
        }  
        else if (rowBoundsIndex != null) {  
            rowBounds = (RowBounds) args[rowBoundsIndex];  
            page = new Page<Object>();  
        }  
        else {  
            throw new BindingException("Invalid bound statement (not found rowBounds or pagination in paramenters)");  
        }  
        page.setTotalCount(executeForCount(param));  
        page.setResult(executeForList(param, rowBounds));  
        return page;  
    }  
      
    private long executeForCount(Object param) {  
        Number result = (Number) sqlSession.selectOne(commandCountName, param);  
        return result.longValue();  
    }  
  
    @SuppressWarnings("rawtypes")  
    private List executeForList(Object param, RowBounds rowBounds) {  
        return sqlSession.selectList(commandName, param, rowBounds);  
    }  
  
    private Object getParam(Object[] args) {  
        final int paramCount = paramPositions.size();  
        if (args == null || paramCount == 0) {  
            return null;  
        } else if (!hasNamedParameters && paramCount == 1) {  
            return args[paramPositions.get(0)];  
        } else {  
            Map<String, Object> param = new HashMap<String, Object>();  
            for (int i = 0; i < paramCount; i++) {  
                param.put(paramNames.get(i), args[paramPositions.get(i)]);  
            }  
            return param;  
        }  
    }  
      
    private void setupMethodSignature() {  
        final Class<?>[] argTypes = method.getParameterTypes();  
        for (int i = 0; i < argTypes.length; i++) {  
            if (Page.class.isAssignableFrom(argTypes[i])) {  
                paginationIndex = i;  
            }  
            else if (RowBounds.class.isAssignableFrom(argTypes[i])) {  
                rowBoundsIndex = i;  
            } else {  
                String paramName = String.valueOf(paramPositions.size());  
                paramName = getParamNameFromAnnotation(i, paramName);  
                paramNames.add(paramName);  
                paramPositions.add(i);  
            }  
        }  
    }  
  
    private String getParamNameFromAnnotation(int i, String paramName) {  
        Object[] paramAnnos = method.getParameterAnnotations()[i];  
        for (Object paramAnno : paramAnnos) {  
            if (paramAnno instanceof Param) {  
                hasNamedParameters = true;  
                paramName = ((Param) paramAnno).value();  
            }  
        }  
        return paramName;  
    }  
  
    private void setupFields() {  
        commandName = declaringInterface.getName() + "." + method.getName();  
        commandCountName = commandName + "Count"; // 命名约定  
    }  
  
    private void setupCommandType() {  
        MappedStatement ms = config.getMappedStatement(commandName);  
        type = ms.getSqlCommandType();  
        if (type != SqlCommandType.SELECT) {  
            throw new BindingException("Unsupport execution method for: " + commandName);  
        }  
    }  
  
    private void validateStatement() {  
        if (!config.hasStatement(commandName)) {  
            throw new BindingException("Invalid bound statement (not found): " + commandName);  
        }  
        if (!config.hasStatement(commandCountName)) {  
            throw new BindingException("Invalid bound statement (not found): " + commandCountName);  
        }  
    }  
  
}
