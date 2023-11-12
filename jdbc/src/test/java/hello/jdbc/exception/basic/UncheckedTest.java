package hello.jdbc.exception.basic;

import org.junit.jupiter.api.Test;

public class UncheckedTest {

	@Test
	void unchecked_catch() {
		Service service = new Service();
		service.callCatch();
	}

	@Test
	void unckecked_throw() {
		Service service = new Service();
		service.callThrow();
	}

	/*
	 * RuntimeException을 항속받은 예외는 언체크 예외가 됩니다.
	 */

	static class MyUncheckedException extends RuntimeException {

		public MyUncheckedException(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	}

	/*
	 * Unchecked예외는 예외를 잡거나, 던지지 않아도된다. 예외를 잡지 않으면 자동을으로 던진다.
	 */
	static class Service {
		Repository repository = new Repository();

		/*
		 * 필요한 경우 예외를 잡아서 처리하면 된다.
		 */
		public void callCatch() {
			try {
				repository.call();
			} catch (Exception e) {
				// 예외 처리 로직
			}
		}

		public void callThrow() {
			repository.call();
		}
	}

	static class Repository {
		public void call() {
			throw new MyUncheckedException("ex");
		}
	}
}
