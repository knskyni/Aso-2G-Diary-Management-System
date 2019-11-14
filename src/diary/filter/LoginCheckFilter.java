package diary.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.UserInfoBeans;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    private final String[] throughPath = {"/login", "/auth"};
    private final String[] throughFile = {"css", "js", "png", "jpg", "jpeg", "gif"};

    @Override
    public void destroy() {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessPath = ((HttpServletRequest)request).getServletPath();
        HttpSession session = ((HttpServletRequest)request).getSession(false);

        // throughPathのいずれかと一致するパスはフィルターを適用しない
        if(Arrays.asList(throughPath).contains(accessPath)) {
            // ログアウト判定 (ログアウト後のsessionはnullにならない)
            if(session != null) {
                if(session.getAttribute("userInfo") != null) {
                    ((HttpServletResponse)response).sendRedirect("top");
                    return;
                }
            }
            chain.doFilter(request, response);
            return;
        }

        /* throughPathのいずれかと一致するパスはフィルターを適用しない */
        String accessPathExtension = accessPath.substring(accessPath.lastIndexOf(".") + 1);
        if(Arrays.asList(throughFile).contains(accessPathExtension)) {
            chain.doFilter(request, response);
            return;
        }

        /* セッションが存在しない場合ログインページへリダイレクト */
        if(session == null) {
            ((HttpServletResponse)response).sendRedirect("login?state=not_logged");
            return;
        }

        /* ログアウト済みへの対応 */
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
        if(userInfo == null) {
            ((HttpServletResponse)response).sendRedirect("login?state=not_logged");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // do nothing
    }
}
