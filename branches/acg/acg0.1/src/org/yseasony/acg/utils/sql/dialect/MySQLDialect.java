package org.yseasony.acg.utils.sql.dialect;
/**
 * @author badqiu
 */
public class MySQLDialect extends Dialect{

	public boolean supportsLimitOffset(){
		return true;
	}
	
    public boolean supportsLimit() {   
        return true;   
    }  
    
	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {   
        	return sql + " LIMIT #{sql_offset} , #{sql_limit} "; 
        } else {   
            return sql + " LIMIT #{sql_limit}";
        }  
	}   
  
}
