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

	@Override
	protected void fillFindAddressServices() {
		Queue<IFindAddressService> findAddressServices = new LinkedList<IFindAddressService>();

		findAddressServices.offer(new ZippopotamFindAddressServiceImpl());
		
		super.setFindAddressServices(findAddressServices);
	}

}
