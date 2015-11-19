package com.hubspot.rosetta.beans;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hubspot.rosetta.beans.CustomBean.CustomBeanDeserializer;
import com.hubspot.rosetta.beans.CustomBean.CustomBeanSerializer;

import java.io.IOException;

@JsonSerialize(using = CustomBeanSerializer.class)
@JsonDeserialize(using = CustomBeanDeserializer.class)
public class CustomBean {
  private long id;

  public long getId() {
    return id;
  }

  public CustomBean setId(long id) {
    this.id = id;
    return this;
  }

  public static class CustomBeanSerializer extends StdSerializer<CustomBean> {

    public CustomBeanSerializer() {
      super(CustomBean.class);
    }

    @Override
    public void serialize(CustomBean custom, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeStartObject();
      jgen.writeNumberField("idValue", custom.getId());
      jgen.writeEndObject();
    }
  }

  public static class CustomBeanDeserializer extends StdDeserializer<CustomBean> {

    public CustomBeanDeserializer() {
      super(CustomBean.class);
    }

    @Override
    public CustomBean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode tree = p.readValueAsTree();

      CustomBean custom = new CustomBean();
      custom.setId(tree.get("idValue").longValue());
      return custom;
    }
  }
}
