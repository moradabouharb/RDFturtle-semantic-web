package semweb;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class JenaToturial {

	static final String myURI = "http://w3id.org/people/az/me";
	static final String myName = "Antoine Zimmermann";
	static final String myNS = "http://w3id.org/people/az";
	static final String myPrefix = "az";
	static final String rdfsprefix = "rdfs";
	static final String DBONS = "http://dbpedia.org/ontology/";
	static final String xsdPrefix = "xsd";
	static final String emse = "Ecole de mines";
	static final String drmns = "http://vocab.data.gov/def/drm#";
	
	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();    //initial an empty graph..
		
		model.setNsPrefix(myPrefix, myNS); 					// set a prefix
		model.setNsPrefix(rdfsprefix, RDFS.uri);
		model.setNsPrefix("xsd", XSD.getURI());
		model.setNsPrefix("dbo",DBONS);
		model.setNsPrefix("az", myURI);
		model.setNsPrefix("emse", emse);
		model.setNsPrefix("drm",drmns);
			
		
		Resource me = model.createResource(myURI); 			//represent a node in the graph that has a URI
		Property dboData = model.createProperty(DBONS,"Birthdate");
		Property workfor = model.createProperty(drmns,"workfor");
		Literal birtDate = model.createTypedLiteral("1981-01-14",XSD.date.getURI());
		
		
		model.add(me, RDFS.label, myName);
		model.add(me, dboData, birtDate);
		model.add(me, workfor, emse);
		
		model.write(System.out,"Turtle");
	}
}