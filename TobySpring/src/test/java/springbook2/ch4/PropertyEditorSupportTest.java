package springbook2.ch4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.beans.PropertyEditorSupport;

import org.junit.Test;

public class PropertyEditorSupportTest {

	@Test
	public void propertyEditor() {
		LevelPropertyEditor levelEditor = new LevelPropertyEditor();
		
		levelEditor.setAsText("3");
		assertThat((Level)levelEditor.getValue(), is(Level.GOLD));
		
		levelEditor.setValue(Level.BASIC);
		assertThat(levelEditor.getAsText(), is("1"));

	}
	
	
	static class LevelPropertyEditor extends PropertyEditorSupport {
		
		@Override
		public String getAsText() {
			return String.valueOf(((Level)this.getValue()).intValue());
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			this.setValue(Level.valueOf(Integer.parseInt(text.trim())));
		}
		
	}

	static enum Level {
		GOLD(3, null), SILVER(2,GOLD), BASIC(1, SILVER);
		
		private final int value;
		private final Level next;

		public int intValue() { return this.value; }
		
		public Level nextLevel() {
			return this.next;
		}
		
		Level(int value, Level next) {
			this.value = value;
			this.next = next;
		}
		
		public static Level valueOf(int value) {
		    switch(value) {
		    case 1: return BASIC;
		    case 2: return SILVER;
		    case 3: return GOLD;
		    default: throw new AssertionError("Unknown value: " + value);
		    }
		  }
	}
}
