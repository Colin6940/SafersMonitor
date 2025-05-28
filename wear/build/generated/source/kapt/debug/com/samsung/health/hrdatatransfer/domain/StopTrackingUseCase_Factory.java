package com.samsung.health.hrdatatransfer.domain;

import com.samsung.health.hrdatatransfer.data.TrackingRepository;
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
public final class StopTrackingUseCase_Factory implements Factory<StopTrackingUseCase> {
  private final Provider<TrackingRepository> trackingRepositoryProvider;

  public StopTrackingUseCase_Factory(Provider<TrackingRepository> trackingRepositoryProvider) {
    this.trackingRepositoryProvider = trackingRepositoryProvider;
  }

  @Override
  public StopTrackingUseCase get() {
    return newInstance(trackingRepositoryProvider.get());
  }

  public static StopTrackingUseCase_Factory create(
      Provider<TrackingRepository> trackingRepositoryProvider) {
    return new StopTrackingUseCase_Factory(trackingRepositoryProvider);
  }

  public static StopTrackingUseCase newInstance(TrackingRepository trackingRepository) {
    return new StopTrackingUseCase(trackingRepository);
  }
}
