package dorocy;

import dorocy.translator.Translator;
import dorocy.translator.TranslatorType;

import javax.servlet.*;
import java.io.IOException;

public class XssServletFilter implements Filter {

    private final XssFilter xssFilter;

    public XssServletFilter() {
        this.xssFilter = new XssFilter();
    }

    public XssServletFilter(Translator translator) {
        this.xssFilter = new XssFilter(translator);
    }

    public XssServletFilter(TranslatorType type) {
        this.xssFilter = new XssFilter(type);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(new XssHttpServletRequestWrapper(servletRequest, xssFilter), servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }
}
