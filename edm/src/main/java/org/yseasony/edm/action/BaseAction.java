package org.yseasony.edm.action;

import org.apache.commons.lang.Validate;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * User: yseasony
 * Date: 13-10-2
 * Time: 下午9:17
 */
public class BaseAction {

    public Map<String, Object> getParameters(ServletRequest request) {
        return getParametersStartingWith(request, null);
    }

    public Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null");
        Enumeration<?> paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

}
