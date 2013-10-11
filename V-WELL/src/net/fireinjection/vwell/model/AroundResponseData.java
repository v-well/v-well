package net.fireinjection.vwell.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AroundResponseData {

	@JsonProperty("data")
	private ResponseData responseData;

	public ResponseData getResponseData() {
		return responseData;
	}
	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
	
	public static class ResponseData {

		@JsonProperty("healthCenters")
		private List<HealthCenters> healthCenters;
		
		@JsonProperty("hospitals")
		private List<Hospitals> hospitals;

		public List<HealthCenters> getHealthCenters() {
			return healthCenters;
		}
		public void setHealthCenters(List<HealthCenters> healthCenters) {
			this.healthCenters = healthCenters;
		}
		
		public List<Hospitals> getHospitals() {
			return hospitals;
		}
		public void setHospitals(List<Hospitals> hospitals) {
			this.hospitals = hospitals;
		}

		public static class HealthCenters {
			@JsonProperty("id") private Long id;
			@JsonProperty("name") private String name;
			@JsonProperty("phone") private String phone;
			@JsonProperty("injectionRoomPhone") private String injectionRoomPhone;
			@JsonProperty("location") private Location location;
			@JsonProperty("homePageLink") private String homePageLink;
			@JsonProperty("registTime") private Date registTime;
			@JsonProperty("updatedTime") private Date updatedTime;
			@JsonProperty("distance") private Double distance;
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
			public String getInjectionRoomPhone() {
				return injectionRoomPhone;
			}
			public void setInjectionRoomPhone(String injectionRoomPhone) {
				this.injectionRoomPhone = injectionRoomPhone;
			}
			public Location getLocation() {
				return location;
			}
			public void setLocation(Location location) {
				this.location = location;
			}
			public String getHomePageLink() {
				return homePageLink;
			}
			public void setHomePageLink(String homePageLink) {
				this.homePageLink = homePageLink;
			}
			public Date getRegistTime() {
				return registTime;
			}
			public void setRegistTime(Date registTime) {
				this.registTime = registTime;
			}
			public Date getUpdatedTime() {
				return updatedTime;
			}
			public void setUpdatedTime(Date updatedTime) {
				this.updatedTime = updatedTime;
			}
			public Double getDistance() {
				return distance;
			}
			public void setDistance(Double distance) {
				this.distance = distance;
			}
		}

		public static class Hospitals {
			@JsonProperty("id") private Long id;
			@JsonProperty("name") private String name;
			@JsonProperty("phone") private String phone;
			@JsonProperty("location") private Location location;
			@JsonProperty("registTime") private Date registTime;
			@JsonProperty("updatedTime") private Date updatedTime;
			@JsonProperty("distance") private Double distance;
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPhone() {
				return phone;
			}
			public void setPhone(String phone) {
				this.phone = phone;
			}
			public Location getLocation() {
				return location;
			}
			public void setLocation(Location location) {
				this.location = location;
			}
			public Date getRegistTime() {
				return registTime;
			}
			public void setRegistTime(Date registTime) {
				this.registTime = registTime;
			}
			public Date getUpdatedTime() {
				return updatedTime;
			}
			public void setUpdatedTime(Date updatedTime) {
				this.updatedTime = updatedTime;
			}
			public Double getDistance() {
				return distance;
			}
			public void setDistance(Double distance) {
				this.distance = distance;
			}
		}

		public static class Location {
			@JsonProperty("id") private Long id;
			@JsonProperty("address") private String address;
			@JsonProperty("newAddress") private String newAddress;
			@JsonProperty("buildingAddress") private String buildingAddress;
			@JsonProperty("mainAddress") private String mainAddress;
			@JsonProperty("subAddress") private String subAddress;
			@JsonProperty("localName_1") private String localName_1;
			@JsonProperty("localName_2") private String localName_2;
			@JsonProperty("localName_3") private String localName_3;
			@JsonProperty("lng") private Double lng;
			@JsonProperty("lat") private Double lat;
			@JsonProperty("point_x") private Double point_x;
			@JsonProperty("point_y") private Double point_y;
			@JsonProperty("point_wx") private String point_wx;
			@JsonProperty("point_wy") private String point_wy;
			@JsonProperty("registTime") private Date registTime;
			@JsonProperty("updatedTime") private Date updatedTime;
			
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getNewAddress() {
				return newAddress;
			}
			public void setNewAddress(String newAddress) {
				this.newAddress = newAddress;
			}
			public String getBuildingAddress() {
				return buildingAddress;
			}
			public void setBuildingAddress(String buildingAddress) {
				this.buildingAddress = buildingAddress;
			}
			public String getMainAddress() {
				return mainAddress;
			}
			public void setMainAddress(String mainAddress) {
				this.mainAddress = mainAddress;
			}
			public String getSubAddress() {
				return subAddress;
			}
			public void setSubAddress(String subAddress) {
				this.subAddress = subAddress;
			}
			public String getLocalName_1() {
				return localName_1;
			}
			public void setLocalName_1(String localName_1) {
				this.localName_1 = localName_1;
			}
			public String getLocalName_2() {
				return localName_2;
			}
			public void setLocalName_2(String localName_2) {
				this.localName_2 = localName_2;
			}
			public String getLocalName_3() {
				return localName_3;
			}
			public void setLocalName_3(String localName_3) {
				this.localName_3 = localName_3;
			}
			public Double getLng() {
				return lng;
			}
			public void setLng(Double lng) {
				this.lng = lng;
			}
			public Double getLat() {
				return lat;
			}
			public void setLat(Double lat) {
				this.lat = lat;
			}
			public Double getPoint_x() {
				return point_x;
			}
			public void setPoint_x(Double point_x) {
				this.point_x = point_x;
			}
			public Double getPoint_y() {
				return point_y;
			}
			public void setPoint_y(Double point_y) {
				this.point_y = point_y;
			}
			public String getPoint_wx() {
				return point_wx;
			}
			public void setPoint_wx(String point_wx) {
				this.point_wx = point_wx;
			}
			public String getPoint_wy() {
				return point_wy;
			}
			public void setPoint_wy(String point_wy) {
				this.point_wy = point_wy;
			}
			public Date getRegistTime() {
				return registTime;
			}
			public void setRegistTime(Date registTime) {
				this.registTime = registTime;
			}
			public Date getUpdatedTime() {
				return updatedTime;
			}
			public void setUpdatedTime(Date updatedTime) {
				this.updatedTime = updatedTime;
			}
		}
	}
}