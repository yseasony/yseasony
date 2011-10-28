package org.yseasony.exam.utils.plugin.mybatis;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

public class MyBaseBuilder {
	  protected final MyConfiguration configuration;
	  protected final TypeAliasRegistry typeAliasRegistry;
	  protected final TypeHandlerRegistry typeHandlerRegistry;

	  public MyBaseBuilder(MyConfiguration configuration) {
	    this.configuration = configuration;
	    this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
	    this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
	  }

	  public MyConfiguration getConfiguration() {
	    return configuration;
	  }

	  protected String stringValueOf(String value, String defaultValue) {
	    return value == null ? defaultValue : value;
	  }

	  protected Boolean booleanValueOf(String value, Boolean defaultValue) {
	    return value == null ? defaultValue : Boolean.valueOf(value);
	  }

	  protected Integer integerValueOf(String value, Integer defaultValue) {
	    return value == null ? defaultValue : Integer.valueOf(value);
	  }

	  protected JdbcType resolveJdbcType(String alias) {
	    if (alias == null) return null;
	    try {
	      return JdbcType.valueOf(alias);
	    } catch (IllegalArgumentException e) {
	      throw new BuilderException("Error resolving JdbcType. Cause: " + e, e);
	    }
	  }

	  protected ResultSetType resolveResultSetType(String alias) {
	    if (alias == null) return null;
	    try {
	      return ResultSetType.valueOf(alias);
	    } catch (IllegalArgumentException e) {
	      throw new BuilderException("Error resolving ResultSetType. Cause: " + e, e);
	    }
	  }

	  protected ParameterMode resolveParameterMode(String alias) {
	    if (alias == null) return null;
	    try {
	      return ParameterMode.valueOf(alias);
	    } catch (IllegalArgumentException e) {
	      throw new BuilderException("Error resolving ParameterMode. Cause: " + e, e);
	    }
	  }

	  protected Class<?> resolveClass(String alias) {
	    if (alias == null) return null;
	    try {
	      return resolveAlias(alias);
	    } catch (Exception e) {
	      throw new BuilderException("Error resolving class . Cause: " + e, e);
	    }
	  }

	  protected Object resolveInstance(String alias) {
	    if (alias == null) return null;
	    try {
	      Class<?> type = resolveClass(alias);
	      return type.newInstance();
	    } catch (Exception e) {
	      throw new BuilderException("Error instantiating class. Cause: " + e, e);
	    }
	  }

	  protected Object resolveInstance(Class<?> type) {
	    if (type == null) return null;
	    try {
	      return type.newInstance();
	    } catch (Exception e) {
	      throw new BuilderException("Error instantiating class. Cause: " + e, e);
	    }
	  }

	  protected Class<?> resolveAlias(String alias) {
	    return typeAliasRegistry.resolveAlias(alias);
	  }
}
