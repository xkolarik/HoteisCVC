package br.com.cvc.HoteisCVC.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.HoteisCVC.model.HotelModel;
import ch.qos.logback.core.joran.spi.DefaultClass;

@RestController
@RequestMapping("/pesquisar")
public class PesquisaController {

	
	@RequestMapping(value="/hotel/{id}", 
			method = RequestMethod.GET,  
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<HotelModel> pesquisarAcomodacaoPorHotel(
			@PathVariable("id") Integer hotelId 
														  , @RequestParam(value = "checkin", defaultValue = "")  String CheckIn
														  , @RequestParam(value = "checkout" , defaultValue = "")  String CheckOut
														  , @RequestParam(value = "paxQuantity" , defaultValue = "") Integer qtdAdultos
														  , @RequestParam(value = "chdQuantity" , defaultValue = "") Integer qtdChd
														 ) throws Exception{
         System.out.println("Quantidade adultos" + qtdAdultos);
		return new HotelModel().buscarPorIdHotel(hotelId, qtdAdultos , qtdChd , CheckIn , CheckOut );
	
	}
	
	@RequestMapping(value="/hotel/cidade/{cityCode}", 
			method = RequestMethod.GET,  
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<HotelModel> pesquisarAcomodacaoPorCidade(
			@PathVariable("cityCode") Integer cityCode 
														  , @RequestParam("checkin") String CheckIn
														  , @RequestParam("checkout") String CheckOut
														  , @RequestParam("paxQuantity") Integer qtdAdultos
														  , @RequestParam("chdQuantity") Integer qtdChd
														 ) throws Exception{
         
		return new HotelModel().buscarPorCodigoCidade(cityCode, qtdAdultos , qtdChd , CheckIn , CheckOut);
	}
}
