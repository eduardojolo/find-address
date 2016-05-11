package br.com.find.address.brazil.service.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import br.com.find.address.service.IFindAddressService;
import br.com.find.address.service.abs.AbstractFindAddressRouter;

/**
 * Implementation of the Find Address Router for Brazilian addresses.
 * 
 * @author Eduardo
 *
 */
@Service
public class FindAddressRouterBrazilImpl extends AbstractFindAddressRouter {

	/**
	 * Country name.
	 */
	private static final String BRAZIL_COUNTRY_NAME = "Brazil";
	
	/**
	 * Country flag source image name.
	 */
	private static final String BRAZIL_FLAG_IMAGE_NAME = "brazil_flag.jpg";
	
	/**
	 * Number of requests processed.
	 */
	private Integer callsCount = -1;

	@Override
	protected void fillFindAddressServices() {
		Queue<IFindAddressService> findAddressServices = new LinkedList<IFindAddressService>();

		findAddressServices.offer(new CorreiosFindAddressServiceImpl());
		findAddressServices.offer(new PostmonFindAddressServiceImpl());
		findAddressServices.offer(new RepublicaVirtualFindAddressServiceImpl());

		super.setFindAddressServices(findAddressServices);
	}

	@Override
	public String getCountryName() {
		return BRAZIL_COUNTRY_NAME;
	}
	
	@Override
	public String getCountryImageName() {
		return BRAZIL_FLAG_IMAGE_NAME;
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
