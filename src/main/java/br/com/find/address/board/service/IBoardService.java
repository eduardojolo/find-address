package br.com.find.address.board.service;

import java.util.List;

import br.com.find.address.board.dto.BoardServiceDTO;

/**
 * Interface of the board service.
 * 
 * @author Eduardo
 *
 */
public interface IBoardService {

	/**
	 * Gather all the information to be displayed in the board.
	 * 
	 * @return List<BoardServiceDTO>
	 */
	List<BoardServiceDTO> getBoardInfo();
}
