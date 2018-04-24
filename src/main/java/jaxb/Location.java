package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Vlad on 30-Oct-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

    @XmlElement(name = "longitude")
    private String longitude;

    @XmlElement(name = "latitude")
    private String latitude;

}
