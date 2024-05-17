package tech.jhipster.fullapp.shared.error.infrastructure.primary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.fullapp.IntegrationTest;

@IntegrationTest
@AutoConfigureMockMvc
class FullappErrorsHandlerIT {

  @Autowired
  private MockMvc rest;

  @Test
  void shouldHandleFullappErrorWithoutLocale() throws Exception {
    rest
      .perform(get("/api/errors/bad-request"))
      .andExpect(status().is4xxClientError())
      .andExpect(jsonPath("title").value("Bad request"))
      .andExpect(jsonPath("detail").value("You send a bad request: 400"));
  }

  @Test
  void shouldHandleFullappErrorForFrenchLocale() throws Exception {
    rest
      .perform(get("/api/errors/bad-request").locale(Locale.FRANCE))
      .andExpect(status().is4xxClientError())
      .andExpect(jsonPath("title").value("Bad request"))
      .andExpect(jsonPath("detail").value("Votre requête n'est pas valide: 400"));
  }
}
