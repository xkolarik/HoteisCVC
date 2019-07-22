package br.com.cvc.HoteisCVC.busines;

import br.com.cvc.HoteisCVC.model.HotelModel;

import java.util.ArrayList;

public interface HotelService {

    ArrayList<HotelModel> buscarPorIdHotel(Integer hotelId, Integer qtdAdultos , Integer qtdChd , String CheckIn , String CheckOut) throws Exception;

    ArrayList<HotelModel> buscarPorCodigoCidade(Integer cityCode, Integer qtdAdultos , Integer qtdChd , String CheckIn , String CheckOut) throws Exception;
}
