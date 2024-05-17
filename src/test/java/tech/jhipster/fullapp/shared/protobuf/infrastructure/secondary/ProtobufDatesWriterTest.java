package tech.jhipster.fullapp.shared.protobuf.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import com.google.protobuf.Timestamp;
import tech.jhipster.fullapp.UnitTest;
import java.time.Instant;
import org.junit.jupiter.api.Test;

@UnitTest
class ProtobufDatesWriterTest {

  @Test
  void shouldBuildNullTimestampFromNullInstant() {
    assertThat(ProtobufDatesWriter.buildTimestamp(null)).isNull();
  }

  @Test
  void shouldBuildTimestampFromInstant() {
    assertThat(ProtobufDatesWriter.buildTimestamp(Instant.ofEpochMilli(1337)))
      .isEqualTo(Timestamp.newBuilder().setSeconds(1).setNanos(337000000).build());
  }
}
