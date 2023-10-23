package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV1Test {

	MemberRepositoryV1 repository;

	@BeforeEach
	void beforeEach() {
//		DriverManagerDataSource dataSource =
//		  new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		repository = new MemberRepositoryV1(dataSource);
	}

	@Test
	void crud() throws SQLException {
		Member member = new Member("memberV0", 20000);
		repository.save(member);

		// find by id
		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);
		Assertions.assertThat(findMember).isEqualTo(member);

		// update: money: 10000 -> 20000
		repository.update(member.getMemberId(), 20000);
		Member updatedMember = repository.findById(member.getMemberId());
		assertThat(updatedMember.getMoney()).isEqualTo(20000);
		// delete
		repository.delete(member.getMemberId());
		assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class);

	}
}
