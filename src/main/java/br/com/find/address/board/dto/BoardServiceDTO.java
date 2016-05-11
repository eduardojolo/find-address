package br.com.find.address.board.dto;

import java.io.Serializable;

/**
 * DTO containing all the individual service info to be displayed in the board.
 * 
 * @author Eduardo
 *
 */
public class BoardServiceDTO implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -1789765358738165716L;

	private String countryName;

	private String imageSrc;

	private String currentWebService;

	private Long responseTime;

	private Integer numberCachedElements;

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the imageSrc
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	/**
	 * @param imageSrc
	 *            the imageSrc to set
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	/**
	 * @return the currentWebService
	 */
	public String getCurrentWebService() {
		return currentWebService;
	}

	/**
	 * @param currentWebService
	 *            the currentWebService to set
	 */
	public void setCurrentWebService(String currentWebService) {
		this.currentWebService = currentWebService;
	}

	/**
	 * @return the responseTime
	 */
	public Long getResponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime
	 *            the responseTime to set
	 */
	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @return the numberCachedElements
	 */
	public Integer getNumberCachedElements() {
		return numberCachedElements;
	}

	/**
	 * @param numberCachedElements
	 *            the numberCachedElements to set
	 */
	public void setNumberCachedElements(Integer numberCachedElements) {
		this.numberCachedElements = numberCachedElements;
	}

}
