package br.com.cvc.HoteisCVC.busines;

import br.com.cvc.HoteisCVC.model.HotelModel;
import br.com.cvc.HoteisCVC.model.RoomModel;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Override
    public ArrayList<HotelModel> buscarPorIdHotel(Integer hotelId, Integer qtdAdultos, Integer qtdChd, String checkIn, String checkOut) throws Exception {
        String readLine = null;
        URL endereco = new URL("https://cvcbackendhotel.herokuapp.com/hotels/" + hotelId);

        HttpURLConnection conexao = (HttpURLConnection) endereco.openConnection();
        conexao.setRequestMethod("GET");
        int responseCode = conexao.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuffer response = new StringBuffer();

            while ((readLine = in.readLine()) != null) {

                response.append(readLine);
            }
            in.close();

            ArrayList<HotelModel> hotelList = transformarJsonEmJavaObject(response.toString());
            int quantidadeDias = 0;

            if (!checkIn.equals("") && !checkOut.equals("")) {
                quantidadeDias = calculaQuantidadeDias(checkIn, checkOut);
            }
            if(qtdAdultos == null ) {
                qtdAdultos = 0;
            }
            if(qtdChd == null ) {
                qtdChd = 0;
            }
            inserePrecoTotal(hotelList, qtdAdultos, qtdChd, quantidadeDias);

            return hotelList;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<HotelModel>buscarPorCodigoCidade (Integer cityCode, Integer quantidadeAdultos, Integer quantidadeChd, String checkIn,
                                                       String checkOut) throws Exception {
        String readLine = null;
        URL endereco = new URL("https://cvcbackendhotel.herokuapp.com/hotels/avail/" + cityCode);
        HttpURLConnection conexao = (HttpURLConnection) endereco.openConnection();
        conexao.setRequestMethod("GET");
        int responseCode = conexao.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            StringBuffer response = new StringBuffer();

            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();

            ArrayList<HotelModel> hotelList = transformarJsonEmJavaObject(response.toString());
            int quantidadeDias = calculaQuantidadeDias(checkIn, checkOut);
            inserePrecoTotal(hotelList, quantidadeAdultos, quantidadeChd, quantidadeDias);
            return hotelList;
        } else {
            return null;
        }
    }


    public void inserePrecoTotal(ArrayList<HotelModel> hotelList, int quantidadeAdultos, int quantidadeChd, int quantidadeDias) {

        for (HotelModel h : hotelList) {
            for (RoomModel r : h.rooms) {
                r.setTotalPrice(quantidadeAdultos, quantidadeChd, quantidadeDias);
            }
        }
    }

    public ArrayList<HotelModel> transformarJsonEmJavaObject(String JSONPayload)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<HotelModel> hotelList = new ArrayList<HotelModel>();
        hotelList = mapper.readValue(JSONPayload.toString(), mapper.getTypeFactory().constructCollectionType(List.class, HotelModel.class));

        return hotelList;

    }

    public int calculaQuantidadeDias(String checkIn, String checkOut) {

        try {
            Date checkin = new SimpleDateFormat("dd/MM/yyyy").parse(checkIn);
            Date checkout = new SimpleDateFormat("dd/MM/yyyy").parse(checkOut);
            Calendar ci = Calendar.getInstance();
            Calendar co = Calendar.getInstance();
            ci.setTime(checkin);
            co.setTime(checkout);
            int dias = co.get(Calendar.DAY_OF_YEAR) - ci.get(Calendar.DAY_OF_YEAR);

            if (dias == 0) {
                return 1;
            }
            return dias;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
