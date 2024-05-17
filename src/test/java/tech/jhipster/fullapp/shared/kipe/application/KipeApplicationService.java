package tech.jhipster.fullapp.shared.kipe.application;

import tech.jhipster.fullapp.shared.kipe.domain.KipeDummy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class KipeApplicationService {

  @PreAuthorize("can('update', #dummy)")
  public void update(KipeDummy dummy) {
    // Nothing to do
  }
}
