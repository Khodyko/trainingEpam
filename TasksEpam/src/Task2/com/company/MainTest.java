package Task2.com.company;

import org.junit.Test;

public class MainTest {
	@Test
	public void test01(){
		Main main= new Main();
		org.junit.Assert.assertTrue(main.isVoid(""));
	}

}
