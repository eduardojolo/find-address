package br.com.find.address.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.find.address.board.dto.BoardServiceDTO;
import br.com.find.address.board.service.IBoardService;
import br.com.find.address.brazil.service.impl.FindAddressRouterBrazilImpl;
import br.com.find.address.canada.service.impl.FindAddressRouterCanadaImpl;
import br.com.find.address.service.abs.AbstractFindAddressRouter;

/**
 * Implementation of the board service.
 * Provides the information to be displayed in the board.
 * 
 * @author Eduardo
 *
 */
@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	private FindAddressRouterBrazilImpl findAddressRouterBrazilImpl;

	@Autowired
	private FindAddressRouterCanadaImpl findAddressRouterCanadaImpl;

	/**
	 * List of different routers implementations.
	 */
	private List<AbstractFindAddressRouter> routers;

	/**
	 * Populates the routers list with all the implementations.
	 */
	@PostConstruct
	private void populateRoutersList() {
		routers = new ArrayList<AbstractFindAddressRouter>();
		
		routers.add(findAddressRouterBrazilImpl);
		routers.add(findAddressRouterCanadaImpl);
	}

	@Override
	public List<BoardServiceDTO> getBoardInfo() {
		List<BoardServiceDTO> boardServiceDTOs = new ArrayList<BoardServiceDTO>();

		BoardServiceDTO boardServiceDTO;
		for (AbstractFindAddressRouter router : routers) {
			boardServiceDTO = new BoardServiceDTO();

			boardServiceDTO.setCountryName(router.getCountryName());
			boardServiceDTO.setImageSrc(router.getCountryImageName());
			boardServiceDTO.setCurrentWebService(router.getCurrentWebServiceName());
			boardServiceDTO.setResponseTime(router.getCurrentResponseTime());
			boardServiceDTO.setNumberCachedElements(router.getCurrentNumberCachedElements());

			boardServiceDTOs.add(boardServiceDTO);
		}

		return boardServiceDTOs;
	}

}
