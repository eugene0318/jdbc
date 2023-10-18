package hello.jdbc.repository;

import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV0Test {

	MemberRepositoryV0 repository = new MemberRepositoryV0();

	@Test
	void crud() throws SQLException {
		Member member = new Member("memberV0", 20000);
		repository.save(member);
		
		//find by id
		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember={}", findMember);
		Assertions.assertThat(findMember).isEqualTo(member);
	}
}
