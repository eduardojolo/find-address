package br.com.find.address.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.find.address.board.dto.BoardServiceDTO;
import br.com.find.address.board.service.IBoardService;

/**
 * Controller for the board screen.
 * 
 * @author Eduardo
 *
 */
@Controller
public class BoardController {
	
	@Autowired
	private IBoardService boardService;

	/**
	 * Access the service status board.
	 * 
	 * @param postalCode Postal code
	 * @return String
	 */
	@RequestMapping(value="/address/board", method= RequestMethod.GET)
	public String getBoard(Model model) {
		List<BoardServiceDTO> boardServiceDTOs = boardService.getBoardInfo();
		
		model.addAttribute("boardDTOs", boardServiceDTOs);
		return "board";
	}
}
