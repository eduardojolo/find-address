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

	@Override
	protected void fillFindAddressServices() {
		Queue<IFindAddressService> findAddressServices = new LinkedList<IFindAddressService>();

		findAddressServices.offer(new CorreiosFindAddressServiceImpl());
		findAddressServices.offer(new PostmonFindAddressServiceImpl());
		findAddressServices.offer(new RepublicaVirtualFindAddressServiceImpl());
		
		super.setFindAddressServices(findAddressServices);
	}

}
