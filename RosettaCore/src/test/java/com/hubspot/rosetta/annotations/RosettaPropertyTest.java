package com.hubspot.rosetta.annotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.rosetta.Rosetta;
import com.hubspot.rosetta.beans.RosettaPropertyBean;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class RosettaPropertyTest {
  private static final String JSON = "{\"mccartney_song_title\":\"Hey Jude\"}";
  private static final String JACKSON_JSON = "{\"mcCartneySongTitle\":\"Hey Jude\"}";

  @Test
  public void itWritesWithThePropertyName() throws JsonProcessingException {
    RosettaPropertyBean bean = new RosettaPropertyBean();
    bean.setMcCartneySongTitle("Hey Jude");
    assertThat(Rosetta.getMapper().writeValueAsString(bean)).isEqualTo(JSON);
    assertThat(new ObjectMapper().writeValueAsString(bean)).isEqualTo(JACKSON_JSON);
  }

  @Test
  public void itReadsThePropertyName() throws IOException {
    assertThat(Rosetta.getMapper().readValue(JSON, RosettaPropertyBean.class).getMcCartneySongTitle()).isEqualTo("Hey Jude");
    assertThat(new ObjectMapper().readValue(JACKSON_JSON, RosettaPropertyBean.class).getMcCartneySongTitle()).isEqualTo("Hey Jude");
  }
}
