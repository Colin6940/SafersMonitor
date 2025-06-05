package com.samsung.health.mobile.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class FirebaseHeartRateRepository_Factory implements Factory<FirebaseHeartRateRepository> {
  @Override
  public FirebaseHeartRateRepository get() {
    return newInstance();
  }

  public static FirebaseHeartRateRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseHeartRateRepository newInstance() {
    return new FirebaseHeartRateRepository();
  }

  private static final class InstanceHolder {
    private static final FirebaseHeartRateRepository_Factory INSTANCE = new FirebaseHeartRateRepository_Factory();
  }
}
