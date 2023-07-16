package io.github.kavishkamk.testapp.eventbus;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class LocalMessageCodec<T> implements MessageCodec<T, T> {

  private final String typeName;

  public LocalMessageCodec(Class<T> type) {
    this.typeName = type.getName();
  }

  @Override
  public void encodeToWire(final Buffer buffer, final T t) {
    throw new UnsupportedOperationException("Only local encode is support");
  }

  @Override
  public T decodeFromWire(int pos, Buffer buffer) {
    throw new UnsupportedOperationException("Only local decode is support");
  }

  @Override
  public T transform(final T obj) {
    return obj;
  }

  @Override
  public String name() {
    return this.typeName;
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }
}
