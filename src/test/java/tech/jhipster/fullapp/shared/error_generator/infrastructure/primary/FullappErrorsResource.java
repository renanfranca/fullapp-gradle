package tech.jhipster.fullapp.shared.error_generator.infrastructure.primary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.fullapp.shared.error.domain.FullappException;
import tech.jhipster.fullapp.shared.error.domain.StandardErrorKey;

@RestController
@RequestMapping("/api/errors")
class FullappErrorsResource {

  @GetMapping("bad-request")
  void getBadRequest() {
    throw FullappException.badRequest(StandardErrorKey.BAD_REQUEST).addParameter("code", "400").build();
  }
}
