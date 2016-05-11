package br.com.find.address.canada.service.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import br.com.find.address.service.IFindAddressService;
import br.com.find.address.service.abs.AbstractFindAddressRouter;

/**
 * Implementation of the Find Address Router for Canadian addresses.
 * 
 * @author Eduardo
 *
 */
@Service
public class FindAddressRouterCanadaImpl extends AbstractFindAddressRouter {

	/**
	 * Country name.
	 */
	private static final String CANADA_COUNTRY_NAME = "Canada";
	
	/**
	 * Country flag source image name.
	 */
	private static final String CANADA_FLAG_IMAGE_NAME = "canada_flag.png";
	
	/**
	 * Number of requests processed.
	 */
	private Integer callsCount = -1;

	@Override
	protected void fillFindAddressServices() {
		Queue<IFindAddressService> findAddressServices = new LinkedList<IFindAddressService>();

		findAddressServices.offer(new ZippopotamFindAddressServiceImpl());

		super.setFindAddressServices(findAddressServices);
	}

	@Override
	public String getCountryName() {
		return CANADA_COUNTRY_NAME;
	}

	@Override
	public String getCountryImageName() {
		return CANADA_FLAG_IMAGE_NAME;
	}

	@Override
	public Integer getCallsCount() {
		return callsCount;
	}

	@Override
	public void setCallsCount(Integer callsCount) {
		this.callsCount = callsCount;
	}
}
