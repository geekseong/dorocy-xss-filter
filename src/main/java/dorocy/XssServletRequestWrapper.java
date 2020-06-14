package dorocy;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class XssServletRequestWrapper extends HttpServletRequestWrapper {

    private final XssFilter xssFilter;

    public XssServletRequestWrapper(ServletRequest request) {
        super((HttpServletRequest)request);
        this.xssFilter = new XssFilter();
    }

    public XssServletRequestWrapper(ServletRequest request, XssFilter xssFilter) {
        super((HttpServletRequest)request);
        this.xssFilter = xssFilter;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return doFilter(value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();
        Map<String, String[]> filteredMap = new HashMap();

        for ( Map.Entry<String, String[]> entry: map.entrySet() ){

            String[] values = entry.getValue();
            String[] filteredValue = new String[values.length];

            for(int i = 0; i < values.length; ++i )
                filteredValue[i] = doFilter(values[i]);

            filteredMap.put(entry.getKey(), filteredValue);
        }

        return filteredMap;

    }


    @Override
    public String[] getParameterValues(String name) {

        String[] values = super.getParameterValues(name);

        if( values == null )
            return null;

        for( int i = 0; i < values.length; ++i ){
            values[i] = doFilter(values[i]);
        }

        return values;

    }

    private String doFilter(String value){
        return xssFilter.doFilter(value);
    }

}
