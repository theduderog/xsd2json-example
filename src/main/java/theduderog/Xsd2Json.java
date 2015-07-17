package theduderog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import generated.Item;

public class Xsd2Json
{

    public static void main( String[] args ) throws Exception
    {
        ObjectMapper objMapper = new ObjectMapper();
        //Use only public getters
        objMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        //Use lower case with underscores for JSON field names
        objMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);


        // Read from STDIN
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String xmlStr;
        while ((xmlStr = br.readLine()) != null) {
            ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream (xmlStr.getBytes());
            JAXBContext context = JAXBContext.newInstance(Item.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Item item = (Item)unmarshaller.unmarshal(xmlContentBytes);

            System.out.println(objMapper.writeValueAsString(item));
        }
    }
}
