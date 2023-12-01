package utn.frc.grutpi60.servicealquileres.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeService {

    @Value("${exchange-service.url}")
    private String exchangeServiceUrl;

    public Double convertirMontoAMoneda(Double monto, String monedaDestino) {
        try {
            // Creación de una instancia de RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // Configuración del encabezado con JSON como tipo de contenido
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Creación del cuerpo de la solicitud
            String requestBody = "{\"moneda_destino\":\"" + monedaDestino.toUpperCase() + "\",\"importe\":" + monto + "}";
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // Realizar la solicitud al servicio de intercambio
            ExchangeResponse response = restTemplate.postForObject(exchangeServiceUrl, entity, ExchangeResponse.class);

            // Devolver el monto convertido
            return response.getImporte();
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }

    // Clase para representar la respuesta del servicio de intercambio
    private static class ExchangeResponse {
        private String moneda;
        private Double importe;

        public String getMoneda() {
            return moneda;
        }

        public void setMoneda(String moneda) {
            this.moneda = moneda;
        }

        public Double getImporte() {
            return importe;
        }

        public void setImporte(Double importe) {
            this.importe = importe;
        }
    }
}
