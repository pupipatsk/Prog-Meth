package test;

import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.Test;

import model.QualityChecker;

public class TestQualityChecker {

	@Test
	public void testStress() {
		try {
			Field stressLevel = QualityChecker.class.getDeclaredField("stressLevel");
			assertEquals("Check stressLevel accessibility",true, Modifier.isPrivate(stressLevel.getModifiers()));
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			fail("no stressLevel variable");
		}
	}
	
	@Test
	public void testIncreaseStress() {
		try {
			QualityChecker q = new QualityChecker();
			int currentStress = q.getStressLevel();
			q.increaseStress();q.increaseStress();q.increaseStress();
			assertEquals("Check increaseStressLevel",currentStress+3, q.getStressLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Cannot check increaseLevel");
		}
	}
	
	@Test
	public void testWork() {
		QualityChecker q = new QualityChecker();
			Field stressLevel;
			try {
				stressLevel = QualityChecker.class.getDeclaredField("stressLevel");
				stressLevel.setAccessible(true);
				try {
					stressLevel.setInt(q, 4);
					q.work();
					assertEquals("stressLevel should not change if <5",4,q.getStressLevel());
					stressLevel.setInt(q, 5);
					q.work();
					assertEquals("stressLevel should change if =5",1,q.getStressLevel());
					stressLevel.setInt(q, 6);
					q.work();
					assertEquals("stressLevel should change if >5",1,q.getStressLevel());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					fail("IllegalArgumentException | IllegalAccessException");
				}
				
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				fail("No stressLevel field");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				fail("SecurityException");
			}
			 
	}
}
