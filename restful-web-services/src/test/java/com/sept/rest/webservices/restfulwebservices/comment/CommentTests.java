import com.sept.rest.webservices.restfulwebservices.model.Comment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import javax.management.InvalidAttributeValueException;
import java.util.Date;

class CommentTests {

	private final Comment comment = new Comment();

	@Test
	void validValueAssignment() throws InvalidAttributeValueException {

		comment.setUpspikes(10);
		assertEquals(10, comment.getUpspikes());

		comment.setUpspikes(0);
		assertEquals(0, comment.getUpspikes());

		comment.setDownspikes(10);
		assertEquals(10, comment.getDownspikes());

		comment.setDownspikes(0);
		assertEquals(0, comment.getDownspikes());
	}

	@Test
	void faultyValueAssignment() {
		assertThrows(InvalidAttributeValueException.class, () -> {
			comment.setUpspikes(-1);
		});

		assertThrows(InvalidAttributeValueException.class, () -> {
			comment.setDownspikes(-1);
		});

		// assertThrows(InvalidAttributeValueException.class, () -> {
		// 	new Comment(new Long(2), new Date(), -1, 2, "Hello!", 1, 1, false);
		// });

		// assertThrows(InvalidAttributeValueException.class, () -> {
		// 	new Comment(new Long(2), new Date(), 1, -2, "Hello!", 1, 1, false);
		// });
	}

	@Test
	void spikeCalculation() throws InvalidAttributeValueException  {
		// Positive expectation
		comment.setUpspikes(10);
		comment.setDownspikes(5);
		assertEquals(5, comment.getSpikes());

		// Neutral expectation
		comment.setUpspikes(5);
		comment.setDownspikes(5);
		assertEquals(0, comment.getSpikes());

		// Negative expectation
		comment.setUpspikes(5);
		comment.setDownspikes(10);
		assertEquals(-5, comment.getSpikes());
	}


	@Test
	void spikeRatioCalculation() throws InvalidAttributeValueException  {
		// Makes sure it's not integer division
		comment.setUpspikes(10);
		comment.setDownspikes(5);
		assertEquals(0.66667, comment.getSpikeRatio(), 0.0001);


		comment.setUpspikes(5);
		comment.setDownspikes(10);
		assertEquals(0.33333, comment.getSpikeRatio(), 0.0001);
	}




}