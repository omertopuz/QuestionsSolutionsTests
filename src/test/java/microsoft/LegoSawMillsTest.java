package microsoft;

import net.questions.solutions.LegoSawMills;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LegoSawMillsTest {

	private LegoSawMills solution;

	@BeforeEach
	void initEach(TestInfo testInfo, TestReporter testReporter) {
		solution = new LegoSawMills();
		testReporter.publishEntry(" Current Test Method : " + testInfo.getDisplayName());
	}

	@Test
	@DisplayName("Calculate Profit")
	void testCalculateProfit() {
		assertAll(
				() -> assertEquals(solution.calculateProfit(new ArrayList<>(Arrays.asList(2,3,1))),4)
				,() -> assertEquals(solution.calculateProfit(new ArrayList<>(Arrays.asList(1,2,1))),1)
		);
	}

	@Test
	@DisplayName("Permutation")
	void testPermutation() {
		assertAll(
				() -> assertEquals(solution.permute(new int[]{1,2,1}).size(),3)
		);
	}

	@Test
	@DisplayName("Calculate Max Profits with TrunkOrder")
	void testCalculateMaxProfitsTrunkOrder() {
		assertAll(
				() -> assertEquals(solution.calculateMaxProfitsTrunkOrder(new int[]{2,3,1}).size(),4)
//				,() -> assertEquals(solution.calculateMaxProfitsTrunkOrder(new int[]{1,2,1}).size(),1)
		);
	}

	@Test
	@DisplayName("Calculate Total Profits")
	void testCalculateTotalProfits() {
		assertAll(
				() -> assertEquals(solution.calculateTotalMaxProfit(new int[][]{{1,2,1},{1,2},{1,4}}),8)
		);
	}

}
