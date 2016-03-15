/**
 * 
 */
package eyeofsauron.domain.utils.arguments;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.carrot2.core.Cluster;
import org.carrot2.core.Document;
import org.junit.Before;
import org.junit.Test;

/**
 * @author john
 *
 */
public class UtilityArgumentStubTest {

	UtilityArgument arg;
	
	Set<Class<?>> classes;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		arg = new UtilityArgumentStub();

		
	}


	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#UtilityArgument(java.util.Collection)}.
	 * @throws UtilityArgumentException 
	 */
	@Test
	public void testUtilityArgumentCollectionOfObject() throws UtilityArgumentException {
		Collection<Object> col = new ArrayList<Object> ();
		Object[] args = new Object[3];
		
		args[0] = "1";
		args[1] = new Cluster();
		args[2] = new Document();
		
		for (Object a : args)
			col.add(a);
		
		UtilityArgument test = new UtilityArgumentStub(col);

		int i = 0;
		for (Object a : test){

			assertTrue(a.equals(args[i++]));
		}
	}

	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#addArgument(java.lang.Object)}.
	 * @throws UtilityArgumentException 
	 */
	@Test
	public void testAddArgument() throws UtilityArgumentException {

		arg.addArgument("Hello");
		arg.addArgument(new Document());
		arg.addArgument(new Cluster());
		
	}

	@Test (expected = UtilityArgumentException.class)
	public void testAddArgument2() throws UtilityArgumentException {

		arg.addArgument(new Integer(1));

	}
	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#iterator()}.
	 * @throws UtilityArgumentException 
	 */
	@Test
	public void testIterator() throws UtilityArgumentException {

		arg.addArgument("1");
		arg.addArgument("2");
		arg.addArgument("3");

		int i = 1;
		for (Object ob: arg){
			assertTrue(Integer.toString(i++).equals(ob.toString()));
		}
	}

	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#addValidArgumentClass(java.lang.Class)}.
	 * @throws UtilityArgumentException 
	 */
	@Test
	public void testAddValidArgumentClass() throws UtilityArgumentException {

		arg.addValidArgumentClass(Integer.class);
		arg.addArgument(new Integer(0));
	}

	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#isValidArgument(java.lang.Object)}.
	 */
	@Test
	public void testIsValidArgument() {
		assertTrue(arg.isValidArgument("String"));
	}

	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#isValidArgument(java.lang.Object)}.
	 */
	@Test 
	public void testIsValidArgument2() {
		assertFalse(arg.isValidArgument(new Integer(0)));
	}
	/**
	 * Test method for {@link eyeofsauron.domain.utils.arguments.UtilityArgument#getValidArgumentClasses()}.
	 */
	@Test
	public void testGetValidArgumentClasses() {
//		Set<Class<?>> classes = arg.getValidArgumentClasses();
		
//		for (Class<?> c : classes)
//			System.out.println(c);
	}

	
	@Test
	public void testGetArgumentsByClass() throws UtilityArgumentException{

		arg.addArgument("1");
		arg.addArgument("2");
		arg.addArgument("3");
		
		Document[] docs = new Document[2];
		docs[0] = new Document();
		docs[1] = new Document();		
		for (Document doc :docs)
			arg.addArgument(doc);

		Cluster[] cl= new Cluster[2];
		cl[0] = new Cluster();
		cl[1] = new Cluster();
		for (Cluster c :cl)
			arg.addArgument(c);

		
		List<String> strings = arg.getArgumentsByClass(String.class);
		
		int i = 1;
		for (String ob: strings)
			assertTrue(Integer.toString(i++).equals(ob.toString()));

		List<Document> docList= arg.getArgumentsByClass(Document.class);
		List<Cluster> clList= arg.getArgumentsByClass(Cluster.class);
		
		i = 0;
		for (Document doc : docList)
			assertTrue(doc.equals(docs[i++]));
		
		i = 0;
		for (Cluster c : clList)
			assertTrue(c.equals(cl[i++]));

	}
}
