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

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Main configuration class for the application.
 * Turns on @Component scanning, loads externalized application.properties, and sets up the database.
 * @author Keith Donald
 */
@Configuration
@ComponentScan(basePackages = "ru.spotadvisor", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:application.properties")
public class MainConfig {

//	@Bean
//  @Primary
////  @ConfigurationProperties(prefix = "t.spring.datasource")
//	public DataSource dataSource() throws URISyntaxException {
//    DataSourceBuilder builder = DataSourceBuilder.create();
//	  try {
//      URI dbUri = new URI(System.getenv("DATABASE_URL"));
//      builder.username(dbUri.getUserInfo().split(":")[0]);
//      builder.password(dbUri.getUserInfo().split(":")[1]);
//      String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
//      builder.url(dbUrl);
//    }catch(Throwable e){
//      builder.username("postgres");
//      builder.password("local");
//      builder.url("jdbc:postgresql://localhost:5432/spotadvisor");
//    }
//		return builder
//						.build();
//	}

	// internal helpers

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}
}
