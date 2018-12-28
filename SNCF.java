package semweb;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class SNCF {
	
	static String fileName = "C:\\Users\\murad\\eclipse-workspace\\semweb\\src\\main\\java\\semweb\\stops.txt";
	public static final String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	public static final String Stop = "http://trainstationstop.com";
	public static final String rdfsprefix = "rdfs";
	
	public static void main(String[] args){
		int i = 0;
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("geo", geo);
        model.setNsPrefix("Stop", Stop);
        model.setNsPrefix(rdfsprefix, RDFS.uri);
        
        try {
            FileReader filereader = new FileReader(fileName);
            Scanner s = new Scanner(filereader);
            while (s.hasNextLine()){
                String line = s.nextLine();
                String[] retval = line.split(",");
                Resource r = model.createProperty(Stop,retval[0]);   
                
                Property type = model.createProperty(RDF.uri, "type");
                Property spatial = model.createProperty(geo, "SpatialThing");
                Property lati = model.createProperty(geo, "latitude");
                Property longi = model.createProperty(geo, "longitude");
                
                model.add(r, type, spatial);
                model.add(r, RDFS.label,retval[1].toString());
                model.add(r, lati, model.createTypedLiteral(retval[3], XSD.decimal.getURI()));
                model.add(r, longi, model.createTypedLiteral(retval[4], XSD.decimal.getURI()));
                
                

                ///////////////// or onother way ..
                /*
                r.addProperty(type, spatial);
                r.addProperty(RDFS.label,retval[1].toString());       
                r.addProperty(lati, model.createTypedLiteral(retval[3], XSD.decimal.getURI()));
                r.addProperty(longi, model.createTypedLiteral(retval[4], XSD.decimal.getURI()));
                */
                ///////////////////
                i = i+1;
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.write(System.out,"turtle");
    }
}