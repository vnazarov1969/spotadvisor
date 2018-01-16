/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.spotadvisor.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.CanvasSignInController;
import ru.spotadvisor.backend.user.JdbcUserRepository;
import ru.spotadvisor.backend.user.SecurityContext;
import ru.spotadvisor.backend.user.UserSignInAdapter;
import ru.spotadvisor.backend.user.UserConnectionSignUp;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Spring Social Configuration.
 * @author Craig Walls
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

  @Inject
  private Environment environment;

  @Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	//
	// SocialConfigurer implementation methods
	//

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("facebook.appId"), env.getProperty("facebook.appSecret")));
	}


	/**
	 * Singleton data access object providing access to connections across all users.
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setConnectionSignUp(new UserConnectionSignUp(new JdbcUserRepository(jdbcTemplate)));
		return repository;
	}

	public UserIdSource getUserIdSource() {
		return new UserIdSource() {
			@Override
			public String getUserId() {
//				return "0";
				return SecurityContext.getCurrentUser().getId();
//          return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			}
		};
	}

	@Bean
	@Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}

  @Bean
  public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
    ProviderSignInController provider = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new UserSignInAdapter());
    provider.setPostSignInUrl(this.environment.getProperty("dev.frontend.url"));
    return provider;
  }

  @Bean
	public CanvasSignInController canvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, Environment env) {
    CanvasSignInController provider = new CanvasSignInController(connectionFactoryLocator, usersConnectionRepository, new UserSignInAdapter(), env.getProperty("facebook.appKey"), env.getProperty("facebook.appSecret"), env.getProperty("facebook.canvasPage"));
    provider.setPostSignInUrl(this.environment.getProperty("dev.frontend.url") + "/index.html/#frame/track");
    return provider;
	}

}
