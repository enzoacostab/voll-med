package med.voll.api.controller;

import med.voll.api.domain.consulta.dto.DatosAgendarConsulta;
import med.voll.api.domain.consulta.dto.DatosRespuestaConsulta;
import med.voll.api.domain.consulta.service.ConsultaService;
import med.voll.api.domain.medico.dto.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester;
    @Autowired
    private JacksonTester<DatosRespuestaConsulta> respuestaConsultaJacksonTester;
    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void agendarConsultaEscenario1() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deberia retornar estado http 200 cuando los datos ingresados sean validos")
    @WithMockUser
    void agendarConsultaEscenario2() throws Exception {
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        DatosRespuestaConsulta datos = new DatosRespuestaConsulta(null, 1L, 1L, fecha);

        when(consultaService.agendar(any())).thenReturn(datos);

        var response = mvc
                .perform(post("/consultas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(agendarConsultaJacksonTester
                            .write(new DatosAgendarConsulta(1L,1L, fecha, especialidad))
                            .getJson()
                    ))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = respuestaConsultaJacksonTester
                .write(datos)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}