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
package ru.spotadvisor.backend.user;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository implements UserRepository {

	private final JdbcTemplate jdbcTemplate;

	@Inject
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public void createUser(User user) throws UsernameAlreadyInUseException {
		try {
			jdbcTemplate.update(
					"insert into users (id, user_name) values (?,?)",
					user.getId(), user.getUserName());
		} catch (DuplicateKeyException e) {
			throw new UsernameAlreadyInUseException(user.getId());
		}
	}

	public User findUserByUsername(String username) {
		return jdbcTemplate.queryForObject("select id, user_name from users where user_name = ?",
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new User(rs.getString("id"), rs.getString("user_name"));
					}
				}, username);
	}

	public User findUserByUserId(String userId) {
		return jdbcTemplate.queryForObject("select id, user_name from users where id = ?",
						new RowMapper<User>() {
							public User mapRow(ResultSet rs, int rowNum) throws SQLException {
								return new User(rs.getString("id"), rs.getString("user_name"));
							}
						}, userId);
	}

}
