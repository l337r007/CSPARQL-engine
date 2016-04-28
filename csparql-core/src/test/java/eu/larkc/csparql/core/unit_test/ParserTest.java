package eu.larkc.csparql.core.unit_test;

import eu.larkc.csparql.core.new_parser.CsparqlParser;
import eu.larkc.csparql.core.new_parser.ParseException;
import org.testng.annotations.Test;

public class ParserTest {
	@Test
	public void shouldNotRequireSpaceBeforeFrom() throws ParseException {
		String csparql = "REGISTER QUERY ex AS \n"
				+ "SELECT *\n"
				+ "FROM STREAM <http://org.ex#stream> [RANGE 5s STEP 1s]\n"
				+ "WHERE { ?s ?p ?o . }";
		CsparqlParser parser = CsparqlParser.createAndParse(csparql);
	}

	@Test
	public void shouldAllowSpaceAroundRange() throws ParseException {
		String csparql = "REGISTER QUERY ex AS \n"
				+ "SELECT *\n"
				+ "FROM STREAM <http://org.ex#stream> [ RANGE 5s STEP 1s ]\n"
				+ "WHERE { ?s ?p ?o .}";
		CsparqlParser parser = CsparqlParser.createAndParse(csparql);
	}

	@Test
	public void shouldAllowMsecRange() throws ParseException {
		String csparql = "REGISTER QUERY ex AS \n"
				+ "SELECT *\n"
				+ "FROM STREAM <http://org.ex#stream> [RANGE 500ms STEP 1s]\n"
				+ "WHERE { ?s ?p ?o .}";
		CsparqlParser parser = CsparqlParser.createAndParse(csparql);
	}

	@Test(expectedExceptions = {ParseException.class})
	public void shouldFailYearRange() throws ParseException {
		String csparql = "REGISTER QUERY ex AS \n"
				+ "SELECT *\n"
				+ "FROM STREAM <http://org.ex#stream> [RANGE 1y STEP 1s]\n"
				+ "WHERE { ?s ?p ?o .}";
		CsparqlParser parser = CsparqlParser.createAndParse(csparql);
		assert(false);
	}
}
