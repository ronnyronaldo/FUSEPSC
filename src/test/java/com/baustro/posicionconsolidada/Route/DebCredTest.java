package com.baustro.posicionconsolidada.Route;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.baustro.posicionconsolidada.Application;

import org.springframework.beans.factory.annotation.Value;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
//@ContextConfiguration(classes = Application.class)
public class DebCredTest {
   @Autowired
   private TestRestTemplate restTemplate;

   @Value("${pin}")
   private String pin;

   @Value("${Authorization}")
   private String Authorization;

   BodyResult bodyresultBiometrica = new BodyResult();

   @Test
   public void token() throws JSONException {

      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("pin", pin);
      HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.set("Authorization", "Basic " + Authorization);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
      // Call the REST API
      ResponseEntity<BodyResult> response = restTemplate.postForEntity("/api/v1/token", request, BodyResult.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      BodyResult s = response.getBody();
      assertTrue("Error al obtener el tkn_token", s.data.tkn_token.length() > 0);
   }

   @Test
   public void generico_metodos() throws JSONException {

      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("pin", pin);
      HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.set("Authorization", "Basic " + Authorization);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
      // Call the REST API
      ResponseEntity<BodyResult> response = restTemplate.postForEntity("/api/v1/token", request, BodyResult.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      BodyResult s = response.getBody();

      HttpHeaders headers2 = new HttpHeaders();
      // headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers2.set("Authorization", "Bearer " + s.data.tkn_token);
      HttpEntity<String> request2 = new HttpEntity<>(headers2);
      // Call the REST API
      ResponseEntity<BodyResult> response2 = restTemplate.exchange("/api/v1/generico/metodos", HttpMethod.GET, request2,
            BodyResult.class);
      assertEquals(response2.getStatusCode(), HttpStatus.OK);
      BodyResult s2 = response2.getBody();
      assertTrue("Error al obtener llamar a /generico/metodos", s2.mensaje.contains("administrador"));

   }

   @Test
   public void solicitud_biometrico() throws JSONException {

      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("pin", pin);
      HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.set("Authorization", "Basic " + Authorization);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
      // Call the REST API
      ResponseEntity<BodyResult> response = restTemplate.postForEntity("/api/v1/token", request, BodyResult.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      BodyResult s = response.getBody();

      MultiValueMap<String, String> body2 = new LinkedMultiValueMap<>();
      body2.add("identificacion", "0104276092");
      body2.add("callback", "{{callback}}");
      HttpHeaders headers2 = new HttpHeaders();
      // headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers2.set("Authorization", "Bearer " + s.data.tkn_token);
      HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(body2, headers2);
      // Call the REST API
      ResponseEntity<BodyResult> response2 = restTemplate.exchange("/api/v1/solicitud/biometrico", HttpMethod.POST,
            request2, BodyResult.class);
      assertEquals(response2.getStatusCode(), HttpStatus.OK);
      bodyresultBiometrica = response2.getBody();
      assertTrue("Error al obtener llamar a /solicitud/biometrico", bodyresultBiometrica.data.codigo.length() > 0);

   }

   @Test
   public void solicitud_firma() throws JSONException {

      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("pin", pin);
      HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.set("Authorization", "Basic " + Authorization);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
      // Call the REST API
      ResponseEntity<BodyResult> response = restTemplate.postForEntity("/api/v1/token", request, BodyResult.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      BodyResult s = response.getBody();

      MultiValueMap<String, String> body2 = new LinkedMultiValueMap<>();
      body2.add("codigoBiometrico", "1805f3a67ae15e93137999805dba5459");
      //body2.add("codigoBiometrico", bodyresultBiometrica.data.codigo);
      body2.add("codigoCliente", "123");
      body2.add("identificacion", "0104276092");
      body2.add("nombres", "Tony Yan");
      body2.add("apellido_paterno", "Bravo");
      body2.add("apellido_materno", "Smith");
      body2.add("email", "test@hotmail.com");
      body2.add("celular", "0987654321");
      body2.add("domicilio", "Cuenca");
      body2.add("ciudad", "Cuenca");
      StringBuilder documentos = new StringBuilder();
      documentos.append("[{");
      documentos.append("\"nombre\": \"nombre\",");
      documentos.append("\"tipo\": \"ACREDITADA\",");
      documentos.append("\"archivoBase64\": \"ARCHIVO EN BASE 64\"");
      documentos.append("}, {");
      documentos.append("\"nombre\": \"nombre\",");
      documentos.append("\"tipo\": \"SIMPLE\",");
      documentos.append("\"archivoBase64\": \"ARCHIVO EN BASE 64\"");
      documentos.append("}, {");
      documentos.append("\"nombre\": \"nombre\",");
      documentos.append("\"tipo\": \"ACREDITADA\",");
      documentos.append("\"archivoBase64\": \"ARCHIVO EN BASE 64\"");
      documentos.append("}]");
      body2.add("documentos", documentos.toString());
      body2.add("callback", "{{callback}}");
      HttpHeaders headers2 = new HttpHeaders();
      // headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers2.set("Authorization", "Bearer " + s.data.tkn_token);
      HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(body2, headers2);
      // Call the REST API
      ResponseEntity<BodyResult> response2 = restTemplate.exchange("/api/v1/solicitud/firma", HttpMethod.POST, request2,
            BodyResult.class);
      assertEquals(response2.getStatusCode(), HttpStatus.OK);
      BodyResult s2 = response2.getBody();
      assertTrue("Error al obtener llamar a /solicitud/firma", s2.messages.error.contains("biometría"));

   }

   @Test
   public void generico_firmador() throws JSONException {

      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("pin", pin);
      HttpHeaders headers = new HttpHeaders();
      // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.set("Authorization", "Basic " + Authorization);
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
      // Call the REST API
      ResponseEntity<BodyResult> response = restTemplate.postForEntity("/api/v1/token", request, BodyResult.class);
      assertEquals(response.getStatusCode(), HttpStatus.OK);
      BodyResult s = response.getBody();

      MultiValueMap<String, String> body2 = new LinkedMultiValueMap<>();
      body2.add("codigoDocumento", "XXXXX");
      body2.add("archivo64", "abc123");
      body2.add("nombreArchivo", "nombreArchivo");
      body2.add("certificado64", "abc123");
      body2.add("cedulaFirmante", "0104276092");
      body2.add("claveCertificado64", "abc123");
      body2.add("firmaVisible", "1");
      body2.add("coordenadaX", "150");
      body2.add("coordenadaY", "450");
      body2.add("numeroPaginaFirma", "LAST");
      HttpHeaders headers2 = new HttpHeaders();
      // headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers2.set("Authorization", "Bearer " + s.data.tkn_token);
      HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<>(body2, headers2);
      // Call the REST API
      ResponseEntity<BodyResult> response2 = restTemplate.exchange("/api/v1/generico/firmador", HttpMethod.POST, request2,
            BodyResult.class);
      assertEquals(response2.getStatusCode(), HttpStatus.OK);
      BodyResult s2 = response2.getBody();
      assertTrue("Error al obtener llamar a /generico/firmador", s2.messages.error.contains("firmar"));

   }

}