package com.samsung.health.hrdatatransfer.data;

import com.google.android.gms.wearable.MessageClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class MessageRepositoryImpl_Factory implements Factory<MessageRepositoryImpl> {
  private final Provider<MessageClient> messageClientProvider;

  public MessageRepositoryImpl_Factory(Provider<MessageClient> messageClientProvider) {
    this.messageClientProvider = messageClientProvider;
  }

  @Override
  public MessageRepositoryImpl get() {
    return newInstance(messageClientProvider.get());
  }

  public static MessageRepositoryImpl_Factory create(
      Provider<MessageClient> messageClientProvider) {
    return new MessageRepositoryImpl_Factory(messageClientProvider);
  }

  public static MessageRepositoryImpl newInstance(MessageClient messageClient) {
    return new MessageRepositoryImpl(messageClient);
  }
}
