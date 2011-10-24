package org.yseasony.acg.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationProcessingFilter extends
		UsernamePasswordAuthenticationFilter {

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		SavedRequestAwareAuthenticationSuccessHandler srh = new SavedRequestAwareAuthenticationSuccessHandler();
		this.setAuthenticationSuccessHandler(srh);
		srh.setRedirectStrategy(new RedirectStrategy() {
			@Override
			public void sendRedirect(HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse, String s)
					throws IOException {
				// do nothing, no redirect
			}
		});
		super.successfulAuthentication(request, response, authResult);
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(
				response);
		Writer out = responseWrapper.getWriter();
		out.write("{success:true}");
		out.close();
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		AuthenticationFailureHandler afh = new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request,
					HttpServletResponse response,
					AuthenticationException exception) throws IOException,
					ServletException {

			}
		};
		this.setAuthenticationFailureHandler(afh);
		super.unsuccessfulAuthentication(request, response, failed);
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(
				response);

		Writer out = responseWrapper.getWriter();

		out.write("{ success: false, errors: { reason: '-1'}}");
		out.close();
	}

}
