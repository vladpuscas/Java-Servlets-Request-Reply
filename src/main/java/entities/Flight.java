package entities;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Vlad on 24-Oct-17.
 */
public class Flight {
    private int id;
    private String airplaneType;
    private int departureCityId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;
    private int arrivalCityId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(int departureCityId) {
        this.departureCityId = departureCityId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(int arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airplaneType='" + airplaneType + '\'' +
                ", departureCityId=" + departureCityId +
                ", departureDate=" + departureDate +
                ", arrivalCityId=" + arrivalCityId +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
