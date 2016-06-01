package br.com.marineteapp.provider;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;
import javax.annotation.Priority;

import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.business.UsuarioBusiness;

// Estabelecer classe como um Provider
// @Secured interface utilizada para anotar quais métodos executarão esse filter
@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			validateToken(token);
			
			// Set new Security Context
			requestContext.setSecurityContext(new SecurityContext() {

				// Pegar usuario ligado ao token
				@Override
				public Principal getUserPrincipal() {

					return new Principal() {
						
						// Retornar nome do usuario da sessao
						@Override
						public String getName() {
							UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
							return usuarioBusiness.getUsuarioByToken(token).getNome();
						}
					};
				}

				@Override
				public boolean isUserInRole(String role) {
					return true;
				}

				@Override
				public boolean isSecure() {
					return false;
				}

				@Override
				public String getAuthenticationScheme() {
					return null;
				}
			});

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}

	}

	private void validateToken(String token) throws Exception {
		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		Usuario usuario = usuarioBusiness.getUsuarioByToken(token);
		if (usuario == null) {
			throw new NotAuthorizedException("Token inválido");
		}
	}
}
