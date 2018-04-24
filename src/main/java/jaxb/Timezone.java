package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Vlad on 30-Oct-17.
 */
@XmlRootElement(name = "timezone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Timezone {
    @XmlElement(name = "version")
    private String version;
    @XmlElement(name = "location")
    private Location location;
    @XmlElement(name = "offset")
    private String offset;
    @XmlElement(name = "suffix")
    private String suffix;
    @XmlElement(name = "localtime")
    private String localtime;
    @XmlElement(name = "isotime")
    private String isotime;
    @XmlElement(name = "utctime")
    private String utctime;
    @XmlElement(name = "dst")
    private String dst;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public String getIsotime() {
        return isotime;
    }

    public void setIsotime(String isotime) {
        this.isotime = isotime;
    }

    public String getUtctime() {
        return utctime;
    }

    public void setUtctime(String utctime) {
        this.utctime = utctime;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
